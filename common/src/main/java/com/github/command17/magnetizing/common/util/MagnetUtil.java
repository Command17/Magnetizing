package com.github.command17.magnetizing.common.util;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public final class MagnetUtil {
    private MagnetUtil() {}

    public static final int NEGATIVE_COLOR_V3F = 0x4903fc;
    public static final int POSITIVE_COLOR_V3F = DustParticleOptions.REDSTONE_PARTICLE_COLOR;

    public static int getColorBasedOnPole(MagneticPole pole) {
        return pole == MagneticPole.POSITIVE ? POSITIVE_COLOR_V3F : NEGATIVE_COLOR_V3F;
    }

    public static void showBoxParticlesServerSide(ServerLevel level, Vec3 pos, double range, MagneticPole pole) {
        showBoxParticlesServerSide(level, pos, range, getColorBasedOnPole(pole));
    }

    public static void showBoxParticlesServerSide(ServerLevel level, Vec3 pos, double range, int color) {
        double startX = pos.x - range;
        double startY = pos.y - range;
        double startZ = pos.z - range;
        double endX = pos.x + range;
        double endY = pos.y + range;
        double endZ = pos.z + range;
        int particleCount = Mth.ceil(2 / range);
        for (double x = startX; x <= endX; x++) {
            for (double y = startY; y <= endY; y++) {
                for (double z = startZ; z <= endZ; z++) {
                    if (x < endX && z < endZ && y < endY && x > startX && z > startZ && y > startY) {
                        continue;
                    }

                    level.sendParticles(new DustParticleOptions(color, 2), x, y, z, particleCount, 0, 0, 0, 0);
                }
            }
        }
    }
}
