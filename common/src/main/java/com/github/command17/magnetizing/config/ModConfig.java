package com.github.command17.magnetizing.config;

import net.neoforged.neoforge.common.ModConfigSpec;

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
                .define("blockMagnets.blockMagnetForce", 0.2);

        this.directionalBlockMagnetForce = builder
                .comment("A directional block magnet's force that it will pull/repel you with.")
                .define("blockMagnets.directionalBlockMagnetForce", 0.2);

        this.itemMagnetForce = builder
                .comment("The force that an item magnet will pull/repel an item with.")
                .gameRestart()
                .define("itemMagnets.itemMagnetForce", 0.1);

        this.itemMagnetMaxRange = builder
                .comment("The maximum magnetic range from an item magnet.")
                .gameRestart()
                .define("itemMagnets.itemMagnetMaxRange", 5);

        this.antiMagneticBootsDurability = builder
                .comment("Anti-magnetic boots' base durability.")
                .gameRestart()
                .define("antiMagneticBoots.antiMagneticBootsDurability", 150);
    }
}
