package mod.machina.data;

import mod.machina.Machina;
import mod.machina.common.registrar.ItemRegistrar;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagsProvider extends net.minecraft.data.tags.ItemTagsProvider {


    public ItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                            CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Machina.ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.HEAD_ARMOR)
                .add(ItemRegistrar.VOID_HELMET.asItem());
        tag(ItemTags.CHEST_ARMOR)
                .add(ItemRegistrar.VOID_CHESTPLATE.asItem());
        tag(ItemTags.LEG_ARMOR)
                .add(ItemRegistrar.VOID_LEGGINGS.asItem());
        tag(ItemTags.FOOT_ARMOR)
                .add(ItemRegistrar.VOID_BOOTS.asItem());
    }
}
