package com.github.command17.magnetizing.common.block;

import com.github.command17.magnetizing.common.util.MagneticPole;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class MagneticMagnetiteBlock extends MagneticBlock {
    public static final MapCodec<MagneticMagnetiteBlock> CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                    MagneticPole.CODEC.fieldOf("pole").forGetter(MagneticMagnetiteBlock::getStaticPole),
                    propertiesCodec()
            ).apply(instance, MagneticMagnetiteBlock::new));

    private final MagneticPole pole;

    public MagneticMagnetiteBlock(MagneticPole pole, Properties properties) {
        super(properties);
        this.pole = pole;
    }

    @Override
    public double getMagnetRange(BlockPos pos, BlockState state, LevelAccessor level) {
        return 0.5;
    }

    @Override
    public double getMagnetForce(BlockPos pos, BlockState state, LevelAccessor level) {
        return 0.05;
    }

    @NotNull
    public MagneticPole getStaticPole() {
        return this.pole;
    }

    @NotNull
    @Override
    public MagneticPole getPole(BlockPos pos, BlockState state, LevelAccessor level) {
        return this.getStaticPole();
    }

    @NotNull
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
