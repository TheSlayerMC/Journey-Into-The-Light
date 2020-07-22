package ru.timeconqueror.timecore.common.network;

import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.jetbrains.annotations.ApiStatus;
import ru.timeconqueror.timecore.TimeCore;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;

public class S2CStartAnimationMsg extends S2CAnimationMsg {
	private AnimationStarter.AnimationData animationData;

	@ApiStatus.Internal
	public S2CStartAnimationMsg() {
	}

	public S2CStartAnimationMsg(Entity entity, String layerName, AnimationStarter.AnimationData animationData) {
		super(entity, layerName);
		this.animationData = animationData;
    }

    public S2CStartAnimationMsg(int entityId, String layerName, AnimationStarter.AnimationData animationData) {
        super(entityId, layerName);
        this.animationData = animationData;
    }

    @Override
    protected void encode(PacketBuffer buffer) {
        AnimationStarter.AnimationData.encode(animationData, buffer);
    }

    @Override
    protected void decode(PacketBuffer buffer) {
        animationData = AnimationStarter.AnimationData.decode(buffer);
    }

    public static class Handler extends S2CAnimationMsg.Handler<S2CStartAnimationMsg> {
        @Override
        public void onPacket(S2CStartAnimationMsg packet, AnimationProvider<?> provider, String layerName, MessageContext contextSupplier) {
            AnimationStarter animationStarter = AnimationStarter.fromAnimationData(packet.animationData);
            Animation animation = animationStarter.getData().getAnimation();

            String errorMessage = null;

			if (animation == null) {
				errorMessage = "Client received an animation, which is not registered on client.";
			}

			if (errorMessage == null) {
				animationStarter.startAt(provider.getActionManager().getAnimationManager(), packet.layerName);
			} else {
				TimeCore.LOGGER.error(errorMessage);
			}
		}
	}
}
