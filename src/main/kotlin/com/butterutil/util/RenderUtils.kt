package com.butterutil.util

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.EnumChatFormatting
import org.lwjgl.opengl.GL11
import java.awt.Color
import java.lang.Math.sqrt
import kotlin.math.roundToInt

object RenderUtils {
    private val mc:Minecraft = Minecraft.getMinecraft()

    /**
     * Taken from NotEnoughUpdates under CCA-NC 3.0
     * Modified
     * https://github.com/Moulberry/NotEnoughUpdates/blob/master/LICENSE
     * @author Moulberry
     * //Butter
     * Renders waypoint in world with given string, x, y, z, and partialTicks.
     */
    fun renderWaypoint(string: String?, x: Double, y: Double, z: Double, partialTicks: Float, esp:Boolean, color: Color) {
        GlStateManager.alphaFunc(516, 0.1f)
        GlStateManager.pushMatrix()
        val viewer = mc.renderViewEntity
        var x = x - mc.renderManager.viewerPosX
        var y = y - mc.renderManager.viewerPosY - viewer.eyeHeight
        var z = z - mc.renderManager.viewerPosZ
        val distSq = x * x + y * y + z * z
        val dist = sqrt(distSq)
        if(distSq > 144) {
            x *= 12 / dist
            y *= 12 / dist
            z *= 12 / dist
        }
        if(esp) {
            GlStateManager.enableDepth()
        } else if(!esp) {
            GlStateManager.disableBlend()
        }
        GlStateManager.translate(x, y, z)
        GlStateManager.translate(0f, viewer.eyeHeight, 0f)
        drawNametag(string, color)
        GlStateManager.rotate(-mc.renderManager.playerViewY, 0.0f, 1.0f, 0.0f)
        GlStateManager.rotate(mc.renderManager.playerViewX, 1.0f, 0.0f, 0.0f)
        GlStateManager.translate(0f, -0.25f, 0f)
        GlStateManager.rotate(-mc.renderManager.playerViewX, 1.0f, 0.0f, 0.0f)
        GlStateManager.rotate(mc.renderManager.playerViewY, 0.0f, 1.0f, 0.0f)
        drawNametag(EnumChatFormatting.YELLOW.toString() + dist.roundToInt() + "m", color)
        GlStateManager.popMatrix()
        GlStateManager.disableLighting()
    }

    /**
     * @author Mojang
     * modified
     */
    private fun drawNametag(str: String?, color: Color) {
        val fontRenderer = mc.fontRendererObj
        val f1 = 0.02666667f
        GlStateManager.pushMatrix()
        GL11.glNormal3f(0.0f, 1.0f, 0.0f)
        GlStateManager.rotate(-mc.renderManager.playerViewY, 0.0f, 1.0f, 0.0f)
        GlStateManager.rotate(
            mc.renderManager.playerViewX,
            1.0f,
            0.0f,
            0.0f
        )
        GlStateManager.scale(-f1, -f1, f1)
        GlStateManager.disableLighting()
        GlStateManager.depthMask(false)
        GlStateManager.enableBlend()
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
        val tessellator = Tessellator.getInstance()
        val worldrenderer = tessellator.worldRenderer
        val i = 0
        val j = fontRenderer.getStringWidth(str) / 2
        GlStateManager.disableTexture2D()
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR)
        worldrenderer.pos((-j - 1).toDouble(), (-1 + i).toDouble(), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
        worldrenderer.pos((-j - 1).toDouble(), (8 + i).toDouble(), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
        worldrenderer.pos((j + 1).toDouble(), (8 + i).toDouble(), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
        worldrenderer.pos((j + 1).toDouble(), (-1 + i).toDouble(), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
        tessellator.draw()
        GlStateManager.enableTexture2D()
        fontRenderer.drawString(str, -j, i, 553648127)
        GlStateManager.depthMask(true)
        fontRenderer.drawString(str, -j, i, -1)
        GlStateManager.enableBlend()
        GlStateManager.color(color.red.toFloat(), color.green.toFloat(), color.blue.toFloat())
        GlStateManager.popMatrix()
    }
}