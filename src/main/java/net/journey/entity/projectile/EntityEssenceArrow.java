package net.journey.entity.projectile;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.journey.init.items.JourneyItems;
import net.journey.util.PotionEffects;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityEssenceArrow extends EntityArrow implements IProjectile {

    private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, new Predicate<Entity>() {
        public boolean apply(@Nullable Entity e) {
            return e.canBeCollidedWith();
        }
    });
    private static final DataParameter<Byte> CRITICAL = EntityDataManager.createKey(EntityArrow.class, DataSerializers.BYTE);
    public int canBePickedUp;
    public int arrowShake;
    public EntityArrow.PickupStatus pickupStatus;
    public Entity shootingEntity;
    protected boolean inGround;
    protected int timeInGround;
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    private int inData;
    private int ticksInGround;
    private int ticksInAir;
    private double damage = 0.0D;
    private int knockbackStrength;
    private BowEffects effect;

    public EntityEssenceArrow(World worldIn) {
        super(worldIn);
    }

    public EntityEssenceArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityEssenceArrow(World worldIn, EntityLivingBase shooter, BowEffects effect) {
        super(worldIn, shooter);
        this.shootingEntity = shooter;
        this.effect = effect;
    }

    public EntityEssenceArrow(World worldIn, EntityLivingBase shooter, EntityLivingBase p_i1755_3_, float p_i1755_4_, float p_i1755_5_) {
        super(worldIn);
        Entity.setRenderDistanceWeight(10.0D);
        this.shootingEntity = shooter;

        if (shooter instanceof EntityPlayer) {
            this.canBePickedUp = 1;
        }

        this.posY = shooter.posY + shooter.getEyeHeight() - 0.10000000149011612D;
        double d0 = p_i1755_3_.posX - shooter.posX;
        double d1 = p_i1755_3_.getEntityBoundingBox().minY + p_i1755_3_.height / 3.0F - this.posY;
        double d2 = p_i1755_3_.posZ - shooter.posZ;
        double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D) {
            float f = (float) (MathHelper.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f1 = (float) (-(MathHelper.atan2(d1, d3) * 180.0D / Math.PI));
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            this.setLocationAndAngles(shooter.posX + d4, this.posY, shooter.posZ + d5, f, f1);
            float f2 = (float) (d3 * 0.20000000298023224D);
            this.shoot(d0, d1 + f2, d2, p_i1755_4_, p_i1755_5_);
        }
    }

    public EntityEssenceArrow(World worldIn, EntityLivingBase shooter, float velocity) {
        super(worldIn);
        Entity.setRenderDistanceWeight(10.0D);
        this.shootingEntity = shooter;

        if (shooter instanceof EntityPlayer) {
            this.canBePickedUp = 1;
        }
        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.posY -= 0.10000000149011612D;
        this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
        this.motionY = -MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI);
        this.shoot(this.motionX, this.motionY, this.motionZ, velocity, 1.0F);
    }

    @Override
    protected void onHit(RayTraceResult target) {
        super.onHit(target);

        Entity hitEntity = target.entityHit;
        if (hitEntity != null && shootingEntity != null && hitEntity instanceof EntityLivingBase) {
            switch (this.effect) {
                case DARKNESS_BOW:
                    ((EntityLivingBase) hitEntity).addPotionEffect(new PotionEffect(PotionEffects.getPotionFromID(PotionEffects.wither), 100, 2));
                    break;
                case FLAME_BOW:
                    hitEntity.setFire(5);
                    break;
                case FROZEN_BOW:
                    ((EntityLivingBase) hitEntity).addPotionEffect(new PotionEffect(PotionEffects.getPotionFromID(PotionEffects.moveSlow), 100, 2));
                    break;
                case POISON_BOW:
                    ((EntityLivingBase) hitEntity).addPotionEffect(new PotionEffect(PotionEffects.getPotionFromID(PotionEffects.poison), 100, 2));
                    break;
                default:
                    break;

            }
        }


    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(JourneyItems.essenceArrow);
    }

    public enum BowEffects {
        DARKNESS_BOW, FROZEN_BOW, FLAME_BOW, POISON_BOW
    }


    //Leaving the rest of this class commented out. For now the above seems to fit the mod purposes

	/*
	@Override
	protected void entityInit() {
		this.dataManager.register(CRITICAL, Byte.valueOf((byte)0));
	}

	@Override
	public void shoot(double x, double y, double z, float velocity, float inaccuracy)
	{
		float f = MathHelper.sqrt(x * x + y * y + z * z);
		x = x / (double)f;
		y = y / (double)f;
		z = z / (double)f;
		x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
		y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
		z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
		x = x * (double)velocity;
		y = y * (double)velocity;
		z = z * (double)velocity;
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
		float f1 = MathHelper.sqrt(x * x + z * z);
		this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
		this.rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * (180D / Math.PI));
		this.prevRotationYaw = this.rotationYaw;
		this.prevRotationPitch = this.rotationPitch;
		this.ticksInGround = 0;
	}

	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean p_180426_10_)
	{
		this.setPosition(x, y, z);
		this.setRotation(yaw, pitch);
	}

	@SideOnly(Side.CLIENT)
	public void setVelocity(double x, double y, double z)
	{
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt(x * x + z * z);
			this.rotationPitch = (float)(MathHelper.atan2(y, (double)f) * (180D / Math.PI));
			this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
			this.rotationPitch = (float)(MathHelper.atan2(this.motionY, (double)f) * (180D / Math.PI));
			this.prevRotationYaw = this.rotationYaw;
			this.prevRotationPitch = this.rotationPitch;
		}

		BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
		IBlockState iblockstate = this.world.getBlockState(blockpos);
		Block block = iblockstate.getBlock();

		if (iblockstate.getMaterial() != Material.AIR)
		{
			AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(this.world, blockpos);

			if (axisalignedbb != Block.NULL_AABB && axisalignedbb.offset(blockpos).contains(new Vec3d(this.posX, this.posY, this.posZ)))
			{
				this.inGround = true;
			}
		}

		if (this.arrowShake > 0)
		{
			--this.arrowShake;
		}

		if (this.inGround)
		{
			int j = block.getMetaFromState(iblockstate);

			if ((block != this.inTile || j != this.inData) && !this.world.collidesWithAnyBlock(this.getEntityBoundingBox().grow(0.05D)))
			{
				this.inGround = false;
				this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
				this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
				this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
				this.ticksInGround = 0;
				this.ticksInAir = 0;
			}
			else
			{
				++this.ticksInGround;

				if (this.ticksInGround >= 1200)
				{
					this.setDead();
				}
			}

			++this.timeInGround;
		}
		else
		{
			this.timeInGround = 0;
			++this.ticksInAir;
			Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
			Vec3d vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d1, vec3d, false, true, false);
			vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
			vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

			if (raytraceresult != null)
			{
				vec3d = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
			}

			Entity entity = this.findEntityOnPath(vec3d1, vec3d);

			if (entity != null)
			{
				raytraceresult = new RayTraceResult(entity);
			}

			if (raytraceresult != null && raytraceresult.entityHit instanceof EntityPlayer)
			{
				EntityPlayer entityplayer = (EntityPlayer)raytraceresult.entityHit;

				if (this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer))
				{
					raytraceresult = null;
				}
			}

			if (raytraceresult != null && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult))
			{
				this.onHit(raytraceresult);
			}

			if (this.getIsCritical())
			{
				for (int k = 0; k < 4; ++k)
				{
					this.world.spawnParticle(EnumParticleTypes.CRIT, this.posX + this.motionX * (double)k / 4.0D, this.posY + this.motionY * (double)k / 4.0D, this.posZ + this.motionZ * (double)k / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
				}
			}

			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			float f4 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));

			for (this.rotationPitch = (float)(MathHelper.atan2(this.motionY, (double)f4) * (180D / Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
			{
				;
			}

			while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
			{
				this.prevRotationPitch += 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw < -180.0F)
			{
				this.prevRotationYaw -= 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
			{
				this.prevRotationYaw += 360.0F;
			}

			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
			float f1 = 0.99F;
			float f2 = 0.05F;

			if (this.isInWater())
			{
				for (int i = 0; i < 4; ++i)
				{
					float f3 = 0.25F;
					this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
				}

				f1 = 0.6F;
			}

			if (this.isWet())
			{
				this.extinguish();
			}

			this.motionX *= (double)f1;
			this.motionY *= (double)f1;
			this.motionZ *= (double)f1;

			if (!this.hasNoGravity())
			{
				this.motionY -= 0.05000000074505806D;
			}

			this.setPosition(this.posX, this.posY, this.posZ);
			this.doBlockCollisions();
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		tagCompound.setShort("xTile", (short)this.xTile);
		tagCompound.setShort("yTile", (short)this.yTile);
		tagCompound.setShort("zTile", (short)this.zTile);
		tagCompound.setShort("life", (short)this.ticksInGround);
		ResourceLocation resourcelocation = (ResourceLocation)Block.REGISTRY.getNameForObject(this.inTile);
		tagCompound.setString("inTile", resourcelocation == null ? "" : resourcelocation.toString());
		tagCompound.setByte("inData", (byte)this.inData);
		tagCompound.setByte("shake", (byte)this.arrowShake);
		tagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
		tagCompound.setByte("pickup", (byte)this.canBePickedUp);
		tagCompound.setDouble("damage", this.damage);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund)
	{
		this.xTile = tagCompund.getShort("xTile");
		this.yTile = tagCompund.getShort("yTile");
		this.zTile = tagCompund.getShort("zTile");
		this.ticksInGround = tagCompund.getShort("life");

		if (tagCompund.hasKey("inTile", 8))
		{
			this.inTile = Block.getBlockFromName(tagCompund.getString("inTile"));
		}
		else
		{
			this.inTile = Block.getBlockById(tagCompund.getByte("inTile") & 255);
		}

		this.inData = tagCompund.getByte("inData") & 255;
		this.arrowShake = tagCompund.getByte("shake") & 255;
		this.inGround = tagCompund.getByte("inGround") == 1;

		if (tagCompund.hasKey("damage", 99))
		{
			this.damage = tagCompund.getDouble("damage");
		}

		if (tagCompund.hasKey("pickup", 99))
		{
			this.canBePickedUp = tagCompund.getByte("pickup");
		}
		else if (tagCompund.hasKey("player", 99))
		{
			this.canBePickedUp = tagCompund.getBoolean("player") ? 1 : 0;
		}
	}*/


	/*

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn)
	{
		if (!this.world.isRemote && this.inGround && this.arrowShake <= 0)
		{
			boolean flag = this.pickupStatus == EntityArrow.PickupStatus.ALLOWED || this.pickupStatus == EntityArrow.PickupStatus.CREATIVE_ONLY && entityIn.capabilities.isCreativeMode;

			if (this.pickupStatus == EntityArrow.PickupStatus.ALLOWED && !entityIn.inventory.addItemStackToInventory(this.getArrowStack()))
			{
				flag = false;
			}

			if (flag)
			{
				entityIn.onItemPickup(this, 1);
				this.setDead();
			}
		}
	}

	@Override
	public void setDamage(double damageIn)
	{
		this.damage = damageIn;
	}

	@Override
	public double getDamage()
	{
		return this.damage;
	}

	@Override
	public void setKnockbackStrength(int knockback)
	{
		this.knockbackStrength = knockback;
	}

	@Override
	public void setIsCritical(boolean critical)
	{
		byte b0 = ((Byte)this.dataManager.get(CRITICAL)).byteValue();

		if (critical)
		{
			this.dataManager.set(CRITICAL, Byte.valueOf((byte)(b0 | 1)));
		}
		else
		{
			this.dataManager.set(CRITICAL, Byte.valueOf((byte)(b0 & -2)));
		}
	}

	@Override
	public boolean getIsCritical()
	{
		byte b0 = ((Byte)this.dataManager.get(CRITICAL)).byteValue();
		return (b0 & 1) != 0;
	}

*/


}