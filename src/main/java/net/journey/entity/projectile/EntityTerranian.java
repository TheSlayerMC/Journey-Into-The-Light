package net.journey.entity.projectile;

import net.journey.JITL;
import net.journey.client.render.particles.EntityCloudiaPortalFX;
import net.journey.enums.EnumParticlesClasses;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityTerranian extends EntitySmallFireball {

	public EntityTerranian(World w) {
		super(w);
	}
	
    public EntityTerranian(World w, EntityLivingBase e, double x, double y, double z) {
        super(w, e, x, y, z);
        this.setSize(0.3125F, 0.3125F);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void onUpdate() {
    	super.onUpdate();
		for(int i = 0; i < 6; ++i) {
			JITL.proxy.spawnParticle(EnumParticlesClasses.CLOUDIA, this.world, this.posX, this.posY - 1.0F, this.posZ, false);
		}
    }
    
	@Override
	protected void onImpact(RayTraceResult m) {
		if (!this.world.isRemote) {
            boolean flag;

            if (m.entityHit != null) {
                flag = m.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 5.0F);

                if (flag) {
                	 this.applyEnchantments(this.shootingEntity, m.entityHit);

                    if (!m.entityHit.isImmuneToFire()) {
                        m.entityHit.setFire(5);
                    }
                }
            } else {
                flag = true;

                if (this.shootingEntity != null && this.shootingEntity instanceof EntityLiving) {
                    flag = this.world.getGameRules().getBoolean("mobGriefing");
                }

                if (flag) {
                    BlockPos blockpos = m.getBlockPos().offset(m.sideHit);

                    if (this.world.isAirBlock(blockpos)) {
                        this.world.setBlockState(blockpos, Blocks.FIRE.getDefaultState());
                    }
                }
            }

            this.setDead();
        }
    }
}