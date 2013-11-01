package paujo.liquidMatter.mod.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import paujo.liquidMatter.mod.LiquidMatter;
import paujo.liquidMatter.mod.tileentities.TileEntitySolidarityEngine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSolidarityEngine extends BlockContainer {
	
	public static final int defaultID = 1002;

	protected BlockSolidarityEngine(Configuration config) {
	  super(config.get("Blocks", "blockSolidarityEngine", defaultID).getInt(), Material.rock);
	  setUnlocalizedName("blockSolidarityEngine");
	  setHardness(1.0F);
	  setCreativeTab(LiquidMatter.liquidMatterTab);
  }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister r) {
		blockIcon = r.registerIcon(LiquidMatter.modid + ":" + BlockAtomizer.bottomTexture);
	}
	

	@Override
  public TileEntity createNewTileEntity(World world) {
		return new TileEntitySolidarityEngine();
  }

}
