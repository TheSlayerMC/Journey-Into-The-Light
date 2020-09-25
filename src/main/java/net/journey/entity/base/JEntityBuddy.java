package net.journey.entity.base;

import baubles.api.BaublesApi;
import net.journey.items.base.JItem;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModTameable;

import javax.annotation.Nullable;

public class JEntityBuddy extends EntityModTameable {

	JItem bauble;

	public JEntityBuddy(World w) {
		super(w);
		this.setSize(1.0F, 1.0F);
		this.setTamed(true);
	}

	public JEntityBuddy(World w, EntityPlayer owner, JItem bauble) {
		super(w);
		this.setSize(1.0F, 1.0F);
		this.setTamed(true);
		this.setOwnerId(owner.getUniqueID());
		this.bauble = bauble;
	}

	@Override
	public void onEntityUpdate() {
		if (ticksExisted % 20 == 0) {
			if (getOwner() != null && bauble != null) {
				if (BaublesApi.isBaubleEquipped((EntityPlayer) getOwner(), bauble) == -1) {
					this.setDead();
				}
			}
		}
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
