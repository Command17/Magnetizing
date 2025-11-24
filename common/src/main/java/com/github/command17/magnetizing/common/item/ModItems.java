package com.github.command17.magnetizing.common.item;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.common.block.ModBlocks;
import com.github.command17.magnetizing.common.item.component.ModItemComponents;
import com.github.command17.magnetizing.common.util.MagneticPole;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public final class ModItems {
    private static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(Magnetizing.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> MAGNETITE_ORE = registerSimpleBlockItem("magnetite_ore", ModBlocks.MAGNETITE_ORE);
    public static final RegistrySupplier<Item> DEEPSLATE_MAGNETITE_ORE = registerSimpleBlockItem("deepslate_magnetite_ore", ModBlocks.DEEPSLATE_MAGNETITE_ORE);
    public static final RegistrySupplier<Item> MAGNETITE_BLOCK = registerSimpleBlockItem("magnetite_block", ModBlocks.MAGNETITE_BLOCK);
    public static final RegistrySupplier<Item> BLUE_MAGNETITE_BLOCK = registerSimpleBlockItem("blue_magnetite_block", ModBlocks.BLUE_MAGNETITE_BLOCK);
    public static final RegistrySupplier<Item> RED_MAGNETITE_BLOCK = registerSimpleBlockItem("red_magnetite_block", ModBlocks.RED_MAGNETITE_BLOCK);
    public static final RegistrySupplier<Item> BLUE_DIRECTIONAL_BLOCK_MAGNET = registerSimpleBlockItem("blue_directional_block_magnet", ModBlocks.BLUE_DIRECTIONAL_BLOCK_MAGNET);
    public static final RegistrySupplier<Item> RED_DIRECTIONAL_BLOCK_MAGNET = registerSimpleBlockItem("red_directional_block_magnet", ModBlocks.RED_DIRECTIONAL_BLOCK_MAGNET);
    public static final RegistrySupplier<Item> BLUE_BLOCK_MAGNET = registerSimpleBlockItem("blue_block_magnet", ModBlocks.BLUE_BLOCK_MAGNET);
    public static final RegistrySupplier<Item> RED_BLOCK_MAGNET = registerSimpleBlockItem("red_block_magnet", ModBlocks.RED_BLOCK_MAGNET);

    public static final RegistrySupplier<Item> RAW_MAGNETITE = registerSimple("raw_magnetite");
    public static final RegistrySupplier<Item> MAGNETITE_INGOT = registerSimple("magnetite_ingot");
    public static final RegistrySupplier<Item> BLUE_MAGNETITE_INGOT = registerSimple("blue_magnetite_ingot");
    public static final RegistrySupplier<Item> RED_MAGNETITE_INGOT = registerSimple("red_magnetite_ingot");
    public static final RegistrySupplier<Item> BLUE_ITEM_MAGNET = register("blue_item_magnet",
            () -> new MagnetItem(new Item.Properties()
                    .stacksTo(1)
                    .component(ModItemComponents.MAGNET_POLE.get(), MagneticPole.MINUS)
                    .component(ModItemComponents.MAGNET_FORCE.get(), 0.1)
                    .component(ModItemComponents.MAX_MAGNET_RANGE.get(), 5)
            ));

    public static final RegistrySupplier<Item> RED_ITEM_MAGNET = register("red_item_magnet",
            () -> new MagnetItem(new Item.Properties()
                    .stacksTo(1)
                    .component(ModItemComponents.MAGNET_POLE.get(), MagneticPole.PLUS)
                    .component(ModItemComponents.MAGNET_FORCE.get(), 0.1)
                    .component(ModItemComponents.MAX_MAGNET_RANGE.get(), 5)
            ));

    private static RegistrySupplier<Item> registerSimpleBlockItem(String id, Supplier<Block> sup) {
        return register(id, () -> new BlockItem(sup.get(), new Item.Properties()));
    }

    private static RegistrySupplier<Item> registerSimple(String id, Supplier<Item.Properties> sup) {
        return register(id, () -> new Item(sup.get()));
    }

    private static RegistrySupplier<Item> registerSimple(String id) {
        return register(id, () -> new Item(new Item.Properties()));
    }

    private static RegistrySupplier<Item> register(String id, Supplier<Item> sup) {
        return REGISTRY.register(id, sup);
    }

    public static void register() {
        REGISTRY.register();
        Magnetizing.LOGGER.info("Registered Items.");
    }
}
