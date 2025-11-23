package com.github.command17.magnetizing.common.block.entity;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.common.block.ModBlocks;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public final class ModBlockEntities {
    private static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(Magnetizing.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<MagneticBlockEntity>> MAGNETIC_BLOCK = register("magnetic_block",
            () -> BlockEntityType.Builder.of(
                        MagneticBlockEntity::new,
                        ModBlocks.BLUE_MAGNETITE_BLOCK.get(),
                        ModBlocks.RED_MAGNETITE_BLOCK.get(),
                        ModBlocks.BLUE_BLOCK_MAGNET.get(),
                        ModBlocks.RED_BLOCK_MAGNET.get()
                    ).build(null));

    public static final RegistrySupplier<BlockEntityType<DirectionalMagneticBlockEntity>> DIRECTIONAL_MAGNETIC_BLOCK = register("directional_magnetic_block",
            () -> BlockEntityType.Builder.of(
                    DirectionalMagneticBlockEntity::new,
                    ModBlocks.BLUE_DIRECTIONAL_BLOCK_MAGNET.get(),
                    ModBlocks.RED_DIRECTIONAL_BLOCK_MAGNET.get()
            ).build(null));

    private static<T extends BlockEntity> RegistrySupplier<BlockEntityType<T>> register(String id, Supplier<BlockEntityType<T>> sup) {
        return REGISTRY.register(id, sup);
    }

    public static void register() {
        REGISTRY.register();
        Magnetizing.LOGGER.info("Registered Block Entities.");
    }
}
