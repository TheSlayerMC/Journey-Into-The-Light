package net.journey.entity.mob.overworld;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.JourneyWeapons;
import net.journey.entity.MobStats;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityWraith extends EntityModMob {

    public EntityWraith(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(0.7F, 2.0F);

    }

    @Override
    public boolean getCanSpawnHere() {
        return this.isValidLightLevel() &&
                this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).isFullBlock() && this.dimension == 0 || this.dimension == -1;
    }

    @Override
    public ItemStack getHeldItem(EnumHand hand) {
        return new ItemStack(JourneyWeapons.demonicSword);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.WraithDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.WraithDamage;
    }

    @Override
    public void onLivingUpdate() {
        if (this.world.isDaytime() && !this.world.isRemote && !this.isChild()) {
            float f = this.getBrightness();
            BlockPos blockpos = new BlockPos(this.posX, Math.round(this.posY), this.posZ);

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(blockpos)) {
                boolean flag = true;
                ItemStack itemstack = this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);

                if (itemstack != null) {
                    if (itemstack.isItemStackDamageable()) {
                        itemstack.setItemDamage(itemstack.getItemDamage() + this.rand.nextInt(2));

                        if (itemstack.getItemDamage() >= itemstack.getMaxDamage()) {
                            this.renderBrokenItemStack(itemstack);
                            setItemStackToSlot(EntityEquipmentSlot.MAINHAND, null);
                        }
                    }

                    flag = false;
                }

                if (flag) {
                    this.setDead();
                }
            }
        }

        if (this.isRiding() && this.getAttackTarget() != null && this.getRidingEntity() instanceof EntityChicken) {
            ((EntityLiving) this.getRidingEntity()).getNavigator().setPath(this.getNavigator().getPath(), 1.5D);
        }

        super.onLivingUpdate();
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.WRAITH;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.WRAITH_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.WRAITH_DEATH;
    }

    @Override
    public Item getItemDropped() {
        return JourneyItems.demonicBone;

    }


    @Override
    protected void dropFewItems(boolean b, int j) {
        if (rand.nextInt(5) == 0) dropItem(JourneyItems.demonicDust, rand.nextInt(5));
        super.dropFewItems(b, j);

    }
}