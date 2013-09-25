package paujo.liquidMatter.mod;

import paujo.liquidMatter.mod.blocks.LiquidMatterBlocks;
import paujo.liquidMatter.mod.fluids.LiquidMatterFluids;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = LiquidMatter.modid, name="Liquid Matter", version = "Alpha v0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class LiquidMatter {
	
	public static final String modid = "liquidMatter";
	
	public static CreativeTabs liquidMatterTab;
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		initCreativeTab();
		LiquidMatterFluids.initFluids();
		LiquidMatterBlocks.initBlocks();
	}
	
	
	/*
	 * Initialize he creative mode tab
	 */
	private void initCreativeTab() {
	  liquidMatterTab = new CreativeTabs("liquidMatter") {
	  	@SideOnly(Side.CLIENT)
	  	public int getTabIconItemIndex() {
	  		// Update this once I have some items...
	  		return Item.bakedPotato.itemID;
	  	}
	  };
	  
	  LanguageRegistry.instance().addStringLocalization(liquidMatterTab.getTranslatedTabLabel(), "Liquid Matter");
  }

	
	/**
	 * Register a new item
	 * @param item The item to be added
	 * @param string The non-human readable name of the block
	 * @param name The human readable name of the block
	 */
	public void registerItem(Item item, String string, String name) {
		GameRegistry.registerItem(item, string);
		LanguageRegistry.addName(item, name);
	}
}
