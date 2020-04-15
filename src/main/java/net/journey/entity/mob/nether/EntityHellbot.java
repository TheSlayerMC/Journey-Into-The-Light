package net.journey.entity.mob.nether;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityHellbot extends EntityModMob {

    public EntityHellbot(World par1World) {
        super(par1World);
        addAttackingAI();
        this.isImmuneToFire = true;
        setSize(0.7F, 1.5F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.HellbotDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.HellbotHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.ROBOT;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.ROBOT_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.ROBOT_DEATH;
    }

    @Override
    protected void dropFewItems(boolean b, int j) {
        if (rand.nextInt(3) == 0) dropItem(JourneyItems.flamingSpring, 2);
        if (rand.nextInt(20) == 0) dropItem(JourneyItems.flamingSprocket, 1);
        super.dropFewItems(b, j);
    }

    @Override
    public Item getItemDropped() {
        return null;
    }
}