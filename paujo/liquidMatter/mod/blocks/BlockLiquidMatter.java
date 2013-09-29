package paujo.liquidMatter.mod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.fluids.BlockFluidClassic;
import paujo.liquidMatter.mod.LiquidMatter;
import paujo.liquidMatter.mod.fluids.LiquidMatterFluids;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLiquidMatter extends BlockFluidClassic {

	public static final int DEFAULT_ID = 1000;
	public static final String TEXTURE_STILL = "liquidMatter_still";
	public static final String TEXTURE_FLOWING = "liquidMatter_flowing";
	
	public Icon[] icons;

	public BlockLiquidMatter(Configuration config) {
		super(config.get("Blocks", "blockLiquidMatter", DEFAULT_ID).getInt(), LiquidMatterFluids.fluidLiquidMatter, Material.water);
	  setCreativeTab(LiquidMatter.liquidMatterTab);
	  setUnlocalizedName("blockLiquidMatter");
  }

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister ir) {
		icons = new Icon[]{ ir.registerIcon(LiquidMatter.modid + ":" + TEXTURE_STILL), ir.registerIcon(LiquidMatter.modid + ":" + TEXTURE_FLOWING) };
		blockIcon = icons[0];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		if (side == 0 || side == 1) return icons[0];
		return icons[1];
	}
}
