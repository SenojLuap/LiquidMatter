package paujo.liquidMatter.mod.tileentities;

import paujo.liquidMatter.mod.LiquidMatterConversionTable;
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
	
	public int burn;
	
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
		ItemStack out = new ItemStack(fromInventory.getItem(), 0, fromInventory.getItemDamage());
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
		if (!getWorldObj().isRemote) {
			if (!isBurning()) {
				int index = getBurnableItem();
				if (index != -1) {
					ItemStack itemStack = inventory[index];
					inventory[index] = (ItemStack)null;
					inventory[BURN_SLOT] = itemStack;
					burn += getBurnRate();
				} else burn = 0;
			} else {
				burn += getBurnRate();
				if (burn >= LiquidMatterConversionTable.getLiquidMatterValue(inventory[BURN_SLOT].getUnlocalizedName()))
					doBurn();
			}
		}
	}
	
	
	/*********************************************************
	 * Crucible
	 ********************************************************/
	
	
	/**
	 * Returns true if the crucible is currently burning something, false otherwise
	 * @return true if the crucible is currently burning something, false otherwise
	 */
	public boolean isBurning() {
		return inventory[BURN_SLOT] != null;
	}
	
	/**
	 * Returns the rate at which the crucible is currently generating 'burn'
	 * In the future, this will be modified by the upgrade slot
	 * @return the rate at which the crucible is currently generating 'burn'
	 */
	public int getBurnRate() {
		// TODO LM Implement update slot
		return 1;
	}
	
	/**
	 * Called when the crucible as generated enough 'burn' to reduce the item 
	 */
	public void doBurn() {
		inventory[BURN_SLOT].stackSize -= 1;
		// TODO LM Generate liquid matter. Requires BuildCraft API
		int lmGenerated = LiquidMatterConversionTable.getLiquidMatterValue(inventory[BURN_SLOT].getUnlocalizedName());
		System.out.println("Generated " + lmGenerated + " mB of Liquid Matter");
		if (inventory[BURN_SLOT].stackSize == 0)
			inventory[BURN_SLOT] = (ItemStack)null;
		burn -= lmGenerated;
	}
	
	/**
	 * Returns the index of the first burnable item in the inventory. Returns -1 if no items can be burned
	 * @return the index of the first burnable item in the inventory. Returns -1 if no items can be burned
	 */
	public int getBurnableItem() {
		for (int index = 0; index < BURN_SLOT; index++) {
			ItemStack itemStack = inventory[index];
			if (itemStack != null) System.out.println("Unlocalized name: " + itemStack.getUnlocalizedName());
			if (itemStack != null && LiquidMatterConversionTable.isBurnable(itemStack.getUnlocalizedName()))
				return index;
		}
		return -1;
	}
}
