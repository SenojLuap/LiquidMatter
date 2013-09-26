package paujo.liquidMatter.mod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import paujo.liquidMatter.mod.LiquidMatter;
import paujo.liquidMatter.mod.fluids.LiquidMatterFluids;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockLiquidMatter extends BlockFluidClassic {

	public static final int defaultID = 1000;
	public static final String texture = "liquidMatter";

	public BlockLiquidMatter() {
		super(defaultID, LiquidMatterFluids.liquidMatterFluid, Material.water);
	  setCreativeTab(LiquidMatter.liquidMatterTab);
	  setUnlocalizedName("liquidMatterBlock");
  }

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister ir) {
		blockIcon = ir.registerIcon(LiquidMatter.modid + ":" + texture);
	}
}
