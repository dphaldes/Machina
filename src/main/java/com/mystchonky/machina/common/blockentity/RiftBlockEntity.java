package com.mystchonky.machina.common.blockentity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.gear.UnlockedGears;
import com.mystchonky.machina.common.network.NetworkedAttachments;
import com.mystchonky.machina.common.recipe.GearRecipe;
import com.mystchonky.machina.common.registrar.BlockEntityRegistrar;
import com.mystchonky.machina.common.registrar.LangRegistrar;
import com.mystchonky.machina.common.registrar.RecipeRegistrar;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RiftBlockEntity extends BlockEntity {

    private BlockPos masterPos;

    @Nullable
    private ResourceLocation recipeId;
    @Nullable
    private RecipeHolder<GearRecipe> recipe;
    private List<ItemStack> consumedStacks = new ArrayList<>();

    public RiftBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistrar.RIFT_PORTAL.get(), pos, blockState);
        this.masterPos = pos;
    }

    public void setMasterPos(BlockPos pos) {
        this.masterPos = pos;
    }

    public BlockPos getMasterPos() {
        return masterPos;
    }

    @Nullable
    public RiftBlockEntity getMaster() {
        if (level.getBlockEntity(masterPos) instanceof RiftBlockEntity rift)
            return rift;

        Machina.LOGGER.warn("Rift master not found for {}", worldPosition);
        return null;
    }

    @Nullable
    public RecipeHolder<GearRecipe> getRecipe() {
        if (recipeId != null && recipe == null) {
            this.recipe = level.getRecipeManager().byKeyTyped(RecipeRegistrar.Types.GEAR.get(), this.recipeId);
        }
        return recipe;
    }

    private void setRecipe(@Nullable ResourceLocation recipeId) {
        this.recipeId = recipeId;
        this.recipe = null;
    }

    public void setRecipeExternal(ResourceLocation recipeId) {
        if (!worldPosition.equals(masterPos)) {
            getMaster().setRecipeExternal(recipeId);
            return;
        }

        refundConsumed();
        setRecipe(recipeId);
        updateSync();
    }

    public void tryConsumeStack(ItemEntity item) {
        if (!(worldPosition.equals(masterPos))) {
            getMaster().tryConsumeStack(item);
            return;
        }

        var stack = item.getItem();
        if (canConsumeStack(stack)) {
            var input = stack.copyWithCount(1);
            consumedStacks.add(input);
            stack.shrink(1);
            updateSync();
        }
    }

    public void tryUnlock(Player player) {
        if (!(worldPosition.equals(masterPos))) {
            getMaster().tryUnlock(player);
            return;
        }
        var recipe = getRecipe();

        if (recipe != null && getRemainingRequired().isEmpty()) {
            var unlockedGears = UnlockedGears.get(player);
            var gear = recipe.value().result();
            if (!unlockedGears.contains(gear)) {
                unlockedGears.add(gear);
                player.sendSystemMessage(Component.translatable(LangRegistrar.GEAR_UNLOCK.key(), Component.translatable(gear.localizationKey())).withStyle(ChatFormatting.GOLD));
                setRecipe(null);
                this.consumedStacks = new ArrayList<>();
                NetworkedAttachments.syncGears(player);
            } else {
                player.sendSystemMessage(Component.translatable(LangRegistrar.GEAR_LEARNT.key(), Component.translatable(gear.localizationKey())).withStyle(ChatFormatting.RED));
                refundConsumed();
            }
        }

        updateSync();
    }


    public boolean canConsumeStack(ItemStack stack) {
        if (getRecipe() == null)
            return false;
        return getRemainingRequired().stream().anyMatch(i -> i.test(stack));
    }

    public void refundConsumed() {
        setRecipe(null);
        for (ItemStack i : consumedStacks) {
            ItemEntity entity = new ItemEntity(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), i);
            level.addFreshEntity(entity);
        }
        consumedStacks = new ArrayList<>();
        updateSync();
    }


    public List<Ingredient> getRemainingRequired() {
        var missing = new ArrayList<Ingredient>();
        var consumed = new ArrayList<>(consumedStacks);
        var recipe = getRecipe();
        if (recipe == null)
            return List.of();

        var ingredients = recipe.value().ingredients();
        if (consumedStacks.isEmpty())
            return ingredients;

        for (var ingredient : ingredients) {
            var found = false;
            for (var stack : consumed) {
                if (ingredient.test(stack)) {
                    consumed.remove(stack);
                    found = true;
                    break;
                }
            }
            if (!found) missing.add(ingredient);
        }

        return missing;
    }

    public void updateSync() {
        if (level != null) {
            BlockState state = level.getBlockState(worldPosition);
            level.sendBlockUpdated(worldPosition, state, state, 3);
            setChanged();
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        var result = Data.CODEC.encodeStart(
                NbtOps.INSTANCE,
                new Data(masterPos, Optional.ofNullable(recipeId), consumedStacks)
        );
        tag.put("data", result.getOrThrow());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        Data.CODEC.decode(NbtOps.INSTANCE, tag.getCompound("data"))
                .result()
                .ifPresent(pair -> {
                    var data = pair.getFirst();
                    this.masterPos = data.master();
                    this.consumedStacks = new ArrayList<>(data.consumedStacks());
                    setRecipe(data.recipe().orElse(null));
                });
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveWithoutMetadata(registries);
    }

    @Override
    public @Nullable ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    private record Data(BlockPos master, Optional<ResourceLocation> recipe, List<ItemStack> consumedStacks) {
        public static Codec<Data> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                BlockPos.CODEC.fieldOf("master").forGetter(Data::master),
                                ResourceLocation.CODEC.optionalFieldOf("recipe").forGetter(Data::recipe),
                                ItemStack.CODEC.listOf().fieldOf("consumedStacks").forGetter(Data::consumedStacks))
                        .apply(instance, Data::new));
    }
}
