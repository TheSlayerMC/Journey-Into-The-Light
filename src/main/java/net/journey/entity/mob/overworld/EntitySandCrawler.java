package net.journey.entity.mob.overworld;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntitySandCrawler extends JEntityMob {

    public EntitySandCrawler(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        this.setSize(1.8F, 1.0F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 20);
        EntityAttributesHelper.setAttackDamage(this, 4);
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
    public ResourceLocation getLootTable() {
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