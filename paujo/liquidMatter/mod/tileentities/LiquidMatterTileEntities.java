package paujo.liquidMatter.mod.tileentities;

import cpw.mods.fml.common.registry.GameRegistry;

public class LiquidMatterTileEntities {
	
	
	public static void initTileEntities() {
		GameRegistry.registerTileEntity(TileEntityAtomizer.class, "tileEntityCrucible");
		GameRegistry.registerTileEntity(TileEntitySolidarityEngine.class, "tileEntitySolidarityEngine");
	}

}
