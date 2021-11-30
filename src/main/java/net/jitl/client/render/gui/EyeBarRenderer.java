package net.jitl.client.render.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.render.gui.BossBarRenderer;
import net.jitl.client.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class EyeBarRenderer extends BossBarRenderer {
    public EyeBarRenderer(LivingEntity boss, ResourceLocation texture) {
        super(boss, texture);
    }

    @Override
    public void render(RenderGameOverlayEvent.BossInfo event) {
        Minecraft minecraft = Minecraft.getInstance();
        MatrixStack stack = event.getMatrixStack();
        int x = event.getX();
        int y = event.getY();

        float health = boss.getHealth() / boss.getMaxHealth();
        double time = (Util.getNanos() - startTime) / 1000000000D;

        int timeWidth = (int) (182 * Math.min(time, 1));
        double healthWidth = boss.getHealth() / boss.getMaxHealth();
        float nonRed;

        if (health < 1F / 3F) {
            nonRed = 1 - 3 * health; //find health missing relative to a third of max
            nonRed = nonRed * ((float) Math.sin(time * 2) / 2 + 0.5F); //sin function for color
            nonRed = 1 - nonRed; //decrease all other colors by this amount

        } else {
            nonRed = 1.0F;
        }
        RenderSystem.color4f(1.0F, nonRed, nonRed, 1.0F);
        minecraft.getTextureManager().bind(texture);

        int v = Math.floor(time) % 2 == 0 ? 0 : 8;
        RenderUtils.blit(stack, 91 + x - timeWidth / 2, y, timeWidth, 9, 0, 16 + v, 182, 7, 182, 31);
        RenderUtils.blit(stack, 91 + x - timeWidth / 2, y, (int) (timeWidth * healthWidth), 9, 0, v, (int) (182 * healthWidth),  7, 182, 31);

        if (time > 1) {
            RenderUtils.drawCenteredString(stack, minecraft.font, boss.getName(), x + 91, y - 9, 255, (int) (255 * nonRed), (int) (255 * nonRed), (int) Math.min(255, (time - 1) * 255));
        }
    }
}
