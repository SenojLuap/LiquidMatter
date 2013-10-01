package paujo.liquidMatter.mod.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import paujo.liquidMatter.mod.LiquidMatter;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;

public class ItemNeutron extends Item {
	
	public static final int DEFAULT_ID = 2001;
	
	public static final String TEXTURE = "spoonfulOfNeutron";

	public ItemNeutron(Configuration config) {
	  super(config.getItem("itemNeutron", DEFAULT_ID).getInt());
	  setUnlocalizedName("itemNutron");
		setCreativeTab(LiquidMatter.liquidMatterTab);
  }
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(LiquidMatter.modid + ":" + TEXTURE);
	}


}
