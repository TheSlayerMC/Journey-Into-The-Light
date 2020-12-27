package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.entity.EssenciaBoltEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import ru.timeconqueror.timecore.registry.AutoRegistrable;
import ru.timeconqueror.timecore.registry.newreg.SimpleForgeRegister;

public class JEntityTypes {

	@AutoRegistrable
	public static final SimpleForgeRegister<EntityType<?>> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.ENTITIES, JITL.MODID);

	public static final EntityType<EssenciaBoltEntity> ESSENCIA_BOLT_TYPE = EntityType.Builder.of(EssenciaBoltEntity::new, EntityClassification.AMBIENT)
			.noSave()
			.sized(0.0F, 0.0F)
			.clientTrackingRange(16)
			.updateInterval(Integer.MAX_VALUE)
			.build("essencia_bolt");

	public static final RegistryObject<EntityType<EssenciaBoltEntity>> ESSENCIA_BOLT_ENTITY = REGISTER.register("essencia_bolt", () -> ESSENCIA_BOLT_TYPE);
}
