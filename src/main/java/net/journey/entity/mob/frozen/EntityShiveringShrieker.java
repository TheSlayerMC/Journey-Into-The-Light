package net.journey.entity.mob.frozen;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.JEntityMob;

public class EntityShiveringShrieker extends JEntityMob {

    public EntityShiveringShrieker(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        this.setSize(0.65F, 1F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 40);
        EntityAttributesHelper.setAttackDamage(this, 8);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.SMALL_HONGO;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.SMALL_HONGO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.SMALL_HONGO_HURT;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 60.0D && super.getCanSpawnHere();
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.SHIVERING_SHRIEKER;
    }
}