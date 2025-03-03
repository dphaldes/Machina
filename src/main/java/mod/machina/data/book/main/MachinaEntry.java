package mod.machina.data.book.main;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import mod.machina.common.registrar.ItemRegistrar;

public class MachinaEntry extends EntryProvider {
    public static final String ID = "about";

    public MachinaEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {
        page("about", () -> BookTextPageModel.create()
                .withTitle(context().pageTitle()))
                .withText(context().pageText());
        pageTitle("Machina");
        pageText("AAAAAAA");

    }

    @Override
    protected String entryName() {
        return "Machina";
    }

    @Override
    protected String entryDescription() {
        return "About this Mod";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(ItemRegistrar.COMPENDIUM);
    }

    @Override
    protected String entryId() {
        return ID;
    }
}
