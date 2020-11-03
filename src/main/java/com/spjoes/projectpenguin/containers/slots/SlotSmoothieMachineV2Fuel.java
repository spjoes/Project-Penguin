package com.spjoes.projectpenguin.containers.slots;

import com.spjoes.projectpenguin.tileentities.TileEntitySmoothieMachineV2;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSmoothieMachineV2Fuel extends Slot {

    public SlotSmoothieMachineV2Fuel(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return TileEntitySmoothieMachineV2.isItemFuel(stack);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return super.getItemStackLimit(stack);
    }
}
