package com.github.command17.magnetizing.common.block;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.common.util.MagnetUtil;
import com.github.command17.magnetizing.common.util.MagneticPole;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class DirectionalBlockMagnetBlock extends DirectionalMagneticBlock {
    public static final MapCodec<DirectionalBlockMagnetBlock> CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                    MagneticPole.CODEC.fieldOf("pole").forGetter(DirectionalBlockMagnetBlock::getStaticPole),
                    propertiesCodec()
            ).apply(instance, DirectionalBlockMagnetBlock::new));

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    private final MagneticPole pole;

    public DirectionalBlockMagnetBlock(MagneticPole pole, Properties properties) {
        super(properties);
        this.pole = pole;
        this.registerDefaultState(
                this.defaultBlockState()
                    .setValue(FACING, Direction.NORTH)
                    .setValue(RANGE10, 1)
                    .setValue(DISABLED, false)
        );
    }

    @NotNull
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            int range = state.getValue(RANGE10);
            if (!player.isShiftKeyDown()) {
                BlockState newState = state.cycle(RANGE10);
                level.setBlock(pos, newState, 3);
                range = newState.getValue(RANGE10); // Reassign range to not show false values
                level.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 1, 0.75f + range / 10f);
            }

            // Location of the last block affected by the magnet
            Direction facing = state.getValue(FACING);
            Vec3 endPos = new Vec3(facing.getStepX() * range, facing.getStepY() * range, facing.getStepZ() * range).add(pos.getCenter());

            // Change color based on current magnetic pole
            MagneticPole pole = this.getPole(pos, state, level);
            Vector3f color = MagnetUtil.getColorBasedOnPole(pole);

            RandomSource random = level.getRandom();

            player.displayClientMessage(Magnetizing.translatable("message.", ".magnetic_range", range).withStyle(ChatFormatting.GRAY), true);
            ((ServerLevel) level).sendParticles(new DustParticleOptions(color, 2), endPos.x, endPos.y, endPos.z, random.nextIntBetweenInclusive(5, 7), 0, 0, 0, 0);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(RANGE10).add(DISABLED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
    }

    @NotNull
    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @NotNull
    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
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
    public Direction getMagnetDirection(BlockPos pos, BlockState state, LevelAccessor level) {
        return state.getValue(FACING);
    }

    @Override
    public double getMagnetRange(BlockPos pos, BlockState state, LevelAccessor level) {
        return state.getValue(RANGE10);
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
