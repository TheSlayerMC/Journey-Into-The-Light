package net.jitl.init;

import net.jitl.common.entity.overworld.HonglowEntity;
import net.jitl.common.entity.overworld.HonglowEntity.Type;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DataSerializerEntry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class JDataSerializers {
	public static final IDataSerializer<HonglowEntity.Type> HONGLOW_VARIANT = new IDataSerializer<HonglowEntity.Type>() {
	    @Override
		public void write(PacketBuffer buf, Type value) {
			buf.writeEnum(value);
			
		}
		
		@Override
		public HonglowEntity.Type read(PacketBuffer buf) {
			return buf.readEnum(HonglowEntity.Type.class);
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
