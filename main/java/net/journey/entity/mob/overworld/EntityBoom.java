package net.journey.entity.mob.overworld;

import net.journey.entity.MobStats;
import net.journey.entity.AI.EntityAIBoomSwell;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModMob;

public class EntityBoom extends EntityModMob {

	private int fuseTime = 40, lastActiveTime, timeSinceIgnited, explosionRadius = 4;

	public EntityBoom(World par1World) {
		super(par1World);
		this.tasks.addTask(1, new EntityAIBoomSwell(this));
        this.tasks.addTask(2, new EntityAIRestrictSun(this));
		addAttackingAI();
		this.setSize(1.0F, 2.0F);
	}
	
	@Override
    public void onLivingUpdate()
    {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote)
        {
            float f = this.getBrightness(1.0F);
            BlockPos blockpos = new BlockPos(this.posX, (double)Math.round(this.posY), this.posZ);

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canSeeSky(blockpos))
            {
                boolean flag = true;
                ItemStack itemstack = this.getEquipmentInSlot(4);

                if (itemstack != null)
                {
                    if (itemstack.isItemStackDamageable())
                    {
                        itemstack.setItemDamage(itemstack.getItemDamage() + this.rand.nextInt(2));

                        if (itemstack.getItemDamage() >= itemstack.getMaxDamage())
                        {
                            this.renderBrokenItemStack(itemstack);
                            this.setCurrentItemOrArmor(4, (ItemStack)null);
                        }
                    }

                    flag = false;
                }

                if (flag)
                {
                    this.setFire(8);
                }
            }
        }

        super.onLivingUpdate();
    }
	
	@Override
	public double setMovementSpeed(){
		return 0.200000011920929D;
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return 0;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.overworldHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.CREEPER;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.CREEPER;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.CREEPER_DEATH;
	}

	@Override
	public boolean getCanSpawnHere() {
		return
			   this.worldObj.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.grass || 
			   this.worldObj.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.leaves || 
			   this.worldObj.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.sand || 
			   this.worldObj.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.dirt;
	}

	@Override
	public Item getItemDropped() {
		return null;
	}

	@Override
	protected void dropFewItems(boolean b, int j) {
		for(int i = 0; i < 1 + rand.nextInt(1); i++) this.dropItem(Items.gunpowder, 1);
		if(rand.nextInt(3) == 0) this.dropItem(SlayerAPI.toItem(Blocks.tnt), 1);
	}

	@Override
    public int getMaxFallHeight() {
        return this.getAttackTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
    }

	@Override
	public void fall(float f, float f1) {
		super.fall(f, f1);
		this.timeSinceIgnited = (int)(this.timeSinceIgnited + f * 1.5F);

		if (this.timeSinceIgnited > this.fuseTime - 5)  {
			this.timeSinceIgnited = this.fuseTime - 5;
		}
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte) - 1));
		this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
		this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		super.writeEntityToNBT(p_70014_1_);

		if (this.dataWatcher.getWatchableObjectByte(17) == 1) {
			p_70014_1_.setBoolean("powered", true);
		}

		p_70014_1_.setShort("Fuse", (short)this.fuseTime);
		p_70014_1_.setByte("ExplosionRadius", (byte)this.explosionRadius);
		p_70014_1_.setBoolean("ignited", this.func_146078_ca());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		super.readEntityFromNBT(p_70037_1_);
		this.dataWatcher.updateObject(17, Byte.valueOf((byte)(p_70037_1_.getBoolean("powered") ? 1 : 0)));

		if (p_70037_1_.hasKey("Fuse", 99)) {
			this.fuseTime = p_70037_1_.getShort("Fuse");
		}

		if (p_70037_1_.hasKey("ExplosionRadius", 99)) {
			this.explosionRadius = p_70037_1_.getByte("ExplosionRadius");
		}

		if (p_70037_1_.getBoolean("ignited")) {
			this.func_146079_cb();
		}
	}

	@Override
	public void onUpdate() {
		if (this.isEntityAlive()) {
			this.lastActiveTime = this.timeSinceIgnited;

			if (this.func_146078_ca()) {
				this.setBoomBoomState(1);
			}

			int i = this.getBoomBoomState();

			if (i > 0 && this.timeSinceIgnited == 0) {
				this.playSound("creeper.primed", 1.0F, 0.5F);
			}

			this.timeSinceIgnited += i;

			if (this.timeSinceIgnited < 0) {
				this.timeSinceIgnited = 0;
			}

			if (this.timeSinceIgnited >= this.fuseTime) {
				this.timeSinceIgnited = this.fuseTime;
				this.func_146077_cc();
			}
		}

		super.onUpdate();
	}

	@Override
	public boolean attackEntityAsMob(Entity p_70652_1_) {
		return true;
	}

	public boolean getPowered() {
		return this.dataWatcher.getWatchableObjectByte(17) == 1;
	}

	@SideOnly(Side.CLIENT)
	public float getFlashIntensity(float p_70831_1_) {
		return (this.lastActiveTime + (this.timeSinceIgnited - this.lastActiveTime) * p_70831_1_) / (this.fuseTime - 2);
	}

	public int getBoomBoomState() {
		return this.dataWatcher.getWatchableObjectByte(16);
	}

	public void setBoomBoomState(int p_70829_1_) {
		this.dataWatcher.updateObject(16, Byte.valueOf((byte)p_70829_1_));
	}

	@Override
	public void onStruckByLightning(EntityLightningBolt p_70077_1_) {
		super.onStruckByLightning(p_70077_1_);
		this.dataWatcher.updateObject(17, Byte.valueOf((byte)1));
	}

	@Override
	protected boolean interact(EntityPlayer p_70085_1_) {
		ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();
		if (itemstack != null && itemstack.getItem() == Items.flint_and_steel) {
			this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "fire.ignite", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
			p_70085_1_.swingItem();
			if (!this.worldObj.isRemote) {
				this.func_146079_cb();
				itemstack.damageItem(1, p_70085_1_);
				return true;
			}
		}

		return super.interact(p_70085_1_);
	}

	private void func_146077_cc() {
		if (!this.worldObj.isRemote) {
			boolean flag = this.worldObj.getGameRules().getBoolean("mobGriefing");

			if (this.getPowered()) {
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)(this.explosionRadius * 2), flag);
			} else {
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius, flag);
			}

			this.setDead();
		}
	}

	public boolean func_146078_ca() {
		return this.dataWatcher.getWatchableObjectByte(18) != 0;
	}

	public void func_146079_cb() {
		this.dataWatcher.updateObject(18, Byte.valueOf((byte)1));
	}
}