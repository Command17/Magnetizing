package com.github.command17.magnetizing.fabric.data;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.common.block.BlockMagnetBlock;
import com.github.command17.magnetizing.common.block.MagneticBlock;
import com.github.command17.magnetizing.common.block.ModBlocks;
import com.github.command17.magnetizing.common.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jspecify.annotations.NonNull;

import java.util.function.BiConsumer;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    // Instead of north being the default direction of the model, it's up
    // Worst change ever in 1.21.11 imo
    public static void facingUpBaseConsumer(BiConsumer<Direction, MultiVariant> consumer, MultiVariant variant) {
        consumer.accept(Direction.NORTH, variant.with(BlockModelGenerators.X_ROT_90));
        consumer.accept(Direction.SOUTH, variant.with(BlockModelGenerators.X_ROT_270));
        consumer.accept(Direction.UP, variant);
        consumer.accept(Direction.DOWN, variant.with(BlockModelGenerators.X_ROT_180));
        consumer.accept(Direction.WEST, variant.with(BlockModelGenerators.Y_ROT_270).with(BlockModelGenerators.X_ROT_90));
        consumer.accept(Direction.EAST, variant.with(BlockModelGenerators.Y_ROT_90).with(BlockModelGenerators.X_ROT_90));
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators generator) {
        simpleBlock(ModBlocks.MAGNETITE_ORE.get(), generator);
        simpleBlock(ModBlocks.DEEPSLATE_MAGNETITE_ORE.get(), generator);
        simpleBlock(ModBlocks.MAGNETITE_BLOCK.get(), generator);
        simpleBlock(ModBlocks.BLUE_MAGNETITE_BLOCK.get(), generator);
        simpleBlock(ModBlocks.RED_MAGNETITE_BLOCK.get(), generator);
        simpleBlock(ModBlocks.RAW_MAGNETITE_BLOCK.get(), generator);

        createDirectionalBlockMagnetBlock(ModBlocks.BLUE_DIRECTIONAL_BLOCK_MAGNET.get(), "blue", generator);
        createDirectionalBlockMagnetBlock(ModBlocks.RED_DIRECTIONAL_BLOCK_MAGNET.get(), "red", generator);

        createBlockMagnetBlock(ModBlocks.BLUE_BLOCK_MAGNET.get(), generator);
        createBlockMagnetBlock(ModBlocks.RED_BLOCK_MAGNET.get(), generator);
    }

    // It works
    private void createDirectionalBlockMagnetBlock(Block block, String namePrefix, BlockModelGenerators generator) {
        Identifier topTexture = Magnetizing.resource("block/" + namePrefix + "_block_magnet");
        Identifier bottomTexture = Magnetizing.resource("block/directional_block_magnet_bottom");
        Identifier sideTexture = TextureMapping.getBlockTexture(block, "_side");
        Identifier disabledTopTexture = Magnetizing.resource("block/disabled_block_magnet");
        Identifier disabledSideTexture = Magnetizing.resource("block/disabled_directional_block_magnet_side");
        TextureMapping textureMapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, bottomTexture)
                .put(TextureSlot.TOP, topTexture)
                .put(TextureSlot.SIDE, sideTexture);

        TextureMapping disabledTextureMapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, bottomTexture)
                .put(TextureSlot.TOP, disabledTopTexture)
                .put(TextureSlot.SIDE, disabledSideTexture);

        Identifier model = ModelTemplates.CUBE_BOTTOM_TOP.create(block, textureMapping, generator.modelOutput);
        Identifier modelDisabled = ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(block, "_disabled", disabledTextureMapping, generator.modelOutput);
        MultiVariant modelVariant = BlockModelGenerators.plainVariant(model);
        MultiVariant modelDisabledVariant = BlockModelGenerators.plainVariant(modelDisabled);

        var dispatch = PropertyDispatch.initial(BlockStateProperties.FACING, MagneticBlock.DISABLED);
        facingUpBaseConsumer((direction, variant) -> dispatch.select(direction, false, variant), modelVariant);
        facingUpBaseConsumer((direction, variant) -> dispatch.select(direction, true, variant), modelDisabledVariant);

        MultiVariantGenerator multiVariant = MultiVariantGenerator.dispatch(block).with(dispatch);
        generator.blockStateOutput.accept(multiVariant);
    }

    private void createBlockMagnetBlock(Block block, BlockModelGenerators generator) {
        Identifier texture = TextureMapping.getBlockTexture(block);
        Identifier disabledTexture = Magnetizing.resource("block/disabled_block_magnet");
        TextureMapping textureMapping = new TextureMapping().put(TextureSlot.ALL, texture);
        TextureMapping disabledTextureMapping = new TextureMapping().put(TextureSlot.ALL, disabledTexture);
        Identifier model = ModelTemplates.CUBE_ALL.create(block, textureMapping, generator.modelOutput);
        Identifier modelDisabled = ModelTemplates.CUBE_ALL.createWithSuffix(block, "_disabled", disabledTextureMapping, generator.modelOutput);
        MultiVariant modelVariant = BlockModelGenerators.plainVariant(model);
        MultiVariant modelDisabledVariant = BlockModelGenerators.plainVariant(modelDisabled);
        MultiVariantGenerator multiVariant = MultiVariantGenerator.dispatch(block)
                .with(BlockModelGenerators.createBooleanModelDispatch(MagneticBlock.DISABLED, modelDisabledVariant, modelVariant));

        generator.blockStateOutput.accept(multiVariant);
    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators generator) {
        flatItem(ModItems.RAW_MAGNETITE.get(), generator);
        flatItem(ModItems.MAGNETITE_INGOT.get(), generator);
        flatItem(ModItems.BLUE_MAGNETITE_INGOT.get(), generator);
        flatItem(ModItems.RED_MAGNETITE_INGOT.get(), generator);
        flatItem(ModItems.BLUE_ITEM_MAGNET.get(), generator);
        flatItem(ModItems.RED_ITEM_MAGNET.get(), generator);
        flatItem(ModItems.ANTI_MAGNETIC_BOOTS.get(), generator);
        flatItem(ModItems.MAGNETITE_NUGGET.get(), generator);
        flatItem(ModItems.BLUE_MAGNETITE_NUGGET.get(), generator);
        flatItem(ModItems.RED_MAGNETITE_NUGGET.get(), generator);
    }

    private static void simpleBlock(Block block, BlockModelGenerators generator) {
        generator.createTrivialCube(block);
    }

    private static void flatItem(Item item, ItemModelGenerators generator) {
        generator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }
}
