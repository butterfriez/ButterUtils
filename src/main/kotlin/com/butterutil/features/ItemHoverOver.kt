package com.butterutil.features

import ButterUtils.Companion
import com.butterutil.config.Config
import net.minecraft.client.gui.GuiTextField
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

class ItemHoverOver {
    var text: String? = null;

    private var textField: GuiTextField = GuiTextField(1, Companion.mc.fontRendererObj, 25, 25, 25, 25)

    @SubscribeEvent
    fun guiOpen(e: GuiOpenEvent) {
        if(Config.itemHover) {
            drawTextField()
        }
    }
    @SubscribeEvent
    fun onTick(e: TickEvent) {
        text = textField.text.toString()
    }

    private fun drawTextField() {
        var x = Companion.mc.displayWidth / 3.5
        var y = Companion.mc.displayHeight / 2
        textField.xPosition = x.toInt()
        textField.yPosition = y
        textField.visible = true
        textField.drawTextBox()
    }
}