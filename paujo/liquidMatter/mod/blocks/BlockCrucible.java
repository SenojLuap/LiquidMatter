package paujo.liquidMatter.mod.blocks;

import paujo.liquidMatter.mod.LiquidMatter;
import paujo.liquidMatter.mod.tileentities.TileEntityCrucible;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCrucible extends BlockContainer {

	public static final int defaultID = 1001;

	protected BlockCrucible() {
	  super(defaultID, Material.rock);
	  setUnlocalizedName("blockCrucible");
	  setHardness(1.5F);
	  setCreativeTab(LiquidMatter.liquidMatterTab);
  }

	@Override
  public TileEntity createNewTileEntity(World world) {
	  return new TileEntityCrucible();
  }

}
