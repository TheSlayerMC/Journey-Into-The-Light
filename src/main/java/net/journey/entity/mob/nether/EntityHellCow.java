package net.journey.entity.mob.nether;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.init.JourneyLootTables;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityPeacefullUntillAttacked;

public class EntityHellCow extends EntityPeacefullUntillAttacked {

    public EntityHellCow(World par1World) {
        super(par1World);
        ((PathNavigateGround) this.getNavigator()).setCanSwim(false);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.isImmuneToFire = true;
        setSize(1.0F, 1.5F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 35);
        EntityAttributesHelper.setAttackDamage(this, 7);
        EntityAttributesHelper.setMovementSpeed(this, 0.3);
    }

    @Override
    public boolean attackEntityAsMob(Entity e) {
        boolean attacked = super.attackEntityAsMob(e);
        if (attacked) {
            e.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F)) * 4, 0.1D, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F)) * 4);
        }
        return attacked;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_COW_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_COW_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_COW_DEATH;
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.HELL_COW;
	}

    @Override
    public void onDeath(DamageSource d) {
        super.onDeath(d);
        if (d.getImmediateSource() instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) d.getImmediateSource();
            //p.trigger(JourneyAchievements.achievementKillNether);
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (itemstack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild()) {
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            itemstack.shrink(1);
            if (itemstack.isEmpty()) {
                player.setHeldItem(hand, new ItemStack(Items.LAVA_BUCKET));
            } else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.LAVA_BUCKET))) {
                player.dropItem(new ItemStack(Items.LAVA_BUCKET), false);
            }
            return true;
        } else {
            return super.processInteract(player, hand);
        }
    }

    public EntityHellCow createChild(EntityAgeable ageable) {
        return new EntityHellCow(this.world);
    }
}