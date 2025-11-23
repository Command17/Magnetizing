package com.github.command17.magnetizing.common.block.entity;

import com.github.command17.magnetizing.common.block.DirectionalMagneticBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class DirectionalMagneticBlockEntity extends MagneticBlockEntity {
    public DirectionalMagneticBlockEntity(BlockEntityType<? extends MagneticBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public DirectionalMagneticBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DIRECTIONAL_MAGNETIC_BLOCK.get(), pos, state);
    }

    @Override
    public Vec3 getVelocityForEntity(Entity entity, Vec3 dirFromBlock, double force) {
        Direction direction = this.getMagnetDirection();
        return entity.getDeltaMovement().add(direction.getStepX() * -force, direction.getStepY() * -force, direction.getStepZ() * -force);
    }

    public Direction getMagnetDirection() {
        BlockState state = this.getBlockState();
        return ((DirectionalMagneticBlock) state.getBlock()).getMagnetDirection(this.getBlockPos(), state, this.level);
    }
}
