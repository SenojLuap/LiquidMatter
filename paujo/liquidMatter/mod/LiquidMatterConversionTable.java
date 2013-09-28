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
		for (Map.Entry<String, Property> entry : entries.entrySet()) {
			table.put(entry.getKey(), entry.getValue().getInt());
			System.out.println("Got entry: " + entry.getKey() + " : " + entry.getValue().getInt());
		}

		
		config.save();
	}
	
	
	/**
	 * Looks up the appropriate amount of LiquidMatter for the given item
	 * @param unlocalizedName The unlocalized name of the item/block to be converted
	 * @return The value of the item/block, measured in millibuckets. A return of 0 indicates no converstion
	 * is specified
	 */
	public static int getLiquidMatterValue(String unlocalizedName) {
		Integer value = (Integer)table.get(unlocalizedName);
		if (value == null)
			return 0;
		return value.intValue();
	}
	
	/**
	 * Returns true if the item can be reduced
	 * @param unlocalizedName The item to be reduced
	 * @return true if the item can be reduced, false otherwise
	 */
	public static boolean isBurnable(String unlocalizedName) {
		return getLiquidMatterValue(unlocalizedName) != 0;
	}
	
}
