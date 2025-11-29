package com.github.command17.magnetizing.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class ModConfig {
    // Block Magnets
    public final ModConfigSpec.ConfigValue<Double> blockMagnetForce;
    public final ModConfigSpec.ConfigValue<Double> directionalBlockMagnetForce;

    // Item Magnets
    public final ModConfigSpec.ConfigValue<Double> itemMagnetForce;
    public final ModConfigSpec.ConfigValue<Integer> itemMagnetMaxRange;

    // Anti-Magnetic Boots
    public final ModConfigSpec.ConfigValue<Integer> antiMagneticBootsDurability;

    public ModConfig(ModConfigSpec.Builder builder) {
        this.blockMagnetForce = builder
                .comment("The force that a block magnet will pull/repel you with.")
                .define(key("blockMagnets", "blockMagnetForce"), 0.2);

        this.directionalBlockMagnetForce = builder
                .comment("A directional block magnet's force that it will pull/repel you with.")
                .define(key("blockMagnets", "directionalBlockMagnetForce"), 0.2);

        this.itemMagnetForce = builder
                .comment("The force that an item magnet will pull/repel an item with.")
                .gameRestart()
                .define(key("itemMagnets", "itemMagnetForce"), 0.1);

        this.itemMagnetMaxRange = builder
                .comment("The maximum magnetic range from an item magnet.")
                .gameRestart()
                .define(key("itemMagnets", "itemMagnetMaxRange"), 5);

        this.antiMagneticBootsDurability = builder
                .comment("Anti-magnetic boots' base durability.")
                .gameRestart()
                .define(key("antiMagneticBoots", "antiMagneticBootsDurability"), 150);
    }

    private static List<String> key(String category, String field) {
        return List.of(category, field);
    }

    private static List<String> key(String category, String subcategory, String field) {
        return List.of(category, subcategory, field);
    }
}
