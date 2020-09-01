package net.journey.entity.mob.boss;

import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.projectile.arrow.EntityDarknessArrow;
import net.journey.entity.projectile.arrow.EntityFlameArrow;
import net.journey.entity.projectile.arrow.EntityFrozenArrow;
import net.journey.entity.projectile.arrow.EntityPoisonArrow;
import net.journey.entity.util.EntityBossCrystal;
import net.journey.init.items.JourneyWeapons;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;
import org.jetbrains.annotations.Nullable;

//FIXME it doesn't drop anything
public class EntityFourfa extends EntityEssenceBoss implements IRangedAttackMob {


    public final int DARKNESS = 0, FLAME = 1, SLOWNESS = 2, POISON = 3;
    public int STAGE;
    public int TICKS = 2500;

    public EntityFourfa(World par1World) {
        super(par1World);
        setSize(2.0F, 4.0F);
        this.STAGE = SLOWNESS;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.tasks.addTask(0, new EntityAIAttackRanged(this, 1.0D, 15, 60.0F));
        addMeleeAttackingAI();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, MobStats.FOURFA_HEALTH);
        EntityAttributesHelper.setAttackDamage(this, MobStats.FOURFA_DAMAGE);
        EntityAttributesHelper.setKnockbackResistance(this, MobStats.FOURFA_KNOCKBACK_RESISTANCE);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_WITHER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return SoundEvents.ENTITY_WITHER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WITHER_DEATH;
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
        int health = (int) getHealth();
        if (health >= 750) STAGE = SLOWNESS;
        else if (health >= 500) STAGE = FLAME;
        else if (health >= 250) STAGE = DARKNESS;
        else if (health >= 0) STAGE = POISON;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase e, float f) {
        EntityArrow arrow = null;
        switch (STAGE) {
            case 0:
                arrow = new EntityDarknessArrow(this.world, this, e, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
                break;
            case 1:
                arrow = new EntityFlameArrow(this.world, this, e, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
                break;
            case 2:
                arrow = new EntityFrozenArrow(this.world, this, e, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
                break;
            case 3:
                arrow = new EntityPoisonArrow(this.world, this, e, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
                break;
        }
        arrow.setDamage(f * 2.0F + this.rand.nextGaussian() * 0.25D + (float) this.world.getDifficulty().getId() * 0.11F);
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(arrow);
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(JourneyWeapons.darknessBow));
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        return livingdata;
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {
    }

    @Nullable
    @Override
    protected EntityBossCrystal.Type getDeathCrystalType() {
        return null;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return null;
    }
}