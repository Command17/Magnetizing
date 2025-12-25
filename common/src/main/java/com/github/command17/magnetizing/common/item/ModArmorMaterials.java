package com.github.command17.magnetizing.common.item;

import com.github.command17.magnetizing.Magnetizing;
import com.google.common.collect.Maps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

public final class ModArmorMaterials {
    public static final ResourceKey<EquipmentAsset> ANTI_MAGNETIC_EQUIPMENT_ASSET = createEquipmentAsset("anti_magnetic");
    public static final ArmorMaterial ANTI_MAGNETIC = new ArmorMaterial(15, makeDefense(2, 5, 6, 2, 5), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, ItemTags.REPAIRS_IRON_ARMOR, ANTI_MAGNETIC_EQUIPMENT_ASSET);

    private static ResourceKey<EquipmentAsset> createEquipmentAsset(String id) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, Magnetizing.resource(id));
    }

    private static Map<ArmorType, Integer> makeDefense(int boots, int leggings, int chestplate, int helmet, int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, leggings, ArmorType.CHESTPLATE, chestplate, ArmorType.HELMET, helmet, ArmorType.BODY, body));
    }
}
