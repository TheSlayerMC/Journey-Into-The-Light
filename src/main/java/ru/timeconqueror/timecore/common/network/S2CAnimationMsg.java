package ru.timeconqueror.timecore.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.timeconqueror.timecore.TimeCore;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;

public abstract class S2CAnimationMsg implements IMessage {
	protected int entityId;
	protected String layerName;

	@Deprecated // is called via reflection, not for direct use
	public S2CAnimationMsg() {
	}

	public S2CAnimationMsg(Entity entity, String layerName) {
		this(entity.getEntityId(), layerName);
	}

	public S2CAnimationMsg(int entityId, String layerName) {
		this.entityId = entityId;
		this.layerName = layerName;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		encodeBaseData(buffer);
		encode(buffer);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		decodeBaseData(buffer);
		decode(buffer);
	}

	protected abstract void encode(PacketBuffer buffer);

	protected abstract void decode(PacketBuffer buffer);

	public void encodeBaseData(PacketBuffer buffer) {
		buffer.writeInt(entityId);
		buffer.writeString(layerName);
	}

	public void decodeBaseData(PacketBuffer buffer) {
		entityId = buffer.readInt();
		layerName = buffer.readString(Short.MAX_VALUE);
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
