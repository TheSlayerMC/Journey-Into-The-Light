package net.journey.entity.mob.boiling;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.init.JourneyLootTables;
import net.journey.init.items.JourneyWeapons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.JEntityMob;

import java.util.List;

public class EntityExposedFlame extends JEntityMob {

    public EntityExposedFlame(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(0.7F, 2.0F);
        isImmuneToFire = true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 45);
        EntityAttributesHelper.setAttackDamage(this, 9);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BLAZE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return SoundEvents.ENTITY_BLAZE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BLAZE_DEATH;
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
                entity.setFire(5 + rand.nextInt(7));
        }
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.EXPOSED_FLAME;
	}

    @Override
    public ItemStack getHeldItemMainhand() {
        return new ItemStack(JourneyWeapons.boilingBlade);
    }
}