package paujo.liquidMatter.mod.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LiquidMatterBlocks {

	public static Block blockLiquidMatter;
	public static Block blockAtomizer;
	
	/*
	 * Initialize all blocks
	 */
	public static void initBlocks(Configuration config) {
		blockLiquidMatter = new BlockLiquidMatter(config);
		registerBlock(blockLiquidMatter, "Liquid Matter");
		
		// TODO LM Think up a better name
		blockAtomizer = new BlockAtomizer(config);
		registerBlock(blockAtomizer, "Atomizer");
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
