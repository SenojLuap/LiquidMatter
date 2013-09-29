package paujo.liquidMatter.mod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import paujo.liquidMatter.mod.fluids.LiquidMatterFluids;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LiquidMatterItems {
	
	// Buckets
	public static Item itemLiquidMatterBucket;
	
	/**
	 * Initializes fluid buckets. Fluid blocks must be initialized before this is called
	 * @param config The configuration file with block/item IDs
	 */
	public static void initBuckets(Configuration config) {
		itemLiquidMatterBucket = new ItemLiquidMatterBucket(config);
		registerItem(itemLiquidMatterBucket, "Liquid Matter Bucket");
		FluidContainerRegistry.registerFluidContainer(new FluidStack(LiquidMatterFluids.fluidLiquidMatter, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(itemLiquidMatterBucket), new ItemStack(Item.bucketEmpty));
	}

	
	/**
	 * Register a new item
	 * @param item The item to be added
	 * @param name The human readable name of the block
	 */
	public static void registerItem(Item item, String name) {
		GameRegistry.registerItem(item, item.getUnlocalizedName());
		LanguageRegistry.addName(item, name);
	}
}
