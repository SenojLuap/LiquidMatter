package paujo.liquidMatter.mod.gui;

import org.lwjgl.opengl.GL11;

import paujo.liquidMatter.mod.LiquidMatter;
import paujo.liquidMatter.mod.container.ContainerCrucible;
import paujo.liquidMatter.mod.tileentities.TileEntityCrucible;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiCrucible extends GuiContainer {
	
	public TileEntityCrucible tileEntityCrucible;
	
	public static final String textureGuiCrucible = "textures/gui/guiCrucible.png";

	public static final ResourceLocation textureRL = new ResourceLocation("liquidmatter", textureGuiCrucible);
	
	public GuiCrucible(InventoryPlayer inventoryPlayer, TileEntityCrucible tileEntityCrucible) {
	  super(new ContainerCrucible(inventoryPlayer, tileEntityCrucible));
	  this.tileEntityCrucible = tileEntityCrucible;
	  
	  this.xSize = 176;
	  this.ySize = 222;
  }
	
	@Override
	public void drawGuiContainerForegroundLayer(int x, int y) {
		String name = "Crucible";
		
//		fontRenderer.drawString(name, (xSize - fontRenderer.getStringWidth(name)) / 2, 6, 4210752);
		fontRenderer.drawString(name, (xSize - fontRenderer.getStringWidth(name)) / 2, 6, 16777215);
		fontRenderer.drawString(I18n.getString("container.inventory"), 8, ySize - 94, 16777215);
	}

	@Override
  public void drawGuiContainerBackgroundLayer(float f, int i, int j) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    this.mc.getTextureManager().bindTexture(textureRL);
    int xPos = (this.width - this.xSize) / 2;
    int yPos = (this.height - this.ySize) / 2;
    this.drawTexturedModalRect(xPos, yPos, 0, 0, this.xSize, this.ySize);
		
  }

}
