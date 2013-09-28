package paujo.liquidMatter.mod.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
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
		addSlotToContainer(new SlotCrucible(tileEntityCrucible, tileEntityCrucible.BURN_SLOT, X_START_POS,
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

	@Override
  public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotIndex) {
		Slot slot = (Slot)inventorySlots.get(slotIndex);
		ItemStack itemStack = slot.getStack();
		
		if (itemStack == null) return null;
		
		ItemStack orig = itemStack.copy();
		
		if (slotIndex < TileEntityCrucible.INVENTORY_SIZE) {
			if (!mergeItemStack(itemStack, TileEntityCrucible.INVENTORY_SIZE, inventorySlots.size(), false))
					return null;
		}
		else
			if (!mergeItemStack(itemStack, 0, TileEntityCrucible.BURN_SLOT, false))
				return null;
		
		
		if (itemStack.stackSize == 0)
			slot.putStack((ItemStack)null);
		else
			slot.onSlotChanged();
		
		if (itemStack.stackSize == orig.stackSize)
			return null;
		return itemStack;
	}
		
}
