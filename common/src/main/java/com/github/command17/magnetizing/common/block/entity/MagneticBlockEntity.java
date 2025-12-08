package com.github.command17.magnetizing.common.block.entity;

import com.github.command17.magnetizing.common.block.MagneticBlock;
import com.github.command17.magnetizing.common.item.component.ModItemComponents;
import com.github.command17.magnetizing.common.util.MagneticPole;
import com.github.command17.magnetizing.common.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Iterator;
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
            double force = pole == MagneticPole.POSITIVE ? this.getMagnetForce(): -this.getMagnetForce();
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
        if (entity instanceof LivingEntity livingEntity) {
            Iterator<ItemStack> iterator = livingEntity.getArmorSlots().iterator();
            do {
                ItemStack stack = iterator.next();
                if (stack.has(ModItemComponents.MAGNETIC_RESISTANCE.get())) {
                    // Damage boots
                    if (stack.isDamageableItem() && this.level.getGameTime() % 400 == 0) {
                        EquipmentSlot slot = livingEntity.getEquipmentSlotForItem(stack);
                        stack.hurtAndBreak(1, livingEntity, slot);
                    }

                    return false;
                }
            } while (iterator.hasNext());

            if (livingEntity instanceof Player player) {
                return !player.getAbilities().flying;
            }
        } else if (entity instanceof ItemEntity itemEntity) {
            return !itemEntity.getItem().has(ModItemComponents.MAGNETIC_RESISTANCE.get());
        }

        return !entity.getType().is(ModTags.EntityTags.UNMOVEABLE_BY_MAGNETS);
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
