package net.journey.entity.projectile;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.journey.JourneyWeapons;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntitySizzlingKnife extends EntityTippedArrow implements IProjectile {

    private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, new Predicate<Entity>() {
        public boolean apply(@Nullable Entity e) {
            return e.canBeCollidedWith();
        }
    });

    public EntitySizzlingKnife(World worldIn) {
        super(worldIn);
    }

    public EntitySizzlingKnife(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    public EntitySizzlingKnife(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    //apparently used for Fourfa's attack
    public EntitySizzlingKnife(World worldIn, EntityLivingBase e, EntityLivingBase eb, float f, float f1) {
        super(worldIn);
        Entity.setRenderDistanceWeight(10.0D);
        this.shootingEntity = e;

        if (e instanceof EntityPlayer) {
            //lol idk what this is
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

    @Override
    protected void onHit(RayTraceResult target) {
        super.onHit(target);
        Entity hitEntity = target.entityHit;
        if (hitEntity != null && shootingEntity != null && hitEntity instanceof EntityLivingBase) {
            ((EntityLivingBase) hitEntity).addPotionEffect(new PotionEffect(PotionEffects.getPotionFromID(PotionEffects.moveSlow), 200, 5));
            hitEntity.setFire(10);
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(JourneyWeapons.sizzlingKnife);
    }
}