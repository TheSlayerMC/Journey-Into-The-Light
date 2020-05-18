package net.journey.entity.mob.overworld;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyLootTables;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntitySandCrawler extends JEntityMob {

    public EntitySandCrawler(World par1World) {
        super(par1World);
	    addMeleeAttackingAI();
	    this.setSize(1.8F, 1.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.SAND_CRAWLER;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.MAGMA_GIANT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.MAGMA_GIANT_HURT;
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.SAND_CRAWLER;
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
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.SAND_CRAWLER;
    }
}