package ru.timeconqueror.timecore.common.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.jetbrains.annotations.ApiStatus;
import ru.timeconqueror.timecore.TimeCore;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;

public abstract class S2CAnimationMsg implements IMessage {
	protected int entityId;
	protected String layerName;

	@ApiStatus.Internal
	public S2CAnimationMsg() {
	}

	public S2CAnimationMsg(Entity entity, String layerName) {
		this(entity.getEntityId(), layerName);
	}

	public S2CAnimationMsg(int entityId, String layerName) {
		this.entityId = entityId;
		this.layerName = layerName;
	}

	public void encodeBaseData(S2CAnimationMsg msg, PacketBuffer buffer) {
		Data data = new Data(msg);

		buffer.writeInt(data.entityId);
		buffer.writeString(data.layerName);
	}

	public Data decodeBaseData(PacketBuffer buffer) {
		return new Data(buffer.readInt(), buffer.readString(Short.MAX_VALUE));
	}

	protected static class Data {
		protected int entityId;
		protected String layerName;

		public Data(S2CAnimationMsg msg) {
			this(msg.entityId, msg.layerName);
		}

		public Data(int entityId, String layerName) {
			this.entityId = entityId;
			this.layerName = layerName;
		}
	}

	public abstract static class Handler<T extends S2CAnimationMsg> implements IMessageHandler<T, IMessage> {

		public abstract void onPacket(T packet, AnimationProvider<?> provider, String layerName, MessageContext contextSupplier);

		@Override
		public IMessage onMessage(T message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				WorldClient world = Minecraft.getMinecraft().world;

				String errorMessage = null;

				Entity entity = world.getEntityByID(message.entityId);
				if (entity == null) {
					errorMessage = "Client received an animation, but entity wasn't found on client.";
				} else if (!(entity instanceof AnimationProvider<?>)) {
					errorMessage = "Provided entity id belongs to entity, which is not an inheritor of " + AnimationProvider.class;
				}

				if (errorMessage == null) {
					onPacket(message, ((AnimationProvider<?>) entity), message.layerName, ctx);
				} else {
					TimeCore.LOGGER.error(errorMessage);
				}
			});

			return null;
		}
	}
}
