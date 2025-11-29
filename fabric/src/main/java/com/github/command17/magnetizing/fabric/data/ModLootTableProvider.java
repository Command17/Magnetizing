package com.github.command17.magnetizing.fabric.data;

import com.github.command17.magnetizing.common.block.ModBlocks;
import com.github.command17.magnetizing.common.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.MAGNETITE_BLOCK.get());
        dropSelf(ModBlocks.BLUE_MAGNETITE_BLOCK.get());
        dropSelf(ModBlocks.RED_MAGNETITE_BLOCK.get());
        dropSelf(ModBlocks.RAW_MAGNETITE_BLOCK.get());
        dropSelf(ModBlocks.BLUE_BLOCK_MAGNET.get());
        dropSelf(ModBlocks.RED_BLOCK_MAGNET.get());
        dropSelf(ModBlocks.BLUE_DIRECTIONAL_BLOCK_MAGNET.get());
        dropSelf(ModBlocks.RED_DIRECTIONAL_BLOCK_MAGNET.get());
        addOreDrop(ModBlocks.MAGNETITE_ORE.get(), ModItems.RAW_MAGNETITE.get());
        addOreDrop(ModBlocks.DEEPSLATE_MAGNETITE_ORE.get(), ModItems.RAW_MAGNETITE.get());
    }

    private void addOreDrop(Block block, Item drop) {
        add(block, createOreDrop(block, drop));
    }
}
