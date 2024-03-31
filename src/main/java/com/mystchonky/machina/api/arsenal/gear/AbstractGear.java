package com.mystchonky.machina.api.arsenal.gear;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.common.item.GearItem;
import com.mystchonky.machina.common.registrar.MachinaRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
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

    public final boolean isCompatibleWith(AbstractGear other) {
        return this.checkCompatibility(other) && other.checkCompatibility(this);
    }

    protected boolean checkCompatibility(AbstractGear other) {
        return this != other;
    }

    public Polarity getPolarity() {
        return Polarity.NONE;
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

    public enum Polarity implements StringRepresentable {
        NONE("none"), DELTA("delta"),  // Δ real
        PHI("phi"),    // Φ imaginary
        THETA("theta"),  // Θ state (known)
        PSI("psi");    // Ψ magic (unknown)

        public static final Codec<Polarity> CODEC = StringRepresentable.fromEnum(Polarity::values);
        private final String name;

        Polarity(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }
    }
}
