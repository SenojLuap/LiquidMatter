package paujo.liquidMatter.mod.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import paujo.liquidMatter.mod.tileentities.TileEntityCrucible;

public class ContainerCrucible extends Container {
	
	private static final int X_START_POS = 8;
	private static final int Y_START_POS_CRUCIBLE = 72;
	private static final int Y_START_POS_PLAYER_INVENTORY = 140;
	private static final int Y_START_POS_PLAYER_HOTBAR = 198;
	private static final int Y_BURN_SLOT_POS = 27;
	
	public TileEntityCrucible tileEntityCrucible;
	
	public ContainerCrucible(InventoryPlayer playerInventory, TileEntityCrucible tileEntityCrucible) {
		this.tileEntityCrucible = tileEntityCrucible;
		
		// Crucible inventory slots
		for (int ySlot = 0; ySlot < 3; ySlot++)
			for (int xSlot = 0; xSlot < 9; xSlot++) {
				addSlotToContainer(new Slot(tileEntityCrucible, xSlot + (ySlot * 9), X_START_POS + (xSlot * 18),
						Y_START_POS_CRUCIBLE + (ySlot * 18) ));
			}
		
		// Crucible burn slot
		addSlotToContainer(new Slot(tileEntityCrucible, tileEntityCrucible.BURN_SLOT, X_START_POS,
				Y_BURN_SLOT_POS));
		
		// Player hotbar slots
		for (int xSlot = 0; xSlot < 9; xSlot++)
			addSlotToContainer(new Slot(playerInventory, xSlot, X_START_POS + (xSlot * 18),
					Y_START_POS_PLAYER_HOTBAR));
		
		// Player inventory slots
		for (int ySlot = 0; ySlot < 3; ySlot++)
			for (int xSlot = 0; xSlot < 9; xSlot++)
				addSlotToContainer(new Slot(playerInventory, xSlot + ((ySlot + 1) * 9), X_START_POS + (xSlot * 18),
						Y_START_POS_PLAYER_INVENTORY + (ySlot * 18)));
	}	

	@Override
  public boolean canInteractWith(EntityPlayer entityplayer) {
	  return tileEntityCrucible.isUseableByPlayer(entityplayer);
  }

}
