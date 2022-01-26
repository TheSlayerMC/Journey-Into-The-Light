package net.jitl.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.core.JITL;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface JToast extends Toast {

    ResourceLocation TEXTURE = JITL.rl("textures/gui/toasts.png");
    Object NO_TOKEN = new Object();

    @Override
    default Object getToken() {
        return NO_TOKEN;
    }

    @Override
    default int width() {
        return 160;
    }

    @Override
    default int height() {
        return 32;
    }

    JToast.Visibility render(PoseStack poseStack_, ToastComponent toastComponent_, long timeSinceLastVisible_);

}