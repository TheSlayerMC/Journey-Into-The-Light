package net.journey.entity.projectile;

import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFrozenSnowball extends EntityThrowable {
    public EntityFrozenSnowball(World worldIn) {
        super(worldIn);
    }

    public EntityFrozenSnowball(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityFrozenSnowball(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public static void registerFixesSnowball(DataFixer fixer) {
        EntityThrowable.registerFixesThrowable(fixer, "Frozen Snowball");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            for (int i = 0; i < 8; ++i) {
                this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null) {
            int i = 0;

            if (result.entityHit instanceof EntityBlaze) {
                i = 3;
            }

            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) i);
            ((EntityLivingBase) result.entityHit).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 10, 5)));
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.setDead();
        }
    }
}