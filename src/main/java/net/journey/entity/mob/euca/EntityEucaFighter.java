package net.journey.entity.mob.euca;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityEucaFighter extends JEntityMob {

    public EntityEucaFighter(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(0.7F, 1.7F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 46);
        EntityAttributesHelper.setAttackDamage(this, 8);
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
	public ResourceLocation getLootTable() {
		return JourneyLootTables.EUCA_FIGHTER;
	}
}