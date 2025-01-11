package com.mystchonky.machina.common.blockentity;

import com.mystchonky.machina.common.recipe.GearRecipe;
import com.mystchonky.machina.common.registrar.BlockEntityRegistrar;
import com.mystchonky.machina.common.registrar.RecipeRegistrar;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
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

public class RiftPortalBlockEntity extends BlockEntity {

    @Nullable
    private BlockPos masterPos = null;

    @Nullable
    private RecipeHolder<GearRecipe> recipe;
    private List<ItemStack> consumedStacks = new ArrayList<>();
    public boolean crafting;

    public RiftPortalBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistrar.RIFT_PORTAL.get(), pos, blockState);
    }

    public void setMasterPos(BlockPos pos) {
        this.masterPos = pos;
    }

    @Nullable
    public BlockPos getMasterPos() {
        return masterPos;
    }

    @Nullable
    public RiftPortalBlockEntity getMaster() {
        if (masterPos == null)
            return null;

        if (level.getBlockEntity(masterPos) instanceof RiftPortalBlockEntity rift)
            return rift;

        return null;
    }

    public void refundConsumed() {
        for (ItemStack i : consumedStacks) {
            ItemEntity entity = new ItemEntity(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), i);
            level.addFreshEntity(entity);
        }
        consumedStacks = new ArrayList<>();

        recipe = null;
        crafting = false;
        setChanged();
    }

    public void setRecipe(RecipeHolder<GearRecipe> holder, Player player) {
        refundConsumed();
        recipe = holder;
        player.sendSystemMessage(Component.literal("Crafting: " + holder.value().result().displayName()).withStyle(ChatFormatting.GOLD));
        setChanged();
    }

    public boolean canConsumeStack(ItemStack stack) {
        if (recipe == null)
            return false;
        return getRemainingRequired().stream().anyMatch(i -> i.test(stack));
    }

    public List<Ingredient> getRemainingRequired() {
        var ingredients = recipe.value().ingredients();
        if (consumedStacks.isEmpty())
            return ingredients;

        var missing = new ArrayList<Ingredient>();
        var consumed = new ArrayList<>(consumedStacks);

        for (var ingredient : ingredients) {
            for (var stack : consumed) {
                if (ingredient.test(stack)) {
                    missing.add(ingredient);
                    consumed.remove(stack);
                    break;
                }
            }
        }

        return missing;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (masterPos != null) {
            tag.putLong("master", masterPos.asLong());
        }
        if (recipe != null) {
            tag.putString("recipe", recipe.id().toString());
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("master")) {
            this.masterPos = BlockPos.of(tag.getLong("master"));
        }
        if (tag.contains("recipe")) {
            var id = ResourceLocation.parse(tag.getString("recipe"));
            this.recipe = level.getRecipeManager().byKeyTyped(RecipeRegistrar.Types.GEAR.get(), id);

        }
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        var tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
