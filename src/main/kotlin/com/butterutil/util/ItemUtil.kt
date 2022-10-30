package com.butterutil.util

import com.butterutil.ButterUtils.Companion.mc
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.inventory.ContainerChest
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack

object ItemUtil {
    val IInventory.items: List<ItemStack?>
        get() = (0 until this.sizeInventory).map { this.getStackInSlot(it) }

    val GuiScreen.chest: ContainerChest?
        get() = (this as? GuiChest)?.inventorySlots as? ContainerChest
}