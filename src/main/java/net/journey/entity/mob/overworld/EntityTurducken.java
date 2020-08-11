package net.journey.entity.mob.overworld;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
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
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 20);
        EntityAttributesHelper.setAttackDamage(this, 5);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.BIRD;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.BIRD_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.BIRD_DEATH;
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.TURDUCKEN;
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