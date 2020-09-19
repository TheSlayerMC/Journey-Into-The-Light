package net.journey.entity.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntitySpiritCrystal extends Entity {

	public EntitySpiritCrystal(World worldIn) {
		super(worldIn);

		setSize(3, 3);
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {
	}

	@Override
	protected void entityInit() {
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		return pass == 1;
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		if (!this.world.isRemote) {
			//if totems are activated, make shoot out a bunch of entityItems from a loot table
		}
		return true;
	}
}
