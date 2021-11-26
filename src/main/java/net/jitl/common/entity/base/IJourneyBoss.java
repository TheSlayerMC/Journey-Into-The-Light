package net.jitl.common.entity.base;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.JITL;
import net.jitl.client.util.RenderUtils;
import net.jitl.common.helper.JMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.BossInfo;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public interface IJourneyBoss {
    ResourceLocation getBarTexture();

    default void renderBossBar(RenderGameOverlayEvent.BossInfo event) {
        Minecraft minecraft = Minecraft.getInstance();
        LivingEntity entity = (LivingEntity) this;
        MatrixStack stack = event.getMatrixStack();
        int x = event.getX();
        int y = event.getY();

        int timeWidth = (int) (182 * Math.min((double) entity.tickCount / 20, 1));
        double healthWidth = entity.getHealth() / entity.getMaxHealth();

        //TODO: Custom blit method?
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bind(getBarTexture());
        RenderUtils.blit(stack, 91 + x - timeWidth / 2, y, timeWidth, 9, 0, 10, 182, 9, 182, 19);
        RenderUtils.blit(stack, 91 + x - timeWidth / 2, y, (int) (timeWidth * healthWidth), 9, 0, 0, (int) (182 * healthWidth),  9, 182, 19);
    }

    JMusic getBossMusic();
}
