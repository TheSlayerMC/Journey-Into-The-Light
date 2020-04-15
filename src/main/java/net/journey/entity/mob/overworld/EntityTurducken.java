package net.journey.entity.mob.overworld;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityPeacefullUntillAttacked;

public class EntityTurducken extends EntityPeacefullUntillAttacked {

    public EntityTurducken(World w) {
        super(w);
        setSize(0.7F, 1.0F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.TurduckenDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.TurduckenHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.BIRD;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.BIRD_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.BIRD_DEATH;
    }

    @Override
    public Item getItemDropped() {
        return JourneyConsumables.rocMeat;
    }

    @Override
    public boolean getCanSpawnHere() {
        return
                this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.GRASS ||
                        this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.LEAVES ||
                        this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.SAND ||
                        this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.DIRT && this.dimension == 0;
    }

    @Override
    protected void dropFewItems(boolean b, int j) {
        if (rand.nextInt(2) == 0) dropItem(JourneyItems.rocFeather, rand.nextInt(4));
        if (this.isBurning()) {
            if (rand.nextInt(2) == 0) dropItem(JourneyConsumables.cookedRocMeat, 2);
        } else {
            if (rand.nextInt(2) == 0) dropItem(JourneyConsumables.rocMeat, 2);
        }
        super.dropFewItems(b, j);
    }

}