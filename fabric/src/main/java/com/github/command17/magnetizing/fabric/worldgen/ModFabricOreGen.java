package com.github.command17.magnetizing.fabric.worldgen;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.common.worldgen.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public final class ModFabricOreGen {
    public static void register() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.MAGNETITE_ORE_LOWER_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.MAGNETITE_ORE_MIDDLE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.MAGNETITE_ORE_UPPER_PLACED);
        Magnetizing.LOGGER.info("Registered Ore Generation.");
    }
}
