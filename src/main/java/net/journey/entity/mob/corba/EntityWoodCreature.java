package net.journey.entity.mob.corba;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyLootTables;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EntityWoodCreature extends JEntityMob {

    public EntityWoodCreature(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(1.2F, 1.5F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.BUSH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.BUSH_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.BUSH_DEATH;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.world.isDaytime() && !this.world.isRemote) {
            float var1 = getBrightness();
        }

        List<Entity> e = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());
        for (Entity entity : e) {
            if (entity instanceof EntityPlayer && canEntityBeSeen(entity))
                ((EntityPlayer) entity).addPotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 60, 1));
        }
    }

    @Override
    protected ResourceLocation getLootTable() {
        return JourneyLootTables.WOOD_CREATURE;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.WOOD_CREATURE;
    }
}