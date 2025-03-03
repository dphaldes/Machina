package mod.machina.data.book;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryParentModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import mod.machina.common.registrar.ItemRegistrar;
import mod.machina.data.book.main.MachinaEntry;
import mod.machina.data.book.main.RiftEntry;

public class MainCategory extends CategoryProvider {
    public static final String ID = "main";

    public MainCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
                "-----------r-----------------------",
                "------m----------------------------",
                "-----------------------------------"
        };
    }

    @Override
    protected void generateEntries() {
        var machina = new MachinaEntry(this).generate("m");
        var rift = new RiftEntry(this).generate("r");
        rift.withParent(BookEntryParentModel.create(machina.getId()).withLineReversed(true));
    }

    @Override
    protected String categoryName() {
        return "The Compendium";
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
