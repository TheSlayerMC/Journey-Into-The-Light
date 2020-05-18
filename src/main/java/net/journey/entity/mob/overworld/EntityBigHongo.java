package net.journey.entity.mob.overworld;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyLootTables;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityBigHongo extends JEntityMob {

    public EntityBigHongo(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        this.setSize(1.0F, 2.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.HONGO;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.HONGO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.HONGO_HURT;
    }

    @Override
    protected ResourceLocation getLootTable() {
        return JourneyLootTables.BIG_HONGO;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.isValidLightLevel() &&
                this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).isFullBlock() && this.dimension == 0;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.BIG_HONGO;
    }
}