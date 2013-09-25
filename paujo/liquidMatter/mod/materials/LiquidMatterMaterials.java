package paujo.liquidMatter.mod.materials;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;

public class LiquidMatterMaterials {

	public static Material liquidMatterMaterial;
	
	public static void initMaterials() {
		liquidMatterMaterial = new MaterialLiquid(MapColor.iceColor);
	}
}
