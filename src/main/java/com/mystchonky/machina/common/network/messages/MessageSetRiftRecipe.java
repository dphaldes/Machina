package com.mystchonky.machina.common.network.messages;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.blockentity.RiftPortalBlockEntity;
import com.mystchonky.machina.common.registrar.RecipeRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public record MessageSetRiftRecipe(BlockPos blockPos, ResourceLocation recipeId) implements Message.Server {
    public static final Type<MessageSetRiftRecipe> TYPE = new Type<>(Machina.prefix("set_codex_recipe"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageSetRiftRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    MessageSetRiftRecipe::blockPos,
                    ResourceLocation.STREAM_CODEC,
                    MessageSetRiftRecipe::recipeId,
                    MessageSetRiftRecipe::new
            );

    @Override
    public void onServerReceived(ServerPlayer player) {
        var level = player.level();
        if (level.getBlockEntity(blockPos()) instanceof RiftPortalBlockEntity rift) {
            var recipe = level.getRecipeManager().byKeyTyped(RecipeRegistrar.Types.GEAR.get(), recipeId());
            if (recipe != null && rift.getMaster() instanceof RiftPortalBlockEntity master)
                master.setRecipe(recipe, player);
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
