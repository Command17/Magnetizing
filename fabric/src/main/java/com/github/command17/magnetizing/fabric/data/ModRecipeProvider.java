package com.github.command17.magnetizing.fabric.data;

import com.github.command17.magnetizing.Magnetizing;
import com.github.command17.magnetizing.common.item.ModItems;
import com.github.command17.magnetizing.common.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @NullMarked
    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeGenerator(provider, recipeOutput);
    }

    @NonNull
    @Override
    public String getName() {
        return Magnetizing.resource("recipe_generator").toString();
    }

    private static class RecipeGenerator extends RecipeProvider {
        public RecipeGenerator(HolderLookup.Provider registries, RecipeOutput output) {
            super(registries, output);
        }

        private void itemMagnetRecipe(ItemLike magnet, ItemLike material) {
            shaped(RecipeCategory.TOOLS, magnet)
                    .pattern("x x")
                    .pattern("# #")
                    .pattern("###")
                    .define('x', material)
                    .define('#', Items.GOLD_INGOT)
                    .unlockedBy(getHasName(material), has(material))
                    .save(this.output);
        }

        private void blockMagnetRecipe(ItemLike magnet, ItemLike material) {
            shaped(RecipeCategory.REDSTONE, magnet)
                    .pattern("x#x")
                    .pattern("#r#")
                    .pattern("x#x")
                    .define('x', material)
                    .define('#', Items.COBBLESTONE)
                    .define('r', Items.REDSTONE)
                    .unlockedBy(getHasName(material), has(material))
                    .save(this.output);
        }

        private void directionalBlockMagnetRecipe(ItemLike magnet, ItemLike material) {
            shaped(RecipeCategory.REDSTONE, magnet)
                    .pattern("xxx")
                    .pattern("#r#")
                    .pattern("###")
                    .define('x', material)
                    .define('#', Items.COBBLESTONE)
                    .define('r', Items.REDSTONE)
                    .unlockedBy(getHasName(material), has(material))
                    .save(this.output);
        }

        @Override
        public void buildRecipes() {
            shapeless(RecipeCategory.MISC, ModItems.BLUE_MAGNETITE_INGOT.get(), 2)
                    .requires(Items.LAPIS_LAZULI, 2)
                    .requires(ModItems.MAGNETITE_INGOT.get())
                    .unlockedBy(getHasName(ModItems.MAGNETITE_INGOT.get()), has(ModItems.MAGNETITE_INGOT.get()))
                    .save(this.output, ResourceKey.create(Registries.RECIPE, Magnetizing.resource("crafting_blue_magnetite_ingot")));

            shapeless(RecipeCategory.MISC, ModItems.RED_MAGNETITE_INGOT.get(), 2)
                    .requires(Items.REDSTONE, 2)
                    .requires(ModItems.MAGNETITE_INGOT.get())
                    .unlockedBy(getHasName(ModItems.MAGNETITE_INGOT.get()), has(ModItems.MAGNETITE_INGOT.get()))
                    .save(this.output, ResourceKey.create(Registries.RECIPE, Magnetizing.resource("crafting_red_magnetite_ingot")));

            // Blocks <=> Items
            nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.RAW_MAGNETITE.get(), RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_MAGNETITE_BLOCK.get());
            nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.MAGNETITE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ModItems.MAGNETITE_BLOCK.get());
            nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.BLUE_MAGNETITE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ModItems.BLUE_MAGNETITE_BLOCK.get());
            nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.RED_MAGNETITE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ModItems.RED_MAGNETITE_BLOCK.get());

            // Smelting Magnetite
            oreSmelting(
                    List.of(ModItems.RAW_MAGNETITE.get(), ModItems.MAGNETITE_ORE.get(), ModItems.DEEPSLATE_MAGNETITE_ORE.get()),
                    RecipeCategory.MISC,
                    ModItems.MAGNETITE_INGOT.get(),
                    0.7f,
                    200,
                    "magnetite_ingot_smelting"
            );

            oreBlasting(
                    List.of(ModItems.RAW_MAGNETITE.get(), ModItems.MAGNETITE_ORE.get(), ModItems.DEEPSLATE_MAGNETITE_ORE.get()),
                    RecipeCategory.MISC,
                    ModItems.MAGNETITE_INGOT.get(),
                    0.7f,
                    100,
                    "magnetite_ingot_blasting"
            );

            // Items <=> Nuggets
            nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.MAGNETITE_NUGGET.get(), RecipeCategory.MISC, ModItems.MAGNETITE_INGOT.get(), Magnetizing.resource("magnetite_ingot_from_nuggets").toString(), null, Magnetizing.resource("nuggets_from_magnetite_ingot").toString(), null);
            nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.BLUE_MAGNETITE_NUGGET.get(), RecipeCategory.MISC, ModItems.BLUE_MAGNETITE_INGOT.get(), Magnetizing.resource("blue_magnetite_ingot_from_nuggets").toString(), null, Magnetizing.resource("nuggets_from_blue_magnetite_ingot").toString(), null);
            nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.RED_MAGNETITE_NUGGET.get(), RecipeCategory.MISC, ModItems.RED_MAGNETITE_INGOT.get(), Magnetizing.resource("red_magnetite_ingot_from_nuggets").toString(), null, Magnetizing.resource("nuggets_from_red_magnetite_ingot").toString(), null);

            // Item Magnets
            itemMagnetRecipe(ModItems.BLUE_ITEM_MAGNET.get(), ModItems.BLUE_MAGNETITE_BLOCK.get());
            itemMagnetRecipe(ModItems.RED_ITEM_MAGNET.get(), ModItems.RED_MAGNETITE_BLOCK.get());

            // Anti-Magnetic Boots
            shaped(RecipeCategory.TOOLS, ModItems.ANTI_MAGNETIC_BOOTS.get())
                    .pattern("x x")
                    .pattern("y z")
                    .define('x', ModTags.ItemTags.INGOTS_MAGNETITE)
                    .define('y', ModItems.BLUE_MAGNETITE_BLOCK.get())
                    .define('z', ModItems.RED_MAGNETITE_BLOCK.get())
                    .unlockedBy(getHasName(ModItems.BLUE_MAGNETITE_BLOCK.get()), has(ModItems.BLUE_MAGNETITE_BLOCK.get()))
                    .unlockedBy(getHasName(ModItems.RED_MAGNETITE_BLOCK.get()), has(ModItems.RED_MAGNETITE_BLOCK.get()))
                    .save(this.output);

            // Block Magnets
            blockMagnetRecipe(ModItems.BLUE_BLOCK_MAGNET.get(), ModItems.BLUE_MAGNETITE_BLOCK.get());
            blockMagnetRecipe(ModItems.RED_BLOCK_MAGNET.get(), ModItems.RED_MAGNETITE_BLOCK.get());
            directionalBlockMagnetRecipe(ModItems.BLUE_DIRECTIONAL_BLOCK_MAGNET.get(), ModItems.BLUE_MAGNETITE_BLOCK.get());
            directionalBlockMagnetRecipe(ModItems.RED_DIRECTIONAL_BLOCK_MAGNET.get(), ModItems.RED_MAGNETITE_BLOCK.get());
        }
    }
}
