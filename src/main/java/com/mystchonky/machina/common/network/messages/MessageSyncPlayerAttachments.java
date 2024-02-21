package com.mystchonky.machina.common.network.messages;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.network.Message;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;

public class MessageSyncPlayerAttachments implements Message {

    public static final ResourceLocation ID = Machina.prefix("sync_player_attachments");

    @Nullable
    CompoundTag tag;

    @Nullable
    ServerPlayer player;

    public MessageSyncPlayerAttachments(ServerPlayer player) {
        this.player = player;
    }

    public MessageSyncPlayerAttachments(FriendlyByteBuf buf) {
        this.decode(buf);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        //TODO : STREAM CODEC
//        tag = new CompoundTag();
//        if (this.player != null) {
//            Tag arsenalTag = PlayerActiveArsenal.CODEC.encodeStart(NbtOps.INSTANCE, player.getData(AttachmentRegistrar.PLAYER_ACTIVE_ARSENAL)).result().get();
//            tag.put("arsenalTag", arsenalTag);
//            Tag unlockedGears = PlayerUnlockedGears.CODEC.encodeStart(NbtOps.INSTANCE, player.getData(AttachmentRegistrar.PLAYER_UNLOCKED_GEARS)).result().get();
//            tag.put("unlockedGears", unlockedGears);
//        }
//        buf.writeNbt(tag);
    }

    @Override
    public void decode(FriendlyByteBuf buf) {
        //TODO : STREAM CODEC
//        tag = buf.readNbt();
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
