package net.journey.entity.mob.overworld;

import net.journey.client.render.particles.ParticleSwampFly;
import net.journey.entity.MobStats;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.WorldUtils;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.JEntityFlyingMob;

import java.util.Random;

public class EntitySwampFly extends JEntityFlyingMob {

    public EntitySwampFly(World par1World) {
        super(par1World);
        this.moveHelper = new EntitySwampFly.SwampFlyMoveHelper();
        initEntityAI();
        setSize(1F, 1F);
    }

    public void initEntityAI() {
        this.tasks.addTask(5, new EntitySwampFly.AIRandomFly());
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).isFullBlock() && this.dimension == 0;
    }

    @Override
    public float getBrightness() {
        return 15728880F;
    }

    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }

    public float getBrightness(float p_70013_1_) {
        return 13.0F;
    }

    @Override
    public double getEntityMaxHealth() {
        return MobStats.SwampFlyHealth;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return JourneySounds.EMPTY;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return JourneySounds.EMPTY;
    }

    @Override
    public SoundEvent getDeathSound() {
        return JourneySounds.EMPTY;
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.SWAMP_FLY;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        double d1 = this.posX;
        double d2 = this.posY + 0.5F;
        double d3 = this.posZ - 0.25F;
        for (int i = 0; i < 1; ++i) {
            WorldUtils.spawnParticle(new ParticleSwampFly(world, d1, d2, d3, 1.0F, 1.0F, 1.0F), world);
        }
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (itemstack.getItem() == Items.GLASS_BOTTLE) {
            world.playSound(player.posX, player.posY, player.posZ, JourneySounds.BOTTLE_PLUG, SoundCategory.MASTER, 1.0f, 1.0f, false);
            itemstack.shrink(1);
            if (itemstack.isEmpty()) {
                player.setHeldItem(hand, new ItemStack(JourneyBlocks.swampLamp));
            } else if (!player.inventory.addItemStackToInventory(new ItemStack(JourneyBlocks.swampLamp))) {
                player.dropItem(new ItemStack(JourneyBlocks.swampLamp), false);
            }
            this.setDead();
            return true;
        } else {
            return super.processInteract(player, hand);
        }
    }

    private class AIRandomFly extends EntityAIBase {
        private final EntitySwampFly e = EntitySwampFly.this;

        public AIRandomFly() {
            this.setMutexBits(1);
        }

        @Override
        public boolean shouldExecute() {
            EntityMoveHelper entitymovehelper = this.e.getMoveHelper();
            if (!entitymovehelper.isUpdating()) {
                return true;
            } else {
                double d0 = entitymovehelper.getX() - this.e.posX;
                double d1 = entitymovehelper.getY() - this.e.posY;
                double d2 = entitymovehelper.getZ() - this.e.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        @Override
        public boolean shouldContinueExecuting() {
            return false;
        }

        @Override
        public void startExecuting() {
            Random random = this.e.getRNG();
            double d0 = this.e.posX + (random.nextFloat() * 2.0F - 1.0F) * 4.0F;
            double d1 = this.e.posY + (random.nextFloat() * 2.0F - 1.0F) * 4.0F;
            double d2 = this.e.posZ + (random.nextFloat() * 2.0F - 1.0F) * 4.0F;
            this.e.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
        }
    }

    private class SwampFlyMoveHelper extends EntityMoveHelper {
        private final EntitySwampFly e = EntitySwampFly.this;
        private int height;

        public SwampFlyMoveHelper() {
            super(EntitySwampFly.this);
        }

        @Override
        public void onUpdateMoveHelper() {
            if (this.action == EntityMoveHelper.Action.MOVE_TO) {
                double d0 = this.posX - this.e.posX;
                double d1 = this.posY - this.e.posY;
                double d2 = this.posZ - this.e.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (this.height-- <= 0) {
                    this.height += this.e.getRNG().nextInt(5) + 2;
                    d3 = MathHelper.sqrt(d3);
                    if (this.canMove(this.posX, this.posY, this.posZ, d3)) {
                        this.e.motionX += d0 / d3 * 0.1D;
                        this.e.motionY += d1 / d3 * 0.1D;
                        this.e.motionZ += d2 / d3 * 0.1D;
                    } else {
                        this.action = EntityMoveHelper.Action.WAIT;
                    }
                }
            }
        }

        private boolean canMove(double x, double y, double z, double h) {
            double d4 = (x - this.e.posX) / h;
            double d5 = (y - this.e.posY) / h;
            double d6 = (z - this.e.posZ) / h;
            AxisAlignedBB axisalignedbb = this.e.getEntityBoundingBox();
            for (int i = 1; i < h; ++i) {
                axisalignedbb = axisalignedbb.offset(d4, d5, d6);
                if (!this.e.world.getCollisionBoxes(this.e, axisalignedbb).isEmpty())
                    return false;
            }
            return true;
        }
    }
}