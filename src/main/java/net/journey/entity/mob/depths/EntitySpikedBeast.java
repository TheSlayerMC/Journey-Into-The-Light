package net.journey.entity.mob.depths;

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

public class EntitySpikedBeast extends JEntityMob {

    public EntitySpikedBeast(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(0.7F, 1.5F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.SPIKED_BEAST;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.SPIKED_BEAST_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.SPIKED_BEAST_HURT;
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
                ((EntityPlayer) entity).addPotionEffect(PotionEffects.setPotionEffect(PotionEffects.blindness, 60, 1));
        }
    }

    @Override
    protected ResourceLocation getLootTable() {
        return JourneyLootTables.SPIKED_BEAST;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.SPIKED_BEAST;
    }
}