package mod.machina.data.book.main;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import mod.machina.common.registrar.ItemRegistrar;

public class CompendiumEntry extends EntryProvider {
    private static final String ID = "the_compendium";

    public CompendiumEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {
        page("book_sword", () -> BookTextPageModel.create()
                .withTitle(context().pageTitle())
                .withText(context().pageText())
        );
        pageTitle("Your strongest weapon");
        pageText("""
                The compendium has the ability to transform into a weapon when you shift right click.
                \\
                \\
                The weapon retains any additional buffs you put on it. You can also swap it with a different weapon in a crafting table.
                """);
    }

    @Override
    protected String entryName() {
        return "The Compendium";
    }

    @Override
    protected String entryDescription() {
        return "Your strongest weapon";
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
