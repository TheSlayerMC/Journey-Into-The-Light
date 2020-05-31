package net.journey.entity.mob.cloudia;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneyLootTables;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EntityCloudFlower extends JEntityMob {

    public EntityCloudFlower(World par1World) {
        super(par1World);
        this.setSize(2.0F, 0.2145F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    public boolean attackEntityFrom(DamageSource e, float a) {
        if (e.getImmediateSource() instanceof EntityPlayer)
            ((EntityPlayer) e.getImmediateSource()).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.jump, 150, 10)));
        return super.attackEntityFrom(e, a);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.world.isDaytime() && !this.world.isRemote) {
            float var1 = getBrightness();
        }

        List<Entity> e = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());
        for (Entity entity : e) {
            if (entity instanceof EntityPlayer && canEntityBeSeen(entity))
                entity.addVelocity(0.0D, 2.5D, 0.0D);
        }
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.CLOUD_FLOWER;
	}

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.CLOUD_FLOWER;
    }
}