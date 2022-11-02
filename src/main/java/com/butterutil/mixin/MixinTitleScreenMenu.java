package com.butterutil.mixin;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
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
        this.buttonList.add(new GuiButton(69420, this.width / 2, this.height / 2 + 100, "Play Hypixel"));

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 69420:
                System.out.println("works!");
                break;
            default:
                System.out.println(button.id);
                break;
        }
        super.actionPerformed(button);
    }
}
