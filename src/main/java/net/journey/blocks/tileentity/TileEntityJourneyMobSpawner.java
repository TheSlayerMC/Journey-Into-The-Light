package net.journey.blocks.tileentity;

import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.IDataFixer;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityJourneyMobSpawner extends TileEntity implements ITickable {
	
	private final MobSpawnerBaseLogic spawnerLogic = new MobSpawnerBaseLogic() {
		
		@Override
		public void broadcastEvent(int id) {
			TileEntityJourneyMobSpawner.this.world.addBlockEvent(TileEntityJourneyMobSpawner.this.pos, Blocks.MOB_SPAWNER, id, 0);
		}

		@Override
		public World getSpawnerWorld() {
			return TileEntityJourneyMobSpawner.this.world;
		}

		@Override
		public BlockPos getSpawnerPosition() {
			return TileEntityJourneyMobSpawner.this.pos;
		}

		@Override
		public void setNextSpawnData(WeightedSpawnerEntity p_184993_1_) {
			super.setNextSpawnData(p_184993_1_);

			if (this.getSpawnerWorld() != null) {
				IBlockState iblockstate = this.getSpawnerWorld().getBlockState(this.getSpawnerPosition());
				this.getSpawnerWorld().notifyBlockUpdate(TileEntityJourneyMobSpawner.this.pos, iblockstate, iblockstate,
						4);
			}
		}
	};

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.spawnerLogic.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		this.spawnerLogic.writeToNBT(compound);
		return compound;
	}

	@Override
	public void update() {
		this.spawnerLogic.updateSpawner();
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbttagcompound = this.writeToNBT(new NBTTagCompound());
		nbttagcompound.removeTag("SpawnPotentials");
		return nbttagcompound;
	}

	@Override
	public boolean receiveClientEvent(int id, int type) {
		return this.spawnerLogic.setDelayToMin(id) ? true : super.receiveClientEvent(id, type);
	}

	@Override
	public boolean onlyOpsCanSetNbt() {
		return true;
	}

	public MobSpawnerBaseLogic getSpawnerBaseLogic() {
		return this.spawnerLogic;
	}
}