package com.github.command17.magnetizing.common.block.entity.neoforge;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Set;

public final class ModBlockEntitiesImpl {
    private ModBlockEntitiesImpl() {}

    public static<T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> sup, Block... validBlocks) {
        return new BlockEntityType<>(sup, Set.of(validBlocks));
    }
}
