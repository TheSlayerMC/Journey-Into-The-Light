package net.journey.entity.mob.overworld;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyItems;
import net.journey.util.JourneyLootTables;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntitySpyclops extends EntityModMob {

    public EntitySpyclops(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(1.0F, 2.0F);
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
    public double setAttackDamage(MobStats s) {
        return MobStats.SpyclopseDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.SpyclopseHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.SPYCLOPS;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.SPYCLOPS;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.SPYCLOPS_HURT;
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.SPYCLOPSE;
    }
}