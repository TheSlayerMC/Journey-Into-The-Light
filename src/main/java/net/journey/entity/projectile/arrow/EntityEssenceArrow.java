package net.journey.entity.projectile.arrow;

import net.journey.init.items.JourneyItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.EnumSet;

public class EntityEssenceArrow extends EntityArrow implements IProjectile {
    public int canBePickedUp;
    public Entity shootingEntity;
    protected boolean inGround;
    protected int timeInGround;
    private EnumSet<BowEffects> effects;

    public EntityEssenceArrow(World worldIn) {
        super(worldIn);
    }

    public EntityEssenceArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityEssenceArrow(World worldIn, EntityLivingBase shooter, EnumSet<BowEffects> effects) {
        super(worldIn, shooter);
        this.shootingEntity = shooter;
        this.effects = effects;
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
        EntityLivingBase hitEntity = (EntityLivingBase) target.entityHit;
        if (hitEntity != null && shootingEntity != null && hitEntity instanceof EntityLivingBase) {
            if (effects.contains(BowEffects.WITHER)) {
                applyPotionEffect(hitEntity, MobEffects.WITHER, 100, 2);
            }
            if (effects.contains(BowEffects.FLAME)) {
                hitEntity.setFire(5);
            }
            if (effects.contains(BowEffects.SLOWNESS)) {
                applyPotionEffect(hitEntity, MobEffects.WITHER, 100, 2);
            }
            if (effects.contains(BowEffects.POISON)) {
                applyPotionEffect(hitEntity, MobEffects.POISON, 100, 2);
            }
            if (effects.contains(BowEffects.DEFAULT)) {
            }
            if (effects.contains(BowEffects.DOUBLE_ARROW)) {
            }
            if (effects.contains(BowEffects.CONSUMES_ESSENCE)) {
            }
        }
    }

    private void applyPotionEffect(EntityLivingBase effectedEntity, Potion potionEffect, int duration, int amplifier) {
        effectedEntity.addPotionEffect(new PotionEffect(potionEffect, duration, amplifier));
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(JourneyItems.essenceArrow);
    }

    public enum BowEffects {
        WITHER, SLOWNESS, FLAME, POISON, DEFAULT, DOUBLE_ARROW, CONSUMES_ESSENCE
    }
}