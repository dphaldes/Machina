package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class TagRegistrar {

    public static final TagKey<Block> RIFT_PORTAL_FRAME = TagKey.create(
            Registries.BLOCK,
            Machina.prefix("rift_portal_frame")
    );


    public static void load() {
    }
}
