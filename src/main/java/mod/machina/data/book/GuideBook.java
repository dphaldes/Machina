package mod.machina.data.book;

import com.klikli_dev.modonomicon.api.datagen.ModonomiconLanguageProvider;
import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookModel;
import mod.machina.Machina;
import mod.machina.common.registrar.ItemRegistrar;
import net.minecraft.resources.ResourceLocation;

public class GuideBook extends SingleBookSubProvider {

    public static final String ID = "compendium";

    public GuideBook(ModonomiconLanguageProvider lang) {
        super(ID, Machina.ID, lang);
    }

    @Override
    protected void registerDefaultMacros() {
    }

    @Override
    protected void generateCategories() {
        add(new MainCategory(this).generate());
    }

    @Override
    protected String bookName() {
        return "Compendium";
    }

    @Override
    protected String bookTooltip() {
        return "Guidebook";
    }

    @Override
    protected BookModel additionalSetup(BookModel book) {
        return book
                .withGenerateBookItem(false)
                .withCustomBookItem(ItemRegistrar.COMPENDIUM.getId())
                .withFont(ResourceLocation.withDefaultNamespace("default"));
    }
}
