package mod.machina.common.content.armor;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AugmentItem extends Item {

    public AugmentItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide())
            return super.use(level, player, hand);
//
//        var unlockedGears = ArsenalManager.getArsenal(player).unlocked();
//        if (!unlockedGears.contains(gear)) {
//            unlockedGears.add(gear);
//            if (!player.isCreative())
//                player.getItemInHand(hand).shrink(1);
//            player.sendSystemMessage(Component.translatable(LangRegistrar.GEAR_UNLOCK.key(), Component.translatable(gear.localizationKey())));
//
//        } else if (player.isCreative() && player.isSecondaryUseActive()) {
//            unlockedGears.remove(gear);
//            player.sendSystemMessage(Component.translatable(LangRegistrar.GEAR_REMOVE.key(), Component.translatable(gear.localizationKey())));
//        }
//
//        NetworkedAttachments.syncGears(player);
        return super.use(level, player, hand);
    }

//    @Override
//    public void appendHoverText(ItemStack stack, TooltipContext tooltipContext, List<Component> components, TooltipFlag flag) {
//        this.gear.getAdditionalTooltip(components);
//    }

}
