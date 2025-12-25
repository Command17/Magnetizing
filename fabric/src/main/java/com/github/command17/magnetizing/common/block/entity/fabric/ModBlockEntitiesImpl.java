package com.github.command17.magnetizing.common.block.entity.fabric;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class ModBlockEntitiesImpl {
    private ModBlockEntitiesImpl() {}

    @SuppressWarnings("unchecked")
    public static<T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> sup, Block... validBlocks) {
        return (BlockEntityType<T>) FabricBlockEntityTypeBuilder.create(sup::create, validBlocks).build();
    }
}
