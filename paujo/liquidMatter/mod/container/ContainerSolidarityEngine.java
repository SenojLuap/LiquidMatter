package paujo.liquidMatter.mod.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import paujo.liquidMatter.mod.LiquidMatterConversionTable;
import paujo.liquidMatter.mod.tileentities.TileEntityAtomizer;
import paujo.liquidMatter.mod.tileentities.TileEntitySolidarityEngine;
import buildcraft.core.gui.slots.SlotPhantom;

public class ContainerSolidarityEngine extends Container {

	private static final int X_START_POS = 8;
	private static final int Y_START_POS_ENGINE_INVENTORY= 72;
	private static final int Y_START_POS_PLAYER_INVENTORY = 140;
	private static final int Y_START_POS_PLAYER_HOTBAR = 198;
	private static final int Y_DUP_SLOT_POS = 10;
	private static final int Y_UPGRADE_SLOT_POS = 44;
	
	private class DupSlot extends Slot {

		/*
		 * Slot for the duped item. Shouldn't actually take an item from the player
		 */
		public DupSlot(IInventory inventory, int slot, int xScreenPos,
        int yScreenPos) {
	    super(inventory, slot, xScreenPos, yScreenPos);
    }
		
		@Override
		public ItemStack decrStackSize(int amt) {
			super.decrStackSize(amt);
			return null;
		}
		
		@Override
		public int getSlotStackLimit() { return 0; }
		
		
		@Override
		public boolean isItemValid(ItemStack itemStack) {
			return LiquidMatterConversionTable.isBurnable(itemStack);
		}
	}
	
	/*
	 * Slot for the capacity upgrades. Currently unimplemented
	 */
	private class UpgradeSlot extends Slot {

		public UpgradeSlot(IInventory inventory, int slot, int xScreenPos,
        int yScreenPos) {
	    super(inventory, slot, xScreenPos, yScreenPos);
    }
		
		@Override
		public boolean isItemValid(ItemStack itemStack) {
			return false;
		}
		
	}
	
	public TileEntitySolidarityEngine tileEntitySolidarityEngine; 

	public ContainerSolidarityEngine(InventoryPlayer playerInventory, 
			TileEntitySolidarityEngine tileEntitySolidarityEngine) {
		this.tileEntitySolidarityEngine = tileEntitySolidarityEngine;
		
		// Solidarity Engine inventory slot
		for (int ySlot = 0; ySlot < 3; ySlot++)
			for (int xSlot = 0; xSlot < 9; xSlot++) {
				addSlotToContainer(new Slot(tileEntitySolidarityEngine, xSlot + (ySlot * 9), X_START_POS + (xSlot * 18),
						Y_START_POS_ENGINE_INVENTORY + (ySlot * 18)));
			}
		
		// Solidarity Engine dupe slot
		addSlotToContainer(new DupSlot(tileEntitySolidarityEngine, tileEntitySolidarityEngine.DUP_SLOT, X_START_POS,
				Y_DUP_SLOT_POS));
		
		// Solidarity Engine upgrade slot
		addSlotToContainer(new UpgradeSlot(tileEntitySolidarityEngine, tileEntitySolidarityEngine.UPGRADE_SLOT, X_START_POS,
				Y_UPGRADE_SLOT_POS));
		
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
	  return tileEntitySolidarityEngine.isUseableByPlayer(entityplayer);
  }
	
	

	@Override
  public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotIndex) {
		Slot slot = (Slot)inventorySlots.get(slotIndex);
		ItemStack itemStack = slot.getStack();
		
		if (itemStack == null) return null;
		
		ItemStack orig = itemStack.copy();
		
		if (slotIndex < TileEntitySolidarityEngine.INVENTORY_SIZE) {
			if (!mergeItemStack(itemStack, TileEntitySolidarityEngine.INVENTORY_SIZE, inventorySlots.size(), false))
					return null;
		}
		else
			if (!mergeItemStack(itemStack, 0, TileEntitySolidarityEngine.UPGRADE_SLOT, false))
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
