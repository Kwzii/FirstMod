package net.kwzii.firstmod.datagen;

import net.kwzii.firstmod.FirstMod;
import net.kwzii.firstmod.block.ModBlocks;
import net.kwzii.firstmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        oreSmelting(consumer, List.of(ModItems.RAW_SAPPHIRE.get(), ModBlocks.SAPPHIRE_ORE.get()),
                RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.25f, 200, "sapphire");

        oreBlasting(consumer, List.of(ModItems.RAW_SAPPHIRE.get(), ModBlocks.SAPPHIRE_ORE.get()),
                RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.25f, 100, "sapphire");

        // SAPPHIRE BLOCK RECIPE
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAPPHIRE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
                .save(consumer);

        // BREAK DOWN SAPPHIRE BLOCK RECIPE
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
                .requires(ModBlocks.SAPPHIRE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SAPPHIRE_BLOCK.get()), has(ModBlocks.SAPPHIRE_BLOCK.get()))
                .save(consumer);

        // DICE RECIPE
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DICE.get())
                .pattern(" B ")
                .pattern("BWB")
                .pattern(" B ")
                .define('W', Items.WHITE_WOOL)
                .define('B', Items.BLACK_DYE)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(consumer);

        // DOUBLE DICE RECIPE
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DOUBLE_DICE.get(), 1)
                .requires(ModItems.DICE.get(), 2)
                .unlockedBy(getHasName(ModItems.DICE.get()), has(ModItems.DICE.get()))
                .save(consumer);

        // DUDEY34 BLOCK RECIPE
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DUDEY34_BLOCK.get())
                .pattern(" D ")
                .pattern("DDD")
                .pattern(" D ")
                .define('D', ModItems.DUDEY34.get())
                .unlockedBy(getHasName(ModItems.DUDEY34.get()), has(ModItems.DUDEY34.get()))
                .save(consumer);

        // GLOWSTONE32 BLOCK FROM DUDEY ITEMS RECIPE
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GLOWSTONE34_BLOCK.get())
                .pattern("GDG")
                .pattern("DDD")
                .pattern("GDG")
                .define('D', ModItems.DUDEY34.get())
                .define('G', Items.GLOWSTONE_DUST)
                .unlockedBy(getHasName(ModItems.DUDEY34.get()), has(ModItems.DUDEY34.get()))
                .save(consumer);

        // GLOWSTONE32 BLOCK FROM DUDEY BLOCK RECIPE
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GLOWSTONE34_BLOCK.get())
//                .pattern(" G ")
//                .pattern("GDG")
//                .pattern(" G ")
//                .define('D', ModBlocks.DUDEY34_BLOCK.get())
//                .define('G', Items.GLOWSTONE_DUST)
//                .unlockedBy(getHasName(ModBlocks.DUDEY34_BLOCK.get()), has(ModBlocks.DUDEY34_BLOCK.get()))
//                .save(consumer);

        // MAXWELL RECIPE
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAXWELL.get())
                .pattern("DDD")
                .pattern("DDD")
                .define('D', ModItems.DUDEY34.get())
                .unlockedBy(getHasName(ModItems.DUDEY34.get()), has(ModItems.DUDEY34.get()))
                .save(consumer);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, FirstMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

}
