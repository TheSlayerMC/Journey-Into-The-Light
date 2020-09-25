package net.journey.entity.base;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModTameable;

import javax.annotation.Nullable;

public class JEntityBuddy extends EntityModTameable {

	public JEntityBuddy(World w) {
		super(w);
		setSize(1.0F, 1.0F);
		this.setTamed(true);
	}

	public JEntityBuddy(World w, EntityPlayer owner) {
		super(w);
		setSize(1.0F, 1.0F);
		this.setTamed(true);
		this.setOwnerId(owner.getUniqueID());
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public SoundEvent setLivingSound() {
		return null;
	}

	@Override
	public SoundEvent setHurtSound() {
		return null;
	}

	@Override
	public SoundEvent setDeathSound() {
		return null;
	}

	@Nullable
	@Override
	public EntityAgeable createChild(EntityAgeable entityAgeable) {
		return null;
	}
}
