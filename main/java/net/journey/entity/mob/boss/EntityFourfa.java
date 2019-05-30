package net.journey.entity.mob.boss;

import javax.annotation.Nullable;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityDarknessArrow;
import net.journey.entity.projectile.EntityFlameArrow;
import net.journey.entity.projectile.EntityFrozenArrow;
import net.journey.entity.projectile.EntityPoisonArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;

public class EntityFourfa extends EntityEssenceBoss implements IRangedAttackMob {

	private final EntityAIAttackRangedBow<EntityFourfa> aiArrowAttack = new EntityAIAttackRangedBow<EntityFourfa>(this, 1.0D, 15, 60.0F);


	public final int DARKNESS = 0, FLAME = 1, SLOWNESS = 2, POISON = 3;
	public int STAGE;
	public int TICKS = 2500;

	public EntityFourfa(World par1World) {
		super(par1World);
		setSize(2.0F, 4.0F);
		if(par1World != null && !par1World.isRemote) this.setCombatTask();
		STAGE = SLOWNESS;
	}

	public int getStage() {
		return STAGE;
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		/*TICKS--;
		if(TICKS >= 2000) STAGE = SLOWNESS;
		else if(TICKS >= 1500) STAGE = FLAME;
		else if(TICKS >= 1000) STAGE = DARKNESS;
		else if(TICKS >= 500) STAGE = POISON;
		if(TICKS <= 0) TICKS = 2000;*/		
		int health = (int)getHealth();
		if(health >= 750) STAGE = SLOWNESS;
		else if(health >= 500) STAGE = FLAME;
		else if(health >= 250) STAGE = DARKNESS;
		else if(health >= 0) STAGE = POISON;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase e, float f) {
		EntityArrow arrow = null;
		switch(STAGE) {
		case 0:
			arrow = new EntityDarknessArrow(this.world, this, e, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
			break;
		case 1:
			arrow = new EntityFlameArrow(this.world, this, e, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
			break;
		case 2:
			arrow = new EntityFrozenArrow(this.world, this, e, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
			break;
		case 3:
			arrow = new EntityPoisonArrow(this.world, this, e, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
			break;
		}
		arrow.setDamage(f * 2.0F + this.rand.nextGaussian() * 0.25D + (float)this.world.getDifficulty().getDifficultyId() * 0.11F);
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		this.world.spawnEntity(arrow);
	}

	public void setCombatTask() {
		this.tasks.removeTask(this.aiArrowAttack);
		ItemStack itemstack = this.getHeldItemMainhand();
		if(itemstack != null && itemstack.getItem() == JourneyItems.darknessBow)
			this.tasks.addTask(4, this.aiArrowAttack);
	}

	@Override
	@Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);
        this.setCombatTask();
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficulty.getClampedAdditionalDifficulty());
        return livingdata;
    }
	
	@Override
	public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {
        super.setItemStackToSlot(slotIn, stack);
        if (!this.world.isRemote && slotIn == EntityEquipmentSlot.MAINHAND) {
            this.setCombatTask();
        }
    }

	@Override
	public ItemStack getHeldItem(EnumHand hand) {
		return new ItemStack(JourneyItems.darknessBow);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.fourfaDamage;
	}

	@Override
	public double setKnockbackResistance() {
		return 1.0D;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.fourfaHealth;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_WITHER_AMBIENT;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource sourceIn) {
		return SoundEvents.ENTITY_WITHER_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_WITHER_DEATH;
	}

	@Override
	public Item getDropItem() {
		return JourneyItems.depthsPortalGem;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		this.dropItem(getDropItem(), 6 + rand.nextInt(4));
	//	if(rand.nextInt(1) == 0) this.dropItem(Item.getItemFromBlock(EssenceBlocks.eudorStatue), 1);
	}

	@Override
	public void setSwingingArms(boolean swingingArms) { }
}