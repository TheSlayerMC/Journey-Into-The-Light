package net.journey.entity.functional;

import io.netty.buffer.ByteBuf;
import net.journey.JITL;
import net.journey.client.render.entity.BossSpawnerClientHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class EntityBossSpawner extends Entity implements IEntityAdditionalSpawnData {
	private int ticksFromActivating;
	private int totalTicksBeforeSpawn;

	/**
	 * Do client stuff, is null on server.
	 */
	private final BossSpawnerClientHandler clientHandler;

	@Nullable
	private Class<? extends Entity> entityClass;
	@Nullable
	private Entity entityToSpawn = null;

	public EntityBossSpawner(World worldIn) {
		super(worldIn);

		if (worldIn.isRemote) {
			this.clientHandler = new BossSpawnerClientHandler(this);
		} else {
			this.clientHandler = null;
		}

		totalTicksBeforeSpawn = State.DEFAULT_TIME_BEFORE_SPAWN;
	}

	public EntityBossSpawner(World worldIn, @NotNull Class<? extends Entity> entityToSpawnClass) {
		this(worldIn, entityToSpawnClass, State.DEFAULT_TIME_BEFORE_SPAWN);
	}

	public EntityBossSpawner(World worldIn, @NotNull Class<? extends Entity> entityToSpawnClass, int totalTicksBeforeSpawn) {
		this(worldIn);
		this.entityClass = entityToSpawnClass;
		this.totalTicksBeforeSpawn = totalTicksBeforeSpawn;
	}

	@Nullable
	public Entity getEntityToSpawn() {
		return entityToSpawn;
	}

	public int getTotalTicksBeforeSpawn() {
		return totalTicksBeforeSpawn;
	}

	public int getTicksFromActivating() {
		return ticksFromActivating;
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();

		if (ticksFromActivating <= totalTicksBeforeSpawn) {
			ticksFromActivating++;
		} else {
			if (!world.isRemote) {
				spawnTargetEntity();
			}
		}

		if (world.isRemote) {
			clientHandler.onTick();
		}
	}

	public void spawnTargetEntity() {
		world.createExplosion(null, posX, posY, posZ, 2, true);

		if (getEntityToSpawn() != null) {
			Entity entityToSpawn = getEntityToSpawn();
			entityToSpawn.setPosition(posX, posY, posZ);
			world.spawnEntity(entityToSpawn);
		}

		setDead();
	}

	public BossSpawnerClientHandler getClientHandler() {
		return clientHandler;
	}

	@Override
	protected void entityInit() {
		if (entityClass != null) {
			entityToSpawn = createEntity(entityClass, world);
		}
	}

	@Override
	public void onAddedToWorld() {
		super.onAddedToWorld();

		if (getEntityToSpawn() == null && !world.isRemote) {
			JITL.LOGGER.warn("Created " + getClass() + " without passing a boss entity. Boss Spawner entity will be killed");
			setDead();
		}

		if (world.isRemote) {
			clientHandler.init();
		}
	}

	@Override
	protected void readEntityFromNBT(@NotNull NBTTagCompound compound) {
		ticksFromActivating = compound.getInteger("activate_ticks");
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
	protected void writeEntityToNBT(@NotNull NBTTagCompound compound) {
		compound.setInteger("spawn_ticks", ticksFromActivating);
		compound.setInteger("ticks_before_spawn", totalTicksBeforeSpawn);

		if (getEntityToSpawn() != null) {
			compound.setString("entity", serializeEntityToSpawn());
		}
	}

	@Nullable
	private static Class<? extends Entity> getClassFromKey(ResourceLocation rl) {
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
		Class<? extends Entity> entityClass = getClassFromKey(new ResourceLocation(rl));
		this.entityClass = entityClass;

		if (entityClass != null) {
			entityToSpawn = createEntity(entityClass, world);
		}
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		PacketBuffer packetBuffer = new PacketBuffer(buffer);
		buffer.writeInt(totalTicksBeforeSpawn);

		packetBuffer.writeBoolean(getEntityToSpawn() != null); // meaning: is spawn-entity not null

		if (getEntityToSpawn() != null) {
			packetBuffer.writeString(serializeEntityToSpawn());
		}
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		PacketBuffer packetBuffer = new PacketBuffer(additionalData);
		totalTicksBeforeSpawn = additionalData.readInt();

		boolean isSpawnEntityNotNull = packetBuffer.readBoolean();// meaning: is spawn-entity not null

		if (isSpawnEntityNotNull) {
			String rl = packetBuffer.readString(Short.MAX_VALUE);
			deserializeEntityToSpawn(rl);
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
