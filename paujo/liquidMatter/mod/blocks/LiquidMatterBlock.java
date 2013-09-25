package paujo.liquidMatter.mod.blocks;

import paujo.liquidMatter.mod.LiquidMatter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class LiquidMatterBlock extends Block {

	public static final int defaultID = 1000;

	public LiquidMatterBlock(int blockID, Material material) {
	  super(blockID, material);
	  setCreativeTab(LiquidMatter.liquidMatterTab);
  }

}
