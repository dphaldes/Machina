package com.mystchonky.machina.api.arsenal.gear;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.common.item.GearItem;
import com.mystchonky.machina.common.registrar.MachinaRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class AbstractGear {
    public static final Codec<AbstractGear> CODEC = MachinaRegistries.GEAR_REGISTRY.byNameCodec();
    public static final StreamCodec<RegistryFriendlyByteBuf, AbstractGear> STREAM_CODEC = ByteBufCodecs.registry(MachinaRegistries.GEAR_REGISTRY.key());
    @Nullable
    private GearItem gearItem;
    private final String id;

    protected AbstractGear(String id) {
        this.id = id;
    }

    public abstract String getDisplayName();

    public final boolean isCompatibleWith(AbstractGear other) {
        return this.checkCompatibility(other) && other.checkCompatibility(this);
    }

    protected boolean checkCompatibility(AbstractGear other) {
        return this != other;
    }

    public GearItem getGearItem() {
        if (gearItem == null) {
            this.gearItem = new GearItem(this);
        }
        return gearItem;
    }

    @NotNull
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return getId();
    }
}
