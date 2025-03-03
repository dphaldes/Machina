package mod.machina.data.book;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import mod.machina.common.registrar.ItemRegistrar;
import mod.machina.data.book.main.MachinaEntry;

public class MainCategory extends CategoryProvider {
    public static final String ID = "main";

    public MainCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
                "-----------------------------------",
                "------m----------------------------",
                "-----------------------------------"
        };
    }

    @Override
    protected void generateEntries() {
        new MachinaEntry(this).generate();
    }

    @Override
    protected String categoryName() {
        return "Compendium";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(ItemRegistrar.COMPENDIUM);
    }

    @Override
    public String categoryId() {
        return ID;
    }
}
