package paujo.liquidMatter.mod.fluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class LiquidMatterFluids {

	public static Fluid liquidMatterFluid;
	
	public static void initFluids() {
		liquidMatterFluid = new Fluid("liquidMatterFluid");
		
		FluidRegistry.registerFluid(liquidMatterFluid);
	}
}
