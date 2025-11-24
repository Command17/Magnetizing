package com.github.command17.magnetizing.common.worldgen;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.common.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public final class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGNETITE_ORE_KEY = registryKey("magnetite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGNETITE_SMALL_ORE_KEY = registryKey("magnetite_small_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> magnetiteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.MAGNETITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_MAGNETITE_ORE.get().defaultBlockState())
        );

        register(context, MAGNETITE_ORE_KEY, Feature.ORE, new OreConfiguration(magnetiteOres, 7));
        register(context, MAGNETITE_SMALL_ORE_KEY, Feature.ORE, new OreConfiguration(magnetiteOres, 3));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registryKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Magnetizing.resource(name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstrapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature, FC configuration
    ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
