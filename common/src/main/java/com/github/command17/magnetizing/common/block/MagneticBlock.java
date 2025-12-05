package com.github.command17.magnetizing.common.block;

import com.github.command17.magnetizing.common.block.entity.MagneticBlockEntity;
import com.github.command17.magnetizing.common.block.entity.ModBlockEntities;
import com.github.command17.magnetizing.common.util.MagneticPole;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class MagneticBlock extends BaseEntityBlock {
    public static final BooleanProperty DISABLED = BooleanProperty.create("disabled");
    public static final IntegerProperty RANGE10 = IntegerProperty.create("range", 1, 10);
    public static final IntegerProperty RANGE5 = IntegerProperty.create("range", 1, 5);

    public MagneticBlock(Properties properties) {
        super(properties);
    }

    public boolean shouldBeMagnetic(BlockPos pos, BlockState state, LevelAccessor level) {
        return true;
    }

    @NotNull
    public AABB getMagnetBounds(BlockPos pos, BlockState state, LevelAccessor level) {
        return state.getCollisionShape(level, pos).bounds().move(pos).inflate(this.getMagnetRange(pos, state, level));
    }

    public abstract double getMagnetRange(BlockPos pos, BlockState state, LevelAccessor level);
    public abstract double getMagnetForce(BlockPos pos, BlockState state, LevelAccessor level);

    @NotNull
    public abstract MagneticPole getPole(BlockPos pos, BlockState state, LevelAccessor level);

    @NotNull
    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MagneticBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(blockEntityType, ModBlockEntities.MAGNETIC_BLOCK.get(),
                (level1, pos, state1, blockEntity) -> blockEntity.tick(pos, state1, level1));
    }
}
