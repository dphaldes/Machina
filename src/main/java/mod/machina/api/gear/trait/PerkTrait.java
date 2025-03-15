package mod.machina.api.gear.trait;

import mod.machina.api.perk.Perk;
import mod.machina.common.arsenal.PerkLibrary;
import mod.machina.common.registrar.LangRegistrar;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.List;

public record PerkTrait(Perk perk) implements Trait {
    @Override
    public void getTooltip(List<Component> tooltip) {
        tooltip.add(Component.translatable(LangRegistrar.PERK.key(), Component.translatable(PerkLibrary.localizationKey(perk().key())))
                .withStyle(ChatFormatting.GOLD));
    }
}
