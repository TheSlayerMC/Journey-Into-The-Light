package net.journey.entity.projectile;

import net.journey.client.render.particles.EntityHellstoneFX;
import net.journey.client.render.particles.EntityIceballFX;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBlizzardFireball extends EntitySmallFireball {

	public EntityBlizzardFireball(World w) {
		super(w);
	}
	
    public EntityBlizzardFireball(World w, EntityLivingBase e, double x, double y, double z) {
        super(w, e, x, y, z);
        this.setSize(0.3125F, 0.3125F);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void onUpdate() {
    	super.onUpdate();
		for(int i = 0; i < 6; ++i) {
			EntityFX effect = new EntityIceballFX(this.worldObj, this.posX, this.posY - 1.0F, this.posZ, 0.0D, 0.0D, 0.0D);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(effect);
		}
    }

	@Override
	protected void onImpact(MovingObjectPosition m) {
		if (!this.worldObj.isRemote) {
            boolean flag;

            if (m.entityHit != null) {
                flag = m.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 5.0F);

                if (flag) {
                	 this.applyEnchantments(this.shootingEntity, m.entityHit);

                    if (!m.entityHit.isImmuneToFire()) {
                    }
                }
            } else {
                flag = true;

                if (this.shootingEntity != null && this.shootingEntity instanceof EntityLiving) {
                    flag = this.worldObj.getGameRules().getBoolean("mobGriefing");
                }

                if (flag) {
                    BlockPos blockpos = m.getBlockPos().offset(m.sideHit);

                    if (this.worldObj.isAirBlock(blockpos)) {
                        this.worldObj.setBlockState(blockpos, Blocks.fire.getDefaultState());
                    }
                }
            }

            this.setDead();
        }
    }
}