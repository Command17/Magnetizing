package com.github.command17.magnetizing.common.item;

import com.github.command17.magnetizing.Magnetizing;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public final class ModArmorMaterials {
    private static final DeferredRegister<ArmorMaterial> REGISTRY = DeferredRegister.create(Magnetizing.MOD_ID, Registries.ARMOR_MATERIAL);

    public static final RegistrySupplier<ArmorMaterial> ANTI_MAGNETIC = register("anti_magnetic", Util.make(new EnumMap<>(ArmorItem.Type.class), (attribute) -> {
        attribute.put(ArmorItem.Type.BOOTS, 2);
        attribute.put(ArmorItem.Type.LEGGINGS, 5);
        attribute.put(ArmorItem.Type.CHESTPLATE, 6);
        attribute.put(ArmorItem.Type.HELMET, 2);
        attribute.put(ArmorItem.Type.BODY, 5);
    }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0, 0, () -> Ingredient.of(ModItems.MAGNETITE_INGOT.get()));

    private static RegistrySupplier<ArmorMaterial> register(String id, EnumMap<ArmorItem.Type, Integer> defense, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        EnumMap<ArmorItem.Type, Integer> enumMap = new EnumMap<>(ArmorItem.Type.class);
        for(ArmorItem.Type type: ArmorItem.Type.values()) {
            enumMap.put(type, defense.get(type));
        }

        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(Magnetizing.resource(id)));
        return register(id,
                () -> new ArmorMaterial(enumMap, enchantmentValue, equipSound, repairIngredient, layers, toughness, knockbackResistance));
    }

    private static RegistrySupplier<ArmorMaterial> register(String id, Supplier<ArmorMaterial> sup) {
        return REGISTRY.register(id, sup);
    }

    public static void register() {
        REGISTRY.register();
        Magnetizing.LOGGER.info("Registered Armor Materials.");
    }
}
