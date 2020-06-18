package net.journey.blocks.tileentity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nonnull;

import net.journey.entity.mob.boss.EntityNetherBeast;
import net.journey.util.handler.Helper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityBossBlock extends TileEntity implements ITickable {

	private static final float SPEED_CHANGE_RATE = 0.1f;
	private static final Random RANDOM = new Random();
	private static final int STAGE_CHANGE_TICK = 100;
	private static final int RISING_TIME = 300;
	private static final int FALLING_TIME = 10;
	public static final int MAX_HEIGHT = 5;
	private static final double STAGE_CHANGE_CHANCE = 0.8;

	public static enum State {
		IDLE(0, 0, false) {
			@Override
			public State getNextState(TileEntityBossBlock target) {
				return null;
			}

			@Override
			public void onTick(TileEntityBossBlock target) {
				;
			}
		},
		INERT(0, 0, false) {
			@Override
			public State getNextState(TileEntityBossBlock target) {
				return target.tryRandomlyChangeState(STAGE_CHANGE_TICK, ROTATING_SLOW);
			}

			@Override
			public void onTick(TileEntityBossBlock target) {
				target.tickCounter++;
			}
		},
		ROTATING_SLOW(1, 0, false) {
			@Override
			public State getNextState(TileEntityBossBlock target) {
				return target.tryRandomlyChangeState(STAGE_CHANGE_TICK, ROTATING_MEDIUM);
			}

			@Override
			public void onTick(TileEntityBossBlock target) {
				target.tickCounter++;
			}
		},
		ROTATING_MEDIUM(10, 0, false) {
			@Override
			public State getNextState(TileEntityBossBlock target) {
				return target.tryRandomlyChangeState(STAGE_CHANGE_TICK, ROTATING_FAST);
			}

			@Override
			public void onTick(TileEntityBossBlock target) {
				target.tickCounter++;
			}
		},
		ROTATING_FAST(50, 0, false) {
			@Override
			public State getNextState(TileEntityBossBlock target) {
				return target.tryRandomlyChangeState(STAGE_CHANGE_TICK, FLOATING);
			}

			@Override
			public void onTick(TileEntityBossBlock target) {
				target.tickCounter++;
			}
		},
		FLOATING(100, 1.0f / RISING_TIME, true) {
			@Override
			public void onEntry(TileEntityBossBlock target) {
				target.tickCounter = RISING_TIME;
			}

			@Override
			public void onTick(TileEntityBossBlock target) {
				target.tickCounter--;
			}

			@Override
			public State getNextState(TileEntityBossBlock target) {
				return (target.tickCounter <= 0)? FALLING : null;
			}
		},
		FALLING(150, -1.0f / FALLING_TIME, true) {
			@Override
			public void onEntry(TileEntityBossBlock target) {
				target.tickCounter = FALLING_TIME;
			}

			@Override
			public void onTick(TileEntityBossBlock target) {
				target.tickCounter--;
			}

			@Override
			public State getNextState(TileEntityBossBlock target) {
				return (target.tickCounter <= 0)? EXPLODING : null;
			}
		},
		EXPLODING(666, 0, true) {
			@Override
			public void onEntry(TileEntityBossBlock target) {
				if(!target.world.isRemote)
					target.explode();
			}

			@Override
			public State getNextState(TileEntityBossBlock target) {
				return null;
			}
		};

		public final float rotationSpeed;

		public final float progressSpeed;

		public final boolean specialEffects;

		public void onEntry(TileEntityBossBlock target) {}

		public void onTick(TileEntityBossBlock target) {}

		public abstract State getNextState(TileEntityBossBlock target);

		private State(float rotationSpeed, float riseSpeed, boolean specialEffects) {
			this.rotationSpeed = rotationSpeed;
			this.progressSpeed = riseSpeed;
			this.specialEffects = specialEffects;
		}
	}

	public int tickCounter;

	private float rotation;
	private float progress;

	private float rotationSpeed;
	private float progressSpeed;

	private State stage;

	private GameProfile owner;

	public void set(State value) {
		Preconditions.checkNotNull(value);
		this.stage = value;
		markDirty();
	}

	public float getRotation(float partialTickTime) {
		return rotation + rotationSpeed * partialTickTime;
	}

	public float getProgress(float partialTickTime) {
		return progress + progressSpeed * partialTickTime;
	}

	public float getOffset(float partialTickTime) {
		return getProgress(partialTickTime) * MAX_HEIGHT;
	}

	public State tryRandomlyChangeState(int delay, State nextState) {
		return (tickCounter % delay == 0) && (RANDOM.nextDouble() < STAGE_CHANGE_CHANCE)? nextState : null;
	}

	public TileEntityBossBlock() {
		stage = State.IDLE;
	}

	private void explode() {
		world.setBlockToAir(pos);
		world.createExplosion(null, 0.5 + pos.getX(), 0.5 + pos.getY(), 0.5 + pos.getZ(), 2, true);
		EntityNetherBeast boss = new EntityNetherBeast(world);
		boss.setPositionAndRotation(0.5 + pos.getX(), 0.5 + pos.getY(), 0.5 + pos.getZ(), 0, 0);
		world.spawnEntity(boss);
	}

	public State getState() {
		return stage;
	}

	@Override
	public void update() {
		State state = getState();
		rotationSpeed = (1 - SPEED_CHANGE_RATE) * rotationSpeed + SPEED_CHANGE_RATE * state.rotationSpeed;
		rotation += rotationSpeed;

		progressSpeed = (1 - SPEED_CHANGE_RATE) * progressSpeed + SPEED_CHANGE_RATE * state.progressSpeed;
		progress += progressSpeed;

		state.onTick(this);

		State nextState = state.getNextState(this);
		if (nextState != null) {
			stage = nextState;
			nextState.onEntry(this);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		return super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(pos.getX(), -1024, pos.getZ(), pos.getX() + 1, 1024, pos.getZ() + 1);
	}
}