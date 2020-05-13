package net.journey.entity.mob.cloudia;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyItems;
import net.journey.util.JourneyLootTables;
import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityStarlightGolem extends EntityModMob {

    public EntityStarlightGolem(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(1.7F, 3.0F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.StarlightGolemDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.StarlightGolemHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.EMPTY;
    }

    @Override
    public SoundEvent setHurtSound() {
        return SoundEvents.ENTITY_IRONGOLEM_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return SoundEvents.ENTITY_IRONGOLEM_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        //this.playSound("mob.irongolem.walk", 1.0F, 1.0F);
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.STARLIGHT_GOLEM;
    }
}