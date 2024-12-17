package com.mystchonky.machina.data.common;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.registrar.ItemRegistrar;
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
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ItemRegistrar.VOID_HELMET.get())
                .add(ItemRegistrar.VOID_CHESTPLATE.get())
                .add(ItemRegistrar.VOID_LEGGINGS.get())
                .add(ItemRegistrar.VOID_BOOTS.get());
    }
}
