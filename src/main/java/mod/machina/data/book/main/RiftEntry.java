package mod.machina.data.book.main;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import mod.machina.Machina;
import mod.machina.common.registrar.BlockRegistrar;

public class RiftEntry extends EntryProvider {
    private static final String ID = "rift";

    public RiftEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {
        page("rift", () -> BookTextPageModel.create()
                .withTitle(context().pageTitle())
                .withText(context().pageText())
        );
        pageTitle("Rifts");
        pageText("""
                Rifts allow you to access the other place. The portal resonates with your armor to grant you boons.
                \\
                \\
                Make a U-Shape portal with Deepslate blocks and ignite it with [](item://minecraft:flint_and_steel).
                """);

        page("rift_preview", () -> BookMultiblockPageModel.create()
                .withMultiblockId(Machina.prefix("preview/rift"))
                .withText(context().pageText())
                .withVisualizeButton(false)
        );
        pageText("The structure can be as big as 21x21.");
    }

    @Override
    protected String entryName() {
        return "Rifts";
    }

    @Override
    protected String entryDescription() {
        return "Portal to the other place";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(BlockRegistrar.RIFT_PORTAL.blockItem());
    }

    @Override
    protected String entryId() {
        return ID;
    }
}
