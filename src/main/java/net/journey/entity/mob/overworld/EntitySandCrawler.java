package net.journey.entity.mob.overworld;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyLootTables;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModMob;

public class EntitySandCrawler extends EntityModMob {

    public EntitySandCrawler(World par1World) {
        super(par1World);
        addAttackingAI();
        this.setSize(1.8F, 1.0F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.SandCrawlerDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.SandCrawlerHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.SAND_CRAWLER;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.MAGMA_GIANT_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
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
}