package mod.machina.common.recipe;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.machina.api.gear.Gear;
import mod.machina.common.registrar.RecipeRegistrar;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public record GearRecipe(Gear result, NonNullList<Ingredient> ingredients) implements Recipe<GearRecipe.Input> {

    private static final int MAX_SIZE = 6;

    @Override
    public boolean matches(Input input, Level level) {
        if (input.ingredientCount != this.ingredients.size()) {
            return false;
        } else {
            return input.size() == 1 && this.ingredients.size() == 1
                    ? this.ingredients.getFirst().test(input.getItem(0))
                    : input.contents.canCraft(this, null);
        }
    }

    @Nullable
    @Override
    public ItemStack assemble(Input input, HolderLookup.Provider registries) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.ingredients().size();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients();
    }

    @Nullable
    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistrar.Serializers.GEAR.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistrar.Types.GEAR.get();
    }

    public static final class Input implements RecipeInput {
        private final List<ItemStack> stacks;
        private final StackedContents contents = new StackedContents();
        private final int ingredientCount;

        public Input(List<ItemStack> stacks) {
            this.stacks = stacks;

            int i = 0;
            for (ItemStack stack : stacks) {
                if (!stack.isEmpty()) {
                    i++;
                    this.contents.accountStack(stack, 1);
                }
            }
            this.ingredientCount = i;
        }

        @Override
        public ItemStack getItem(int index) {
            if (index != 0) throw new IllegalArgumentException("No item for index: " + index);
            return stacks().get(index);
        }

        @Override
        public int size() {
            return MAX_SIZE;
        }

        public List<ItemStack> stacks() {
            return stacks;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Input) obj;
            return Objects.equals(this.stacks, that.stacks);
        }

        @Override
        public int hashCode() {
            return Objects.hash(stacks);
        }

        @Override
        public String toString() {
            return "Input[" +
                    "stacks=" + stacks + ']';
        }

    }

    public static class Serializer implements RecipeSerializer<GearRecipe> {

        private static final MapCodec<GearRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Gear.CODEC.fieldOf("result").forGetter(GearRecipe::result),
                NonNullList.codecOf(Ingredient.CODEC_NONEMPTY)
                        .fieldOf("ingredients")
                        .flatXmap(list -> {
                            var size = list.size();
                            if (size == 0) {
                                return DataResult.error(() -> "No ingredients for shapeless recipe");
                            } else {
                                return size > MAX_SIZE
                                        ? DataResult.error(() -> "Too many ingredients for gear recipe. The maximum is: %s".formatted(MAX_SIZE))
                                        : DataResult.success(list);
                            }
                        }, DataResult::success)
                        .forGetter(GearRecipe::ingredients)
        ).apply(instance, GearRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, GearRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Gear.STREAM_CODEC, GearRecipe::result,
                        Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()).map(NonNullList::copyOf, List::copyOf),
                        GearRecipe::ingredients,
                        GearRecipe::new
                );

        @Override
        public MapCodec<GearRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, GearRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
