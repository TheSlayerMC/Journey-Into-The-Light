package net.jitl.client.eventhandler;

import com.mojang.math.Vector3f;
import net.jitl.core.JITL;
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
            double FOV = 5;
            event.setFOV(FOV);
        }
    }

    @SubscribeEvent
    public static void onScrolled(InputEvent.MouseScrollEvent event) {
        if (event.getScrollDelta() > 0) {
            DELTA++;
        } else {
            DELTA--;
        }
    }

    @SubscribeEvent
    public static void overrideCamera(EntityViewRenderEvent.CameraSetup event) {
        if (JConfigs.CLIENT.GUI_CATEGORY.isIsometricFOVEnabled()) {
            Camera camera = event.getCamera();

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

            //FIXME: set up fixed camera angle:
            // 45 degrees for X rot, 135 degress for Y rot
            //camera.setRotation(135, 45);
        }
    }
}
