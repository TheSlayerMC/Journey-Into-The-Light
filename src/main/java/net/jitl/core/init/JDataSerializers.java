package net.jitl.core.init;

import net.jitl.common.entity.overworld.HonglowEntity.Type;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DataSerializerEntry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class JDataSerializers {
	public static final EntityDataSerializer<Type> HONGLOW_VARIANT = new EntityDataSerializer<Type>() {
	    @Override
		public void write(FriendlyByteBuf buf, Type value) {
			buf.writeEnum(value);
		}
		@Override
		public Type read(FriendlyByteBuf buf) {
			return buf.readEnum(Type.class);
		}

		@Override
		public Type copy(Type value) {
			return value;
		}
	};
	
	@SubscribeEvent
	public static void registerSerializers(RegistryEvent.Register<DataSerializerEntry> register) {
		DataSerializerEntry honglowVariant = new DataSerializerEntry(HONGLOW_VARIANT);
		register.getRegistry().register(honglowVariant.setRegistryName("honglow_type"));
	}
}
