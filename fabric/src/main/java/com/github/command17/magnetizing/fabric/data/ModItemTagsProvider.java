package com.github.command17.magnetizing.fabric.data;

import com.github.command17.magnetizing.common.item.ModItems;
import com.github.command17.magnetizing.common.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    @NullMarked
    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(ItemTags.EQUIPPABLE_ENCHANTABLE)
                .add(ModItems.ANTI_MAGNETIC_BOOTS.get());

        valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.ANTI_MAGNETIC_BOOTS.get());

        valueLookupBuilder(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.ANTI_MAGNETIC_BOOTS.get());

        valueLookupBuilder(ModTags.ItemTags.INGOTS)
                .addOptionalTag(ModTags.ItemTags.INGOTS_MAGNETITE)
                .add(ModItems.BLUE_MAGNETITE_INGOT.get())
                .add(ModItems.RED_MAGNETITE_INGOT.get());

        valueLookupBuilder(ModTags.ItemTags.INGOTS_MAGNETITE)
                .add(ModItems.MAGNETITE_INGOT.get());

        valueLookupBuilder(ModTags.ItemTags.NUGGETS)
                .addOptionalTag(ModTags.ItemTags.NUGGETS_MAGNETITE)
                .add(ModItems.BLUE_MAGNETITE_NUGGET.get())
                .add(ModItems.RED_MAGNETITE_NUGGET.get());

        valueLookupBuilder(ModTags.ItemTags.NUGGETS_MAGNETITE)
                .add(ModItems.MAGNETITE_NUGGET.get());

        valueLookupBuilder(ModTags.ItemTags.RAW_BLOCKS)
                .addOptionalTag(ModTags.ItemTags.RAW_BLOCKS_MAGNETITE);

        valueLookupBuilder(ModTags.ItemTags.RAW_MATERIALS)
                .addOptionalTag(ModTags.ItemTags.RAW_MATERIALS_MAGNETITE)
                .addOptionalTag(ModTags.ItemTags.RAW_BLOCKS_MAGNETITE);

        valueLookupBuilder(ModTags.ItemTags.RAW_BLOCKS_MAGNETITE)
                .add(ModItems.RAW_MAGNETITE_BLOCK.get());

        valueLookupBuilder(ModTags.ItemTags.ORES)
                .addOptionalTag(ModTags.ItemTags.ORES_MAGNETITE);

        valueLookupBuilder(ModTags.ItemTags.ORES_MAGNETITE)
                .add(ModItems.MAGNETITE_ORE.get())
                .add(ModItems.DEEPSLATE_MAGNETITE_ORE.get());

        valueLookupBuilder(ModTags.ItemTags.STORAGE_BLOCKS)
                .addOptionalTag(ModTags.ItemTags.STORAGE_BLOCKS_MAGNETITE)
                .addOptionalTag(ModTags.ItemTags.STORAGE_BLOCKS_RAW_MAGNETITE)
                .add(ModItems.BLUE_MAGNETITE_BLOCK.get())
                .add(ModItems.RED_MAGNETITE_BLOCK.get());

        valueLookupBuilder(ModTags.ItemTags.STORAGE_BLOCKS_MAGNETITE)
                .add(ModItems.MAGNETITE_BLOCK.get());

        valueLookupBuilder(ModTags.ItemTags.STORAGE_BLOCKS_RAW_MAGNETITE)
                .add(ModItems.RAW_MAGNETITE_BLOCK.get());

        valueLookupBuilder(ModTags.ItemTags.ITEM_MAGNETS)
                .add(ModItems.BLUE_ITEM_MAGNET.get())
                .add(ModItems.RED_ITEM_MAGNET.get());

        valueLookupBuilder(ModTags.ItemTags.BLOCK_MAGNETS)
                .add(ModItems.BLUE_BLOCK_MAGNET.get())
                .add(ModItems.RED_BLOCK_MAGNET.get())
                .add(ModItems.BLUE_DIRECTIONAL_BLOCK_MAGNET.get())
                .add(ModItems.RED_DIRECTIONAL_BLOCK_MAGNET.get());
    }
}
