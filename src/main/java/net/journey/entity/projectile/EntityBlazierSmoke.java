package net.journey.entity.projectile;

import net.journey.client.render.particles.EntityBlazierSmokeFX;
import net.journey.client.render.particles.EntityHellstoneFX;
import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBlazierSmoke extends EntitySmallFireball {

    public EntityBlazierSmoke(World w) {
        super(w);
    }

    public EntityBlazierSmoke(World w, EntityLivingBase e, double x, double y, double z) {
        super(w, e, x, y, z);
        this.setSize(0.3125F, 0.3125F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onUpdate() {
        super.onUpdate();
        for (int i = 0; i < 6; ++i) {
        	EntityBlazierSmokeFX effect = new EntityBlazierSmokeFX(this.world, this.posX, this.posY - 1.0F, this.posZ, 0.0D, 0.0D, 0.0D);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(effect);
        }
    }

    @Override
    protected void onImpact(RayTraceResult m) {
        if (!this.world.isRemote) {
            boolean flag;

            if (m.entityHit != null) {
                flag = m.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 5.0F);
                m.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(shootingEntity, m.entityHit), 5.0F);
                if (flag) {
                    this.applyEnchantments(this.shootingEntity, m.entityHit);
                    ((EntityLivingBase) m.entityHit).addPotionEffect(new PotionEffect(PotionEffects.getPotionFromID(PotionEffects.poison), 100, 1));
                }
            } else {
                flag = true;

                if (this.shootingEntity != null && this.shootingEntity instanceof EntityLiving) {
                    flag = this.world.getGameRules().getBoolean("mobGriefing");
                }
            }

            this.setDead();
        }
    }
}