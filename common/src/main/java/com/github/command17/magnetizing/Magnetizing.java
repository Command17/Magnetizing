package com.github.command17.magnetizing;

import com.github.command17.magnetizing.common.block.ModBlocks;
import com.github.command17.magnetizing.common.block.entity.ModBlockEntities;
import com.github.command17.magnetizing.common.item.ModArmorMaterials;
import com.github.command17.magnetizing.common.item.ModItems;
import com.github.command17.magnetizing.common.item.component.ModItemComponents;
import com.github.command17.magnetizing.common.item.tab.ModCreativeModeTabs;
import com.github.command17.magnetizing.config.ModConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Magnetizing {
    public static final String MOD_ID = "magnetizing";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ModConfig CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    public static void init() {
        LOGGER.info("Initializing...");
        ModBlocks.register();
        ModItemComponents.register();
        ModArmorMaterials.register();
        ModItems.register();
        ModBlockEntities.register();
        ModCreativeModeTabs.register();
        LOGGER.info("Initialized.");
    }

    public static ResourceLocation resource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static MutableComponent translatable(String prefix, String suffix, Object... objects) {
        return Component.translatable(prefix + MOD_ID + suffix, objects);
    }

    static {
        var configPair = new ModConfigSpec.Builder().configure(ModConfig::new);
        CONFIG = configPair.getKey();
        CONFIG_SPEC = configPair.getValue();
    }
}
