package com.mystchonky.machina.common.blockentity;

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
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
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

public class RiftBlockEntity extends BlockEntity {

    private BlockPos masterPos;

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
        return recipe;
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


        if (recipe != null && getRemainingRequired().isEmpty()) {
            var unlockedGears = UnlockedGears.get(player);
            var gear = recipe.value().result();
            if (!unlockedGears.contains(gear)) {
                unlockedGears.add(gear);
                player.sendSystemMessage(Component.translatable(LangRegistrar.GEAR_UNLOCK.key(), Component.translatable(gear.localizationKey())).withStyle(ChatFormatting.GOLD));
                recipe = null;
                this.consumedStacks = new ArrayList<>();
                NetworkedAttachments.syncGears(player);
            } else {
                player.sendSystemMessage(Component.translatable(LangRegistrar.GEAR_LEARNT.key(), Component.translatable(gear.localizationKey())).withStyle(ChatFormatting.RED));
                refundConsumed();
            }
        }

        updateSync();
    }


    public void setRecipe(RecipeHolder<GearRecipe> holder, Player player) {
        if (!worldPosition.equals(masterPos)) {
            getMaster().setRecipe(holder, player);
            return;
        }

        refundConsumed();
        recipe = holder;
        player.sendSystemMessage(Component.literal("Crafting: " + holder.value().result().displayName()).withStyle(ChatFormatting.GOLD));
        updateSync();
    }

    public boolean canConsumeStack(ItemStack stack) {
        if (recipe == null)
            return false;
        return getRemainingRequired().stream().anyMatch(i -> i.test(stack));
    }

    public void refundConsumed() {
        recipe = null;
        for (ItemStack i : consumedStacks) {
            ItemEntity entity = new ItemEntity(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), i);
            level.addFreshEntity(entity);
        }
        consumedStacks = new ArrayList<>();
    }


    public List<Ingredient> getRemainingRequired() {
        var missing = new ArrayList<Ingredient>();
        var consumed = new ArrayList<>(consumedStacks);
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
        tag.putLong("master", masterPos.asLong());
        if (recipe != null) {
            tag.putString("recipe", recipe.id().toString());
        }

        ListTag list = new ListTag();
        for (int i = 0; i < consumedStacks.size(); i++) {
            if (!consumedStacks.get(i).isEmpty()) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putInt("Slot", i);
                list.add(consumedStacks.get(i).save(provider, itemTag));
            }
        }
        CompoundTag stacks = new CompoundTag();
        stacks.put("Items", list);
        stacks.putInt("Size", list.size());
        tag.put("consumedStacks", stacks);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.masterPos = BlockPos.of(tag.getLong("master"));
        if (tag.contains("recipe")) {
            var id = ResourceLocation.parse(tag.getString("recipe"));
            this.recipe = level.getRecipeManager().byKeyTyped(RecipeRegistrar.Types.GEAR.get(), id);
        }

        var stacks = tag.getCompound("consumedStacks");
        var consumedStacks = new ArrayList<ItemStack>();
        ListTag tagList = stacks.getList("Items", Tag.TAG_COMPOUND);
        for (int i = 0; i < tagList.size(); i++) {
            CompoundTag itemTags = tagList.getCompound(i);
            int slot = itemTags.getInt("Slot");

            if (slot >= 0 && slot < stacks.size()) {
                ItemStack.parse(provider, itemTags).ifPresent(consumedStacks::add);
            }
        }
        this.consumedStacks = consumedStacks;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveWithoutMetadata(registries);
    }

    @Override
    public @Nullable ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
