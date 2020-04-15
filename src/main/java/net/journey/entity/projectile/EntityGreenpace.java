package net.journey.entity.projectile;

import net.journey.JITL;
import net.journey.enums.EnumParticlesClasses;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class EntityGreenpace extends EntityBasicProjectile {

    public EntityGreenpace(World var1) {
        super(var1);
    }

    public EntityGreenpace(World var1, EntityLivingBase var3, float dam) {
        super(var1, var3, dam);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onUpdate() {
        Random rand = new Random();
        super.onUpdate();
        for (int i = 0; i < 6; ++i) {
            JITL.proxy.spawnParticle(EnumParticlesClasses.GREENPACE, this.world, this.posX, this.posY - 1.0F, this.posZ, false);
        }
    }
}