package net.jitl.client.eventhandler;

import com.mojang.math.Vector3f;
import net.jitl.core.JITL;
import net.jitl.core.config.JClientConfig;
import net.jitl.core.config.JConfigs;
import net.minecraft.client.Camera;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class FOVEventHandler {

    private static double DELTA = 0;

    @SubscribeEvent
    public static void overrideFOV(EntityViewRenderEvent.FieldOfView event) {
        if (JConfigs.CLIENT.GUI_CATEGORY.isIsometricFOVEnabled()) {
            event.setFOV(4);
        }
    }

    @SubscribeEvent
    public static void onScrolled(InputEvent.MouseScrollEvent event) {
        double scrollAmplifier = 4;

        if (event.getScrollDelta() > 0) {
            DELTA += scrollAmplifier;
        } else {
            DELTA -= scrollAmplifier;
        }
    }

    @SubscribeEvent
    public static void overrideCamera(EntityViewRenderEvent.CameraSetup event) {
        JClientConfig clientConfig = JConfigs.CLIENT;

        if (clientConfig.GUI_CATEGORY.isIsometricFOVEnabled()) {
            Camera camera = event.getCamera();

            if (clientConfig.GUI_CATEGORY.isIsometricPerspectiveLocked()) {
                camera.setRotation(135, 35);
            }

            Vector3f lookVector = camera.getLookVector();

            float
                    lookX = lookVector.x(),
                    lookY = lookVector.y(),
                    lookZ = lookVector.z();

            double
                    x = camera.getPosition().x,
                    y = camera.getPosition().y,
                    z = camera.getPosition().z;

            camera.setPosition(x + (DELTA * lookX), y + (DELTA * lookY), z + (DELTA * lookZ));
        }
    }
}
