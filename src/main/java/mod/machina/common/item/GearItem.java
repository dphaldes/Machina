package mod.machina.common.item;

import mod.machina.api.gear.Gear;
import mod.machina.common.arsenal.ArsenalManager;
import mod.machina.common.network.NetworkedAttachments;
import mod.machina.common.registrar.LangRegistrar;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class GearItem extends Item {
    private final Gear gear;

    public GearItem(Gear gear) {
        super(new Properties().stacksTo(1));
        this.gear = gear;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide())
            return super.use(level, player, hand);

        var unlockedGears = ArsenalManager.getUnlockedGears(player);
        if (!unlockedGears.contains(gear)) {
            unlockedGears.add(gear);
            if (!player.isCreative())
                player.getItemInHand(hand).shrink(1);
            player.sendSystemMessage(Component.translatable(LangRegistrar.GEAR_UNLOCK.key(), Component.translatable(gear.localizationKey())));

        } else if (player.isCreative() && player.isSecondaryUseActive()) {
            unlockedGears.remove(gear);
            player.sendSystemMessage(Component.translatable(LangRegistrar.GEAR_REMOVE.key(), Component.translatable(gear.localizationKey())));
        }

        NetworkedAttachments.syncGears(player);
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext tooltipContext, List<Component> components, TooltipFlag flag) {
        this.gear.getAdditionalTooltip(components);
    }

    public Gear gear() {
        return gear;
    }
}
