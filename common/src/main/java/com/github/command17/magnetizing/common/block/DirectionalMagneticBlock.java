package com.github.command17.magnetizing.common.block;

import com.github.command17.magnetizing.common.block.entity.DirectionalMagneticBlockEntity;
import com.github.command17.magnetizing.common.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public abstract class DirectionalMagneticBlock extends MagneticBlock {
    public DirectionalMagneticBlock(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    public AABB getMagnetBounds(BlockPos pos, BlockState state, LevelAccessor level) {
        Direction direction = this.getMagnetDirection(pos, state, level);
        double range = this.getMagnetRange(pos, state, level);
        return state.getCollisionShape(level, pos).bounds().move(pos)
                .expandTowards(direction.getStepX() * range, direction.getStepY() * range, direction.getStepZ() * range);
    }

    public abstract Direction getMagnetDirection(BlockPos pos, BlockState state, LevelAccessor level);

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new DirectionalMagneticBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NonNull BlockState state, @NonNull BlockEntityType<T> blockEntityType) {
        return level.isClientSide() ? null : createTickerHelper(blockEntityType, ModBlockEntities.DIRECTIONAL_MAGNETIC_BLOCK.get(),
                (level1, pos, state1, blockEntity) -> blockEntity.tick(pos, state1, level1));
    }
}
