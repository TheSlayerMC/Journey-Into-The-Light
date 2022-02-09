package net.jitl.client.eventhandler;

import com.mojang.blaze3d.platform.InputConstants;
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

import static net.jitl.client.eventhandler.KeybindEventHandler.*;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class IsometricCameraHandler {

    private static double DELTA = 0;

    private static double XAXIS = 0, YAXIS = 0;

    private static float XROT = 135, YROT = 35;

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

    static void handleIsometricCameraKeys(InputConstants.Key key, int action) {
        JClientConfig clientConfig = JConfigs.CLIENT;

        double keyAmplifier = 2;

        if (key == keyIsometricView.getKey()) {
            boolean toggle = !clientConfig.GUI_CATEGORY.isIsometricFOVEnabled();
            clientConfig.GUI_CATEGORY.setIsometricFov(toggle);

        } else if (key == keyLockPerspective.getKey()) {
            boolean toggle = !clientConfig.GUI_CATEGORY.isIsometricPerspectiveLocked();
            clientConfig.GUI_CATEGORY.lockIsometricPerspective(toggle);

        } else if (key == keyMoveCameraUp.getKey()) {
            YAXIS += keyAmplifier;

        } else if (key == keyMoveCameraDown.getKey()) {
            YAXIS -= keyAmplifier;

        } else if (key == keyMoveCameraRight.getKey()) {
            XAXIS += keyAmplifier;

        } else if (key == keyMoveCameraLeft.getKey()) {
            XAXIS -= keyAmplifier;

            //TODO: this is jenky. Maybe set the camera position based on the look vector, so it doesn't jump around when you rotate?
        } else if (key == keyRotateCameraClockwise.getKey()) {
            XROT += keyAmplifier;

        } else if (key == keyRotateCameraCounterClockwise.getKey()) {
            XROT -= keyAmplifier;

        } else if (key == keyResetRotation.getKey()) {
            XROT = 135;

        } else if (key == keyResetCameraPosition.getKey()) {
            XAXIS = 0;
            YAXIS = 0;

        } else if (key == keyResetAll.getKey()) {
            XROT = 135;
            YROT = 35;
            XAXIS = 0;
            YAXIS = 0;
            DELTA = 0;
        }
    }

    @SubscribeEvent
    public static void overrideCamera(EntityViewRenderEvent.CameraSetup event) {
        JClientConfig clientConfig = JConfigs.CLIENT;

        if (clientConfig.GUI_CATEGORY.isIsometricFOVEnabled()) {
            Camera camera = event.getCamera();

            if (clientConfig.GUI_CATEGORY.isIsometricPerspectiveLocked()) {
                camera.setRotation(XROT, YROT);
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

            camera.setPosition(x + (DELTA * lookX) + XAXIS, y + (DELTA * lookY) + YAXIS, z + (DELTA * lookZ));
        }
    }
}
