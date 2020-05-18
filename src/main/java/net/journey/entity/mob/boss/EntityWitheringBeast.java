package net.journey.entity.mob.boss;

import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityDeathSkull;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;
import org.jetbrains.annotations.NotNull;

public class EntityWitheringBeast extends EntityEssenceBoss implements IRangedAttackMob {


    public EntityWitheringBeast(World par1World) {
        super(par1World);
        setSize(2.0F, 4.8F);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAIAttackRanged(this, 0.27F, 30, 10.0F));
        addMeleeAttackingAI();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_WITHER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return SoundEvents.ENTITY_WITHER_HURT;
    }

    @Override
    public void onDeath(DamageSource damage) {
        //if(damage.getEntity() instanceof EntityPlayer) {
        //	EntityPlayer p = (EntityPlayer)damage.getEntity();
        //	p.triggerAchievement(JourneyAchievements.achievementWitherBeast); {
        //	}
        //}
        this.world.setBlockState(new BlockPos((int) Math.floor(this.posX + 0), ((int) Math.floor(this.posY + 1)), ((int) Math.floor(this.posZ + 0))), JourneyBlocks.trophyWitherBeast.getStateFromMeta(5));
        this.world.setBlockState(new BlockPos((int) Math.floor(this.posX + 0), ((int) Math.floor(this.posY + 0)), ((int) Math.floor(this.posZ + 0))), JourneyBlocks.journeyChest.getStateFromMeta(5));
        TileEntityJourneyChest te = (TileEntityJourneyChest) world.getTileEntity(new BlockPos((int) Math.floor(this.posX + 0), ((int) Math.floor(this.posY + 0)), ((int) Math.floor(this.posZ + 0))));
        switch (rand.nextInt(2)) {
            case 0:
                te.setInventorySlotContents(2, new ItemStack(JourneyWeapons.witheringBeastSword, 1));
                te.setInventorySlotContents(11, new ItemStack(JourneyItems.eucaPortalPiece_0, 1));
                break;
            case 1:
                te.setInventorySlotContents(5, new ItemStack(JourneyWeapons.witheringBeastSword, 1));
                te.setInventorySlotContents(15, new ItemStack(JourneyItems.eucaPortalPiece_0, 2));
                break;
        }
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase e, float f1) {
        this.launchWitherSkullToEntity(0, e);
    }

    private void launchWitherSkullToEntity(int var1, EntityLivingBase e) {
        this.launchWitherSkullToCoords(var1, e.posX, e.posY + e.getEyeHeight() * 0.5D, e.posZ, var1 == 0 && this.rand.nextFloat() < 0.001F);

    }

    private void launchWitherSkullToCoords(int var1, double f2, double f4, double f6, boolean f8) {
        //this.world.playAuxSFXAtEntity((EntityPlayer)null, 1014, new BlockPos(this), 0);
        double d3 = this.coordX(var1);
        double d4 = this.coordY(var1);
        double d5 = this.coordZ(var1);
        double d6 = f2 - d3;
        double d7 = f4 - d4;
        double d8 = f6 - d5;
        EntityDeathSkull entitydeathskull = new EntityDeathSkull(this.world, this, d6, d7, d8);

        if (f8) {
            entitydeathskull.setInvulnerable(true);
        }

        entitydeathskull.posY = d4;
        entitydeathskull.posX = d3;
        entitydeathskull.posZ = d5;
        this.world.spawnEntity(entitydeathskull);
    }

    private double coordX(int par1) {
        if (par1 <= 0) {
            return this.posX;
        } else {
            float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F * (float) Math.PI;
            float f1 = MathHelper.cos(f);
            return this.posX + f1 * 1.3D;
        }
    }

    private double coordY(int par1) {
        return par1 <= 0 ? this.posY + 3.0D : this.posY + 2.2D;
    }

    private double coordZ(int par1) {
        if (par1 <= 0) {
            return this.posZ;
        } else {
            float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F * (float) Math.PI;
            float f1 = MathHelper.sin(f);
            return this.posZ + f1 * 1.3D;
        }
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.WITHERING_BEAST;
    }
}