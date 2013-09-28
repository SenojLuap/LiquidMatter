package paujo.liquidMatter.mod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import paujo.liquidMatter.mod.container.ContainerCrucible;
import paujo.liquidMatter.mod.tileentities.TileEntityCrucible;
import cpw.mods.fml.common.network.IGuiHandler;

public class LiquidMatterGuiHandler implements IGuiHandler {
	
	public static int guiIDCrucible = 0;

	@Override
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity != null) {
			if (tileEntity instanceof TileEntityCrucible && ID == guiIDCrucible)
				return new ContainerCrucible(player.inventory, (TileEntityCrucible)tileEntity);
		}
	  return null;
  }

	@Override
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity != null) {
			if (tileEntity instanceof TileEntityCrucible && ID == guiIDCrucible)
				return new GuiCrucible(player.inventory, (TileEntityCrucible)tileEntity);
		}
	  return null;
  }

}
