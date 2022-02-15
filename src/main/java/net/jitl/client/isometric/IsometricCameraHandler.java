package net.jitl.client.isometric;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.math.Vector3f;
import net.jitl.core.JITL;
import net.jitl.core.config.JClientConfig;
import net.jitl.core.config.JConfigs;
import net.jitl.core.config.enums.IsometricAngleSnap;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import static net.jitl.client.eventhandler.KeybindEventHandler.*;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class IsometricCameraHandler {

    private static final double FOV = -1.7;
    //Counts the scroll wheel delta
    private static double DELTA = 0;

    //Counts the offset of the X and Y axis
    private static double XAXIS = 0, YAXIS = 0;

    //Sets the default (NW) camera rotation to match the isometric view
    private static float XROT, YROT;

    //Counts the player angle snap timer
    private static int PLAYER_ANGLE_SNAP_TIMER = 0;

    /**
     * Overrides the player's set FOV if the config option is enabled
     */
    @SubscribeEvent
    public static void overrideFOV(EntityViewRenderEvent.FieldOfView event) {
        if (JConfigs.CLIENT.guiCategory.isIsometricFOVEnabled()) {
            event.setFOV(FOV);
        }
    }

    /**
     * Handles zooming in and out when the mouse wheel is scrolled.
     * When a positive input to the scroll wheel is received (like when scrolling up), the delta (1) will be added to the DELTA parameter
     * When a negative input to the scroll wheel is received (like when scrolling down), the delta (-1) will be added to the DELTA parameter
     */
    @SubscribeEvent
    public static void onScrolled(InputEvent.MouseScrollEvent event) {
        double scrollAmplifier = 4;

        if (event.getScrollDelta() > 0) {
            DELTA += scrollAmplifier;
        } else {
            DELTA -= scrollAmplifier;
        }
    }

    public static void handleIsometricCameraKeys(InputConstants.Key key) {
        JClientConfig clientConfig = JConfigs.CLIENT;

        double keyAmplifier = 2;

        /*
        Toggles the Isometric Camera config option when the configured key is pressed
        Sets angle snap timer to 1, which sets the player's look angle
        The look angle is important, because it lines up the camera to center of the screen when switching between modes and/or angle snaps
         */
        if (key == keyIsometricView.getKey()) {
            boolean toggle = !clientConfig.guiCategory.isIsometricFOVEnabled();
            clientConfig.guiCategory.setIsometricFov(toggle);

            PLAYER_ANGLE_SNAP_TIMER = 1;


        }

        /*
        Toggles the Locked Perspective config option when the configured key is pressed
        Locked perspective prevents the camera from rotating around the X and Y axis'
        Sets angle snap timer to 1, which sets the players look angle
         */
        else if (key == keyLockPerspective.getKey()) {
            boolean toggle = !clientConfig.guiCategory.isIsometricPerspectiveLocked();
            clientConfig.guiCategory.lockIsometricPerspective(toggle);

            PLAYER_ANGLE_SNAP_TIMER = 1;

        }

        /*
        Moves the camera up when the configured key is pressed
        The number that keyAmplifier represents is added to the YAXIS number whenever the key is pressed
         */
        else if (key == keyMoveCameraUp.getKey()) {
            YAXIS += keyAmplifier;
        }

        /*
        Moves the camera down when the configured key is pressed
        The number that keyAmplifier represents is subtracted from the YAXIS number whenever the key is pressed
         */
        else if (key == keyMoveCameraDown.getKey()) {
            YAXIS -= keyAmplifier;
        }

        /*
        Moves the camera on the positive X axis when the configured key is pressed
        The number that keyAmplifier represents is added to the XAXIS number whenever the key is pressed
         */
        else if (key == keyMoveCameraRight.getKey()) {
            XAXIS += keyAmplifier;
        }

        /*
        Moves the camera on the negative X axis when the configured key is pressed
        The number that keyAmplifier represents is subtracted from the XAXIS number whenever the key is pressed
         */
        else if (key == keyMoveCameraLeft.getKey()) {
            XAXIS -= keyAmplifier;

            //FIXME make better
        } else if (key == keyRotateCameraClockwise.getKey()) {
            YROT += keyAmplifier;

            PLAYER_ANGLE_SNAP_TIMER = 1;

        } else if (key == keyRotateCameraCounterClockwise.getKey()) {
            YROT -= keyAmplifier;

            PLAYER_ANGLE_SNAP_TIMER = 1;
        }

        /*
        Resets the X rotation of the camera back to the NW position
        The default XROT of the NW position is 135
         */
        else if (key == keyResetRotation.getKey()) {
            YROT = 0;

            PLAYER_ANGLE_SNAP_TIMER = 1;
        }

        /*
        Resets the position of the camera back to [0, 0]
         */
        else if (key == keyResetCameraPosition.getKey()) {
            XAXIS = 0;
            YAXIS = 0;
        }

        /*
        Resets to position of the camera back to [0, 0], resets the X and Y rotation of the camera back to the NW position, and resets the zoom delta
         */
        else if (key == keyResetAll.getKey()) {
            XROT = 0;
            YROT = 0;
            XAXIS = 0;
            YAXIS = 0;
            DELTA = 0;

            PLAYER_ANGLE_SNAP_TIMER = 1;
        }

        /*
        Cycles through the various snap angles for the fixed isometric view
        Sets angle snap timer to 1, which sets the players look angle
         */
        else if (key == keyCycleSnapAngle.getKey()) {
            JClientConfig config = JConfigs.CLIENT;

            IsometricAngleSnap angleSnap = clientConfig.guiCategory.getIsometricAngleSnap();

            List<IsometricAngleSnap> snaps = List.of(IsometricAngleSnap.NORTH_WEST, IsometricAngleSnap.SOUTH_WEST, IsometricAngleSnap.SOUTH_EAST, IsometricAngleSnap.NORTH_EAST);

            //TODO make better
            if (angleSnap == snaps.get(0)) {
                config.guiCategory.setIsometricAngleSnap(snaps.get(1));

            } else if (angleSnap == snaps.get(1)) {
                config.guiCategory.setIsometricAngleSnap(snaps.get(2));

            } else if (angleSnap == snaps.get(2)) {
                config.guiCategory.setIsometricAngleSnap(snaps.get(3));

            } else if (angleSnap == snaps.get(3)) {
                config.guiCategory.setIsometricAngleSnap(snaps.get(0));
            }

            XROT = 0;
            YROT = 0;

            PLAYER_ANGLE_SNAP_TIMER = 1;
        }
    }

    /*
    Overrides the Minecraft's camera to fit our needs, of the config option is enabled
     */
    @SubscribeEvent
    public static void overrideCamera(EntityViewRenderEvent.CameraSetup event) {
        JClientConfig clientConfig = JConfigs.CLIENT;

        IsometricAngleSnap angleSnap = clientConfig.guiCategory.getIsometricAngleSnap();

        GameRenderer gameRenderer = event.getRenderer();

        float xRot = 30 + XROT;
        float yRot = -YROT;

        if (clientConfig.guiCategory.isIsometricFOVEnabled()) {
            Camera camera = event.getCamera();

            Entity entity = camera.getEntity();

            /*
            If the player snap timer is equal to 1, set the players X and Y rotation based on the IsometricAngleSnap that's configured.
            Then, reset the player snap timer back to 0.
             */
            if (entity instanceof Player player) {

                if (PLAYER_ANGLE_SNAP_TIMER == 1) {
                    if (angleSnap == IsometricAngleSnap.NORTH_WEST) {
                        player.setYRot(135 + yRot);
                        player.setXRot(xRot);

                    } else if (angleSnap == IsometricAngleSnap.SOUTH_WEST) {
                        player.setYRot((float) 1125 + yRot);
                        player.setXRot(xRot);

                    } else if (angleSnap == IsometricAngleSnap.NORTH_EAST) {
                        player.setYRot((float) 1305 + yRot);
                        player.setXRot(xRot);

                    } else if (angleSnap == IsometricAngleSnap.SOUTH_EAST) {
                        player.setYRot((float) 1035 + yRot);
                        player.setXRot(xRot);
                    }

                    PLAYER_ANGLE_SNAP_TIMER = 0;
                }
            }

            /*
            If the isometric perspective is locked, the camera's rotation will be set based on the IsometricAngleSnap that's configured
             */
            if (clientConfig.guiCategory.isIsometricPerspectiveLocked()) {
                if (angleSnap == IsometricAngleSnap.NORTH_WEST) {
                    camera.setRotation(135 + yRot, xRot);

                } else if (angleSnap == IsometricAngleSnap.SOUTH_WEST) {
                    camera.setRotation(1125 + yRot, xRot);

                } else if (angleSnap == IsometricAngleSnap.NORTH_EAST) {
                    camera.setRotation(1305 + yRot, xRot);

                } else if (angleSnap == IsometricAngleSnap.SOUTH_EAST) {
                    camera.setRotation(1035 + yRot, xRot);
                }
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

            /*
            Handles the location and rotation of the camera when the isometric perspective isn't locked.
            The camera will follow the location of the player, plus the X/YAXIS offset, while also handling the zoom of the camera
             */
            camera.setPosition(
                    x + (DELTA * lookX) + XAXIS,
                    y + (DELTA * lookY) + YAXIS,
                    z + (DELTA * lookZ));


            /*
            Offset the zoom created by extremely low FOV
             */
            gameRenderer.zoom = (float) (FOV * 0.1D);
        } else {
            /*
            Reset the zoom to 1.0F (TODO: this might cause problems)
             */
            gameRenderer.zoom = 1.0F;
        }
    }
}
