package com.github.command17.magnetizing;

import com.github.command17.magnetizing.common.block.ModBlocks;
import com.github.command17.magnetizing.common.block.entity.ModBlockEntities;
import com.github.command17.magnetizing.common.item.ModItems;
import com.github.command17.magnetizing.common.item.component.ModItemComponents;
import com.github.command17.magnetizing.common.item.tab.ModCreativeModeTabs;
import com.github.command17.magnetizing.common.worldgen.ModOreGen;
import com.github.command17.magnetizing.config.ModConfig;
import dev.architectury.platform.Platform;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
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
        ModItems.register();
        ModBlockEntities.register();
        ModCreativeModeTabs.register();

        // For some reason it doesn't work on NeoForge???
        if (!Platform.isNeoForge()) {
            ModOreGen.register();
        }

        LOGGER.info("Initialized.");
    }

    public static Identifier resource(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
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
