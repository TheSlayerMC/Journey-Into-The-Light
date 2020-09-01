package net.journey.entity.mob.end;

import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.journey.util.PotionEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityEnderCrawler extends JEntityMob {

    public EntityEnderCrawler(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(1.0F, 1.0F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, MobStats.ENDER_CRAWLER_HEALTH);
        EntityAttributesHelper.setAttackDamage(this, MobStats.ENDER_CRAWLER_DAMAGE);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.REAPER;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.REAPER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.REAPER_HURT;
    }

    @Override
    public boolean attackEntityFrom(DamageSource e, float a) {
        if (e.getImmediateSource() instanceof EntityPlayer)
            ((EntityPlayer) e.getImmediateSource()).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.blindness, 60, 1)));
        return super.attackEntityFrom(e, a);
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.ENDER_CRAWLER;
	}
}