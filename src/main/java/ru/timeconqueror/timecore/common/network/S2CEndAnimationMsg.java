package ru.timeconqueror.timecore.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.jetbrains.annotations.ApiStatus;
import ru.timeconqueror.timecore.api.animation.AnimationManager;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;

public class S2CEndAnimationMsg extends S2CAnimationMsg {
	private int transitionTime;

	@ApiStatus.Internal
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
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		Data data = decodeBaseData(buffer);

		transitionTime = buffer.readInt();
		entityId = data.entityId;
		layerName = data.layerName;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		encodeBaseData(this, buffer);

		buffer.writeInt(transitionTime);
	}

	public static class Handler extends S2CAnimationMsg.Handler<S2CEndAnimationMsg> {

		@Override
		public void onPacket(S2CEndAnimationMsg packet, AnimationProvider<?> provider, String layerName, MessageContext contextSupplier) {
			AnimationManager animationManager = provider.getActionManager().getAnimationManager();
			animationManager.removeAnimation(layerName, packet.transitionTime);
		}
	}
}
