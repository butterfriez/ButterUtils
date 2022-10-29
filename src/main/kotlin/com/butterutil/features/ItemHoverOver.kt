package com.butterutil.features

import com.butterutil.config.Config
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.GuiTextField

class ItemHoverOver : GuiScreen() {
    var text:String = ""
    private var textField : GuiTextField? = null

    override fun initGui() {
        super.initGui()

        this.textField = GuiTextField(0, mc.fontRendererObj, 25, 25, 25, 25)
        this.textField!!.setTextColor(-1);
        this.textField!!.setDisabledTextColour(-1);
        this.textField!!.enableBackgroundDrawing = false;
        this.textField!!.maxStringLength = 50;
        this.textField!!.setEnabled(true);
        this.textField!!.text = text;
        this.textField!!.isFocused = false;
        this.textField!!.setCursorPositionEnd();
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        super.drawScreen(mouseX, mouseY, partialTicks)
        if(Config.itemHover) return
        this.textField!!.drawTextBox()
    }
    override fun keyTyped(typedChar: Char, keyCode: Int) {
        super.keyTyped(typedChar, keyCode)

        text += typedChar
    }
}