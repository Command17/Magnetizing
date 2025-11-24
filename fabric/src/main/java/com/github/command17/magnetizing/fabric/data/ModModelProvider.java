package com.github.command17.magnetizing.fabric.data;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.common.block.MagneticBlock;
import com.github.command17.magnetizing.common.block.ModBlocks;
import com.github.command17.magnetizing.common.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    // Instead of north being the default direction of the model, it's up
    public static PropertyDispatch createFacingUpBaseDispatch() {
        return PropertyDispatch.property(BlockStateProperties.FACING)
                .select(Direction.NORTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
                .select(Direction.SOUTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R270))
                .select(Direction.UP, Variant.variant())
                .select(Direction.DOWN, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
                .select(Direction.WEST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
                .select(Direction.EAST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90));
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        simpleBlock(ModBlocks.MAGNETITE_ORE.get(), generator);
        simpleBlock(ModBlocks.DEEPSLATE_MAGNETITE_ORE.get(), generator);
        simpleBlock(ModBlocks.MAGNETITE_BLOCK.get(), generator);
        simpleBlock(ModBlocks.BLUE_MAGNETITE_BLOCK.get(), generator);
        simpleBlock(ModBlocks.RED_MAGNETITE_BLOCK.get(), generator);

        createDirectionalBlockMagnetBlock(ModBlocks.BLUE_DIRECTIONAL_BLOCK_MAGNET.get(), "blue", generator);
        createDirectionalBlockMagnetBlock(ModBlocks.RED_DIRECTIONAL_BLOCK_MAGNET.get(), "red", generator);

        createBlockMagnetBlock(ModBlocks.BLUE_BLOCK_MAGNET.get(), generator);
        createBlockMagnetBlock(ModBlocks.RED_BLOCK_MAGNET.get(), generator);
    }

    // It works
    private void createDirectionalBlockMagnetBlock(Block block, String namePrefix, BlockModelGenerators generator) {
        ResourceLocation topTexture = Magnetizing.resource("block/" + namePrefix + "_block_magnet");
        ResourceLocation bottomTexture = Magnetizing.resource("block/directional_block_magnet_bottom");
        ResourceLocation sideTexture = TextureMapping.getBlockTexture(block, "_side");
        ResourceLocation disabledTopTexture = Magnetizing.resource("block/disabled_block_magnet");
        ResourceLocation disabledSideTexture = Magnetizing.resource("block/disabled_directional_block_magnet_side");
        TextureMapping textureMapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, bottomTexture)
                .put(TextureSlot.TOP, topTexture)
                .put(TextureSlot.SIDE, sideTexture);

        TextureMapping disabledTextureMapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, bottomTexture)
                .put(TextureSlot.TOP, disabledTopTexture)
                .put(TextureSlot.SIDE, disabledSideTexture);

        ResourceLocation model = ModelTemplates.CUBE_BOTTOM_TOP.create(block, textureMapping, generator.modelOutput);
        ResourceLocation modelDisabled = ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(block, "_disabled", disabledTextureMapping, generator.modelOutput);
        MultiVariantGenerator multiVariant = MultiVariantGenerator.multiVariant(block)
                .with(BlockModelGenerators.createBooleanModelDispatch(MagneticBlock.DISABLED, modelDisabled, model))
                .with(createFacingUpBaseDispatch());

        generator.blockStateOutput.accept(multiVariant);
    }

    private void createBlockMagnetBlock(Block block, BlockModelGenerators generator) {
        ResourceLocation texture = TextureMapping.getBlockTexture(block);
        ResourceLocation disabledTexture = Magnetizing.resource("block/disabled_block_magnet");
        TextureMapping textureMapping = new TextureMapping().put(TextureSlot.ALL, texture);
        TextureMapping disabledTextureMapping = new TextureMapping().put(TextureSlot.ALL, disabledTexture);
        ResourceLocation model = ModelTemplates.CUBE_ALL.create(block, textureMapping, generator.modelOutput);
        ResourceLocation modelDisabled = ModelTemplates.CUBE_ALL.createWithSuffix(block, "_disabled", disabledTextureMapping, generator.modelOutput);
        MultiVariantGenerator multiVariant = MultiVariantGenerator.multiVariant(block)
                .with(BlockModelGenerators.createBooleanModelDispatch(MagneticBlock.DISABLED, modelDisabled, model));

        generator.blockStateOutput.accept(multiVariant);
    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        flatItem(ModItems.RAW_MAGNETITE.get(), generator);
        flatItem(ModItems.MAGNETITE_INGOT.get(), generator);
        flatItem(ModItems.BLUE_MAGNETITE_INGOT.get(), generator);
        flatItem(ModItems.RED_MAGNETITE_INGOT.get(), generator);
        flatItem(ModItems.BLUE_ITEM_MAGNET.get(), generator);
        flatItem(ModItems.RED_ITEM_MAGNET.get(), generator);
        flatItem(ModItems.ANTI_MAGNETIC_BOOTS.get(), generator);
    }

    private static void simpleBlock(Block block, BlockModelGenerators generator) {
        generator.createTrivialCube(block);
    }

    private static void flatItem(Item item, ItemModelGenerators generator) {
        generator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }
}
