package net.journey.entity.projectile;

import net.journey.client.render.particles.EntityHellstoneFX;
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

public class EntitySentryKingFlamethrower extends EntityMagmaFireball {

    public EntitySentryKingFlamethrower(World w) {
        super(w);
    }

    public EntitySentryKingFlamethrower(World w, EntityLivingBase e, double x, double y, double z) {
        super(w, e, x, y, z);
        this.setSize(0.3125F, 0.3125F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onUpdate() {
        super.onUpdate();
        for (int i = 0; i < 6; ++i) {
        	/*EntityHellstoneFX effect = new EntityHellstoneFX(this.world, this.posX, this.posY - 1.0F, this.posZ, 0.0D, 0.0D, 0.0D);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(effect);*/
        }
        if (ticksExisted > 20) this.setDead();
    }
}