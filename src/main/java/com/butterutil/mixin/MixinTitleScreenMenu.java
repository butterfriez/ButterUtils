package com.butterutil.mixin;

import com.butterutil.config.Config;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.fml.client.FMLClientHandler;
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
    int y = 0;

    @Inject(method = "initGui", at = @At("RETURN"))
    private void initGui(final CallbackInfo cbi) {
        int y = this.height / 4 + 48 + 72 + 12; // final buttons @ bottom of MainMenu
        if(Config.INSTANCE.getHypixelButton()) { this.buttonList.add(new GuiButton(69, this.width / 2 - 100, y + 25, "Play Hypixel")); }

        super.initGui();
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
            if(button.id == 69)
                FMLClientHandler.instance().connectToServer(
                        new GuiMultiplayer(new GuiMainMenu()),
                        new ServerData("Server", "play.hypixel.net", false)
                );
    }

}