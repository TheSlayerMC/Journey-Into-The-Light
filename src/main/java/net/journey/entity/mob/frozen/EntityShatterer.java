package net.journey.entity.mob.frozen;

import net.journey.entity.MobStats;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModFlying;

import java.util.Random;

public class EntityShatterer extends EntityModFlying {

    public EntityShatterer(World par1World) {
        super(par1World);
        this.moveHelper = new EntityShatterer.ShattererMoveHelper();
        initEntityAI();
        setSize(2F, 2F);
    }

    public void initEntityAI() {
        this.tasks.addTask(5, new EntityShatterer.AIRandomFly());
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) this.setDead();
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.ShattererHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.HONGO;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.SAND_CRAWLER;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.SAND_CRAWLER;
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.SHATTERER;
    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == JourneyBlocks.brittleIce;
    }

    private class AIRandomFly extends EntityAIBase {
        private EntityShatterer e = EntityShatterer.this;

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
            double d0 = this.e.posX + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double d1 = this.e.posY + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double d2 = this.e.posZ + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            this.e.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
        }
    }

    private class ShattererMoveHelper extends EntityMoveHelper {
        private EntityShatterer e = EntityShatterer.this;
        private int height;

        public ShattererMoveHelper() {
            super(EntityShatterer.this);
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
                if (!this.e.world.getCollisionBoxes(this.e, axisalignedbb).isEmpty()) {
                    return false;
                }
            }
            return true;
        }
    }
}