package mod.machina.common.item;

import com.klikli_dev.modonomicon.client.gui.BookGuiManager;
import com.klikli_dev.modonomicon.client.gui.book.BookAddress;
import com.klikli_dev.modonomicon.data.BookDataManager;
import mod.machina.Machina;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class GuideBookItem extends Item {

    public static final String ID = "compendium";
    public static final ResourceLocation BOOK = Machina.prefix(ID);

    public GuideBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        var itemStack = player.getItemInHand(usedHand);
        if (level.isClientSide() && !player.isSecondaryUseActive()) {
            var book = BookDataManager.get().getBook(BOOK);
            BookGuiManager.get().openBook(BookAddress.defaultFor(book));
        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return itemStack.copy();
    }

}
