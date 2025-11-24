package com.github.command17.magnetizing.fabric.data;

import com.github.command17.magnetizing.common.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MAGNETITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_MAGNETITE_ORE.get())
                .add(ModBlocks.MAGNETITE_BLOCK.get())
                .add(ModBlocks.BLUE_MAGNETITE_BLOCK.get())
                .add(ModBlocks.RED_MAGNETITE_BLOCK.get());

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.MAGNETITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_MAGNETITE_ORE.get())
                .add(ModBlocks.MAGNETITE_BLOCK.get())
                .add(ModBlocks.BLUE_MAGNETITE_BLOCK.get())
                .add(ModBlocks.RED_MAGNETITE_BLOCK.get());
    }
}
