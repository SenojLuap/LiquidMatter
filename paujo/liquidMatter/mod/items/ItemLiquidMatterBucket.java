package paujo.liquidMatter.mod.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import paujo.liquidMatter.mod.LiquidMatter;
import paujo.liquidMatter.mod.blocks.LiquidMatterBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLiquidMatterBucket extends ItemBucket {
	
	public static final int DEFAULT_ID = 2000;
	public static final String TEXTURE = "liquidMatterBucket";
	
	public ItemLiquidMatterBucket(Configuration config) {
		super(config.getItem("itemLiquidMatterBucket", DEFAULT_ID).getInt(),
				LiquidMatterBlocks.blockLiquidMatter.blockID);
		setUnlocalizedName("itemLiquidMatterBucket");
		setContainerItem(Item.bucketEmpty);
		setCreativeTab(LiquidMatter.liquidMatterTab);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(LiquidMatter.modid + ":" + TEXTURE);
	}
}
