package net.jitl.common.entity.euca;

import net.jitl.common.entity.base.JVillagerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.world.World;

public class AlloyMenderEntity extends JVillagerEntity {

    public AlloyMenderEntity(EntityType<? extends VillagerEntity> type, World worldIn) {
        super(type, worldIn);
    }
}
