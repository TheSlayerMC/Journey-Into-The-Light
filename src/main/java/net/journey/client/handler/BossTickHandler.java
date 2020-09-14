package net.journey.client.handler;

import net.journey.util.JourneyBossStatus;
import net.journey.util.gui.TextRenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import org.lwjgl.opengl.GL11;

public class BossTickHandler {

    private final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRender(RenderTickEvent event) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        this.mc.profiler.startSection("essenceBossHealth");
        this.renderBossHealth();
        this.mc.profiler.endSection();
        GL11.glDisable(GL11.GL_BLEND);
    }

    private void renderBossHealth() {
        if (JourneyBossStatus.bar == null) return;
        ResourceLocation r = set(JourneyBossStatus.bar);

        if (JourneyBossStatus.statusBarTime > 0 && mc.currentScreen == null && !(mc.gameSettings.hideGUI)) {
            JourneyBossStatus.statusBarTime--;
            GuiIngame gig = mc.ingameGUI;
            FontRenderer fontrenderer = this.mc.fontRenderer;
            ScaledResolution scaledresolution = new ScaledResolution(mc);
            int i = scaledresolution.getScaledWidth();
            int barLength = 182;
            int finalBarLength = barLength + 1;
            int barDisX = i / 2 - barLength / 2;
            int barDisY = 6;
            int health = (int) (JourneyBossStatus.healthScale * (finalBarLength));
            int barHeight = 10;
            this.mc.getTextureManager().bindTexture(r);
            gig.drawTexturedModalRect(barDisX, barDisY, 0, 0, health, barHeight);
            gig.drawTexturedModalRect(barDisX, barDisY, 0, barHeight, finalBarLength, barHeight);
            if (health > 0) gig.drawTexturedModalRect(barDisX, barDisY, 0, 0, health, 10);

            String glitchedText;
            if (health <= health % 33) {
                glitchedText = TextFormatting.OBFUSCATED.toString();
            } else {
                glitchedText = "";
            }

            TextRenderUtils textRenderUtils = new TextRenderUtils();

            if (JourneyBossStatus.bossName != null) {
                textRenderUtils.drawBoldCenteredString(fontrenderer, barDisX + 92, barDisY, glitchedText + I18n.format("journey.boss.name." + JourneyBossStatus.bossName), JourneyBossStatus.stringTextColor);
            }
        }
    }

    public void drawCenteredString(FontRenderer fontRenderer, String text, int x, int y, int color) {
        fontRenderer.drawStringWithShadow(text, (float) (x - fontRenderer.getStringWidth(text) / 2), (float) y, color);
    }

    private ResourceLocation set(String name) {
        return new ResourceLocation(SlayerAPI.PREFIX + "textures/gui/bossBars/" + name + ".png");
    }
}