package com.github.command17.magnetizing.mixin;

import com.github.command17.magnetizing.common.item.MagnetItem;
import com.github.command17.magnetizing.common.item.component.ModItemComponents;
import com.github.command17.magnetizing.common.util.MagneticPole;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract ItemStack getMainHandItem();

    @Shadow
    public abstract ItemStack getOffhandItem();

    @Unique
    private static final AABB BASE_MAGNET_AABB = Shapes.block().bounds();

    @Inject(method = "tick", at = @At("TAIL"))
    private void magnetizing$tick(CallbackInfo ci) {
        ItemStack stack = this.getMainHandItem();
        ItemStack stackOffhand = this.getOffhandItem();
        ItemStack magnetStack = null;
        if (stack.has(ModItemComponents.MAGNET_POLE.get())) {
            magnetStack = stack;
        } else if (stackOffhand.has(ModItemComponents.MAGNET_POLE.get())) {
            magnetStack = stackOffhand;
        }

        if (magnetStack != null) {
            Vec3 pos = this.position().add(0, 1, 0);
            MagneticPole pole = magnetStack.get(ModItemComponents.MAGNET_POLE.get());
            double baseForce = magnetStack.has(ModItemComponents.MAGNET_FORCE.get()) ? magnetStack.get(ModItemComponents.MAGNET_FORCE.get()) : MagnetItem.DEFAULT_MAGNET_FORCE;
            double force = pole == MagneticPole.POSITIVE ? baseForce : -baseForce;
            int range = magnetStack.has(ModItemComponents.MAGNET_RANGE.get()) ? magnetStack.get(ModItemComponents.MAGNET_RANGE.get()) : MagnetItem.DEFAULT_MAGNET_RANGE;
            List<Entity> entities = this.level().getEntities((Entity) null, BASE_MAGNET_AABB.move(pos).inflate(range), MagnetItem::magneticEntityPredicate);
            for (Entity entity: entities) {
                Vec3 direction = pos.subtract(entity.position()).normalize();
                entity.addDeltaMovement(direction.multiply(force, force, force));
                entity.hurtMarked = true;
            }
        }
    }
}
