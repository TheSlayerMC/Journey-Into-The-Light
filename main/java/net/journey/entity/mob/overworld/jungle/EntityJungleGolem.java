package net.journey.entity.mob.overworld.jungle;

import java.util.List;

import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.journey.util.PotionEffects;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityJungleGolem extends EntityModMob{

	public EntityJungleGolem(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(1.2F, 2.5F);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(3);
	}
	
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(this.world.isDaytime() && !this.world.isRemote) {
            float var1 = getBrightness();
        }
        
        List<Entity> e = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());        
        for(Entity entity : e) {
        	if(entity instanceof EntityPlayer && canEntityBeSeen(entity)) ((EntityPlayer)entity).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 60, 1)));
        }   

    }

	@Override
	public boolean attackEntityFrom(DamageSource e, float a) {
		if(e.getImmediateSource() instanceof EntityPlayer)
			((EntityPlayer)e.getImmediateSource()).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 60, 1)));
		return super.attackEntityFrom(e, a);
	}
    
	@Override
	public boolean attackEntityAsMob(Entity e) {
		boolean attacked = super.attackEntityAsMob(e);
		if(attacked) {
			e.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F)) * 4, 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F)) * 4);
		}
		return attacked;
	}
	
	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.JungleGolemDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.JungleGolemHealth;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
    }
	
	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.BUSH;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.BUSH_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.BUSH_DEATH;
	}
	
	@Override
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {}
}