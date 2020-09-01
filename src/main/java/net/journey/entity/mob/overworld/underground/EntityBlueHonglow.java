package net.journey.entity.mob.overworld.underground;

import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityBlueHonglow extends JEntityMob {

    public EntityBlueHonglow(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        this.setSize(1.0F, 2.0F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, MobStats.BLUE_HONGLOW_HEALTH);
        EntityAttributesHelper.setAttackDamage(this, MobStats.BLUE_HONGLOW_DAMAGE);
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
    public float getBrightness() {
        return 15728880F;
    }

    @Override
    public int getBrightnessForRender() {
        return 100000;
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.BLUE_HONGLOW;
    }

    @Override
    public void onDeath(DamageSource d) {
        super.onDeath(d);
        if (d.getImmediateSource() instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) d.getImmediateSource();
            //p.triggerAchievement(JourneyAchievements.achievementCave);
        }
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 40.0D && super.getCanSpawnHere() &&
                this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getMaterial() == Material.ROCK && this.dimension == 0;
    }
}