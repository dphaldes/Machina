package com.mystchonky.machina.common.armament.gear;

public class GearLibrary {
    public static final String SPEED = name("speed");
    public static final String WATER_BREATH = name("breath");
    public static final String GLIDE = name("glide");
    public static final String HEALTH = name("health");

    public static String name(String id) {
        return "gear_" + id;
    }
}
