package paujo.liquidMatter.mod;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import paujo.liquidMatter.mod.blocks.LiquidMatterBlocks;
import paujo.liquidMatter.mod.fluids.LiquidMatterFluids;
import paujo.liquidMatter.mod.gui.LiquidMatterGuiHandler;
import paujo.liquidMatter.mod.network.PacketHandler;
import paujo.liquidMatter.mod.tileentities.LiquidMatterTileEntities;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = LiquidMatter.modid, name="Liquid Matter", version = "Alpha v0.1")
@NetworkMod(channels={"lm_comms"}, clientSideRequired=true, serverSideRequired=true, packetHandler=PacketHandler.class)
public class LiquidMatter {
	
	public static final String modid = "liquidMatter";

	@Instance(modid)
	public static LiquidMatter instance;
	
	public static CreativeTabs liquidMatterTab;
	
	@SidedProxy(clientSide="paujo.liquidMatter.mod.ClientProxy", serverSide="paujo.liquidMatter.mod.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		initCreativeTab();
		NetworkRegistry.instance().registerGuiHandler(instance, new LiquidMatterGuiHandler());
		
		Configuration config = new Configuration(new File("config/" + this.modid + ".cfg"));
		config.load();
		
		LiquidMatterConversionTable.init();
		LiquidMatterFluids.initFluids();
		LiquidMatterTileEntities.initTileEntities();
		LiquidMatterBlocks.initBlocks(config);
		
		proxy.initFluidTextures();
		
		config.save();
		MinecraftForge.EVENT_BUS.register(this);
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

	
	@ForgeSubscribe
	@SideOnly(Side.CLIENT)
	public void textureHook(TextureStitchEvent.Post event) {
		System.out.println("textureHook " + event.map.textureType);
		if (event.map.textureType == 0) {
			LiquidMatterFluids.fluidLiquidMatter.setIcons(LiquidMatterBlocks.blockLiquidMatter.getBlockTextureFromSide(1), LiquidMatterBlocks.blockLiquidMatter.getBlockTextureFromSide(2));
		}
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
