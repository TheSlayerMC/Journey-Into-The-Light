package net.journey.entity.mob.depths;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.List;

public class EntityDepthsBeast extends JEntityMob {

    public EntityDepthsBeast(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(1.2F, 2.5F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 60);
        EntityAttributesHelper.setAttackDamage(this, 15);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.MAGMA_GIANT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.SPYCLOPS_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.SPYCLOPS_HURT;
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
                ((EntityPlayer) entity).addPotionEffect(PotionEffects.setPotionEffect(PotionEffects.blindness, 60, 1));
        }
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.DEPTHS_BEAST;
	}
}