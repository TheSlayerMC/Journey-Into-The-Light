package net.journey.entity.mob.cloudia;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.JEntityMob;

public class EntityStarlightWalker extends JEntityMob {

    public EntityStarlightWalker(World w) {
        super(w);
        addMeleeAttackingAI();
        setSize(1.0F, 1.0F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 75);
        EntityAttributesHelper.setAttackDamage(this, 17);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.EMPTY;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return SoundEvents.ENTITY_IRONGOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRONGOLEM_DEATH;
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.STARLIGHT_WALKER;
	}
}