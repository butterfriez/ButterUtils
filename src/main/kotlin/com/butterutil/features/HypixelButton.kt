/**
 * @author Dalwyn (Hypixel Button CTJS Module As Reference)
 */

package com.butterutil.features

import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiMainMenu

class HypixelButton : GuiMainMenu() {

    override fun initGui() {
        this.buttonList.add(GuiButton(69420, this.width /2 + 104, this.height /2 + 74, "Play Hypixel"))
        super.initGui()
    }


    override fun actionPerformed(button: GuiButton?) {
        if (button != null) {
            when(button.id) {
                69420 -> System.out.println("Works!")
            }
        }
        super.actionPerformed(button)
    }
}