package mod.machina.data.client;

import mod.machina.Machina;
import mod.machina.common.registrar.BlockRegistrar;
import mod.machina.common.registrar.ItemRegistrar;
import mod.machina.common.registrar.LangRegistrar;
import net.minecraft.data.PackOutput;

public class LanguageProvider extends net.neoforged.neoforge.common.data.LanguageProvider {
    public LanguageProvider(PackOutput output, String locale) {
        super(output, Machina.ID, locale);
    }

    @Override
    protected void addTranslations() {
        add(ItemRegistrar.VOID_HELMET.get(), "Voidwalker Hood");
        add(ItemRegistrar.VOID_CHESTPLATE.get(), "Voidwalker Mantle");
        add(ItemRegistrar.VOID_LEGGINGS.get(), "Voidwalker Breeches");
        add(ItemRegistrar.VOID_BOOTS.get(), "Voidwalker Greaves");
        add(ItemRegistrar.GUIDE.get(), "The Index");

        add(BlockRegistrar.RUNE_PROJECTOR.block(), "Runic Projector");
        add(BlockRegistrar.RUNESMITH.block(), "Runesmith");


//        PerkRegistrar.entries().forEach(this::add);
        LangRegistrar.entries().forEach(this::add);
    }
}
