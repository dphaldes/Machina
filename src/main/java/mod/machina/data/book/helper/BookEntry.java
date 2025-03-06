package mod.machina.data.book.helper;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookPageModel;
import com.mojang.datafixers.util.Pair;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BookEntry extends EntryProvider {

    private final BookIconModel icon;
    private final String id;
    private final String name;
    private final String description;
    private final Consumer<BookEntry> consumer;

    public BookEntry(String id, CategoryProviderBase parent, String name, String description, BookIconModel icon, Consumer<BookEntry> consumer) {
        super(parent);
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.description = description;
        this.consumer = consumer;
    }

    @Override
    protected void generatePages() {
        consumer.accept(this);
    }

    @Override
    protected String entryName() {
        return name;
    }

    @Override
    protected String entryDescription() {
        return description;
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return icon;
    }

    @Override
    protected String entryId() {
        return id;
    }

    @Override
    public <T extends BookPageModel<?>> T page(T model) {
        return super.page(model);
    }

    @Override
    public <T extends BookPageModel<?>> T page(String page, Supplier<T> modelSupplier) {
        return super.page(page, modelSupplier);
    }

    @Override
    public void pageText(String text) {
        super.pageText(text);
    }

    @Override
    public void pageText(String text, Object... args) {
        super.pageText(text, args);
    }

    @Override
    public void pageTitle(String title) {
        super.pageTitle(title);
    }


}
