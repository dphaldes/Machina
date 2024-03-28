package com.mystchonky.machina.common.network.messages;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.network.Message;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;

public class MessageSyncPlayerAttachments implements Message {

    public static final CustomPacketPayload.Type<MessageSyncPlayerAttachments> TYPE = new Type<>(Machina.prefix("sync_player_attachments"));
    public static final StreamCodec<FriendlyByteBuf, MessageSyncPlayerAttachments> STREAM_CODEC = CustomPacketPayload.codec(MessageSyncPlayerAttachments::encode, MessageSyncPlayerAttachments::new);

    @Nullable
    CompoundTag tag;

    @Nullable
    ServerPlayer player;

    public MessageSyncPlayerAttachments(ServerPlayer player) {
        this.player = player;
    }

    public MessageSyncPlayerAttachments(FriendlyByteBuf buf) {
    }

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
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
