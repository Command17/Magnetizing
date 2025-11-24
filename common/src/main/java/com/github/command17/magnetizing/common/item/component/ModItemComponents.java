package com.github.command17.magnetizing.common.item.component;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.common.util.MagneticPole;
import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Supplier;

public final class ModItemComponents {
    private static final DeferredRegister<DataComponentType<?>> REGISTRY = DeferredRegister.create(Magnetizing.MOD_ID, Registries.DATA_COMPONENT_TYPE);

    public static final RegistrySupplier<DataComponentType<Integer>> MAX_MAGNET_RANGE = register("max_magnet_range",
            () -> DataComponentType.<Integer>builder().persistent(ExtraCodecs.intRange(0, 99)).build());

    public static final RegistrySupplier<DataComponentType<Integer>> MAGNET_RANGE = register("magnet_range",
            () -> DataComponentType.<Integer>builder().persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT).build());

    public static final RegistrySupplier<DataComponentType<Double>> MAGNET_FORCE = register("magnet_force",
            () -> DataComponentType.<Double>builder().persistent(Codec.DOUBLE).build());

    public static final RegistrySupplier<DataComponentType<MagneticPole>> MAGNET_POLE = register("magnet_pole",
            () -> DataComponentType.<MagneticPole>builder().persistent(MagneticPole.CODEC).networkSynchronized(MagneticPole.STREAM_CODEC).build());

    private static<T> RegistrySupplier<DataComponentType<T>> register(String id, Supplier<DataComponentType<T>> sup) {
        return REGISTRY.register(id, sup);
    }

    public static void register() {
        REGISTRY.register();
        Magnetizing.LOGGER.info("Registered Item Components.");
    }
}
