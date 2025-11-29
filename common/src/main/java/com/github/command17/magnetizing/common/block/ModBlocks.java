package com.github.command17.magnetizing.common.block;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.common.util.MagneticPole;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public final class ModBlocks {
    private static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(Magnetizing.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> MAGNETITE_ORE = registerSimple("magnetite_ore",
            () -> Properties.ofFullCopy(Blocks.IRON_ORE));

    public static final RegistrySupplier<Block> DEEPSLATE_MAGNETITE_ORE = registerSimple("deepslate_magnetite_ore",
            () -> Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE));

    public static final RegistrySupplier<Block> RAW_MAGNETITE_BLOCK = registerSimple("raw_magnetite_block",
            () -> Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK));

    public static final RegistrySupplier<Block> MAGNETITE_BLOCK = registerSimple("magnetite_block",
            () -> Properties.ofFullCopy(Blocks.IRON_BLOCK));

    public static final RegistrySupplier<Block> BLUE_MAGNETITE_BLOCK = register("blue_magnetite_block",
            () -> new MagneticMagnetiteBlock(MagneticPole.MINUS, Properties.ofFullCopy(MAGNETITE_BLOCK.get())));

    public static final RegistrySupplier<Block> RED_MAGNETITE_BLOCK = register("red_magnetite_block",
            () -> new MagneticMagnetiteBlock(MagneticPole.PLUS, Properties.ofFullCopy(MAGNETITE_BLOCK.get())));

    public static final RegistrySupplier<Block> BLUE_DIRECTIONAL_BLOCK_MAGNET = register("blue_directional_block_magnet",
            () -> new DirectionalBlockMagnetBlock(
                    MagneticPole.MINUS,
                    Properties.of()
                        .mapColor(MapColor.STONE)
                        .instrument(NoteBlockInstrument.BASEDRUM)
                        .requiresCorrectToolForDrops()
                        .strength(3.5f)
            ));

    public static final RegistrySupplier<Block> RED_DIRECTIONAL_BLOCK_MAGNET = register("red_directional_block_magnet",
            () -> new DirectionalBlockMagnetBlock(MagneticPole.PLUS, Properties.ofFullCopy(BLUE_DIRECTIONAL_BLOCK_MAGNET.get())));

    public static final RegistrySupplier<Block> BLUE_BLOCK_MAGNET = register("blue_block_magnet",
            () -> new BlockMagnetBlock(MagneticPole.MINUS, Properties.ofFullCopy(BLUE_DIRECTIONAL_BLOCK_MAGNET.get())));

    public static final RegistrySupplier<Block> RED_BLOCK_MAGNET = register("red_block_magnet",
            () -> new BlockMagnetBlock(MagneticPole.PLUS, Properties.ofFullCopy(BLUE_DIRECTIONAL_BLOCK_MAGNET.get())));

    private static RegistrySupplier<Block> registerSimple(String id, Supplier<Properties> sup) {
        return register(id, () -> new Block(sup.get()));
    }

    private static RegistrySupplier<Block> register(String id, Supplier<Block> sup) {
        return REGISTRY.register(id, sup);
    }

    public static void register() {
        REGISTRY.register();
        Magnetizing.LOGGER.info("Registered Blocks.");
    }
}
