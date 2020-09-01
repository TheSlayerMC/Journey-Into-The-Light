package net.slayer.api.entity;

import net.journey.entity.base.JEntityMob;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityPeacefullUntillAttacked extends JEntityMob {

    private int angerLevel = 0;

    public EntityPeacefullUntillAttacked(World w) {
        super(w);
        this.targetTasks.addTask(1, new EntityPeacefullUntillAttacked.AIHurtByAggressor());
        this.targetTasks.addTask(2, new EntityPeacefullUntillAttacked.AITargetAggressor());
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound var1) {
        super.writeEntityToNBT(var1);
        var1.setShort("Anger", (short) this.angerLevel);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound var1) {
        super.readEntityFromNBT(var1);
        this.angerLevel = var1.getShort("Anger");
    }

    public boolean canAttack() {
        return this.angerLevel > 0;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.getImmediateSource() instanceof EntityPlayer) {
            angerLevel = 400;
            addMeleeAttackingAI();
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    private void becomeAngryAt(Entity e) {
        this.angerLevel = 400 + this.rand.nextInt(400);

        if (e instanceof EntityLivingBase) {
            this.setRevengeTarget((EntityLivingBase) e);
        }
    }

    private class AIHurtByAggressor extends EntityAIHurtByTarget {

        public AIHurtByAggressor() {
            super(EntityPeacefullUntillAttacked.this, true);
        }

        protected void func_179446_a(EntityCreature p_179446_1_, EntityLivingBase p_179446_2_) {
            if (p_179446_1_ instanceof EntityPeacefullUntillAttacked) {
                ((EntityPeacefullUntillAttacked) p_179446_1_).becomeAngryAt(p_179446_2_);
            }
        }
    }

    private class AITargetAggressor extends EntityAINearestAttackableTarget {

        public AITargetAggressor() {
            super(EntityPeacefullUntillAttacked.this, EntityPlayer.class, true);
        }

        @Override
        public boolean shouldExecute() {
            return ((EntityPeacefullUntillAttacked) this.taskOwner).canAttack() && super.shouldExecute();
        }
    }
}