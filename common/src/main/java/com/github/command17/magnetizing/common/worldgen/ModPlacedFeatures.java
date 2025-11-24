package com.github.command17.magnetizing.common.worldgen;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.common.util.OrePlacementUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public final class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> MAGNETITE_ORE_LOWER_PLACED = registryKey("magnetite_ore_lower");
    public static final ResourceKey<PlacedFeature> MAGNETITE_ORE_MIDDLE_PLACED = registryKey("magnetite_ore_middle");
    public static final ResourceKey<PlacedFeature> MAGNETITE_ORE_UPPER_PLACED = registryKey("magnetite_ore_upper");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, MAGNETITE_ORE_LOWER_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.MAGNETITE_SMALL_ORE_KEY),
                OrePlacementUtil.commonOrePlacement(
                        7,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(72))
                ));

        register(context, MAGNETITE_ORE_MIDDLE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.MAGNETITE_ORE_KEY),
                OrePlacementUtil.commonOrePlacement(
                        7,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))
                ));

        register(context, MAGNETITE_ORE_UPPER_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.MAGNETITE_ORE_KEY),
                OrePlacementUtil.commonOrePlacement(
                        82,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))
                ));
    }

    private static ResourceKey<PlacedFeature> registryKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Magnetizing.resource(name));
    }

    private static void register(
            BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> configuration,
            List<PlacementModifier> modifiers
    ) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
