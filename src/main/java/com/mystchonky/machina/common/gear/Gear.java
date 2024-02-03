package com.mystchonky.machina.common.gear;

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

    public enum POLARITY {
        NONE,
    }
}
