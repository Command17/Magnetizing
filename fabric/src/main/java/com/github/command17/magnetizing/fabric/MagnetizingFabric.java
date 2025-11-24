package com.github.command17.magnetizing.fabric;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.fabric.worldgen.ModFabricOreGen;
import net.fabricmc.api.ModInitializer;

public final class MagnetizingFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Magnetizing.init();
        ModFabricOreGen.register();
    }
}
