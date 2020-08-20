package net.journey.blocks.tileentity;

import net.journey.JITL;
import net.journey.entity.mob.senterian.mob.EntityMiniSentryLord;
import net.journey.entity.mob.senterian.mob.EntityMiniSentryStalker;
import net.journey.entity.mob.senterian.mob.EntityMiniSentryWalker;
import net.journey.enums.EnumParticlesClasses;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyItems;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.slayer.api.entity.JEntityMob;

import java.util.Random;

public class TileEntitySenterianAltar extends TileEntity implements ITickable {

	public Item orb;
	public boolean isFull;
	public int spawnTimer;
	public int spawnCount;

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("orb", Item.getIdFromItem(orb));
		nbt.setBoolean("isFull", false);
		nbt.setInteger("spawnTimer", 0);
		nbt.setInteger("spawnCount", 0);
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		orb = Item.getItemById(nbt.getInteger("orb"));
		isFull = nbt.getBoolean("isFull");
		spawnTimer = nbt.getInteger("spawnTimer");
		spawnCount = nbt.getInteger("spawnCount");
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound var1 = new NBTTagCompound();
		this.writeToNBT(var1);
		return new SPacketUpdateTileEntity(pos, 1, var1);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

	public void putInOrb(Item orb) {
		this.orb = orb;
	}

	public Item getOrbItem() {
		return orb;
	}

	public boolean getHasOrb() {
		return isFull;
	}

	@Override
	public void update() {
		int x = this.pos.getX(), y = this.pos.getY(), z = this.pos.getZ();
		isFull = getOrbItem() == JourneyItems.SENTRY_OBSERVER;
		if (!isFull) orb = null;

		if (isFull && spawnTimer == 0) {
			spawnTimer = 50;
			world.playSound(x, y, z, JourneySounds.SENTRY_ALTAR_ACTIVATE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
		}

		if (isFull && spawnTimer == 5) {
			world.playSound(x, y, z, JourneySounds.SENTRY_AMBIENT_1, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
		}

		if(spawnTimer >= 0) 
			spawnTimer--;

		if(spawnTimer <= 0)
			spawnTimer = 0;

		if(isFull && spawnTimer == 0) {
			spawnMob();
			spawnCount++;
		}

		if(spawnCount == 5) {
			putInOrb(null);
	        world.playSound(x, y, z, JourneySounds.SENTRY_ALTAR_DEACTIVATE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
			spawnCount = 0;
		}
	}

	public void spawnMob() {
		Random r = new Random();
		int x = this.pos.getX(), y = this.pos.getY(), z = this.pos.getZ();
		JEntityMob mob = null;
		switch (r.nextInt(3)) {
			case 0:
				mob = new EntityMiniSentryLord(world);
				break;
			case 1:
				mob = new EntityMiniSentryStalker(world);
				break;
			case 2:
				mob = new EntityMiniSentryWalker(world);
				break;
		default:
			mob = new EntityMiniSentryLord(world);
			break;
		}


		mob.setLocationAndAngles(x + 0.5, y + 1, z + 0.5, 0.0F, 0.0F);
		if (!world.isRemote && mob != null)
			world.spawnEntity(mob);

		world.playSound(x, y, z, JourneySounds.SENTRY_HURT_2, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
		if (world.isRemote) {
			for (int i = 0; i < 50; i++) {
				JITL.proxy.spawnParticle(EnumParticlesClasses.WITHER, this.world, x + 0.25, y + 0.5, z + 0.25, r.nextFloat(), r.nextFloat(), r.nextFloat());
			}
		}
	}
}
