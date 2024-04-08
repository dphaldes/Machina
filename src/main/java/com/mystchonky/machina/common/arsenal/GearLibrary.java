package com.mystchonky.machina.common.arsenal;

public class GearLibrary {
    public static final String SPEED = prependGear("speed");
    public static final String WATER_BREATH = prependGear("breath");
    public static final String GLIDE = prependGear("glide");
    public static final String HEALTH = prependGear("health");

    public static String prependGear(String name) {
        return "gear_" + name;
    }
}
