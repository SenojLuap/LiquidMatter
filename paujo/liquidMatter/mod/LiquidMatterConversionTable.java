package paujo.liquidMatter.mod;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class LiquidMatterConversionTable {
	
	public static void init() {
		Configuration config = new Configuration(new File("config/" + LiquidMatter.modid + "_ConversionValues.cfg"));
		config.load();
		
		
		
		config.save();
	}
}
