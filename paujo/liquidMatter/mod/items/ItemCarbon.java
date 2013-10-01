package paujo.liquidMatter.mod.items;

import paujo.liquidMatter.mod.LiquidMatter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;

public class ItemCarbon extends Item {
	
	public static final int DEFAULT_ID = 2002;
	
	public static final String TEXTURE = "hyperDenseCarbon";
	
	public ItemCarbon(Configuration config) {
		super(config.getItem("itemCarbon", DEFAULT_ID).getInt());
	  setUnlocalizedName("itemCarbon");
		setCreativeTab(LiquidMatter.liquidMatterTab);
  }
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(LiquidMatter.modid + ":" + TEXTURE);
	}


}
