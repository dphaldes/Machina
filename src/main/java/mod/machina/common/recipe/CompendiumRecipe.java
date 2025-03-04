package mod.machina.common.recipe;

import mod.machina.common.item.components.ItemStackHolder;
import mod.machina.common.registrar.DataComponentRegistrar;
import mod.machina.common.registrar.ItemRegistrar;
import mod.machina.common.registrar.RecipeRegistrar;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class CompendiumRecipe extends CustomRecipe {

    public CompendiumRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return getBookPair(input) != null;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        BookPair pair = getBookPair(input);
        if (pair == null) {
            return ItemStack.EMPTY;
        }
        var book = pair.book().copy();
        book.set(DataComponentRegistrar.STACK_HOLDER, ItemStackHolder.of(pair.sword().copy()));
        return book;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistrar.Serializers.COMPENDIUM.get();
    }

    @Nullable
    private BookPair getBookPair(CraftingInput input) {
        ItemStack stackA = null;
        ItemStack stackB = null;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty()) {
                if (stackA == null) {
                    stackA = stack;
                } else {
                    if (stackB != null) {
                        return null;
                    }

                    stackB = stack;
                }
            }
        }
        if (stackA != null && stackB != null) {
            if (stackA.is(ItemRegistrar.COMPENDIUM) && stackB.is(ItemTags.SWORDS)) {
                return new BookPair(stackA, stackB);
            } else if (stackB.is(ItemRegistrar.COMPENDIUM) && stackA.is(ItemTags.SWORDS)) {
                return new BookPair(stackB, stackA);
            }
        }

        return null;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> list = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        BookPair pair = getBookPair(input);
        if (pair != null) {
            var book = pair.book();
            if (book.has(DataComponentRegistrar.STACK_HOLDER)) {
                var oldStack = book.get(DataComponentRegistrar.STACK_HOLDER).stack().copy();
                oldStack.remove(DataComponentRegistrar.STACK_HOLDER);
                list.set(0, oldStack);
            }
        }
        return list;
    }

    private record BookPair(ItemStack book, ItemStack sword) {
    }
}
