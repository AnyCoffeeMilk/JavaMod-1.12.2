package com.mods.reaper.gui.container;

import com.mods.reaper.gui.ScytheUpgrade;
import com.mods.reaper.items.ReaperScythe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerScytheUpgrade extends Container 
{
	private ReaperScythe su;
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !player.isSpectator();
	}
	
	public int getSizeInventory()
    {
        return 1;
    }
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);
	
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
	
			int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();
	
			if (index < containerSlots) {
				if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
				return ItemStack.EMPTY;
			}
	
			if (itemstack1.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
	
			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
	
			slot.onTake(player, itemstack1);
		}
	
		return itemstack;
	}
	
	public ContainerScytheUpgrade(IInventory playerInv, ReaperScythe su) {
		this.su = su;
		IItemHandler handler = su.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 27, 12));
	
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
	
		for (int k = 0; k < 9; k++) {
			addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
		}
	}
}
