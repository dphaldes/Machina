package com.mystchonky.machina.common.arsenal;

public class GearLibrary {
    public static final String SPEED = prependGear("speed");

    public static String prependGear(String name) {
        return "gear_" + name;
    }
}
