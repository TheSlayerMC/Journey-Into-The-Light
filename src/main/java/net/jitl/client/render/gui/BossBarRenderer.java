package net.jitl.client.render.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class BossBarRenderer {
    private final LivingEntity boss;
    private final ResourceLocation texture;
    private final long startTime;

    public BossBarRenderer(LivingEntity boss, ResourceLocation texture) {
        this.boss = boss;
        this.texture = texture;
        startTime = Util.getNanos();
    }

    public void render(RenderGameOverlayEvent.BossInfo event) {
        Minecraft minecraft = Minecraft.getInstance();
        MatrixStack stack = event.getMatrixStack();
        int x = event.getX();
        int y = event.getY();

        int timeWidth = (int) (182 * Math.min((double) boss.tickCount / 20, 1));
        double healthWidth = boss.getHealth() / boss.getMaxHealth();

        //TODO: Custom blit method?
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bind(texture);
        RenderUtils.blit(stack, 91 + x - timeWidth / 2, y, timeWidth, 9, 0, 10, 182, 9, 182, 19);
        RenderUtils.blit(stack, 91 + x - timeWidth / 2, y, (int) (timeWidth * healthWidth), 9, 0, 0, (int) (182 * healthWidth),  9, 182, 19);
    }
}
