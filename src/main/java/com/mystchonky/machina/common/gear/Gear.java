package com.mystchonky.machina.common.gear;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public abstract class Gear {
    public int getMinLevel() {
        return 1;
    }

    public int getMaxLevel() {
        return 1;
    }

    public final boolean isCompatibleWith(Gear other) {
        return this.checkCompatibility(other) && other.checkCompatibility(this);
    }

    protected boolean checkCompatibility(Gear other) {
        return this != other;
    }

    public Polarity getPolarity() {
        return Polarity.NONE;
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
        public String getSerializedName() {
            return name;
        }
    }
}
