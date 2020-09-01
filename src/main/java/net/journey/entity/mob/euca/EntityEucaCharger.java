package net.journey.entity.mob.euca;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityEucaCharger extends JEntityMob {

    public EntityEucaCharger(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(0.7F, 1.0F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 44);
        EntityAttributesHelper.setAttackDamage(this, 8);
        EntityAttributesHelper.setMovementSpeed(this, 0.5);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.HONGO;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.SAND_CRAWLER;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.SAND_CRAWLER;
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.EUCA_CHARGER;
	}
}