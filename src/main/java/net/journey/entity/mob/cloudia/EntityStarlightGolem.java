package net.journey.entity.mob.cloudia;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyLootTables;
import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityStarlightGolem extends JEntityMob {

    public EntityStarlightGolem(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(1.7F, 3.0F);
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        //this.playSound("mob.irongolem.walk", 1.0F, 1.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.EMPTY;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return SoundEvents.ENTITY_IRONGOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRONGOLEM_DEATH;
    }

    @Override
    protected ResourceLocation getLootTable() {
        return JourneyLootTables.STARLIGHT_GOLEM;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.STARLIGHT_GOLEM;
    }
}