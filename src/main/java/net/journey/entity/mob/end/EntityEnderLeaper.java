package net.journey.entity.mob.end;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityEnderLeaper extends JEntityMob {

    public EntityEnderLeaper(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(0.7F, 1.2F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 40);
        EntityAttributesHelper.setAttackDamage(this, 6);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.SHIMMERER;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.SHIMMERER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.SHIMMERER_DEATH;
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.ENDER_LEAPER;
	}
}