package net.journey.entity.projectile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityChaosProjectile extends EntityBouncingProjectile {

    public EntityChaosProjectile(World w) {
        super(w);
    }

    public EntityChaosProjectile(World par1, EntityPlayer par2, float damage) {
        super(par1, par2, 2, 3);
    }
}