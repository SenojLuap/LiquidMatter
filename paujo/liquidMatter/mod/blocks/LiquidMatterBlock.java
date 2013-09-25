package paujo.liquidMatter.mod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import paujo.liquidMatter.mod.LiquidMatter;
import paujo.liquidMatter.mod.fluids.LiquidMatterFluids;
import paujo.liquidMatter.mod.materials.LiquidMatterMaterials;
//import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraftforge.fluids.BlockFluidClassic;

public class LiquidMatterBlock extends BlockFluidClassic {

	public static final int defaultID = 1000;

	public LiquidMatterBlock() {
		super(defaultID, LiquidMatterFluids.liquidMatterFluid, LiquidMatterMaterials.liquidMatterMaterial);
	  setCreativeTab(LiquidMatter.liquidMatterTab);
	  setUnlocalizedName("liquidMatterBlock");
  }

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister ir) {
		blockIcon = ir.registerIcon(LiquidMatter.modid + ":" + getUnlocalizedName().substring(5));
	}
}
