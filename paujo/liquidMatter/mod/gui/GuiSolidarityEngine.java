package paujo.liquidMatter.mod.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import paujo.liquidMatter.mod.container.ContainerSolidarityEngine;
import paujo.liquidMatter.mod.fluids.LiquidMatterFluids;
import paujo.liquidMatter.mod.tileentities.TileEntitySolidarityEngine;

public class GuiSolidarityEngine extends GuiContainer {
	
	public TileEntitySolidarityEngine tileEntitySolidarityEngine;
	
	public static final String textureGuiSolidarityEngine = "textures/gui/guiSolidarityEngine.png";
	
	public static final ResourceLocation textureRL = new ResourceLocation("liquidmatter", textureGuiSolidarityEngine);

	private static final int TANK_X_POS = 152;
	private static final int TANK_Y_POS = 7;
	private static final int TANK_HEIGHT = 58;
	
	public GuiSolidarityEngine(InventoryPlayer inventoryPlayer, TileEntitySolidarityEngine tileEntitySolidarityEngine) {
		super(new ContainerSolidarityEngine(inventoryPlayer, tileEntitySolidarityEngine));
		this.tileEntitySolidarityEngine = tileEntitySolidarityEngine;
	  
	  this.xSize = 176;
	  this.ySize = 222;
  }

	@Override
	public void drawGuiContainerForegroundLayer(int x, int y) {
		String name = "Solidarity Engine";
		
		fontRenderer.drawString(name, (xSize - fontRenderer.getStringWidth(name)) / 2, 6, 4210752);
		fontRenderer.drawString(I18n.getString("container.inventory"), 8, ySize - 94, 4210752);
	}

	@Override
  protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(textureRL);
		int xPos = (this.width - this.xSize) / 2;
		int yPos = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(xPos, yPos, 0, 0, this.xSize, this.ySize);
		drawTank(xPos, yPos);
  }

	public void drawTank(int xPos, int yPos) {
//		FluidStack fluidStack = tileEntityCrucible.tank.getFluid();
//		if (fluidStack != null && fluidStack.amount > 0) {
		int tankAmt = tileEntitySolidarityEngine.tankAmount; 
		if ( tankAmt > 0) {
			Icon icon = LiquidMatterFluids.fluidLiquidMatter.getStillIcon();
//			Icon icon = fluidStack.getFluid().getStillIcon();
//			icon = LiquidMatterBlocks.blockCrucible.getIcon(0, 0);
			if (icon == null) {
				return;
			}
			mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			int height = MathHelper.floor_double( ( (double)tankAmt / (double)tileEntitySolidarityEngine.tankCapacity() ) * TANK_HEIGHT);
			
			int fromBottom = 0, drawAmt;
			
			while (fromBottom < height) {
				if (height - fromBottom >= 16) {
					drawAmt = 16;
				} else drawAmt = height - fromBottom;
				
				drawTexturedModelRectFromIcon(xPos + TANK_X_POS, yPos + TANK_Y_POS + TANK_HEIGHT - drawAmt - fromBottom, icon, 16, drawAmt);
				fromBottom += 16;
			}
		}
		mc.renderEngine.bindTexture(textureRL);
		drawTexturedModalRect(xPos + TANK_X_POS, yPos + TANK_Y_POS, 176, 7, 16, 58);
	}
	
}
