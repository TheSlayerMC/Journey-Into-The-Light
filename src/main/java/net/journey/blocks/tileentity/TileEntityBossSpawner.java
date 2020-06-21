package net.journey.blocks.tileentity;

import net.journey.JITL;
import net.journey.blocks.tileentity.base.TileEntitySynced;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.util.MathUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class TileEntityBossSpawner extends TileEntitySynced implements ITickable {
	private int ticksFromActivating;
	private int totalTicksBeforeSpawn;

	private boolean activated = false;

	@Nullable
	private Class<? extends Entity> entityClass = null;
	@Nullable
	private Entity entityToSpawn = null;

	/**
	 * Does client stuff, is null on server.
	 */
	private ClientHandler clientHandler;

	public TileEntityBossSpawner() {
		this.totalTicksBeforeSpawn = State.DEFAULT_TIME_BEFORE_SPAWN;
	}

	@Override
	public void setWorld(World worldIn) {
		super.setWorld(worldIn);

		if (world.isRemote) {
			this.clientHandler = new ClientHandler();
		}

		if (entityClass != null) {
			setEntityToSpawn(createEntity(entityClass, world));
		}
	}

	@Override
	public void update() {
		if (isActivated()) {
			if (ticksFromActivating <= totalTicksBeforeSpawn) {
				ticksFromActivating++;
			} else {
				if (!world.isRemote) {
					spawnTargetEntity();
				}
			}
		}

		if (world.isRemote) {
			clientHandler.onTick();
		}
	}

	public void activate() {
		activated = true;
		setBlockToUpdateAndSave();
	}

	public boolean isActivated() {
		return activated;
	}

	private void spawnTargetEntity() {
		world.createExplosion(null, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, 2, true);

		if (getEntityToSpawn() != null) {
			Entity entityToSpawn = getEntityToSpawn();
			entityToSpawn.setPosition(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F);
			world.spawnEntity(entityToSpawn);
		} else {
			JITL.LOGGER.error("Can't spawn entity, because tile entity doesn't store its instance. Entity Class: {}", entityClass);
		}

		world.setBlockState(pos, Blocks.AIR.getDefaultState());
	}

	public void setEntity(Class<? extends Entity> entityClass) {
		this.entityClass = entityClass;

		entityToSpawn = createEntity(entityClass, world);

		setBlockToUpdateAndSave();
	}

	@Override
	public void readFromNBT(@NotNull NBTTagCompound compound) {
		super.readFromNBT(compound);

		activated = compound.getBoolean("activated");
		ticksFromActivating = compound.getInteger("ticks_from_activating");
		if (compound.hasKey("ticks_before_spawn")) {
			totalTicksBeforeSpawn = compound.getInteger("ticks_before_spawn");
		} else {
			totalTicksBeforeSpawn = State.DEFAULT_TIME_BEFORE_SPAWN;
		}

		if (compound.hasKey("entity")) {
			deserializeEntityToSpawn(compound.getString("entity"));
		}
	}

	@Override
	public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound compound) {
		compound.setBoolean("activated", activated);
		compound.setInteger("ticks_from_activating", ticksFromActivating);
		compound.setInteger("ticks_before_spawn", totalTicksBeforeSpawn);

		if (getEntityToSpawn() != null) {
			compound.setString("entity", serializeEntityToSpawn());
		}
		return super.writeToNBT(compound);
	}

	@Nullable
	public static Class<? extends Entity> getEntityClassFromKey(ResourceLocation rl) {
		EntityEntry entry = ForgeRegistries.ENTITIES.getValue(rl);
		if (entry == null) {
			return null;
		}

		return entry.getEntityClass();
	}

	@Nullable
	private static Entity createEntity(Class<? extends Entity> entityClass, World world) {
		try {
			Constructor<? extends Entity> entityConstructor = entityClass.getConstructor(World.class);
			return entityConstructor.newInstance(world);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			JITL.LOGGER.error("Can't create entity from class " + entityClass.getSimpleName(), e);
		}

		return null;
	}

	private String serializeEntityToSpawn() {
		Objects.requireNonNull(getEntityToSpawn());

		return EntityList.getKey(getEntityToSpawn()).toString();
	}

	private void deserializeEntityToSpawn(String rl) {
		Class<? extends Entity> entityClass = getEntityClassFromKey(new ResourceLocation(rl));
		this.entityClass = entityClass;

		if (entityClass != null) {
			setEntityToSpawn(createEntity(entityClass, world));
		}
	}

	public ClientHandler getClientHandler() {
		return clientHandler;
	}

	@Nullable
	public Entity getEntityToSpawn() {
		return entityToSpawn;
	}

	private void setEntityToSpawn(Entity entityToSpawn) {
		this.entityToSpawn = entityToSpawn;

		if (world != null && world.isRemote) {
			clientHandler.recalculateScale();
		}
	}

	public int getTotalTicksBeforeSpawn() {
		return totalTicksBeforeSpawn;
	}

	public int getTicksFromActivating() {
		return ticksFromActivating;
	}

	public class ClientHandler {
		private static final int MAX_HEIGHT = 5;
		private static final int MAX_ROTATION_SPEED = 25;
		private static final double HEIGHT_OF_RENDERED_ENTITY = 1.3; // in blocks

		private float prevRotationAngle;
		private float rotationAngle;
		private float prevTranslationY;
		private float translationY;
		private double scale;

		public void onTick() {
			if (isActivated()) {
				int current = getTicksFromActivating();
				int total = getTotalTicksBeforeSpawn();

				recalculateRotation(current, total);
				recalculateTranslation(current, total);
			}
		}

		private void recalculateScale() {
			Entity entityToSpawn = getEntityToSpawn();
			if (entityToSpawn != null) {
				AxisAlignedBB boundingBox = entityToSpawn.getEntityBoundingBox();

				scale = HEIGHT_OF_RENDERED_ENTITY / (boundingBox.maxY - boundingBox.minY);
			}
		}

		private void recalculateRotation(int currentTime, int total) {
			prevRotationAngle = rotationAngle;

			float risingEndTime = State.RISING.getEndTime(total);
			float speedFactor = MathUtils.calcPercentage(currentTime, 0, risingEndTime);
			float speed = MathUtils.interpolate(speedFactor, 0, MAX_ROTATION_SPEED);

			float baseAngle = 5;
			rotationAngle += 10 + (baseAngle * speed);
		}

		private void recalculateTranslation(int currentTime, int total) {
			prevTranslationY = translationY;

			State currentState = State.getCurrentState(currentTime, total);

			if (currentState == State.STAYING) {
				translationY = 0F;
			} else if (currentState == State.RISING) {
				float startTime = currentState.getStartTime(total);
				float endTime = currentState.getEndTime(total);

				float factor = MathUtils.calcPercentage(currentTime, startTime, endTime);
				translationY = MathUtils.interpolate(factor, 0, MAX_HEIGHT);
			} else if (currentState == State.FALLING) {
				float startTime = currentState.getStartTime(total);
				float endTime = currentState.getEndTime(total);

				float factor = MathUtils.calcPercentage(currentTime, startTime, endTime);
				translationY = MathUtils.interpolate(factor, MAX_HEIGHT, 0);
			}
		}

		public float getTranslationY(float partialTicks) {
			return MathUtils.interpolate(partialTicks, prevTranslationY, translationY);
		}

		public float getRotationAngle(float partialTicks) {
			return MathUtils.interpolate(partialTicks, prevRotationAngle, rotationAngle);
		}

		public State getCurrentState() {
			return State.getCurrentState(getTicksFromActivating(), getTotalTicksBeforeSpawn());
		}

		public double getScale() {
			return scale;
		}

		public double getRayOffsetY() {
			return HEIGHT_OF_RENDERED_ENTITY / 2 + (2F / 16 /* 1 / 16 of block */);
		}
	}

	public enum State {
		STAYING(80),
		RISING(300),
		FALLING(10);

		private static final int DEFAULT_TIME_BEFORE_SPAWN;
		private static final float[] PERCENTAGE_PREFIX_SUM;
		private final int tickTime;
		private int id;

		State(int tickTime) {
			this.tickTime = tickTime;
		}

		static {
			State[] values = values();

			int defFullTime = 0;
			for (State value : values) {
				defFullTime += value.tickTime;
			}
			DEFAULT_TIME_BEFORE_SPAWN = defFullTime;

			float[] percentagePrefixSum = new float[values.length];

			for (int i = 0; i < values.length; i++) {
				State value = values[i];

				value.id = i;

				if (i == 0) {
					percentagePrefixSum[i] = value.getPercentage();
				} else {
					percentagePrefixSum[i] = percentagePrefixSum[i - 1] + value.getPercentage();
				}
			}

			PERCENTAGE_PREFIX_SUM = percentagePrefixSum;
		}

		public float getPercentage() {
			return ((float) tickTime) / DEFAULT_TIME_BEFORE_SPAWN;
		}

		public float getStartTime(int fullTime) {
			if (this.id == 0) {
				return 0;
			}
			return fullTime * PERCENTAGE_PREFIX_SUM[this.id - 1];
		}

		public float getEndTime(int fullTime) {
			if (this.id == values().length - 1) {
				return fullTime;
			}

			return fullTime * PERCENTAGE_PREFIX_SUM[this.id];
		}

		public static State getCurrentState(int time, int fullTime) {
			float percentage = (float) time / fullTime;

			for (int i = 0; i < PERCENTAGE_PREFIX_SUM.length; i++) {
				float prefSum = PERCENTAGE_PREFIX_SUM[i];
				if (percentage <= prefSum) {
					return values()[i];
				}
			}

			return values()[values().length - 1];
		}
	}
}