package paujo.liquidMatter.mod.fluids;

import paujo.liquidMatter.mod.blocks.BlockLiquidMatter;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class LiquidMatterFluids {

	public static Fluid liquidMatterFluid;
	
	public static void initFluids() {
		liquidMatterFluid = new Fluid("liquidMatterFluid").setBlockID(BlockLiquidMatter.defaultID);
		
		FluidRegistry.registerFluid(liquidMatterFluid);
	}
}
