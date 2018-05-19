package net.journey.entity.mob.pet;

import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModTameable;

public class EntityEucaHopper extends EntityModTameable {

	public EntityEucaHopper(World w) {
		super(w);
		this.setSize(1.0F, 1.0F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(MobStats.fastSpeed);
		if(this.isTamed()) this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(MobStats.baseNetherHealth);
		else this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(MobStats.baseNetherHealth);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		EntityEucaHopper e = new EntityEucaHopper(this.world);
		String s = this.getOwnerId();
		if(s != null && s.trim().length() > 0) {
			e.setOwnerId(s);
			e.setTamed(true);
		}
		return e;
	}

	@Override
	public void setAttackTarget(EntityLivingBase par1EntityLivingBase) {
		super.setAttackTarget(par1EntityLivingBase);
		if(par1EntityLivingBase == null) this.setAngry(false);
		else if(!this.isTamed()) this.setAngry(true);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(18, new Float(this.getHealth()));
	}

	@Override
	protected void updateAITick() {
		this.dataWatcher.updateObject(18, Float.valueOf(this.getHealth()));
	}

	@Override
	protected void playStepSound(BlockPos pos, Block b) {
		this.playSound("mob.wolf.step", 0.15F, 1.0F);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("Angry", this.isAngry());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setAngry(par1NBTTagCompound.getBoolean("Angry"));
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if(this.isEntityInvulnerable(par1DamageSource)) return false;
		else {
			Entity entity = par1DamageSource.getEntity();
			this.aiSit.setSitting(false);
			if(entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))            
				par2 = (par2 + 1.0F) / 2.0F;
			return super.attackEntityFrom(par1DamageSource, par2);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		int i = this.isTamed() ? 10 : 5;
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this),i);
	}

	@Override
	public void setTamed(boolean par1) {
		super.setTamed(par1);
		if(par1) this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(MobStats.baseNetherHealth);
		else this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(MobStats.baseNetherHealth);
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

		if(this.isTamed()) {
			if(itemstack != null) {
				if(itemstack.getItem() instanceof ItemFood) {
					ItemFood itemfood = (ItemFood)itemstack.getItem();
					if(itemfood.isWolfsFavoriteMeat() && this.dataWatcher.getWatchableObjectFloat(18) < 20.0F) {
						if(!par1EntityPlayer.capabilities.isCreativeMode)
							--itemstack.stackSize;            
						this.heal(itemfood.getHealAmount(itemstack));
						if(itemstack.stackSize <= 0)
							par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem,(ItemStack)null);
						return true;
					}
				}
			}

			if(isOwner(par1EntityPlayer) && !this.world.isRemote && !this.isBreedingItem(itemstack)) {
				this.aiSit.setSitting(!this.isSitting());
				this.isJumping = false;
				this.navigator.clearPathEntity();
				this.setAttackTarget((EntityLivingBase)null);
			}
		}

		else if(itemstack != null && itemstack.getItem() == Items.APPLE && !this.isAngry()) {
			if(!par1EntityPlayer.capabilities.isCreativeMode)    
				--itemstack.stackSize;

			if(itemstack.stackSize <= 0) par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem,(ItemStack)null);

			if(!this.world.isRemote) {
				if(this.rand.nextInt(3) == 0) {
					this.setTamed(true);
					this.navigator.clearPathEntity();
					this.setAttackTarget((EntityLivingBase)null);
					this.aiSit.setSitting(true);
					this.setHealth(20.0F);
					this.setOwnerId(par1EntityPlayer.getUniqueID().toString());
					this.playTameEffect(true);
					this.world.setEntityState(this,(byte)7);
				} else {
					this.playTameEffect(false);
					this.world.setEntityState(this,(byte)6);
				}
			}
			return true;
		}
		return super.interact(par1EntityPlayer);
	}

	@Override
	public boolean isBreedingItem(ItemStack i) {
		return i == null ? false : i.getItem() == Items.apple;
	}

	@Override
	public boolean isAngry() {
		return(this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
	}

	@Override
	public void setAngry(boolean b) {
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
		if(b) this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 2)));
		else this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -3)));
	}

	@Override
	protected boolean canDespawn() {
		return !this.isTamed() && this.ticksExisted > 1200;
	}

	public boolean func_142018_a(EntityLivingBase par1EntityLivingBase, EntityLivingBase par2EntityLivingBase) {
		if(!(par1EntityLivingBase instanceof EntityCreeper) && !(par1EntityLivingBase instanceof EntityGhast)) {
			if(par1EntityLivingBase instanceof EntityEucaHopper) {
				EntityEucaHopper pig = (EntityEucaHopper)par1EntityLivingBase;
				if(pig.isTamed() && pig.getOwner() == par2EntityLivingBase) return false;
			}
			return par1EntityLivingBase instanceof EntityPlayer && par2EntityLivingBase instanceof EntityPlayer && !((EntityPlayer)par2EntityLivingBase).canAttackPlayer((EntityPlayer)par1EntityLivingBase) ? false : !(par1EntityLivingBase instanceof EntityHorse) || !((EntityHorse)par1EntityLivingBase).isTame();
		}
		else return false;
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	@Override
	public String setLivingSound() {
		return EnumSounds.HONGO.getNonPrefixedName();
	}

	@Override
	public String setHurtSound() {
		return EnumSounds.TURTLE.getNonPrefixedName();
	}

	@Override
	public String setDeathSound() {
		return EnumSounds.TURTLE.getNonPrefixedName();
	}
}