package net.jitl.common.entity.overworld;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.world.World;

public class MageEntity extends VillagerEntity {

    public MageEntity(EntityType<? extends VillagerEntity> type, World worldIn) {
        super(type, worldIn);
    }
}
