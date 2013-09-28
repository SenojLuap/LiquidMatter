package paujo.liquidMatter.mod.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCrucible extends TileEntity implements IInventory {
	
	public static final int INVENTORY_SIZE = 28;

	private ItemStack[] inventory = new ItemStack[INVENTORY_SIZE];
	
	public static final int BURN_SLOT = INVENTORY_SIZE - 1;
	
	private static final int[] availableSlots;
	
	// Static initialization
	static {
		availableSlots = new int[INVENTORY_SIZE - 1];
		for (int i = 0; i < INVENTORY_SIZE - 1; i++) availableSlots[i] = i;
	}
	
	@Override
  public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
  	for (int invIndex = 0; invIndex < inventory.length; invIndex++) {
  		String tag = "slot" + invIndex;
  		int stackSize = compound.getInteger(tag + "stackSize");
  		if (stackSize > 0)
  			inventory[invIndex] = new ItemStack(compound.getInteger(tag + "itemID"), stackSize, compound.getInteger(tag + "damage"));
  	}
  }
	
	@Override
  public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
  	for (int invIndex = 0; invIndex < inventory.length ; invIndex++) {
  		ItemStack itemStack = inventory[invIndex];
  		if (itemStack != null) {
  			String tag = "slot" + invIndex;
  			compound.setInteger(tag + "stackSize", itemStack.stackSize);
  			compound.setInteger(tag + "itemID", itemStack.itemID);
  			compound.setInteger(tag + "damage", itemStack.getItemDamage());
  		}
  	}
  }

	@Override
  public int getSizeInventory() {
	  return inventory.length;
  }

	@Override
  public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
  }

	@Override
  public ItemStack decrStackSize(int slot, int amount) {
		ItemStack fromInventory = inventory[slot];
		if (fromInventory == null) return null;
		ItemStack out = new ItemStack(fromInventory.getItem());
		if (amount > inventory[slot].stackSize) amount = fromInventory.stackSize;
		fromInventory.stackSize -= amount;
		out.stackSize = amount;
		if (fromInventory.stackSize == 0) inventory[slot] = null;
		return out;
  }

	@Override
  public ItemStack getStackInSlotOnClosing(int i) {
	  return null;
  }

	@Override
  public void setInventorySlotContents(int slot, ItemStack itemStack) {
		inventory[slot] = itemStack;
  }

	@Override
  public String getInvName() {
		return "Crucible";
  }

	@Override
  public boolean isInvNameLocalized() {
	  return false;
  }

	@Override
  public int getInventoryStackLimit() {
	  return 64;
  }

	@Override
  public boolean isUseableByPlayer(EntityPlayer entityplayer) {
	  return true;
  }

	@Override
  public void openChest() { }

	@Override
  public void closeChest() { }

	@Override
  public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		return slot != BURN_SLOT;
  }

	@Override
	public void updateEntity() {
		// TODO LM Create updateEntity() method in TileEntityCrucible
	}
}
