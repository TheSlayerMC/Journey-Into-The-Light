package ru.timeconqueror.timecore.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.jetbrains.annotations.ApiStatus;
import ru.timeconqueror.timecore.TimeCore;
import ru.timeconqueror.timecore.animation.BaseAnimationManager;
import ru.timeconqueror.timecore.animation.ServerAnimationManager;
import ru.timeconqueror.timecore.animation.util.AnimationSerializer;
import ru.timeconqueror.timecore.animation.watcher.AnimationWatcher;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;

import java.util.Map;

public class S2CSyncEntityAnimationsMsg implements IMessage {
	//server side only
	private ServerAnimationManager<?> animationManager;
	// client side only
	private Map<String, AnimationWatcher> layerMap;
	private int entityId;

	@ApiStatus.Internal
	public S2CSyncEntityAnimationsMsg() {
	}

	public S2CSyncEntityAnimationsMsg(ServerAnimationManager<?> animationManager, Entity entity) {
		this.animationManager = animationManager;
		this.entityId = entity.getEntityId();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		this.entityId = buffer.readInt();
		this.layerMap = AnimationSerializer.deserializeWatchers(buffer);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);

		buffer.writeInt(entityId);
		AnimationSerializer.serializeWatchers(animationManager, buffer);
	}

	public static class Handler implements IMessageHandler<S2CSyncEntityAnimationsMsg, IMessage> {
		@Override
		public IMessage onMessage(S2CSyncEntityAnimationsMsg message, MessageContext ctx) {
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
					Map<String, AnimationWatcher> layerMap = message.layerMap;
					BaseAnimationManager animationManager = (BaseAnimationManager) ((AnimationProvider<?>) entity).getActionManager().getAnimationManager();

					layerMap.forEach((name, watcher) -> animationManager.getLayer(name).setAnimationWatcher(watcher));
				} else {
					TimeCore.LOGGER.error(errorMessage);
				}
			});

			return null;
		}
	}
}
