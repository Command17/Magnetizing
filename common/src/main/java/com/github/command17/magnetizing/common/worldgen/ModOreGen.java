package com.github.command17.magnetizing.common.worldgen;

import com.github.command17.magnetizing.Magnetizing;
import dev.architectury.hooks.level.biome.BiomeProperties;
import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.BiConsumer;

public final class ModOreGen {
    public static void register() {
        BiomeModifications.addProperties(inOverworldOre(ModPlacedFeatures.MAGNETITE_ORE_LOWER_PLACED));
        BiomeModifications.addProperties(inOverworldOre(ModPlacedFeatures.MAGNETITE_ORE_MIDDLE_PLACED));
        BiomeModifications.addProperties(inOverworldOre(ModPlacedFeatures.MAGNETITE_ORE_UPPER_PLACED));
        Magnetizing.LOGGER.info("Added Biome Modifications for Ore Generation.");
    }

    private static BiConsumer<BiomeModifications.BiomeContext, BiomeProperties.Mutable> inOverworldOre(ResourceKey<PlacedFeature> feature) {
        return (context, mutable) -> {
            if (context.hasTag(BiomeTags.IS_OVERWORLD)) {
                mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, feature);
            }
        };
    }
}
