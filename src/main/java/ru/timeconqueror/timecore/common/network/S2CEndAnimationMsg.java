package ru.timeconqueror.timecore.common.network;

import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.timeconqueror.timecore.api.animation.AnimationManager;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;

public class S2CEndAnimationMsg extends S2CAnimationMsg {
	private int transitionTime;

	@Deprecated // is called via reflection, not for direct use
	public S2CEndAnimationMsg() {
	}

	public S2CEndAnimationMsg(Entity entity, String layerName, int transitionTime) {
		super(entity, layerName);
		this.transitionTime = transitionTime;
	}

	public S2CEndAnimationMsg(int entityId, String layerName, int transitionTime) {
		super(entityId, layerName);
		this.transitionTime = transitionTime;
	}

	@Override
	protected void encode(PacketBuffer buffer) {
		buffer.writeInt(transitionTime);
	}

	@Override
	protected void decode(PacketBuffer buffer) {
		transitionTime = buffer.readInt();
	}

	public static class Handler extends S2CAnimationMsg.Handler<S2CEndAnimationMsg> {

		@Override
		public void onPacket(S2CEndAnimationMsg packet, AnimationProvider<?> provider, String layerName, MessageContext contextSupplier) {
			AnimationManager animationManager = provider.getActionManager().getAnimationManager();
			animationManager.removeAnimation(layerName, packet.transitionTime);
		}
	}
}
