package net.journey.entity.mob.end;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyLootTables;
import net.journey.util.PotionEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityEnderCrawler extends EntityModMob {


    public EntityEnderCrawler(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(1.0F, 1.0F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.EnderCrawlerDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.EnderCrawlerHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.REAPER;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.REAPER_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.REAPER_HURT;
    }

    @Override
    public boolean attackEntityFrom(DamageSource e, float a) {
        if (e.getImmediateSource() instanceof EntityPlayer)
            ((EntityPlayer) e.getImmediateSource()).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.blindness, 60, 1)));
        return super.attackEntityFrom(e, a);
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.ENDER_CRAWLER;
    }
}