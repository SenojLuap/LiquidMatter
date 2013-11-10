package paujo.liquidMatter.mod.fluids;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import paujo.liquidMatter.mod.blocks.BlockLiquidMatter;
import paujo.liquidMatter.mod.blocks.LiquidMatterBlocks;

public class LiquidMatterFluids {

	public static Fluid fluidLiquidMatter;
	
	public static void initFluids(Configuration config) {
		fluidLiquidMatter = new Fluid("fluidLiquidMatter").setBlockID(config.get("Blocks", "blockLiquidMatter", BlockLiquidMatter.DEFAULT_ID).getInt());
		
		FluidRegistry.registerFluid(fluidLiquidMatter);
	}
	
}
