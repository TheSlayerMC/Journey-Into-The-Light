package net.jitl.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.world.World;

public class EssenciaBoltEntity extends LightningBoltEntity {
	public EssenciaBoltEntity(EntityType<? extends LightningBoltEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}
}
