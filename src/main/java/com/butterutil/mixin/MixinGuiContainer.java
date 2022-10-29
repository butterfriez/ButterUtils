package com.butterutil.mixin;

import com.butterutil.config.Config;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.io.IOException;

@Mixin(GuiContainer.class)
public abstract class MixinGuiContainer extends GuiScreen {

    private GuiTextField textField1;
    public String text;

    @Inject(method = "initGui", at=@At("RETURN"))
    private void initGui(final CallbackInfo callbackInfo) {
        this.textField1 = new GuiTextField(0, fontRendererObj, 25, 25, 200, 10);

        this.textField1.setText(text);
    }

    @Inject(method = "drawScreen", at=@At("RETURN"))
    private void drawScreen(CallbackInfo ci) {
        if(Config.INSTANCE.getItemHover()) {
            fontRendererObj.drawStringWithShadow("Item Hover Over", 200, 10 + 25, Color.WHITE.getRGB());

            this.textField1.drawTextBox();
        }
    }

    /**
     * @author Butther
     */
    @Overwrite
    protected void keyTyped(char typedChar, int keyCode) {
        this.textField1.textboxKeyTyped(typedChar, keyCode);

        if(textField1.isFocused()) return;
        text += typedChar;
        System.out.println("GuiTextField drawn.");
    }
}