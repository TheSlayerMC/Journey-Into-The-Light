package net.journey.entity.mob.euca;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.journey.util.PotionEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityInsecto extends EntityModMob {


    public EntityInsecto(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(0.7F, 1.7F);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.InsectoDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.InsectoHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.INSECTO;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.INSECTO_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.INSECTO_HURT;
    }

    @Override
    public boolean attackEntityFrom(DamageSource e, float a) {
        if (e.getImmediateSource() instanceof EntityPlayer)
            ((EntityPlayer) e.getImmediateSource()).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 60, 1)));
        return super.attackEntityFrom(e, a);
    }

    @Override
    public Item getItemDropped() {
        return JourneyConsumables.eucaMeat;
    }

    @Override
    protected void dropFewItems(boolean b, int j) {
        if (rand.nextInt(70) == 0) dropItem(JourneyItems.eucaTablet, 1);
        super.dropFewItems(b, j);
    }
}