package com.github.command17.magnetizing.fabric.data;

import com.github.command17.magnetizing.common.block.ModBlocks;
import com.github.command17.magnetizing.common.util.ModTags;
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
                .add(ModBlocks.RED_MAGNETITE_BLOCK.get())
                .add(ModBlocks.RAW_MAGNETITE_BLOCK.get())
                .addOptionalTag(ModTags.BlockTags.BLOCK_MAGNETS);

        getOrCreateTagBuilder(ModTags.BlockTags.NEEDS_WOOD_TOOL)
                .addOptionalTag(ModTags.BlockTags.BLOCK_MAGNETS);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.MAGNETITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_MAGNETITE_ORE.get())
                .add(ModBlocks.MAGNETITE_BLOCK.get())
                .add(ModBlocks.BLUE_MAGNETITE_BLOCK.get())
                .add(ModBlocks.RED_MAGNETITE_BLOCK.get())
                .add(ModBlocks.RAW_MAGNETITE_BLOCK.get());

        getOrCreateTagBuilder(ModTags.BlockTags.BLOCK_MAGNETS)
                .add(ModBlocks.BLUE_BLOCK_MAGNET.get())
                .add(ModBlocks.BLUE_DIRECTIONAL_BLOCK_MAGNET.get())
                .add(ModBlocks.RED_BLOCK_MAGNET.get())
                .add(ModBlocks.RED_DIRECTIONAL_BLOCK_MAGNET.get());

        getOrCreateTagBuilder(ModTags.BlockTags.ORES)
                .addOptionalTag(ModTags.BlockTags.ORES_MAGNETITE);

        getOrCreateTagBuilder(ModTags.BlockTags.ORES_MAGNETITE)
                .add(ModBlocks.MAGNETITE_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_MAGNETITE_ORE.get());

        getOrCreateTagBuilder(ModTags.BlockTags.STORAGE_BLOCKS)
                .addOptionalTag(ModTags.BlockTags.STORAGE_BLOCKS_MAGNETITE)
                .addOptionalTag(ModTags.BlockTags.STORAGE_BLOCKS_RAW_MAGNETITE)
                .add(ModBlocks.BLUE_MAGNETITE_BLOCK.get())
                .add(ModBlocks.RED_MAGNETITE_BLOCK.get());

        getOrCreateTagBuilder(ModTags.BlockTags.STORAGE_BLOCKS_MAGNETITE)
                .add(ModBlocks.MAGNETITE_BLOCK.get());

        getOrCreateTagBuilder(ModTags.BlockTags.STORAGE_BLOCKS_RAW_MAGNETITE)
                .add(ModBlocks.RAW_MAGNETITE_BLOCK.get());

        getOrCreateTagBuilder(ModTags.BlockTags.RAW_BLOCKS)
                .addOptionalTag(ModTags.BlockTags.RAW_BLOCKS_MAGNETITE);

        getOrCreateTagBuilder(ModTags.BlockTags.RAW_BLOCKS_MAGNETITE)
                .add(ModBlocks.RAW_MAGNETITE_BLOCK.get());
    }
}
