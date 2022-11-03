package com.butterutil.features

class ItemHover {
    //ill work on this shit later this is so confusing
    /*@SubscribeEvent
    fun onContainerDraw(e: GuiScreenEvent.DrawScreenEvent) {
        var chest = e.gui.chest ?: return
        var items = chest?.lowerChestInventory?.items
        getLore(items as List<ItemStack>)
    }

    private fun getLore(items: List<ItemStack>) {
        var size = mc.thePlayer.openContainer.inventory.size
        var i = 0
        while(i < size) {
            val item = items[i]
                ?.lore
                ?.getOrNull(6)
                ?.stripControlCodes()

            UChat.chat(item)
            i++
        }
    }
    companion object {

        var Text: String = ""
    }*/
    companion object {
        val text: String
            get() {
                return text
            }

    }
}
