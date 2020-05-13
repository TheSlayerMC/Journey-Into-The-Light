package net.journey.entity.mob.boiling;

import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityFlameLotus extends EntityModMob {

    public EntityFlameLotus(World par1World) {
        super(par1World);
        this.setSize(2.0F, 0.4F);
    }

    @Override
    public double setMovementSpeed() {
        return 0;
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return 0;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return 0;
    }

    @Override
    public SoundEvent setLivingSound() {
        return null;
    }

    @Override
    public SoundEvent setHurtSound() {
        return null;
    }

    @Override
    public SoundEvent setDeathSound() {
        return null;
    }
}