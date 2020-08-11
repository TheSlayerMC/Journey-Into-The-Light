package net.journey.entity.mob.pet;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.init.JourneySounds;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityJourneyPet;

public class EntityEucaHopper extends EntityJourneyPet {

    public EntityEucaHopper(World w) {
        super(w);
        this.setSize(1.0F, 1.0F);
    }

    public EntityEucaHopper(World worldIn, EntityPlayer owner) {
        super(worldIn, owner);
        this.setSize(1.0F, 1.0F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 40);
        EntityAttributesHelper.setAttackDamage(this, 7.5);
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.HONGO;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.TURTLE_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.TURTLE_HURT;
    }


	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}
}