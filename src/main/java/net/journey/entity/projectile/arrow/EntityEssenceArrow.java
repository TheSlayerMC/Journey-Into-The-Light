package net.journey.entity.projectile.arrow;

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
}