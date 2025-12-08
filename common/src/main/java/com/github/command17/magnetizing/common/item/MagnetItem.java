package com.github.command17.magnetizing.common.item;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.common.item.component.ModItemComponents;
import com.github.command17.magnetizing.common.util.MagnetUtil;
import com.github.command17.magnetizing.common.util.MagneticPole;
import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class MagnetItem extends Item {
    public static final double DEFAULT_MAGNET_FORCE = 0.05;
    public static final int DEFAULT_MAX_MAGNET_RANGE = 10;
    public static final int DEFAULT_MAGNET_RANGE = 1;
    public static final MagneticPole DEFAULT_MAGNETIC_POLE = MagneticPole.NEGATIVE;

    public MagnetItem(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Vec3 pos = player.position().add(0, 1, 0);
        boolean showParticles = player.isShiftKeyDown() || hand != InteractionHand.OFF_HAND;
        if (!level.isClientSide) {
            int maxRange = stack.has(ModItemComponents.MAX_MAGNET_RANGE.get()) ? stack.get(ModItemComponents.MAX_MAGNET_RANGE.get()) : DEFAULT_MAX_MAGNET_RANGE;
            int range = stack.has(ModItemComponents.MAGNET_RANGE.get()) ? stack.get(ModItemComponents.MAGNET_RANGE.get()) : DEFAULT_MAGNET_RANGE;
            MagneticPole pole = stack.has(ModItemComponents.MAGNET_POLE.get()) ? stack.get(ModItemComponents.MAGNET_POLE.get()) : DEFAULT_MAGNETIC_POLE;
            if (player.isShiftKeyDown()) {
                range++;
                if (range > maxRange) {
                    range = 1;
                }

                stack.set(ModItemComponents.MAGNET_RANGE.get(), range);
                level.playSound(null, player.getOnPos(), SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 1, 0.75f + range / 10f);
                player.displayClientMessage(Magnetizing.translatable("message.", ".magnetic_range", range).withStyle(ChatFormatting.GRAY), true);
            }

            if (showParticles) {
                MagnetUtil.showBoxParticlesServerSide((ServerLevel) level, pos, range, pole);
            }
        }

        return showParticles ? InteractionResultHolder.success(stack) : super.use(level, player, hand);
    }

    public static boolean magneticEntityPredicate(Entity entity) {
        return entity instanceof ItemEntity;
    }
}
