package net.jitl.common.entity.projectile.base;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.jitl.init.JEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

public class JourneyEffectCloudEntity extends Entity {
	public static final DataParameter<Float> RADIUS = EntityDataManager.defineId(JourneyEffectCloudEntity.class, DataSerializers.FLOAT); //the current radius of the cloud
	public static final DataParameter<Integer> COLOR = EntityDataManager.defineId(JourneyEffectCloudEntity.class, DataSerializers.INT);
	public ArrayList<ImmutablePair<Integer, Float>> sizes = new ArrayList<ImmutablePair<Integer, Float>>(); //the list of size keys
	public LivingEntity owner; //the entity who is considered the cloud's owner
	public boolean isOwnerException = false; //whether the owner should be considered an exception
	public ArrayList<LivingEntity> exceptions = new ArrayList<LivingEntity>(); //the enemies who are exceptions
	public ArrayList<EffectInstance> primaryEffects = new ArrayList<EffectInstance>(); //the effects that will be inflicted normally
	public ArrayList<EffectInstance> secondaryEffects = new ArrayList<EffectInstance>(); //the effects that will be inflicted to enemies who are exceptions
	

	public JourneyEffectCloudEntity(EntityType<? extends JourneyEffectCloudEntity> entityType, World world) {
		super(entityType, world);
		this.noPhysics = true;
	}
	
	public JourneyEffectCloudEntity(LivingEntity cloudCreator, World world, double x, double y, double z, float initialRadius) {
		this(JEntities.EFFECT_CLOUD_TYPE, world);
		owner = cloudCreator;
		this.setPos(x, y, z);
		this.addSizeKey(0, initialRadius);
	}
	
	/**
	 * Adds a new size. Must be called in order to match the steps the cloud will follow in-game.
	 * @param time the time (in ticks) when the cloud will reach this size
	 * @param size the size the cloud will have at this stage of its life
	 */
	public void addSizeKey(int time, float size) {
		sizes.add(new ImmutablePair<Integer, Float>(time, size));
	}
	
	/**
	 * Makes the owner receive secondary effects only
	 */
	public void treatOwnerAsException() {
		isOwnerException = true;
	}
	
	
	public void markMobException(LivingEntity mob) {
		exceptions.add(mob);
	}
	
	
	public void addPrimaryEffect(EffectInstance effect) {
		primaryEffects.add(effect);
	}
	
	
	public void addSecondaryEffect(EffectInstance effect) {
		secondaryEffects.add(effect);
	}
	
	public void setColor(int color) {
		getEntityData().set(COLOR, color);
	}
	
	
	/**
	 * Performs final actions and spawns the cloud
	 */
	public void spawn() {
		sizes.trimToSize();
		exceptions.trimToSize();
		primaryEffects.trimToSize();
		secondaryEffects.trimToSize();
		this.level.addFreshEntity(this);
	}
	
	@Override
	public void tick() {
		super.tick();
		if (this.level.isClientSide) { //Client
			//spawn particles (same behavior as vanilla effect cloud particles, but code edited for better readability)
			float radius = getEntityData().get(RADIUS);
			float loops = (float)Math.PI * radius * radius;
            for(float current = 0; current < loops; ++current) {
               float rotation = this.random.nextFloat() * ((float) Math.PI * 2F);
               float distance = this.random.nextFloat() * radius; //vanilla has a sqrt here but it seems pointless
               float xOffset = MathHelper.cos(rotation) * distance;
               float zOffset = MathHelper.sin(rotation) * distance;
               int color = getEntityData().get(COLOR);
               this.level.addAlwaysVisibleParticle(ParticleTypes.ENTITY_EFFECT, this.getX() + xOffset, this.getY(), this.getZ() + zOffset, (color >> 16 & 255) / 255.0, (color >> 8 & 255) / 255.0, (color & 255) / 255.0);
            }
		} else { //Server
			//adjust the radius or delete the entity
			ImmutablePair<Integer, Float> previousPair = null; //the last specified time-size value the cloud was at
			ImmutablePair<Integer, Float> nextPair = null; //the time-size value the cloud is moving towards
			for (int pairCount = 0; pairCount < sizes.size(); pairCount++) {
				if (sizes.get(pairCount).getKey() < this.tickCount) { //check if the cloud has already been at the specified time-size pair
					if (pairCount == sizes.size() - 1) { //make sure there is actually another pair (otherwise the cloud has completed its life cycle)
						this.remove();
						return;
					} else if (sizes.get(pairCount + 1).getKey() > this.tickCount) { //the cloud is between this pair and the previous one
						previousPair = sizes.get(pairCount);
						nextPair = sizes.get(pairCount + 1);
					}
				}
			}
			if (previousPair != null && nextPair != null) {
				float timeFromLastPair = this.tickCount - ((Integer) previousPair.getKey()).intValue();
				float timeBetweenPairs = ((Integer) nextPair.getKey()).intValue() - ((Integer) previousPair.getKey()).intValue();
				float sizeDifference = ((Float) nextPair.getValue()).floatValue() - ((Float) previousPair.getValue()).floatValue();
				//radius = ((Float) previousPair.getValue()).floatValue() + sizeDifference * (timeFromLastPair / timeBetweenPairs);
				getEntityData().set(RADIUS, (timeFromLastPair / timeBetweenPairs) * sizeDifference + ((Float) previousPair.getValue()).floatValue());
			}
			
			//handle entity collisions
			List<LivingEntity> entityList = level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox());
			for (LivingEntity entity : entityList) {
				if (Math.sqrt(Math.pow(entity.getX() - this.getX(), 2) + Math.pow(entity.getZ() - this.getZ(), 2)) <= getEntityData().get(RADIUS)) { //better precision (makes real hitbox round)
					for (EffectInstance effect : isEntityException(entity) ? secondaryEffects : primaryEffects) { //decide which effects to apply
						entity.addEffect(effect);
					}
				}
			}
		}
	}
	
	private boolean isEntityException(LivingEntity entity) {
		if (isOwnerException && entity == owner) return true;
		for (LivingEntity listEntity : exceptions) {
			if (entity.getClass() == listEntity.getClass()) return true;
		}
		return false;
	}
	
	@Override
	public EntitySize getDimensions(Pose pose) {
		return EntitySize.scalable(getEntityData().get(RADIUS) * 2F, 0.5F); //this gets the bounding box. Change second value to adjust height
	}
	
	@Override
	protected void addAdditionalSaveData(CompoundNBT nbt) {
		nbt.putInt("Time", this.tickCount);
		nbt.putFloat("Radius", getEntityData().get(RADIUS));
		nbt.putInt("Color", getEntityData().get(COLOR));
		nbt.putUUID("Owner UUID", owner.getUUID());
		nbt.putBoolean("Owner exception", isOwnerException);
		ListNBT primaryEffectList = new ListNBT();
		for (EffectInstance effect : primaryEffects) {
			primaryEffectList.add(effect.save(new CompoundNBT()));
		}//
		nbt.put("Primary Effects", primaryEffectList);
		ListNBT secondaryEffectList = new ListNBT();
		for (EffectInstance effect : secondaryEffects) {
			secondaryEffectList.add(effect.save(new CompoundNBT()));
		}//
		nbt.put("Secondary Effects", secondaryEffectList);
		ListNBT sizesNBT = new ListNBT();
		for (ImmutablePair<Integer, Float> pair : sizes) {
			CompoundNBT pairNBT = new CompoundNBT();
			pairNBT.putInt("Time", pair.getKey());
			pairNBT.putFloat("Size", pair.getValue());
			sizesNBT.add(pairNBT);
		}
		nbt.put("Sizes", sizesNBT);
		ListNBT exceptionsNBT = new ListNBT();
		for (LivingEntity entity : exceptions) {
			CompoundNBT entityCompound = new CompoundNBT();
			entityCompound.putUUID("Exception UUID", entity.getUUID());
			exceptionsNBT.add(entityCompound);
		}
		nbt.put("Exceptions", exceptionsNBT);
	}
	
	
	@Override
	protected void readAdditionalSaveData(CompoundNBT nbt) {
		//commented = broken
		this.tickCount = nbt.getInt("Time");
		getEntityData().set(RADIUS, nbt.getFloat("Radius"));
		getEntityData().set(COLOR, nbt.getInt("Color"));
		/*LOGGER.debug("Reading owner");
		if (nbt.hasUUID("Owner UUID")) {
			LOGGER.debug("Owner UUID is saved");
			owner = getEntityFromUUID(nbt.getUUID("Owner UUID"));
			LOGGER.debug("Owner is " + owner.getName().getString());
		}*/
		isOwnerException = nbt.getBoolean("Owner exception");
		/*LOGGER.debug("Reading primary effects");
		ListNBT primaryList = nbt.getList("Primary Effects", 9);
		for (int current = 0; current < primaryList.size(); current++) {
			addPrimaryEffect(EffectInstance.load(primaryList.getCompound(current)));
		}
		LOGGER.debug("There are " + primaryEffects.size() + " primary effects");
		LOGGER.debug("Reading secondary effects");
		ListNBT secondaryList = nbt.getList("Secondary Effects", 9);
		for (int current = 0; current < secondaryList.size(); current++) {
			addSecondaryEffect(EffectInstance.load(secondaryList.getCompound(current)));
		}
		LOGGER.debug("There are " + secondaryEffects.size() + " secondary effects");
		LOGGER.debug("Reading size keys");
		ListNBT sizesNBT = nbt.getList("Sizes", 9);
		for (int current = 0; current < sizesNBT.size(); current++) {
			CompoundNBT pairCompound = sizesNBT.getCompound(current);
			addSizeKey(pairCompound.getInt("Time"), pairCompound.getFloat("Size"));
		}
		LOGGER.debug("There are " + sizes.size() + " size keys");
		LOGGER.debug("Reading exceptions");
		ListNBT exceptionsList = nbt.getList("Exceptions", 9);
		for (int current = 0; current < exceptionsList.size(); current++) {
			CompoundNBT exceptionCompound = exceptionsList.getCompound(current);
			if (exceptionCompound.hasUUID("Exception UUID")) {
				exceptions.add(getEntityFromUUID(exceptionCompound.getUUID("ExceptionUUID")));
			}
		}
		LOGGER.debug("Reading there are " + exceptions.size() + " exceptions");*/
	}
	
	private LivingEntity getEntityFromUUID(UUID id) {
		return (LivingEntity) (LivingEntity) ((ServerWorld) this.level).getEntity(id);
	}
	
	
	@Override
	protected void defineSynchedData() {
		this.getEntityData().define(RADIUS, 0F);
		this.getEntityData().define(COLOR, 0);
	}
	
	
	@Override
	public void onSyncedDataUpdated(DataParameter<?> data) {
		if (data == RADIUS) {
			double oldX = this.getX();
			double oldY = this.getY();
			double oldZ = this.getZ();
			this.refreshDimensions();
			this.setPos(oldX, oldY, oldZ);
		}
		super.onSyncedDataUpdated(data);
	}
	

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
