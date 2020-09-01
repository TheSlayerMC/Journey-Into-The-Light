package net.journey.entity.mob.euca;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityPsyollom extends JEntityMob {
    public static final int ENTITY_TYPE = 25;

    public EntityPsyollom(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(1.7F, 2.7F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 44);
        EntityAttributesHelper.setAttackDamage(this, 10);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.PSYOLLOM;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.PSYOLLOM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.PSYOLLOM_HURT;
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.PSYOLLUM;
	}
}