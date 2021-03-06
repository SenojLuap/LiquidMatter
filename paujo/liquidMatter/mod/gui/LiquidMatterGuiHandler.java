package paujo.liquidMatter.mod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import paujo.liquidMatter.mod.container.ContainerAtomizer;
import paujo.liquidMatter.mod.container.ContainerSolidarityEngine;
import paujo.liquidMatter.mod.tileentities.TileEntityAtomizer;
import paujo.liquidMatter.mod.tileentities.TileEntitySolidarityEngine;
import cpw.mods.fml.common.network.IGuiHandler;

public class LiquidMatterGuiHandler implements IGuiHandler {
	
	public static int guiIDCrucible = 0;
	public static int guiIDSolidarityEngine = 1;

	@Override
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity != null) {
			if (tileEntity instanceof TileEntityAtomizer && ID == guiIDCrucible)
				return new ContainerAtomizer(player.inventory, (TileEntityAtomizer)tileEntity);
			if (tileEntity instanceof TileEntitySolidarityEngine && ID == guiIDSolidarityEngine)
				return new ContainerSolidarityEngine(player.inventory, (TileEntitySolidarityEngine)tileEntity);
		}
	  return null;
  }

	@Override
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity != null) {
			if (tileEntity instanceof TileEntityAtomizer && ID == guiIDCrucible)
				return new GuiAtomizer(player.inventory, (TileEntityAtomizer)tileEntity);
			if (tileEntity instanceof TileEntitySolidarityEngine && ID == guiIDSolidarityEngine)
				return new GuiSolidarityEngine(player.inventory, (TileEntitySolidarityEngine)tileEntity);
		}
	  return null;
  }

}
