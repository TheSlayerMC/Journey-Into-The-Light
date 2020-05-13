package net.journey.entity.mob.boss;

import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;

public class EntityWraith extends EntityEssenceBoss {

    public EntityWraith(World par1World) {
        super(par1World);
        addAttackingAI();
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.wraithDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.wraithHealth;
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