package com.github.command17.magnetizing.fabric.data;

import com.github.command17.magnetizing.common.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.EntityType;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public ModEntityTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        getOrCreateTagBuilder(ModTags.EntityTags.UNMOVEABLE_BY_MAGNETS)
                .add(EntityType.SHULKER)
                .add(EntityType.ENDER_DRAGON)
                .add(EntityType.EVOKER_FANGS)
                .add(EntityType.INTERACTION)
                .add(EntityType.LIGHTNING_BOLT)
                .add(EntityType.MARKER)
                .add(EntityType.BLOCK_DISPLAY)
                .add(EntityType.ITEM_DISPLAY)
                .add(EntityType.ITEM_FRAME)
                .add(EntityType.GLOW_ITEM_FRAME)
                .add(EntityType.END_CRYSTAL)
                .add(EntityType.AREA_EFFECT_CLOUD);
    }
}
