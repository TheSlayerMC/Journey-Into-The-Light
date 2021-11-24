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
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerBossInfo;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.util.RandHelper;
import ru.timeconqueror.timecore.api.util.lookups.EnumLookup;

import javax.annotation.Nullable;
import java.util.Random;

public class HonglowEntity extends MonsterEntity implements IJourneyBoss {
	public static final DataParameter<Type> VARIANT = EntityDataManager.defineId(HonglowEntity.class, JDataSerializers.HONGLOW_VARIANT);
	private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.NOTCHED_6);

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
	
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		this.getEntityData().set(VARIANT, RandHelper.chooseEqually(Type.values()));
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
		this.getEntityData().set(VARIANT, Type.getVariantFromInt(nbt.getInt("color")));
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
	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);
		JBossInfo.addInfo(player, bossInfo, this);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);
		JBossInfo.removeInfo(player, bossInfo, this);
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
				level.playSound(null, this.blockPosition(), JSounds.HONGO_SPORE_RELEASE.get(), SoundCategory.HOSTILE, 1.0F, 1.0F);
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

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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

	@Override
	public ResourceLocation getBarTexture() {
		return null;
	}

	@Override
	public JMusic getBossMusic() {
		return null;
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
		private static final EnumLookup<Type, Integer> VARIANT_FINDER = EnumLookup.make(Type.class, Type::getTypeInt);
		
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

		public static Type getVariantFromInt(int number) {
			return VARIANT_FINDER.by(number);
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



