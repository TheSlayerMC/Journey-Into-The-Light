package net.jitl.common.entity.overworld;

import net.jitl.JITL;
import net.jitl.common.entity.base.IJourneyBoss;
import net.jitl.common.entity.projectile.base.JEffectCloudEntity;
import net.jitl.common.helper.JBossInfo;
import net.jitl.common.helper.JMusic;
import net.jitl.init.JDataSerializers;
import net.jitl.init.JSounds;
import net.jitl.network.JBossPacket;
import net.jitl.network.JPacketHandler;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.server.ServerBossInfo;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.util.RandHelper;
import ru.timeconqueror.timecore.api.util.lookups.EnumLookup;

import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;

public class HonglowEntity extends Monster {
	public static final EntityDataAccessor<Type> VARIANT = SynchedEntityData.defineId(HonglowEntity.class, JDataSerializers.HONGLOW_VARIANT);

	public HonglowEntity(EntityType<? extends HonglowEntity> entityType, Level world) {
		super(entityType, world);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
	}
	
	public Type getVariant() {
		return this.getEntityData().get(VARIANT);
	}
	
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		this.getEntityData().set(VARIANT, RandHelper.chooseEqually(Type.values()));
	    return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(VARIANT, Type.RED);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putInt("color", getVariant().getTypeInt());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		this.getEntityData().set(VARIANT, Type.getVariantFromInt(nbt.getInt("color")));
	}

	public static boolean canSpawn(EntityType<? extends PathfinderMob> entityType, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random random) {
		return !worldIn.getBlockState(pos).is(Blocks.WATER)
				&& worldIn.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)
				&& worldIn.getBiome(pos).getBiomeCategory() == Biome.BiomeCategory.MUSHROOM
				|| worldIn.getBiome(pos).getBiomeCategory() == Biome.BiomeCategory.SWAMP;
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
				Entity attacker = source.getEntity();
				if (attacker instanceof LivingEntity) {
					poison.exclude((LivingEntity) attacker);
				}
				poison.spawn();
				level.playSound(null, this.blockPosition(), JSounds.HONGO_SPORE_RELEASE.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
			}
    		return true;
    	} else {
    		return false;
    	}
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return sizeIn.height * 0.55F;
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
		RED(0, JITL.rl("textures/entity/overworld/honglow_red.png"), new MobEffectInstance(MobEffects.DAMAGE_BOOST, 500, 3), new MobEffectInstance(MobEffects.WEAKNESS, 200), MobEffects.REGENERATION.getColor()),
	    GREEN(1, JITL.rl("textures/entity/overworld/honglow_green.png"), new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 500, 3), new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 200), MobEffects.JUMP.getColor()),
	    BLUE(2, JITL.rl("textures/entity/overworld/honglow_blue.png"), new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 500, 3), new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200), MobEffects.MOVEMENT_SPEED.getColor());
		
		private final int variantNum;
		private final ResourceLocation texture;
		private final MobEffectInstance primaryPotionEffect;
		private final MobEffectInstance secondaryPotionEffect;
		private final int cloudColor;
		private static final EnumLookup<Type, Integer> VARIANT_FINDER = EnumLookup.make(Type.class, Type::getTypeInt);
		
		Type(int id, ResourceLocation location, MobEffectInstance goodPotion, MobEffectInstance badPotion, int potionColor) {
			variantNum = id;
			texture = location;
			primaryPotionEffect = goodPotion;
			secondaryPotionEffect = badPotion;
			cloudColor = potionColor;
		}
		
		public int getTypeInt() {
			return this.variantNum;
		}

		public static Type getVariantFromInt(int number) {
			return VARIANT_FINDER.by(number);
		}
		
		public ResourceLocation getTexture() {
			return texture;
		}
		
		public MobEffectInstance getPrimaryPotion() {
			return primaryPotionEffect;
		}
		
		public MobEffectInstance getSecondaryPotion() {
			return secondaryPotionEffect;
		}
		
		public int getPotionColor() {
			return cloudColor;
		}
	}
}



