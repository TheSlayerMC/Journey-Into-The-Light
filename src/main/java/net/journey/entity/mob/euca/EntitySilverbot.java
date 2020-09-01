package net.journey.entity.mob.euca;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntitySilverbot extends JEntityMob {

    public EntitySilverbot(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(0.7F, 1.2F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 30);
        EntityAttributesHelper.setAttackDamage(this, 7);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.ROBOT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.ROBOT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.ROBOT_DEATH;
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.SILVERBOT;
	}
}