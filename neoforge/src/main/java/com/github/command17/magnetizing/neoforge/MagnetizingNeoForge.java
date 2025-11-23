package com.github.command17.magnetizing.neoforge;

import com.github.command17.magnetizing.Magnetizing;
import net.neoforged.fml.common.Mod;

@Mod(Magnetizing.MOD_ID)
public final class MagnetizingNeoForge {
    public MagnetizingNeoForge() {
        // Run our common setup.
        Magnetizing.init();
    }
}
