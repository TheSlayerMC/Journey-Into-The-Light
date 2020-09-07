package net.slayer.api.entity;

import net.journey.init.items.JourneyItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class EntityJourneyPet extends EntityModTameable {

	public EntityJourneyPet(World w) {
		super(w);
		setSize(1.0F, 1.0F);
	}
	
	public EntityJourneyPet(World worldIn, EntityPlayer owner) {
		super(worldIn);
		setSize(1.0F, 1.0F);
		this.setTamed(true);
		this.setOwnerId(owner.getUniqueID());
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		if(!itemstack.isEmpty()) {
			if (itemstack.getItem() == JourneyItems.petFood) {
				if (!player.capabilities.isCreativeMode) {
					itemstack.shrink(1);
				}
				this.heal(3);
				return true;
			}
		}
		if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack)) {
			this.aiSit.setSitting(!this.isSitting());
			this.isJumping = false;
			this.navigator.clearPath();
			this.setAttackTarget(null);
		}      
		return super.processInteract(player, hand);
	}
}