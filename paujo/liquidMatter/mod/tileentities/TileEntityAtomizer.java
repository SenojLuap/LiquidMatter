package paujo.liquidMatter.mod.tileentities;

import java.util.HashMap;
import java.util.Map;

import buildcraft.core.TileBuffer;
import buildcraft.core.fluids.FluidUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import paujo.liquidMatter.mod.LiquidMatterConversionTable;
import paujo.liquidMatter.mod.fluids.LiquidMatterFluids;
import paujo.liquidMatter.mod.network.LiquidMatterPacketHandler;

public class TileEntityAtomizer extends TileEntity implements IInventory, IFluidHandler {
	
	public static final int INVENTORY_SIZE = 28;

	private ItemStack[] inventory = new ItemStack[INVENTORY_SIZE];
	
	public static final int BURN_SLOT = INVENTORY_SIZE - 1;
	
	private static final int[] availableSlots;
	
	public int burn;
	
	public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 10);
	
	private TileBuffer[] tileBuffer;
	
	// Static initialization
	static {
		availableSlots = new int[INVENTORY_SIZE - 1];
		for (int i = 0; i < INVENTORY_SIZE - 1; i++) availableSlots[i] = i;
	}
	
	@Override
  public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		tank.readFromNBT(compound);
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
		tank.writeToNBT(compound);
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
  public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		return slot != BURN_SLOT;
  }

	@Override
	public void updateEntity() {
		if (!getWorldObj().isRemote) {
			int burnBefore = burn;
			int tankBefore = tank.getFluidAmount();
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
				if (burn >= LiquidMatterConversionTable.getLiquidMatterValue(inventory[BURN_SLOT]))
					doBurn();
			}
			drainTank();
			if (burn != burnBefore)
				LiquidMatterPacketHandler.sendCrucibleBurnInfo(this);
			if (tank.getFluidAmount() != tankBefore)
				updateClientTank();
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
		return 5;
	}
	
	/**
	 * Called when the crucible as generated enough 'burn' to reduce the item 
	 */
	public void doBurn() {
		int lmGenerated = LiquidMatterConversionTable.getLiquidMatterValue(inventory[BURN_SLOT]);
		if (tank.getFluidAmount() + lmGenerated <= tank.getCapacity()) {
			inventory[BURN_SLOT].stackSize -= 1;
			tank.fill(new FluidStack(LiquidMatterFluids.fluidLiquidMatter, lmGenerated), true);
			drainTank();
			if (inventory[BURN_SLOT].stackSize == 0)
				inventory[BURN_SLOT] = (ItemStack)null;
			burn -= lmGenerated;
		} else burn = lmGenerated;
	}
	
	/**
	 * Returns the index of the first burnable item in the inventory. Returns -1 if no items can be burned
	 * @return the index of the first burnable item in the inventory. Returns -1 if no items can be burned
	 */
	public int getBurnableItem() {
		for (int index = 0; index < BURN_SLOT; index++) {
			ItemStack itemStack = inventory[index];
			if (itemStack != null) System.out.println("Unlocalized name: " + itemStack.getUnlocalizedName());
			if (itemStack != null && LiquidMatterConversionTable.isBurnable(itemStack))
				return index;
		}
		return -1;
	}
	
	/**
	 * Returns the progress in 'burning' the current item
	 * @return the progress in 'burning' the current item
	 */
	public float burnProgress() {
		if (isBurning()) {
			float res = (float)burn / (float)LiquidMatterConversionTable.getLiquidMatterValue(inventory[BURN_SLOT]);
			if (res > 1.0f) res = 1.0f;
			return res;
		}
		return 0.0f;
	}
	
	/**
	 * Will attempt to pump the contents of the tank into adjacent fluid handlers and pipes 
	 */
	public void drainTank() {
		if(tileBuffer == null)
			tileBuffer = TileBuffer.makeBuffer(worldObj, xCoord, yCoord, zCoord, false);
		FluidUtils.pushFluidToConsumers(tank, 400, tileBuffer);
	}
	
	/*********************************************************
	 * Fluid Handler
	 ********************************************************/

	@Override
  public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
	  return 0;
  }

	@Override
  public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource.isFluidEqual(tank.getFluid())) {
			FluidStack res = tank.drain(resource.amount, doDrain); 
			updateClientTank();
			return res;
		}
		return null;
  }

	@Override
  public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		FluidStack res = tank.drain(maxDrain, doDrain); 
		updateClientTank();
		return res;
  }

	@Override
  public boolean canFill(ForgeDirection from, Fluid fluid) {
	  return false;
  }

	@Override
  public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return tank.getFluidAmount() > 0 ? tank.getFluid().fluidID == fluid.getID() : false;
  }

	@Override
  public FluidTankInfo[] getTankInfo(ForgeDirection from) {
	  return new FluidTankInfo[] { tank.getInfo() };
  }
	
	
	/*********************************************************
	 * Network Handling
	 ********************************************************/
	
	
	public void setFluidLevel(int level) {
		if (tank.getFluidAmount() == 0)
			tank.fill(new FluidStack(LiquidMatterFluids.fluidLiquidMatter, level), true);
		else
			tank.getFluid().amount = level;
	}
	
	/**
	 * Sends the tank info to the client 
	 */
	public void updateClientTank() {
		LiquidMatterPacketHandler.sendCrucibleTankInfo(this);
	}

}
