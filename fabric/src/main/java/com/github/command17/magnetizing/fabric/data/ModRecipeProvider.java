package com.github.command17.magnetizing.fabric.data;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.common.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLUE_MAGNETITE_INGOT.get(), 2)
                .requires(Items.LAPIS_LAZULI, 2)
                .requires(ModItems.MAGNETITE_INGOT.get())
                .unlockedBy(getHasName(ModItems.MAGNETITE_INGOT.get()), has(ModItems.MAGNETITE_INGOT.get()))
                .save(output, Magnetizing.resource("crafting_blue_magnetite_ingot"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RED_MAGNETITE_INGOT.get(), 2)
                .requires(Items.REDSTONE, 2)
                .requires(ModItems.MAGNETITE_INGOT.get())
                .unlockedBy(getHasName(ModItems.MAGNETITE_INGOT.get()), has(ModItems.MAGNETITE_INGOT.get()))
                .save(output, Magnetizing.resource("crafting_red_magnetite_ingot"));

        nineBlockStorageRecipes(output, RecipeCategory.MISC, ModItems.MAGNETITE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ModItems.MAGNETITE_BLOCK.get());
        nineBlockStorageRecipes(output, RecipeCategory.MISC, ModItems.BLUE_MAGNETITE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ModItems.BLUE_MAGNETITE_BLOCK.get());
        nineBlockStorageRecipes(output, RecipeCategory.MISC, ModItems.RED_MAGNETITE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ModItems.RED_MAGNETITE_BLOCK.get());

        oreSmelting(
                output,
                List.of(ModItems.RAW_MAGNETITE.get(), ModItems.MAGNETITE_ORE.get(), ModItems.DEEPSLATE_MAGNETITE_ORE.get()),
                RecipeCategory.MISC,
                ModItems.MAGNETITE_INGOT.get(),
                0.7f,
                200,
                "magnetite_ingot_smelting"
        );

        oreBlasting(
                output,
                List.of(ModItems.RAW_MAGNETITE.get(), ModItems.MAGNETITE_ORE.get(), ModItems.DEEPSLATE_MAGNETITE_ORE.get()),
                RecipeCategory.MISC,
                ModItems.MAGNETITE_INGOT.get(),
                0.7f,
                200,
                "magnetite_ingot_blasting"
        );
    }
}
