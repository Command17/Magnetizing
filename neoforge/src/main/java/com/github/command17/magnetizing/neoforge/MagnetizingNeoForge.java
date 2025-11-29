package com.github.command17.magnetizing.neoforge;

import com.github.command17.magnetizing.Magnetizing;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(Magnetizing.MOD_ID)
public final class MagnetizingNeoForge {
    public MagnetizingNeoForge(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.STARTUP, Magnetizing.CONFIG_SPEC);
        Magnetizing.init();
    }
}
