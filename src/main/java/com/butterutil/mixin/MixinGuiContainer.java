package com.butterutil.mixin;

import com.butterutil.features.ItemHover;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(GuiContainer.class)
public abstract class MixinGuiContainer extends GuiScreen {

    private GuiTextField textField1;


    @Inject(method = "initGui", at=@At("RETURN"))
    private void initGui(final CallbackInfo callbackInfo) {
        this.textField1 = new GuiTextField(0, fontRendererObj, this.width / 2 - 100, height - 15, 200, 10);

        this.textField1.setText(ItemHover.Companion.getText());
        this.textField1.setFocused(false);
        this.textField1.setCanLoseFocus(true);
    }

    @Inject(method = "drawScreen", at=@At("RETURN"))
    private void drawScreen(CallbackInfo ci) {
        this.textField1.drawTextBox();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.textField1.mouseClicked(mouseX, mouseY, 0);

        this.textField1.isFocused();
    }

    /**
     * @author Butther
     */
    @Overwrite
    protected void keyTyped(char typedChar, int keyCode) throws IOException{
        this.textField1.textboxKeyTyped(typedChar, keyCode);

        //this took too long to figure out
        if(keyCode == Keyboard.KEY_ESCAPE || keyCode == Keyboard.KEY_E && !this.textField1.isFocused()) {
            this.textField1.setFocused(false);
            mc.thePlayer.closeScreen();
            this.textField1.setVisible(false);
        }
        if(textField1.isFocused()) return;
        ItemHover.Companion.setText(ItemHover.Companion.getText() + typedChar);
    }
}