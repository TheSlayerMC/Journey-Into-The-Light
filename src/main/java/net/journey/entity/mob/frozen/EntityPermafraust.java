package net.journey.entity.mob.frozen;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyLootTables;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityPermafraust extends EntityModMob {

    public EntityPermafraust(World par1World) {
        super(par1World);
        addAttackingAI();
        this.setSize(0.5F, 2F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.PermafraustDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.PermafraustHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.SMALL_HONGO;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.SMALL_HONGO_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.SMALL_HONGO_HURT;
    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY > 60.0D && super.getCanSpawnHere();
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.PERMAFRAUST;
    }
}