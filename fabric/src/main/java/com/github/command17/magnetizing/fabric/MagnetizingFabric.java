package com.github.command17.magnetizing.fabric;

import com.github.command17.magnetizing.Magnetizing;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.neoforged.fml.config.ModConfig;

public final class MagnetizingFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigRegistry.INSTANCE.register(Magnetizing.MOD_ID, ModConfig.Type.STARTUP, Magnetizing.CONFIG_SPEC, "magnetizing-config.toml");
        Magnetizing.init();
    }
}
