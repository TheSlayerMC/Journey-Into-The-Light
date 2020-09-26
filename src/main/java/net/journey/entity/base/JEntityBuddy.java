package net.journey.entity.base;

import baubles.api.BaublesApi;
import net.journey.items.base.JItem;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class JEntityBuddy extends EntityTameable {

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
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0F, 1.5F, 1.0F));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
	}

	//TODO: NBT stuff for linking bauble durability, player owner (bauble user), and if bauble is / isn't equipped

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
	public boolean getLeashed() {
		return false;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Nullable
	@Override
	public EntityAgeable createChild(EntityAgeable entityAgeable) {
		return null;
	}
}
