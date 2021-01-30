package net.jitl.common.entity.overworld;

import net.jitl.JITL;
import net.jitl.common.entity.projectile.base.JEffectCloudEntity;
import net.jitl.init.JDataSerializers;
import net.jitl.init.JSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import ru.timeconqueror.timecore.api.util.Requirements;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

import javax.annotation.Nullable;

public class HonglowEntity extends CreatureEntity {
	public static final DataParameter<Type> VARIANT = EntityDataManager.defineId(HonglowEntity.class, JDataSerializers.HONGLOW_VARIANT);

	public HonglowEntity(EntityType<? extends HonglowEntity> entityType, World world) {
		super(entityType, world);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	}
	
	public Type getVariant() {
		return this.getEntityData().get(VARIANT);
	}
	
	public Type getVariantFromInt(int number) {
		Requirements.inRangeInclusive(number, 0, 2);
		switch (number) {
			case 0:
				return Type.RED;	
			case 1: 
				return Type.GREEN;	
			case 2:
				return Type.BLUE;
		}
		return null;
	}
	
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		
		this.getEntityData().set(VARIANT, getVariantFromInt(this.random.nextInt(3)));
	    return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(VARIANT, Type.RED);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putInt("color", getVariant().getTypeInt());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT nbt) {
		super.readAdditionalSaveData(nbt);
		this.getEntityData().set(VARIANT, getVariantFromInt(nbt.getInt("color")));
	}

	public static boolean canSpawn(EntityType<? extends CreatureEntity> entityType, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
		return !worldIn.getBlockState(pos).is(Blocks.WATER)
				&& worldIn.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)
				&& worldIn.getBiome(pos).getBiomeCategory() == Biome.Category.MUSHROOM
				|| worldIn.getBiome(pos).getBiomeCategory() == Biome.Category.SWAMP;
	}

	@Override
	public float getBrightness() {
		return 10.0F;
	}

	@Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        if (super.hurt(source, amount) && random.nextInt(10) == 0) {
            if (source != DamageSource.OUT_OF_WORLD && source != DamageSource.MAGIC) {
    			JEffectCloudEntity poison = new JEffectCloudEntity(this, this.level, this.getX(), this.getY(), this.getZ(), 0.5F);
    			poison.addSizeKey(5, 2);
    			poison.addSizeKey(300, 0);
    			poison.addPrimaryEffect(getVariant().getPrimaryPotion());
    			poison.addSecondaryEffect(getVariant().getSecondaryPotion());
    			poison.setColor(getVariant().getPotionColor());
    			/*switch (this.getEntityData().get(VARIANT)) {
    				case 0: //blue
    					poison.addPrimaryEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 500, 3));
    	    			poison.addSecondaryEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 200));
    	    			poison.setColor(Effects.MOVEMENT_SPEED.getColor());
    	    			break;
    				case 1: //red
    					poison.addPrimaryEffect(new EffectInstance(Effects.DAMAGE_BOOST, 500, 3));
    	    			poison.addSecondaryEffect(new EffectInstance(Effects.WEAKNESS, 200));
    	    			poison.setColor(Effects.REGENERATION.getColor());
    	    			break;
    				case 2: //green
    					poison.addPrimaryEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 500, 3));
    	    			poison.addSecondaryEffect(new EffectInstance(Effects.DIG_SLOWDOWN, 200));
    	    			poison.setColor(Effects.JUMP.getColor());
    			}*/
    			Entity attacker = source.getEntity();
    			if (attacker != null && attacker instanceof LivingEntity) {
    				poison.markMobException((LivingEntity) attacker); 
    			}
    			poison.spawn();
                /*AreaEffectCloudEntity poison = new AreaEffectCloudEntity(this.level, this.getX(), this.getY(), this.getZ());
                poison.setRadius(0.5F); //the base radius. Vanilla starts large and shrinks down, but I think the opposite is more realistic
                poison.setWaitTime(5); //time before the cloud starts growing/damaging. Might be worth decreasing since player can run away anyways
                poison.setRadiusPerTick(0.15F); //the speed at which the cloud size changes. Set negative if you want it to shrink like in vanilla
                poison.setOwner(this); //does nothing to my knowledge
                poison.setDuration(20); //how long the cloud lasts
                poison.setPotion(new Potion(new EffectInstance(Effects.POISON, 500, 3)));
                poison.addEffect(new EffectInstance(Effects.CONFUSION, 200));
                level.playSound(null, this.blockPosition(), JSounds.HONGO_SPORE_RELEASE.get(), SoundCategory.HOSTILE, 1.0F, 1.0F);
                this.level.addFreshEntity(poison);*/
            }
    		return true;
    	} else {
    		return false;
    	}
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	protected SoundEvent getAmbientSound() {
		return JSounds.HONGO_AMBIENT.get();
	}

	protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
		return JSounds.HONGO_HURT.get();
	}

	protected SoundEvent getDeathSound() {
		return JSounds.HONGO_DEATH.get();
	}

	protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
		this.playSound(SoundEvents.PIG_STEP, 0.15F, 1.0F);
	}
	
	public enum Type {
		RED(0, JITL.rl("textures/entity/overworld/honglow_red.png"), new EffectInstance(Effects.DAMAGE_BOOST, 500, 3), new EffectInstance(Effects.WEAKNESS, 200), Effects.REGENERATION.getColor()),
	    GREEN(1, JITL.rl("textures/entity/overworld/honglow_green.png"), new EffectInstance(Effects.DAMAGE_RESISTANCE, 500, 3), new EffectInstance(Effects.DIG_SLOWDOWN, 200), Effects.JUMP.getColor()),
	    BLUE(2, JITL.rl("textures/entity/overworld/honglow_blue.png"), new EffectInstance(Effects.MOVEMENT_SPEED, 500, 3), new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 200), Effects.MOVEMENT_SPEED.getColor());
		
		private final int variantNum;
		private final ResourceLocation texture;
		private final EffectInstance primaryPotionEffect;
		private final EffectInstance secondaryPotionEffect;
		private final int cloudColor;
		
		Type(int id, ResourceLocation location, EffectInstance goodPotion, EffectInstance badPotion, int potionColor) {
			variantNum = id;
			texture = location;
			primaryPotionEffect = goodPotion;
			secondaryPotionEffect = badPotion;
			cloudColor = potionColor;
		}
		
		public int getTypeInt() {
			return this.variantNum;
		}
		
		public ResourceLocation getTexture() {
			return texture;
		}
		
		public EffectInstance getPrimaryPotion() {
			return primaryPotionEffect;
		}
		
		public EffectInstance getSecondaryPotion() {
			return secondaryPotionEffect;
		}
		
		public int getPotionColor() {
			return cloudColor;
		}
	}
}



