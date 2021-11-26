package net.jitl.common.entity.base;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.JITL;
import net.jitl.client.render.gui.BossBarRenderer;
import net.jitl.client.util.RenderUtils;
import net.jitl.common.helper.JMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.BossInfo;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public interface IJourneyBoss {
    BossBarRenderer getBossBar();

    JMusic getBossMusic();
}
