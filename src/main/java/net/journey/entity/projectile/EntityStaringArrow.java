package net.journey.entity.projectile;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.journey.JourneyItems;
import net.journey.util.PotionEffects;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityStaringArrow extends EntityArrow implements IProjectile {

    private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, new Predicate<Entity>() {
        public boolean apply(@Nullable Entity e) {
            return e.canBeCollidedWith();
        }
    });
    private static final DataParameter<Byte> CRITICAL = EntityDataManager.createKey(EntityArrow.class, DataSerializers.BYTE);
    public int canBePickedUp;
    public int arrowShake;
    public Entity shootingEntity;
    private PotionEffect potioneffect = new PotionEffect(PotionEffects.getPotionFromID(PotionEffects.poison), 100, 2);
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inBlock;
    private int inData;
    private boolean inGround;
    private int ticksInGround;
    private int ticksInAir;
    private double damage = 4.0D;
    private int knockbackStrength;

    public EntityStaringArrow(World worldIn) {
        super(worldIn);
        Entity.setRenderDistanceWeight(10.0D);
        this.setSize(0.5F, 0.5F);
    }

    public EntityStaringArrow(World worldIn, double d, double d1, double d2) {
        super(worldIn);
        Entity.setRenderDistanceWeight(10.0D);
        this.setSize(0.5F, 0.5F);
        this.setPosition(d, d1, d2);
    }

    public EntityStaringArrow(World worldIn, EntityLivingBase e, EntityLivingBase eb, float f, float f1) {
        super(worldIn);
        Entity.setRenderDistanceWeight(10.0D);
        this.shootingEntity = e;

        if (e instanceof EntityPlayer) {
            this.canBePickedUp = 1;
        }

        this.posY = e.posY + e.getEyeHeight() - 0.10000000149011612D;
        double d0 = eb.posX - e.posX;
        double d1 = eb.getEntityBoundingBox().minY + eb.height / 3.0F - this.posY;
        double d2 = eb.posZ - e.posZ;
        double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D) {
            float f2 = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f3 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI));
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            this.setLocationAndAngles(e.posX + d4, this.posY, e.posZ + d5, f2, f3);
            float f4 = (float) (d3 * 0.20000000298023224D);
            this.shoot(d0, d1 + f4, d2, f, f1);
        }
    }

    public EntityStaringArrow(World worldIn, EntityLivingBase e, float f) {
        super(worldIn);
        Entity.setRenderDistanceWeight(10.0D);
        this.shootingEntity = e;
        if (e instanceof EntityPlayer) this.canBePickedUp = 1;
        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(e.posX, e.posY + e.getEyeHeight(), e.posZ, e.rotationYaw, e.rotationPitch);
        this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.posY -= 0.10000000149011612D;
        this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
        this.motionY = -MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI);
        this.shoot(this.motionX, this.motionY, this.motionZ, f * 1.5F, 1.0F);
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(CRITICAL, Byte.valueOf((byte) 0));
    }

    @Override
    public void shoot(double d, double d1, double d2, float f, float f1) {
        float f2 = MathHelper.sqrt(d * d + d1 * d1 + d2 * d2);
        d /= f2;
        d1 /= f2;
        d2 /= f2;
        d += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * f1;
        d1 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * f1;
        d2 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * f1;
        d *= f;
        d1 *= f;
        d2 *= f;
        this.motionX = d;
        this.motionY = d1;
        this.motionZ = d2;
        float f3 = MathHelper.sqrt(d * d + d2 * d2);
        this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(d, d2) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(d1, f3) * 180.0D / Math.PI);
        this.ticksInGround = 0;
    }

    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double x, double y, double z, float r, float yaw, int i, boolean b) {
        this.setPosition(x, y, z);
        this.setRotation(r, yaw);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setVelocity(double x, double y, double z) {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(x * x + z * z);
            this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, f) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            this.ticksInGround = 0;
        }
    }

    @Override
    public void onUpdate() {
        super.onEntityUpdate();

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, f) * 180.0D / Math.PI);
        }

        BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
        IBlockState iblockstate = this.world.getBlockState(blockpos);
        Block block = iblockstate.getBlock();

        if (iblockstate.getMaterial() != Material.AIR) {
            AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(this.world, blockpos);

            if (axisalignedbb != Block.NULL_AABB && axisalignedbb.offset(blockpos).contains(new Vec3d(this.posX, this.posY, this.posZ))) {
                this.inGround = true;
            }
        }

        if (this.arrowShake > 0) {
            --this.arrowShake;
        }

        if (this.inGround) {
            int j = block.getMetaFromState(iblockstate);

            if (block == this.inBlock && j == this.inData) {
                ++this.ticksInGround;

                if (this.ticksInGround >= 1200) {
                    this.setDead();
                }
            } else {
                this.inGround = false;
                this.motionX *= this.rand.nextFloat() * 0.2F;
                this.motionY *= this.rand.nextFloat() * 0.2F;
                this.motionZ *= this.rand.nextFloat() * 0.2F;
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        } else {

            this.timeInGround = 0;
            ++this.ticksInAir;
            Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d1, vec3d, false, true, false);
            vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
            vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (raytraceresult != null) {
                vec3d = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
            }

            Entity entity = this.findEntityOnPath(vec3d1, vec3d);

            if (entity != null) {
                raytraceresult = new RayTraceResult(entity);
            }

            if (raytraceresult != null && raytraceresult.entityHit instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) raytraceresult.entityHit;

                if (this.shootingEntity instanceof EntityPlayer && !((EntityPlayer) this.shootingEntity).canAttackPlayer(entityplayer)) {
                    raytraceresult = null;
                }
            }

            if (raytraceresult.entityHit instanceof EntityLivingBase && !(raytraceresult.entityHit instanceof EntityEnderman) && raytraceresult.entityHit != shootingEntity) {
                EntityLivingBase e = (EntityLivingBase) raytraceresult.entityHit;
                e.addPotionEffect((potioneffect));
            }

            if (raytraceresult != null && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onHit(raytraceresult);
            }

            if (this.getIsCritical()) {
                for (int k = 0; k < 4; ++k) {
                    this.world.spawnParticle(EnumParticleTypes.CRIT, this.posX + this.motionX * (double) k / 4.0D, this.posY + this.motionY * (double) k / 4.0D, this.posZ + this.motionZ * (double) k / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
                }
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float f4 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));

            for (this.rotationPitch = (float) (MathHelper.atan2(this.motionY, f4) * (180D / Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
            }

            while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
                this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
                this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float f1 = 0.99F;
            float f2 = 0.05F;

            if (this.isInWater()) {
                for (int i = 0; i < 4; ++i) {
                    float f3 = 0.25F;
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
                }

                f1 = 0.6F;
            }

            if (this.isWet()) {
                this.extinguish();
            }

            this.motionX *= f1;
            this.motionY *= f1;
            this.motionZ *= f1;

            if (!this.hasNoGravity()) {
                this.motionY -= 0.05000000074505806D;
            }

            this.setPosition(this.posX, this.posY, this.posZ);
            this.doBlockCollisions();
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        tagCompound.setShort("xTile", (short) this.xTile);
        tagCompound.setShort("yTile", (short) this.yTile);
        tagCompound.setShort("zTile", (short) this.zTile);
        tagCompound.setShort("life", (short) this.ticksInGround);
        ResourceLocation resourcelocation = Block.REGISTRY.getNameForObject(this.inBlock);
        tagCompound.setString("inTile", resourcelocation == null ? "" : resourcelocation.toString());
        tagCompound.setByte("inData", (byte) this.inData);
        tagCompound.setByte("shake", (byte) this.arrowShake);
        tagCompound.setByte("inGround", (byte) (this.inGround ? 1 : 0));
        tagCompound.setByte("pickup", (byte) this.canBePickedUp);
        tagCompound.setDouble("damage", this.damage);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {
        this.xTile = tagCompund.getShort("xTile");
        this.yTile = tagCompund.getShort("yTile");
        this.zTile = tagCompund.getShort("zTile");
        this.ticksInGround = tagCompund.getShort("life");

        if (tagCompund.hasKey("inTile", 8)) {
            this.inBlock = Block.getBlockFromName(tagCompund.getString("inTile"));
        } else {
            this.inBlock = Block.getBlockById(tagCompund.getByte("inTile") & 255);
        }

        this.inData = tagCompund.getByte("inData") & 255;
        this.arrowShake = tagCompund.getByte("shake") & 255;
        this.inGround = tagCompund.getByte("inGround") == 1;

        if (tagCompund.hasKey("damage", 99)) {
            this.damage = tagCompund.getDouble("damage");
        }

        if (tagCompund.hasKey("pickup", 99)) {
            this.canBePickedUp = tagCompund.getByte("pickup");
        } else if (tagCompund.hasKey("player", 99)) {
            this.canBePickedUp = tagCompund.getBoolean("player") ? 1 : 0;
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        if (!this.world.isRemote && this.inGround && this.arrowShake <= 0) {
            boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && entityIn.capabilities.isCreativeMode;

            if (this.canBePickedUp == 1 && !entityIn.inventory.addItemStackToInventory(new ItemStack(JourneyItems.essenceArrow, 1))) {
                flag = false;
            }

            if (flag) {
                entityIn.onItemPickup(this, 1);
                this.setDead();
            }
        }
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public double getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(double d) {
        this.damage = d;
    }

    @Override
    public void setKnockbackStrength(int k) {
        this.knockbackStrength = k;
    }

    @Override
    public boolean getIsCritical() {
        byte b0 = this.dataManager.get(CRITICAL).byteValue();
        return (b0 & 1) != 0;
    }

    @Override
    public void setIsCritical(boolean c) {
        byte b0 = this.dataManager.get(CRITICAL).byteValue();

        if (c) {
            this.dataManager.set(CRITICAL, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.dataManager.set(CRITICAL, Byte.valueOf((byte) (b0 & -2)));
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }
}