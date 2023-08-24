package io.github.acdeasdff.infinityCompress.items.FNFALsAmplifications;

import io.github.acdeasdff.infinityCompress.items.Multiblock_Autocrafter;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import ne.fnfal113.fnamplifications.multiblocks.FnGemAltar;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AutoGemAltar extends Multiblock_Autocrafter {
    public AutoGemAltar(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe, "&d自动FN宝石祭坛", Material.ENCHANTING_TABLE, "&dFN宝石祭坛", FnGemAltar.RECIPE_TYPE);
    }
}
