package paujo.liquidMatter.mod;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import paujo.liquidMatter.mod.blocks.LiquidMatterBlocks;
import paujo.liquidMatter.mod.fluids.LiquidMatterFluids;
import paujo.liquidMatter.mod.gui.LiquidMatterGuiHandler;
import paujo.liquidMatter.mod.items.LiquidMatterItems;
import paujo.liquidMatter.mod.network.PacketHandler;
import paujo.liquidMatter.mod.tileentities.LiquidMatterTileEntities;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
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
		LiquidMatterFluids.initFluids(config);
		LiquidMatterTileEntities.initTileEntities();
		LiquidMatterBlocks.initBlocks(config);
		
		LiquidMatterItems.initBuckets(config);
		
		LiquidMatterRecipes.initRecipes();
		
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

	@ForgeSubscribe
	public void onBucketFill(FillBucketEvent event) {
		MovingObjectPosition pos = event.target;
		int blockID = event.world.getBlockId(pos.blockX, pos.blockY, pos.blockZ);
		if (blockID == LiquidMatterBlocks.blockLiquidMatter.blockID) {
			event.world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
			event.result = new ItemStack(LiquidMatterItems.itemLiquidMatterBucket);
			event.setResult(Result.ALLOW);
		}
		return;
	}
	
}
