package net.journey.entity.mob.cloudia;

import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyItems;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityPeacefullUntillAttacked;

public class EntityStarlightTransporter extends EntityPeacefullUntillAttacked {

    public EntityStarlightTransporter(World w) {
        super(w);
        setSize(1.5F, 1.7F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, MobStats.STARLIGHT_TRANSPORTER_HEALTH);
        EntityAttributesHelper.setAttackDamage(this, MobStats.STARLIGHT_TRANSPORTER_DAMAGE);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.BUSH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.BUSH_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.BUSH_DEATH;
    }

    @Override
    protected void dropFewItems(boolean b, int j) {
        if (rand.nextInt(25) == 0) dropItem(JourneyItems.cloudiaOrb, 1);
        super.dropFewItems(b, j);

    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.STARLIGHT_TRANSPORTER;
	}
}