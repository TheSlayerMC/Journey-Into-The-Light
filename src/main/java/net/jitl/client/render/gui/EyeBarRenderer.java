package net.jitl.client.render.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.util.RenderUtils;
import net.jitl.common.entity.nether.SoulWatcherEntity;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class EyeBarRenderer extends BossBarRenderer {
    public EyeBarRenderer(SoulWatcherEntity boss, ResourceLocation texture) {
        super(boss, texture);
    }

    @Override
    public void render(RenderGameOverlayEvent.BossInfo event) {
        Minecraft minecraft = Minecraft.getInstance();
        PoseStack stack = event.getMatrixStack();
        int x = event.getX() + 91;
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
        minecraft.getTextureManager().bindForSetup(texture);

        /*stack.scale(2F, 2F, 2F);
        stack.translate(-100, 0, 0);*/
        RenderUtils.blit(stack, x - timeWidth / 2, y, timeWidth, 7, 7, 8, 182, 7, 196, 15);
        RenderUtils.blit(stack, x - timeWidth / 2, y, (int) (timeWidth * healthWidth), 7, 7, 0, (int) (182 * healthWidth),  7, 196, 15);
        if (((SoulWatcherEntity) boss).isClosed()) {
            RenderUtils.blit(stack, x - 91 + 4, y + 1, 6, 5, 0, 1, 6, 5, 196, 15); //render left eye
            RenderUtils.blit(stack, x - 91 + 172, y + 1, 6, 5, 190, 1, 6, 5, 196, 15); //render left eye
        }

        if (time > 1) {
            RenderUtils.drawCenteredString(stack, minecraft.font, boss.getName(), x, y - 9, 255, (int) (255 * nonRed), (int) (255 * nonRed), (int) Math.min(255, (time - 1) * 255));
        }
    }
}
