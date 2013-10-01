package paujo.liquidMatter.mod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import paujo.liquidMatter.mod.items.LiquidMatterItems;

public class LiquidMatterRecipes {
	
	/**
	 * Register all recipes 
	 */
	public static void initRecipes() {
		
		// Hyper-Dense Carbon
		CraftingManager.getInstance().addRecipe(new ItemStack(LiquidMatterItems.itemCarbon), new Object[] {
			"ddd",
			"dBd",
			"ddd",
			'd', Item.diamond,
			'B', Block.blockDiamond
		});
		
		// Spoonful of Neutron
		CraftingManager.getInstance().addRecipe(new ItemStack(LiquidMatterItems.itemNeutron), new Object[] {
			"ddd",
			"hhh",
			"ddd",
			'd', Item.diamond,
			'h', LiquidMatterItems.itemCarbon
		});
		
	}

}
