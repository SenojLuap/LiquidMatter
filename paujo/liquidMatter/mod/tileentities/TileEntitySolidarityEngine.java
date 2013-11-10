package paujo.liquidMatter.mod.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import paujo.liquidMatter.mod.LiquidMatterConversionTable;
import paujo.liquidMatter.mod.fluids.LiquidMatterFluids;
import paujo.liquidMatter.mod.network.LiquidMatterPacketHandler;

public class TileEntitySolidarityEngine extends TileEntity implements IFluidHandler, IInventory {

	public final static int INVENTORY_SIZE = 29;
	public final static int DUP_SLOT = INVENTORY_SIZE-1;
	public final static int UPGRADE_SLOT = INVENTORY_SIZE-2;
	
	public int tankAmount;
	
	public ItemStack[] inventory;
	
	public TileEntitySolidarityEngine() {
		tankAmount = 0;
		inventory = new ItemStack[INVENTORY_SIZE];
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		tankAmount = nbt.getInteger("tankAmount");
		for (int index = 0; index < INVENTORY_SIZE; index++) {
			String tag = "inventory.slot" + index + ".";
			int stackSize = nbt.getInteger(tag + "stackSize");
			if (stackSize > 0)
				inventory[index] = new ItemStack(nbt.getInteger(tag + "itemID"), stackSize, nbt.getInteger(tag + "damage"));
		}
	}
	
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setInteger("tankAmount", tankAmount);
		for (int index = 0; index < INVENTORY_SIZE; index++) {
			String tag = "inventory.slot" + index + ".";
			if (inventory[index] != null) {
				nbt.setInteger(tag + "stackSize", inventory[index].stackSize);
				nbt.setInteger(tag + "itemID", inventory[index].itemID);
				nbt.setInteger(tag + "damage", inventory[index].getItemDamage());
			} else nbt.setInteger(tag + "stackSize", 0);
		}
	}
	
	
	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (inventory[DUP_SLOT] != null) {
				int index = findValidOutputSlot();
				int lmv = LiquidMatterConversionTable.getLiquidMatterValue(inventory[DUP_SLOT]);
				if (lmv > 0 && index > -1 && tankAmount >= lmv) {
					tankAmount -= lmv;
					if (inventory[index] != null)
						inventory[index].stackSize++;
					else
						inventory[index] = new ItemStack(inventory[DUP_SLOT].itemID, 1, inventory[DUP_SLOT].getItemDamage());
					LiquidMatterPacketHandler.sendSolidarityEngineTankInfo(this);
				}
			}
		}
	}

	
	public int findValidOutputSlot() {
		// First, check for an ItemStack that the output can stack with
		for (int index = 0; index < UPGRADE_SLOT; index++)
			if (inventory[index] != null)
				if (inventory[index].itemID == inventory[DUP_SLOT].itemID &&
					inventory[index].getItemDamage() == inventory[DUP_SLOT].getItemDamage() &&
					inventory[index].stackSize < inventory[index].getMaxStackSize())
					return index;
		
//		 Next, check for a free slot
		for (int index = 0; index < UPGRADE_SLOT; index++)
			if (inventory[index] == null) return index;
		
		// Finally, give up
		return -1;
	}
	
	/**************************************************************
	 * Fluid
	 **************************************************************/
	
	
	public int tankCapacity() {
		return FluidContainerRegistry.BUCKET_VOLUME * 10;
	}
	
	
	@Override
  public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (resource.fluidID == LiquidMatterFluids.fluidLiquidMatter.getID()) {
			int fill = Math.min(resource.amount, tankCapacity() - tankAmount);
			if (doFill) {
				tankAmount += fill;
				LiquidMatterPacketHandler.sendSolidarityEngineTankInfo(this);
			}
			return fill;
		}
	  return 0;
  }

	
	@Override
  public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource.fluidID == LiquidMatterFluids.fluidLiquidMatter.getID()) {
			FluidStack result = new FluidStack(LiquidMatterFluids.fluidLiquidMatter, Math.min(tankAmount, resource.amount));
			if (doDrain) tankAmount -= result.amount;
			return result;
		}
		return null;
  }

	
	@Override
  public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		FluidStack result = new FluidStack(LiquidMatterFluids.fluidLiquidMatter, Math.min(tankAmount, maxDrain));
		if (doDrain) tankAmount -= result.amount;
		return result;
  }

	
	@Override
  public boolean canFill(ForgeDirection from, Fluid fluid) {
		return fluid.getID() == LiquidMatterFluids.fluidLiquidMatter.getID();
  }

	
	@Override
  public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return fluid.getID() == LiquidMatterFluids.fluidLiquidMatter.getID();
  }

	
	@Override
  public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		FluidTankInfo[] result = new FluidTankInfo[1];
		result[0] = new FluidTankInfo(new FluidStack(LiquidMatterFluids.fluidLiquidMatter, tankAmount),
				tankCapacity());
		return result;
  }

	
	/**************************************************************
	 * Inventory
	 **************************************************************/
	
	
	@Override
  public int getSizeInventory() {
	  return INVENTORY_SIZE;
  }


	@Override
  public ItemStack getStackInSlot(int index) {
		return inventory[index];
  }


	@Override
  public ItemStack decrStackSize(int index, int amount) {
		ItemStack result = inventory[index].copy();
		result.stackSize = Math.min(amount, result.stackSize);
		inventory[index].stackSize -= result.stackSize;
		if (inventory[index].stackSize == 0) inventory[index] = null;
		return result;
  }


	@Override
  public ItemStack getStackInSlotOnClosing(int index) {
	  return null;
  }


	@Override
  public void setInventorySlotContents(int index, ItemStack itemStack) {
		inventory[index] = itemStack;
  }


	@Override
  public String getInvName() {
		return "Solidarity Engine";
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
  public boolean isItemValidForSlot(int index, ItemStack itemStack) {
		if (index == DUP_SLOT && LiquidMatterConversionTable.getLiquidMatterValue(itemStack) > 0)
			return true;
		return false;
  }
	

}
