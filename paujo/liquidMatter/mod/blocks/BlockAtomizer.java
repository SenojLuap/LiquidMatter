package paujo.liquidMatter.mod.blocks;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import paujo.liquidMatter.mod.LiquidMatter;
import paujo.liquidMatter.mod.gui.LiquidMatterGuiHandler;
import paujo.liquidMatter.mod.network.LiquidMatterPacketHandler;
import paujo.liquidMatter.mod.tileentities.TileEntityAtomizer;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;

public class BlockAtomizer extends BlockContainer {

	public static final int defaultID = 1001;
	
	public static final String sideTexture = "crucible_side";
	public static final String topIdleTexture = "crucible_top_idle";
	public static final String bottomTexture = "crucible_bottom";
	
	public Icon sideIcon;
	public Icon topIdleIcon;
	
	protected BlockAtomizer(Configuration config) {
	  super(config.get("Blocks", "blockAtomizer", defaultID).getInt(), Material.rock);
	  setUnlocalizedName("blockAtomizer");
	  setHardness(1.5F);
	  setCreativeTab(LiquidMatter.liquidMatterTab);
  }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir) {
		blockIcon = ir.registerIcon(LiquidMatter.modid + ":" + bottomTexture);
		sideIcon = ir.registerIcon(LiquidMatter.modid + ":" + sideTexture);
		topIdleIcon = ir.registerIcon(LiquidMatter.modid + ":" + topIdleTexture);
	}
	
	@Override
  public TileEntity createNewTileEntity(World world) {
	  return new TileEntityAtomizer();
  }

	@Override
	@SideOnly(Side.CLIENT)
  public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side) {
		switch (side) {
			case 0: return blockIcon;
			case 1: return topIdleIcon;
			default : return sideIcon;
		}
  }
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && player != null && !player.isSneaking()) {
			TileEntityAtomizer tileEntity = (TileEntityAtomizer)world.getBlockTileEntity(x, y, z);
			if (tileEntity != null) {
				LiquidMatterPacketHandler.sendCrucibleBurnInfo(tileEntity);
				LiquidMatterPacketHandler.sendCrucibleTankInfo(tileEntity);
				FMLNetworkHandler.openGui(player, LiquidMatter.instance, LiquidMatterGuiHandler.guiIDCrucible, world, x, y, z);
			}
		}
		return true;
	}
}
