package mod.machina.common.item;

import mod.machina.client.ClientData;
import mod.machina.common.registrar.DataComponentRegistrar;
import mod.machina.common.registrar.LangRegistrar;
import mod.machina.common.registrar.RuneRegistrar;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.Nullable;
import java.util.List;

public class CompendiumItem extends SwordItem {

    public CompendiumItem() {
        super(Tiers.IRON, new Properties()
                .stacksTo(1)
                .rarity(Rarity.RARE)
                .attributes(createAttributes(Tiers.IRON, 3, -2.4F))
        );
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (player.level().isClientSide()) {
            stack.set(DataComponentRegistrar.COMPENDIUM_LAST_USED, ClientData.ticks);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    public static float getVisualState(ItemStack stack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int seed) {
        var lastUsed = stack.getOrDefault(DataComponentRegistrar.COMPENDIUM_LAST_USED, 0);
        if (ClientData.ticks - lastUsed < 100) {
            return 1f;
        }

        return 0f;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        var rune = stack.getOrDefault(DataComponentRegistrar.RUNE, RuneRegistrar.PHI.get());
        tooltipComponents.add(Component.translatable(LangRegistrar.HELD_RUNE.key(), rune.id().toLanguageKey()));
    }
}
