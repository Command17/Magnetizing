package com.github.command17.magnetizing.common.block;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.common.util.MagneticPole;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class BlockMagnetBlock extends MagneticBlock {
    public static final MapCodec<BlockMagnetBlock> CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                    MagneticPole.CODEC.fieldOf("pole").forGetter(BlockMagnetBlock::getStaticPole),
                    propertiesCodec()
            ).apply(instance, BlockMagnetBlock::new));

    private final MagneticPole pole;

    public BlockMagnetBlock(MagneticPole pole, Properties properties) {
        super(properties);
        this.pole = pole;
        this.registerDefaultState(this.defaultBlockState().setValue(DISABLED, false).setValue(RANGE5, 1));
    }

    @NotNull
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            int range = state.getValue(RANGE5);
            if (!player.isShiftKeyDown()) {
                BlockState newState = state.cycle(RANGE5);
                level.setBlock(pos, newState, 3);
                range = newState.getValue(RANGE5); // Reassign range to not show false values
                level.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 1, 0.75f + range / 10f);
            }

            // Change color based on current magnetic pole
            MagneticPole pole = this.getPole(pos, state, level);
            Vector3f color = pole == MagneticPole.PLUS ? DustParticleOptions.REDSTONE_PARTICLE_COLOR : Vec3.fromRGB24(0x4903fc).toVector3f();

            player.displayClientMessage(Magnetizing.translatable("message.", ".magnetic_range", range).withStyle(ChatFormatting.GRAY), true);

            int startX = pos.getX() - range;
            int startY = pos.getY() - range;
            int startZ = pos.getZ() - range;
            int endX = pos.getX() + range;
            int endY = pos.getY() + range;
            int endZ = pos.getZ() + range;
            for (int x = startX; x <= endX; x++) {
                for (int y = startY; y <= endY; y++) {
                    for (int z = startZ; z <= endZ; z++) {
                        if (x < endX && z < endZ && y < endY && x > startX && z > startZ && y > startY) {
                            continue;
                        }

                        ((ServerLevel) level).sendParticles(new DustParticleOptions(color, 2), x + 0.5, y + 0.5, z + 0.5, 5 / range, 0, 0, 0, 0);
                    }
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DISABLED).add(RANGE5);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if (!level.isClientSide) {
            boolean disabled = state.getValue(DISABLED);
            if (disabled != level.hasNeighborSignal(pos)) {
                level.setBlock(pos, state.cycle(DISABLED), 2);
            }
        }
    }

    @Override
    public boolean shouldBeMagnetic(BlockPos pos, BlockState state, LevelAccessor level) {
        return !state.getValue(DISABLED);
    }

    @Override
    public double getMagnetRange(BlockPos pos, BlockState state, LevelAccessor level) {
        return state.getValue(RANGE5);
    }

    @Override
    public double getMagnetForce(BlockPos pos, BlockState state, LevelAccessor level) {
        return 0.2;
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
