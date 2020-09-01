package net.journey.entity.mob.boiling;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityFlameLotus extends JEntityMob {

    public EntityFlameLotus(World par1World) {
        super(par1World);
        this.setSize(2.0F, 0.4F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 100);
        EntityAttributesHelper.setMovementSpeed(this, 0);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.FLAME_LOTUS;
	}
}