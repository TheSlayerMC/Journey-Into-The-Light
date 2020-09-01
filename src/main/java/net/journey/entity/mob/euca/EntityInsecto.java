package net.journey.entity.mob.euca;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.journey.util.PotionEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityInsecto extends JEntityMob {

    public EntityInsecto(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(0.7F, 1.7F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 42);
        EntityAttributesHelper.setAttackDamage(this, 9);
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
	public ResourceLocation getLootTable() {
		return JourneyLootTables.INSECTO;
	}
}