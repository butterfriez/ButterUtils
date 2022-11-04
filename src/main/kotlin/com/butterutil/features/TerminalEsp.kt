package com.butterutil.features

import com.butterutil.config.Config
import com.butterutil.util.RenderUtils
import gg.essential.universal.UMinecraft
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class TerminalEsp {
    val checkStr:String = "[BOSS] Storm: At least my son died by your hands."

    @SubscribeEvent
    fun onChatReceivedMessage(event: ClientChatReceivedEvent) {

        when(event.message.unformattedText) {
            checkStr -> {
                if(Config.TerminalEsp)
                UMinecraft.getMinecraft()
            }
        }
    }
}