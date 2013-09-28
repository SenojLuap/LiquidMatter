package paujo.liquidMatter.mod.fluids;

import paujo.liquidMatter.mod.blocks.BlockLiquidMatter;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class LiquidMatterFluids {

	public static Fluid fluidLiquidMatter;
	
	public static void initFluids() {
		fluidLiquidMatter = new Fluid("fluidLiquidMatter").setBlockID(BlockLiquidMatter.defaultID);
		
		FluidRegistry.registerFluid(fluidLiquidMatter);
	}
}
