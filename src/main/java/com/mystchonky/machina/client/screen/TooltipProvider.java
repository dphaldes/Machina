package com.mystchonky.machina.client.screen;

import net.minecraft.network.chat.Component;

import java.util.List;

public interface TooltipProvider {
    void getTooltip(List<Component> tooltip);
}
