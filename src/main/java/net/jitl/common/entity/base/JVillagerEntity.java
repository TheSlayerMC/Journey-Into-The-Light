package net.jitl.common.entity.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.INPC;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.world.World;

public class JVillagerEntity extends VillagerEntity implements INPC, IMerchant, IMob {

    public JVillagerEntity(EntityType<? extends VillagerEntity> type, World worldIn) {
        super(type, worldIn, VillagerType.PLAINS);
    }

}
