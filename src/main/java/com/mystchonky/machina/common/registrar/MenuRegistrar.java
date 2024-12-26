package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MenuRegistrar {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, Machina.ID);


    public static void register(IEventBus bus) {
        MENUS.register(bus);
    }
}
