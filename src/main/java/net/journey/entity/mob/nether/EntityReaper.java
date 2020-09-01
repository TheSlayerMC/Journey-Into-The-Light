package net.journey.entity.mob.nether;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityReaper extends JEntityMob {

    protected int animID = 1;
    protected int animTick = 1;

    public EntityReaper(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        this.isImmuneToFire = true;
        setSize(0.7F, 2.0F);
    }

    @Override
    public boolean attackEntityAsMob(Entity e) {
        boolean attacked = super.attackEntityAsMob(e);
        if (attacked) {
            if (e instanceof EntityLivingBase)
                ((EntityLivingBase) e).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.blindness, 100, 2)));
        }
        return attacked;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 35);
        EntityAttributesHelper.setAttackDamage(this, 7);
        EntityAttributesHelper.setMovementSpeed(this, 0.3);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_WITHER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_WITHER_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WITHER_SKELETON_DEATH;
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.REAPER;
	}
}