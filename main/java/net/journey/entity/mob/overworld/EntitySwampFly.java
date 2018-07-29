package net.journey.entity.mob.overworld;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModFlying;

public class EntitySwampFly extends EntityModFlying {

	public EntitySwampFly(World par1World) {
		super(par1World);
		this.moveHelper = new EntitySwampFly.ShattererMoveHelper();
		this.tasks.addTask(5, new EntitySwampFly.AIRandomFly());
		setSize(1F, 1F);
	}

	@Override
	public boolean getCanSpawnHere() {
		return this.world.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).isFullBlock();
	}

	@Override
	public float getBrightness() {
		return 15728880F;
	}

	public float getBrightness(float p_70013_1_) {
		return 13.0F;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.flyingHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.EMPTY;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.EMPTY;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.EMPTY;
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.world.isDaytime() && !this.world.isRemote && !this.isChild())
		{
			float f = this.getBrightness();
			BlockPos blockpos = new BlockPos(this.posX, Math.round(this.posY), this.posZ);

			if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(blockpos))
			{
				boolean flag = true;
				ItemStack itemstack = this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);

				if (itemstack != null)
				{
					if (itemstack.isItemStackDamageable())
					{
						itemstack.setItemDamage(itemstack.getItemDamage() + this.rand.nextInt(2));

						if (itemstack.getItemDamage() >= itemstack.getMaxDamage())
						{
							this.renderBrokenItemStack(itemstack);
							setItemStackToSlot(EntityEquipmentSlot.MAINHAND, null);
						}
					}

					flag = false;
				}

				if (flag)
				{
					this.setDead();
				}
			}
		}

		if (this.isRiding() && this.getAttackTarget() != null && this.getRidingEntity() instanceof EntityChicken)
		{
			((EntityLiving)this.getRidingEntity()).getNavigator().setPath(this.getNavigator().getPath(), 1.5D);
		}

		super.onLivingUpdate();
	}

	@Override
	public Item getItemDropped() {
		return null;
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		return pass == 1;
	}

	private class AIRandomFly extends EntityAIBase {
		private EntitySwampFly e = EntitySwampFly.this;

		public AIRandomFly() {
			this.setMutexBits(1);
		}

		@Override
		public boolean shouldExecute() {
			EntityMoveHelper entitymovehelper = this.e.getMoveHelper();
			if(!entitymovehelper.isUpdating()) {
				return true;
			} else {
				double d0 = entitymovehelper.getX() - this.e.posX;
				double d1 = entitymovehelper.getY() - this.e.posY;
				double d2 = entitymovehelper.getZ() - this.e.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				return d3 < 1.0D || d3 > 3600.0D;
			}
		}

		@Override
		public boolean shouldContinueExecuting() {
			return false;
		}

		@Override
		public void startExecuting() {
			Random random = this.e.getRNG();
			double d0 = this.e.posX + (random.nextFloat() * 2.0F - 1.0F) * 4.0F;
			double d1 = this.e.posY + (random.nextFloat() * 2.0F - 1.0F) * 4.0F;
			double d2 = this.e.posZ + (random.nextFloat() * 2.0F - 1.0F) * 4.0F;
			this.e.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
		}
	}

	private class ShattererMoveHelper extends EntityMoveHelper {
		private EntitySwampFly e = EntitySwampFly.this;
		private int height;

		public ShattererMoveHelper() {
			super(EntitySwampFly.this);
		}

		@Override
		public void onUpdateMoveHelper() {
			if(this.action == EntityMoveHelper.Action.MOVE_TO) {
				double d0 = this.posX - this.e.posX;
				double d1 = this.posY - this.e.posY;
				double d2 = this.posZ - this.e.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				if(this.height-- <= 0) {
					this.height += this.e.getRNG().nextInt(5) + 2;
					d3 = (double)MathHelper.sqrt(d3);
					if(this.canMove(this.posX, this.posY, this.posZ, d3)) {
						this.e.motionX += d0 / d3 * 0.1D;
						this.e.motionY += d1 / d3 * 0.1D;
						this.e.motionZ += d2 / d3 * 0.1D;
					} else {
						this.action = EntityMoveHelper.Action.WAIT;
					}
				}
			}
		}

		private boolean canMove(double x, double y, double z, double h)  {
			double d4 = (x - this.e.posX) / h;
			double d5 = (y - this.e.posY) / h;
			double d6 = (z - this.e.posZ) / h;
			AxisAlignedBB axisalignedbb = this.e.getEntityBoundingBox();
			for(int i = 1; i < h; ++i) {
				axisalignedbb = axisalignedbb.offset(d4, d5, d6);
				if (!this.e.world.getCollisionBoxes(this.e, axisalignedbb).isEmpty())
					return false;
			}
			return true;
		}
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		if(itemstack.getItem() == Items.GLASS_BOTTLE && !player.capabilities.isCreativeMode) {
			itemstack.shrink(1);
			if(itemstack.isEmpty()) {
				player.setHeldItem(hand, new ItemStack(JourneyBlocks.swampLamp));
			}
			else if(!player.inventory.addItemStackToInventory(new ItemStack(JourneyBlocks.swampLamp))) {
				player.dropItem(new ItemStack(JourneyBlocks.swampLamp), false);
			}
			this.setDead();
			return true;
		} else {
			return super.processInteract(player, hand);
		}
	}
}