package ru.timeconqueror.timecore.animation;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.animation.watcher.AnimationWatcher;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;
import ru.timeconqueror.timecore.common.network.S2CEndAnimationMsg;
import ru.timeconqueror.timecore.common.network.S2CStartAnimationMsg;
import ru.timeconqueror.timecore.common.network.TCNetworkHandler;

public class ServerAnimationManager<T extends EntityMob> extends BaseAnimationManager {
    private ActionManagerImpl<T> actionManager;

    public ServerAnimationManager(@Nullable AnimationSetting walkingAnimationStarter) {
        super(walkingAnimationStarter);
    }

    void setActionManager(ActionManagerImpl<T> actionManager) {
        this.actionManager = actionManager;
    }

    @Override
    protected void applyAnimation(TimeEntityModel model, Layer layer, AnimationWatcher watcher, long currentTime) {
        proceedActions(watcher);
    }

    @Override
    protected void onAnimationSet(AnimationStarter.AnimationData data, Layer layer) {
        super.onAnimationSet(data, layer);

        TCNetworkHandler.INSTANCE.sendToAllTracking(new S2CStartAnimationMsg(actionManager.getEntity(), layer.getName(), data), actionManager.getEntity());
    }

    @Override
    protected void onAnimationEnd(@Nullable TimeEntityModel model, Layer layer, AnimationWatcher watcher, long currentTime) {
        proceedActions(watcher);

        actionManager.getActionWatchers().removeIf(actionWatcher -> actionWatcher.isBound(watcher.getAnimation()));
    }

    private void proceedActions(AnimationWatcher watcher) {
        for (ActionManagerImpl.ActionWatcher<T, ?> actionWatcher : actionManager.getActionWatchers()) {
            if (actionWatcher.isBound(watcher.getAnimation())) {
                if (actionWatcher.shouldBeExecuted(watcher)) {
                    actionWatcher.runAction(actionManager.getEntity());
                }
            }
        }
    }

    @Override
    public void removeAnimation(String layerName, int transitionTime) {
        super.removeAnimation(layerName, transitionTime);

        TCNetworkHandler.INSTANCE.sendToAllTracking(new S2CEndAnimationMsg(actionManager.getEntity(), layerName, transitionTime), actionManager.getEntity());
    }

    @Override
    public boolean isGamePaused() {
        return FMLCommonHandler.instance().getSide() == Side.CLIENT && Minecraft.getMinecraft().isGamePaused();
    }
}
