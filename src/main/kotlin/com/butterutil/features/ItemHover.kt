package com.butterutil.features

import com.butterutil.util.ItemUtil.chest
import com.butterutil.util.ItemUtil.items
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.item.ItemStack
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ItemHover {
    @SubscribeEvent
    fun onContainerDraw(Event: GuiOpenEvent) {

    }

    private fun getLore(items: List<ItemStack>) {
        items.forEach()
    }
    companion object {

        var Text: String = ""
    }
}
