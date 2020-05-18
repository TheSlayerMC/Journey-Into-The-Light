package net.journey.entity.mob.euca;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyLootTables;
import net.journey.util.PotionEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityInsecto extends JEntityMob {


    public EntityInsecto(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(0.7F, 1.7F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.INSECTO;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.INSECTO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.INSECTO_HURT;
    }

    @Override
    public boolean attackEntityFrom(DamageSource e, float a) {
        if (e.getImmediateSource() instanceof EntityPlayer)
            ((EntityPlayer) e.getImmediateSource()).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 60, 1)));
        return super.attackEntityFrom(e, a);
    }

    @Override
    protected ResourceLocation getLootTable() {
        return JourneyLootTables.INSECTO;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.INSECTO;
    }
}