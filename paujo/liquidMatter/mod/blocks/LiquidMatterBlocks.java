package paujo.liquidMatter.mod.blocks;

import paujo.liquidMatter.mod.LiquidMatter;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class LiquidMatterBlocks {

	public static Block liquidMatterBlock;
	
	/*
	 * Initialize all blocks
	 */
	public static void initBlocks() {
		liquidMatterBlock = new LiquidMatterBlock(LiquidMatterBlock.defaultID, Material.rock).setUnlocalizedName("liquidMatterBlock");
		registerBlock(liquidMatterBlock, "Liquid Matter");
	}


	/**
	 * Register a new block
	 * @param block The block to be added
	 * @param string The non-human readable name of the block
	 * @param name The human readable name of the block
	 */
	public static void registerBlock(Block block, String name) {
		GameRegistry.registerBlock(block, LiquidMatter.modid + block.getUnlocalizedName().substring(5));
		LanguageRegistry.addName(block, name);
	}
	
	
}
