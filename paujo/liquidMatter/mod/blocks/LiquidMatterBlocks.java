package paujo.liquidMatter.mod.blocks;

import paujo.liquidMatter.mod.LiquidMatter;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class LiquidMatterBlocks {

	public static Block blockLiquidMatter;
	public static Block blockCrucible;
	
	/*
	 * Initialize all blocks
	 */
	public static void initBlocks() {
		blockLiquidMatter = new BlockLiquidMatter();
		registerBlock(blockLiquidMatter, "Liquid Matter");
		
		// TODO LM Think up a better name
		blockCrucible = new BlockCrucible();
		registerBlock(blockCrucible, "Crucible");
	}


	/**
	 * Register a new block
	 * @param block The block to be added
	 * @param string The non-human readable name of the block
	 * @param name The human readable name of the block
	 */
	public static void registerBlock(Block block, String name) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
		LanguageRegistry.addName(block, name);
	}
	
	
}
