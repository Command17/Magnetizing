package com.github.command17.magnetizing.common.util;

import com.github.command17.magnetizing.Magnetizing;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class ModTags {
    private ModTags() {}

    public static ResourceLocation conventionResource(String path) {
        return ResourceLocation.fromNamespaceAndPath("c", path);
    }

    public static final class ItemTags {
        private ItemTags() {}

        public static TagKey<Item> BLOCK_MAGNETS = tag("block_magnets");
        public static TagKey<Item> ITEM_MAGNETS = tag("item_magnets");

        public static final TagKey<Item> INGOTS = conventionTag("ingots");
        public static final TagKey<Item> INGOTS_MAGNETITE = conventionTag("ingots/magnetite");
        public static final TagKey<Item> NUGGETS = conventionTag("nuggets");
        public static final TagKey<Item> NUGGETS_MAGNETITE = conventionTag("nuggets/magnetite");
        public static final TagKey<Item> ORES = conventionTag("ores");
        public static final TagKey<Item> ORES_MAGNETITE = conventionTag("ores/magnetite");
        public static final TagKey<Item> STORAGE_BLOCKS = conventionTag("storage_blocks");
        public static final TagKey<Item> STORAGE_BLOCKS_MAGNETITE = conventionTag("storage_blocks/magnetite");
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_MAGNETITE = conventionTag("storage_blocks/raw_magnetite");
        public static final TagKey<Item> RAW_BLOCKS = conventionTag("raw_blocks");
        public static final TagKey<Item> RAW_BLOCKS_MAGNETITE = conventionTag("raw_blocks/magnetite");
        public static final TagKey<Item> RAW_MATERIALS = conventionTag("raw_materials");
        public static final TagKey<Item> RAW_MATERIALS_MAGNETITE = conventionTag("raw_materials/magnetite");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, Magnetizing.resource(name));
        }

        private static TagKey<Item> conventionTag(String name) {
            return TagKey.create(Registries.ITEM, conventionResource(name));
        }
    }

    public static final class BlockTags {
        private BlockTags() {}

        public static final TagKey<Block> BLOCK_MAGNETS = tag("block_magnets");

        public static final TagKey<Block> NEEDS_WOOD_TOOL = conventionTag("needs_wood_tool");
        public static final TagKey<Block> ORES = conventionTag("ores");
        public static final TagKey<Block> ORES_MAGNETITE = conventionTag("ores/magnetite");
        public static final TagKey<Block> STORAGE_BLOCKS = conventionTag("storage_blocks");
        public static final TagKey<Block> STORAGE_BLOCKS_MAGNETITE = conventionTag("storage_blocks/magnetite");
        public static final TagKey<Block> STORAGE_BLOCKS_RAW_MAGNETITE = conventionTag("storage_blocks/raw_magnetite");
        public static final TagKey<Block> RAW_BLOCKS = conventionTag("raw_blocks");
        public static final TagKey<Block> RAW_BLOCKS_MAGNETITE = conventionTag("raw_blocks/magnetite");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, Magnetizing.resource(name));
        }

        private static TagKey<Block> conventionTag(String name) {
            return TagKey.create(Registries.BLOCK, conventionResource(name));
        }
    }

    public static final class EntityTags {
        private EntityTags() {}

        public static final TagKey<EntityType<?>> UNMOVEABLE_BY_MAGNETS = tag("unmoveable_by_magnets");

        private static TagKey<EntityType<?>> tag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, Magnetizing.resource(name));
        }

        private static TagKey<EntityType<?>> conventionTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, conventionResource(name));
        }
    }
}
