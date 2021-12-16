package net.jitl.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.world.World;

public class BlueLightningEntity extends LightningBoltEntity {

    private float damage = 0.0F;

    public BlueLightningEntity(EntityType<? extends BlueLightningEntity> entityType_, World worldIn) {
        super(entityType_, worldIn);
        setDamage(0.0F);
        setVisualOnly(true);
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return new SSpawnObjectPacket(this);
    }

}
