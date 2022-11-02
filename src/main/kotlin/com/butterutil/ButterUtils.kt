package com.butterutil

import com.butterutil.commands.MainCommand
import com.butterutil.config.Config
import com.butterutil.config.PersistentData
import com.butterutil.features.HypixelButton
import com.butterutil.features.ItemHover
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.ModMetadata
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import java.io.File

@Mod(
    modid = "butterutil",
    name = "Butter Utils",
    version = "1.0",
    useMetadata = true,
    clientSideOnly = true
)
class ButterUtils {

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        metadata = event.modMetadata
        val directory = File(event.modConfigurationDirectory, "butterutil")
        directory.mkdirs()
        configDirectory = directory
        persistentData = PersistentData.load()
    }

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent) {
        Config.init()
        ClientCommandHandler.instance.registerCommand(MainCommand())

        listOf(
            this,
            ItemHover(),
            HypixelButton(),
        ).forEach(MinecraftForge.EVENT_BUS::register)
    }


    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        if (event.phase != TickEvent.Phase.START || currentGui == null) return
        mc.displayGuiScreen(currentGui)
        currentGui = null
    }

    companion object {
        val mc: Minecraft = Minecraft.getMinecraft()
        var currentGui: GuiScreen? = null

        lateinit var configDirectory: File
        lateinit var persistentData: PersistentData

        lateinit var metadata: ModMetadata
    }
}