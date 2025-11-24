package com.github.command17.magnetizing.common.item.tab;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.common.item.ModItems;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public final class ModCreativeModeTabs {
    private static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Magnetizing.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> MAIN = register("main",
            () -> CreativeTabRegistry.create((builder) ->
                    builder.title(Magnetizing.translatable("itemGroup.", ".main"))
                            .icon(() -> new ItemStack(ModItems.RED_DIRECTIONAL_BLOCK_MAGNET.get()))
                            .displayItems((parameters, output) -> {
                                output.accept(ModItems.MAGNETITE_ORE.get());
                                output.accept(ModItems.DEEPSLATE_MAGNETITE_ORE.get());
                                output.accept(ModItems.RAW_MAGNETITE.get());
                                output.accept(ModItems.MAGNETITE_INGOT.get());
                                output.accept(ModItems.BLUE_MAGNETITE_INGOT.get());
                                output.accept(ModItems.RED_MAGNETITE_INGOT.get());

                                output.accept(ModItems.MAGNETITE_BLOCK.get());
                                output.accept(ModItems.BLUE_MAGNETITE_BLOCK.get());
                                output.accept(ModItems.RED_MAGNETITE_BLOCK.get());

                                output.accept(ModItems.BLUE_ITEM_MAGNET.get());
                                output.accept(ModItems.RED_ITEM_MAGNET.get());

                                output.accept(ModItems.BLUE_BLOCK_MAGNET.get());
                                output.accept(ModItems.RED_BLOCK_MAGNET.get());
                                output.accept(ModItems.BLUE_DIRECTIONAL_BLOCK_MAGNET.get());
                                output.accept(ModItems.RED_DIRECTIONAL_BLOCK_MAGNET.get());
                            })
            ));

    private static RegistrySupplier<CreativeModeTab> register(String id, Supplier<CreativeModeTab> sup) {
        return REGISTRY.register(id, sup);
    }

    public static void register() {
        REGISTRY.register();
        Magnetizing.LOGGER.info("Registered Creative Mode Tabs.");
    }
}
