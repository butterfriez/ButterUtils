package com.butterutil.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.client.GuiModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

/**
 * @author Dalwyn (used his hypixelbutton ct module as idea.)
 */
@Mixin(GuiMainMenu.class)
public abstract class MixinTitleScreenMenu extends GuiScreen {
    @Inject(method = "initGui", at = @At("RETURN"))
    private void initGui(final CallbackInfo cbi) {
        this.buttonList.add(new GuiButton(69420, this.width / 2 - 100, this.height / 2 + 100, "Play Hypixel"));

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            //me
            case 69420:
                System.out.println("works!");
                break;
            /**
             * @author mojang
             */
            case 0:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 1:
                this.mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            case 2:
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 4:
                this.mc.shutdown();
                break;
            case 5:
                this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
                break;
            case 6:
                this.mc.displayGuiScreen(new GuiModList(this));
                break;
            case 11:
                this.mc.launchIntegratedServer("Demo_World", "Demo_World", DemoWorldServer.demoWorldSettings);
                break;
            case 12:
                ISaveFormat isaveformat = this.mc.getSaveLoader();
                WorldInfo worldinfo = isaveformat.getWorldInfo("Demo_World");
                if (worldinfo != null) {
                    GuiYesNo guiyesno = GuiSelectWorld.makeDeleteWorldYesNo(this, worldinfo.getWorldName(), 12);
                    this.mc.displayGuiScreen(guiyesno);
                }
                break;
            case 14:
                this.switchToRealms();
                break;
            default:
                System.out.println(button.id + " " + button.displayString);
                break;
        }
        super.actionPerformed(button);
    }

    private void switchToRealms() {
        RealmsBridge realmsbridge = new RealmsBridge();
        realmsbridge.switchToRealms(this);
    }
}
