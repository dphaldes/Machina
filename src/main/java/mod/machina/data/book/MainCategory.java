package mod.machina.data.book;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryParentModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import mod.machina.Machina;
import mod.machina.common.registrar.BlockRegistrar;
import mod.machina.common.registrar.ItemRegistrar;
import mod.machina.data.book.helper.BookEntry;

public class MainCategory extends CategoryProvider {
    private static final String ID = "main";

    public MainCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
                "-----------r----a------------------",
                "------m----------------------------",
                "-----------c-----------------------"
        };
    }

    @Override
    protected void generateEntries() {
        var machina = new BookEntry("about", this, "Machina", "About this Mod",
                BookIconModel.create(ItemRegistrar.COMPENDIUM),
                (entry) -> {
                    entry.page("about", () -> BookTextPageModel.create()
                                    .withTitle(context().pageTitle()))
                            .withText(context().pageText());
                    entry.pageTitle("Machina");
                    entry.pageText("Mod about Magic and Technology");
                }).generate("m");

        var rift = new BookEntry("rift", this, "Rifts", "Portal to the other place",
                BookIconModel.create(BlockRegistrar.RIFT_PORTAL.blockItem()),
                (entry) -> {
                    entry.page("rift", () -> BookTextPageModel.create()
                            .withTitle(context().pageTitle())
                            .withText(context().pageText())
                    );
                    entry.pageTitle("Rifts");
                    entry.pageText("""
                            Rifts allow you to access the other place. The portal resonates with your armor to grant you boons.
                            \\
                            \\
                            Make a U-Shape portal with Deepslate blocks and ignite it with [](item://minecraft:flint_and_steel).
                            """);

                    entry.page("rift_preview", () -> BookMultiblockPageModel.create()
                            .withMultiblockId(Machina.prefix("preview/rift"))
                            .withText(context().pageText())
                            .withVisualizeButton(false)
                    );
                    entry.pageText("The structure can be as big as 21x21.");
                }).generate("r");
        rift.withParent(BookEntryParentModel.create(machina.getId()).withLineReversed(true));

        var compendium = new BookEntry("the_compendium", this, "The Compendium", "Your strongest weapon",
                BookIconModel.create(ItemRegistrar.COMPENDIUM),
                (entry) -> {
                    entry.page("book_sword", () -> BookTextPageModel.create()
                            .withTitle(context().pageTitle())
                            .withText(context().pageText())
                    );
                    entry.pageTitle("Your strongest weapon");
                    entry.pageText("""
                            The compendium has the ability to transform into a weapon when you shift right click.
                            \\
                            \\
                            The weapon retains any additional buffs you put on it. You can also swap it with a different weapon in a crafting table.
                            """);
                }).generate("c");
        compendium.withParent(BookEntryParentModel.create(machina.getId()).withLineReversed(true));

        var armor = new BookEntry("armor", this, "Armor", "Suit your needs",
                BookIconModel.create(ItemRegistrar.VOID_CHESTPLATE),
                (entry) -> {
                    entry.page("info", () -> BookTextPageModel.create()
                            .withTitle(context().pageTitle())
                            .withText(context().pageText())
                    );
                    entry.pageTitle("Void Armor");
                    entry.pageText("""
                            This armor is highly customizable and you can switch buffs on the fly after you have unlocked them.
                            \\
                            \\
                            Wear the full set and press 'O' to switch gears. To unlock new gears, right click on the rift with The compendium, select the item required, toss them in and walk right through it.
                            """);

                }
        ).generate('a');
        armor.withParent(rift);
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
