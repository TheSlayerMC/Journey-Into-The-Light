package net.journey.entity.mob.overworld;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.JEntityMob;

public class EntitySpectre extends JEntityMob {

    public EntitySpectre(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(1.0F, 2.0F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 25);
        EntityAttributesHelper.setAttackDamage(this, 4);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.WRAITH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.WRAITH_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.WRAITH_DEATH;
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
    public boolean getCanSpawnHere() {
        return this.isValidLightLevel() &&
                this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).isFullBlock() && this.dimension == 0 || this.dimension == -1;
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.SPECTRE;
    }
}