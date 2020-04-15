package net.journey.entity.mob.terrania.mob;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyConsumables;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityTerrashroom extends EntityModMob {

    public EntityTerrashroom(World par1World) {
        super(par1World);
        addAttackingAI();
        this.setSize(1.0F, 2.0F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.TerrashroomDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.TerrashroomHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.HONGO;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.HONGO_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.HONGO_HURT;
    }

    @Override
    protected void dropFewItems(boolean b, int j) {
        if (rand.nextInt(1) == 0) dropItem(JourneyConsumables.terrashroom, rand.nextInt(2));
        super.dropFewItems(b, j);
    }

    @Override
    public Item getItemDropped() {
        return null;
    }
}