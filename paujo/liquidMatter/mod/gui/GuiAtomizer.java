package paujo.liquidMatter.mod.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import paujo.liquidMatter.mod.blocks.LiquidMatterBlocks;
import paujo.liquidMatter.mod.container.ContainerAtomizer;
import paujo.liquidMatter.mod.tileentities.TileEntityAtomizer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAtomizer extends GuiContainer {
	
	public TileEntityAtomizer tileEntityCrucible;
	
	public static final String textureGuiCrucible = "textures/gui/guiCrucible.png";

	public static final ResourceLocation textureRL = new ResourceLocation("liquidmatter", textureGuiCrucible);
	
	private static final int TANK_X_POS = 152;
	private static final int TANK_Y_POS = 7;
	private static final int TANK_HEIGHT = 58;
	
	private static final int PROGRESS_TEXTURE_X_POS = 176;
	private static final int PROGRESS_TEXTURE_Y_POS = 71;
	private static final int PROGRESS_GUI_X_POS = 127;
	private static final int PROGRESS_GUI_Y_POS = 48;
	private static final int PROGRESS_WIDTH = 24;
	private static final int PROGRESS_HEIGHT = 17;
	
	public GuiAtomizer(InventoryPlayer inventoryPlayer, TileEntityAtomizer tileEntityCrucible) {
	  super(new ContainerAtomizer(inventoryPlayer, tileEntityCrucible));
	  this.tileEntityCrucible = tileEntityCrucible;
	  
	  this.xSize = 176;
	  this.ySize = 222;
  }
	
	@Override
	public void drawGuiContainerForegroundLayer(int x, int y) {
		String name = "Atomizer";
		
		fontRenderer.drawString(name, (xSize - fontRenderer.getStringWidth(name)) / 2, 6, 4210752);
		fontRenderer.drawString(I18n.getString("container.inventory"), 8, ySize - 94, 4210752);
	}

	@Override
  public void drawGuiContainerBackgroundLayer(float f, int i, int j) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    this.mc.getTextureManager().bindTexture(textureRL);
    int xPos = (this.width - this.xSize) / 2;
    int yPos = (this.height - this.ySize) / 2;
    this.drawTexturedModalRect(xPos, yPos, 0, 0, this.xSize, this.ySize);
    drawTank(xPos, yPos);
    drawProgress(xPos, yPos);
  }

	public void drawTank(int xPos, int yPos) {
		FluidStack fluidStack = tileEntityCrucible.tank.getFluid();
		if (fluidStack != null && fluidStack.amount > 0) {
			Icon icon = fluidStack.getFluid().getStillIcon();
//			icon = LiquidMatterBlocks.blockCrucible.getIcon(0, 0);
			if (icon == null) {
				return;
			}
			mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			int height = MathHelper.floor_double( ( (double)tileEntityCrucible.tank.getFluidAmount() / (double)tileEntityCrucible.tank.getCapacity() ) * TANK_HEIGHT);
			
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
	
	public void drawProgress(int xPos, int yPos) {
		int width = MathHelper.floor_float((float)PROGRESS_WIDTH * tileEntityCrucible.burnProgress());
		drawTexturedModalRect(xPos + PROGRESS_GUI_X_POS, yPos + PROGRESS_GUI_Y_POS, PROGRESS_TEXTURE_X_POS, PROGRESS_TEXTURE_Y_POS, width, PROGRESS_HEIGHT);
	}
}
