package paujo.liquidMatter.mod;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class LiquidMatterConversionTable {

	public static Hashtable<String, Integer> table;
	
	public static void init() {
		table = new Hashtable<String, Integer>(256);
		
		Configuration config = new Configuration(new File("config/" + LiquidMatter.modid + "_ConversionValues.cfg"));
		config.load();
		
		ConfigCategory cat = config.getCategory("Conversions");
		Map<String, Property> entries = cat.getValues();

		// TODO LM Add hard-coded default values?
		for (Map.Entry<String, Property> entry : entries.entrySet())
			table.put(entry.getKey(), entry.getValue().getInt());

		
		config.save();
	}

	
}
