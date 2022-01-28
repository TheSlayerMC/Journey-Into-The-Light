package net.jitl.client.render.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.JToast;
import net.jitl.common.helper.ArgbColor;
import net.jitl.common.helper.EnumKnowledgeType;
import net.jitl.core.JITL;
import net.jitl.core.init.JSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;

import java.util.List;

public class KnowledgeToast implements JToast {

    private final EnumKnowledgeType knowledge;
    private boolean playedSound;
    private final boolean isLevel;
    private final ResourceLocation KNOWLEDGE_SPRITE = JITL.rl("textures/gui/knowledge/knowledge_sprites.png");


    public KnowledgeToast(EnumKnowledgeType knowledge, boolean isLevel) {
        this.knowledge = knowledge;
        this.isLevel = isLevel;
    }

    @Override
    public JToast.Visibility render(PoseStack poseStack, ToastComponent toastComponent, long timeSinceLastVisible) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        JDisplayInfo displayinfo = isLevel ? this.knowledge.getLevelDisplay() : this.knowledge.getXPDisplay();
        toastComponent.blit(poseStack, 0, 0, 0, 0, this.width(), this.height());
        if(displayinfo != null) {
            List<FormattedCharSequence> list = toastComponent.getMinecraft().font.split(displayinfo.getDescription(), 125);
            int i = displayinfo.getFrame() == JFrameType.LEVEL ? ArgbColor.from(ChatFormatting.DARK_PURPLE) : ArgbColor.from(ChatFormatting.BLACK);
            if(list.size() == 1) {
                toastComponent.getMinecraft().font.draw(poseStack, displayinfo.getFrame().getDisplayName(), 30.0F, 18.0F, i);//Level or XP
                toastComponent.getMinecraft().font.draw(poseStack, list.get(0), 30.0F, 7.0F, ArgbColor.from(ChatFormatting.BLACK));// Knowledge name
            } else {
                if(timeSinceLastVisible < 1500L) {
                    int k = Mth.floor(Mth.clamp((float)(1500L - timeSinceLastVisible) / 300.0F, 0.0F, 1.0F) * 255.0F) << 24 | 67108864;
                    toastComponent.getMinecraft().font.draw(poseStack, displayinfo.getFrame().getDisplayName(), 30.0F, 11.0F, i | k);
                } else {
                    int i1 = Mth.floor(Mth.clamp((float)(timeSinceLastVisible - 1500L) / 300.0F, 0.0F, 1.0F) * 252.0F) << 24 | 67108864;
                    int l = this.height() / 2 - list.size() * 9 / 2;

                    for(FormattedCharSequence formattedcharsequence : list) {
                        toastComponent.getMinecraft().font.draw(poseStack, formattedcharsequence, 30.0F, (float)l, 16777215 | i1);
                        l += 9;
                    }
                }
            }
            if(!this.playedSound && timeSinceLastVisible > 0L) {
                this.playedSound = true;
                if(displayinfo.getFrame() == JFrameType.LEVEL) {
                    toastComponent.getMinecraft().getSoundManager().play(SimpleSoundInstance.forUI(JSounds.TOAST_SPECIAL.get(), 1.0F, 1.0F));
                }
                if(displayinfo.getFrame() == JFrameType.XP) {
                    toastComponent.getMinecraft().getSoundManager().play(SimpleSoundInstance.forUI(JSounds.TOAST.get(), 1.0F, 1.0F));
                }
            }
            //toastComponent.getMinecraft().getItemRenderer().renderAndDecorateFakeItem(displayinfo.getIcon(), 8, 8);
            poseStack.pushPose();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, KNOWLEDGE_SPRITE);
            toastComponent.blit(poseStack, 8, 8, knowledge.getSpriteX(), knowledge.getSpriteY(), 32, 32);
            poseStack.popPose();
            return timeSinceLastVisible >= 5000L ? JToast.Visibility.HIDE : JToast.Visibility.SHOW;
        } else {
            return JToast.Visibility.HIDE;
        }
    }
}