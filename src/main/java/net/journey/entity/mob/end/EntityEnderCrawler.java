package net.journey.entity.mob.end;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.journey.util.PotionEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityEnderCrawler extends JEntityMob {

    public EntityEnderCrawler(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(1.0F, 1.0F);
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

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.ENDER_CRAWLER;
    }
}