package net.jitl.client.render.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
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

        float health = boss.getHealth() / boss.getMaxHealth();
        double time = (Util.getNanos() - startTime) / 1000000000D;

        int timeWidth = (int) (182 * Math.min(time, 1));
        double healthWidth = boss.getHealth() / boss.getMaxHealth();

        if (health < 1F / 3F) {
            float nonRed = 1 - 3 * health; //find health missing relative to a third of max
            nonRed = nonRed * ((float) Math.sin(time * 2) / 2 + 0.5F); //sin function for color
            nonRed = 1 - nonRed; //decrease all other colors by this amount
            RenderSystem.color4f(1.0F, nonRed, nonRed, 1.0F);
        } else {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
        minecraft.getTextureManager().bind(texture);
        //TODO: Custom blit method?
        RenderUtils.blit(stack, 91 + x - timeWidth / 2, y, timeWidth, 9, 0, 10, 182, 9, 182, 19);
        RenderUtils.blit(stack, 91 + x - timeWidth / 2, y, (int) (timeWidth * healthWidth), 9, 0, 0, (int) (182 * healthWidth),  9, 182, 19);

        if (time > 1) {
            ITextComponent itextcomponent = boss.getName();
            int l = minecraft.font.width(itextcomponent);
            int i1 = minecraft.getWindow().getGuiScaledWidth() / 2 - l / 2;
            int j1 = y - 9;
            minecraft.font.drawShadow(stack, itextcomponent, (float)i1, (float)j1, 16777215);
        }
    }
}
