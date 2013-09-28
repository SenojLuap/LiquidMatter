package paujo.liquidMatter.mod.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCrucible extends Slot {

	public SlotCrucible(IInventory inventory, int slot, int xScreenPos, int yScreenPos) {
	  super(inventory, slot, xScreenPos, yScreenPos);
  }
	
	@Override
	public boolean isItemValid(ItemStack itemStack) {
		return false;
	}

}
