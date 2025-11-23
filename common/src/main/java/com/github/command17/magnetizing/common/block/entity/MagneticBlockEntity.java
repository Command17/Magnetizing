package com.github.command17.magnetizing.common.block.entity;

import com.github.command17.magnetizing.common.block.MagneticBlock;
import com.github.command17.magnetizing.common.util.MagneticPole;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class MagneticBlockEntity extends BlockEntity {
    public MagneticBlockEntity(BlockEntityType<? extends MagneticBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public MagneticBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.MAGNETIC_BLOCK.get(), pos, state);
    }

    public void tick(BlockPos pos, BlockState state, Level level) {
        if (this.shouldBeMagnetic()) {
            MagneticPole pole = this.getPole();
            AABB bounds = this.getMagnetBounds();
            double force = pole == MagneticPole.PLUS ? this.getMagnetForce(): -this.getMagnetForce();
            List<Entity> entities = level.getEntities((Entity) null, bounds, this::canAffectEntity);
            for (Entity entity: entities) {
                Vec3 direction = pos.getCenter().subtract(entity.position()).normalize();
                entity.setDeltaMovement(this.getVelocityForEntity(entity, direction, force));
                entity.hurtMarked = true;
            }
        }
    }

    public Vec3 getVelocityForEntity(Entity entity, Vec3 direction, double force) {
        return entity.getDeltaMovement().add(direction.multiply(force, force, force));
    }

    public boolean canAffectEntity(Entity entity) {
        if (entity instanceof Player player) {
            return !player.getAbilities().flying;
        }

        return true;
    }

    public boolean shouldBeMagnetic() {
        BlockState state = this.getBlockState();
        return ((MagneticBlock) state.getBlock()).shouldBeMagnetic(this.getBlockPos(), state, this.level);
    }

    public double getMagnetForce() {
        BlockState state = this.getBlockState();
        return ((MagneticBlock) state.getBlock()).getMagnetForce(this.getBlockPos(), state, this.level);
    }

    public MagneticPole getPole() {
        BlockState state = this.getBlockState();
        return ((MagneticBlock) state.getBlock()).getPole(this.getBlockPos(), state, this.level);
    }

    public AABB getMagnetBounds() {
        BlockState state = this.getBlockState();
        return ((MagneticBlock) state.getBlock()).getMagnetBounds(this.getBlockPos(), state, this.level);
    }
}
