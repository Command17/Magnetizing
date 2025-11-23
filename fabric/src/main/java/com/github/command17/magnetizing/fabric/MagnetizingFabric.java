package com.github.command17.magnetizing.fabric;

import com.github.command17.magnetizing.Magnetizing;
import net.fabricmc.api.ModInitializer;

public final class MagnetizingFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Magnetizing.init();
    }
}
