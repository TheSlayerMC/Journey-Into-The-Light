package net.jitl.core.mixins.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import net.jitl.client.isometric.IsometricCameraHandler;
import net.jitl.core.config.JClientConfig;
import net.jitl.core.config.JConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Inject(method = "getProjectionMatrix", at = @At("HEAD"), cancellable = true)
    private void getProjectionMatrix(double d, CallbackInfoReturnable<Matrix4f> cir) {
        Minecraft minecraft = Minecraft.getInstance();
        JClientConfig clientConfig = JConfigs.CLIENT;

        if (clientConfig.guiCategory.isIsometricFOVEnabled()) {
            PoseStack posestack = new PoseStack();
            posestack.last().pose().setIdentity();

            posestack.last().pose().multiply(IsometricCameraHandler.getIsometricProjectionMatrix(minecraft));

            cir.setReturnValue(posestack.last().pose());
        }
    }
}
