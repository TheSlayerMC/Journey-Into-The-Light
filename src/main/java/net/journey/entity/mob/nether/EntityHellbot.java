package net.journey.entity.mob.nether;

import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityHellbot extends JEntityMob {

    public EntityHellbot(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        this.isImmuneToFire = true;
        setSize(0.7F, 1.5F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, MobStats.HELLBOT_HEALTH);
        EntityAttributesHelper.setAttackDamage(this, MobStats.HELLBOT_DAMAGE);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.ROBOT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.ROBOT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.ROBOT_DEATH;
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.HELLBOT;
    }
}