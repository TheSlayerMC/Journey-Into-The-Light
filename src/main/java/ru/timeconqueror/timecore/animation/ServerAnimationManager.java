package ru.timeconqueror.timecore.animation;

import net.journey.network.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.animation.watcher.AnimationWatcher;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;
import ru.timeconqueror.timecore.common.network.S2CEndAnimationMsg;
import ru.timeconqueror.timecore.common.network.S2CStartAnimationMsg;

public class ServerAnimationManager<T extends EntityMob> extends BaseAnimationManager {
    private ActionControllerImpl<T> stateMachine;

    public ServerAnimationManager(@Nullable AnimationSetting walkingAnimationStarter) {
        super(walkingAnimationStarter);
    }

    void setStateMachine(ActionControllerImpl<T> stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    protected void applyAnimation(TimeEntityModel model, Layer layer, AnimationWatcher watcher, long currentTime) {
        proceedActions(watcher);
    }

    @Override
    protected void onAnimationSet(AnimationStarter.AnimationData data, Layer layer) {
        super.onAnimationSet(data, layer);

        NetworkHandler.INSTANCE.sendToAllTracking(new S2CStartAnimationMsg(stateMachine.getEntity(), layer.getName(), data), stateMachine.getEntity());
    }

    @Override
    protected void onAnimationEnd(@Nullable TimeEntityModel model, Layer layer, AnimationWatcher watcher, long currentTime) {
        proceedActions(watcher);

        stateMachine.getActionWatchers().removeIf(actionWatcher -> actionWatcher.isBound(watcher.getAnimation()));
    }

    private void proceedActions(AnimationWatcher watcher) {
        for (ActionControllerImpl.ActionWatcher<T> actionWatcher : stateMachine.getActionWatchers()) {
            if (actionWatcher.isBound(watcher.getAnimation())) {
                if (actionWatcher.shouldBeExecuted(watcher)) {
                    actionWatcher.runAction(stateMachine.getEntity());
                }
            }
        }
    }

    @Override
    public void removeAnimation(String layerName, int transitionTime) {
        super.removeAnimation(layerName, transitionTime);

        NetworkHandler.INSTANCE.sendToAllTracking(new S2CEndAnimationMsg(stateMachine.getEntity(), layerName, transitionTime), stateMachine.getEntity());
    }

    @Override
    public boolean isGamePaused() {
        return FMLCommonHandler.instance().getSide() == Side.CLIENT && Minecraft.getMinecraft().isGamePaused();
    }
}
