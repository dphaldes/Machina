package com.mystchonky.machina.api.arsenal.gear;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.arsenal.gear.subtypes.GearType;
import com.mystchonky.machina.client.screen.TooltipProvider;
import com.mystchonky.machina.common.item.GearItem;
import com.mystchonky.machina.common.registrar.MachinaRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGear implements TooltipProvider {
    public static final Codec<AbstractGear> CODEC = MachinaRegistries.GEAR_REGISTRY.byNameCodec();
    public static final StreamCodec<RegistryFriendlyByteBuf, AbstractGear> STREAM_CODEC = ByteBufCodecs.registry(MachinaRegistries.GEAR_REGISTRY.key());
    private final String id;
    private final List<GearType> subtypes = new ArrayList<>();
    @Nullable
    private GearItem gearItem;

    protected AbstractGear(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public GearItem getGearItem() {
        if (gearItem == null) {
            this.gearItem = new GearItem(this);
        }
        return gearItem;
    }

    public abstract String getDisplayName();

    protected boolean checkCompatibility(AbstractGear other) {
        return this != other;
    }

    public final boolean isCompatibleWith(AbstractGear other) {
        return this.checkCompatibility(other) && other.checkCompatibility(this);
    }

    public final <T extends GearType> void addSubType(T type) {
        subtypes.add(type);
    }

    public final void onEquip(Player player) {
        if (player.level().isClientSide()) return;

        subtypes.forEach(it -> it.onEquip(player));
    }

    public final void onRemove(Player player) {
        if (player.level().isClientSide()) return;

        subtypes.forEach(it -> it.onRemove(player));
    }

    @Override
    public final void getTooltip(List<Component> tooltip) {
        subtypes.forEach(it -> it.getTooltip(tooltip));
    }

    @Override
    public String toString() {
        return getId();
    }

    public final String getLocalizationKey() {
        return "name." + Machina.MODID + "." + getId();
    }
}
