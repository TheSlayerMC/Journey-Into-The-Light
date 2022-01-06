package net.jitl.client.render.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import LivingEntity;
import ResourceLocation;

public class BossBarRenderer {
    protected final LivingEntity boss;
    protected final ResourceLocation texture;
    protected final long startTime;

    public BossBarRenderer(LivingEntity boss, ResourceLocation texture) {
        this.boss = boss;
        this.texture = texture;
        startTime = Util.getNanos();
    }

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
        minecraft.getTextureManager().bind(texture);

        RenderUtils.blit(stack, x - timeWidth / 2, y, timeWidth, 9, 0, 10, 182, 9, 182, 19);
        RenderUtils.blit(stack, x - timeWidth / 2, y, (int) (timeWidth * healthWidth), 9, 0, 0, (int) (182 * healthWidth),  9, 182, 19);

        if (time > 1) {
            RenderUtils.drawCenteredString(stack, minecraft.font, boss.getName(), x, y - 9, 255, (int) (255 * nonRed), (int) (255 * nonRed), (int) Math.min(255, (time - 1) * 255));
        }
    }
}
