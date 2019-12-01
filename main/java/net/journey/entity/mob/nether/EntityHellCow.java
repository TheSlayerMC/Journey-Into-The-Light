package net.journey.entity.mob.nether;

import net.journey.JourneyConsumables;
import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityPeacefullUntillAttacked;

public class EntityHellCow extends EntityPeacefullUntillAttacked {

	public EntityHellCow(World par1World) {
		super(par1World);
        ((PathNavigateGround)this.getNavigator()).setCanSwim(false);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
		this.isImmuneToFire = true;
		setSize(0.7F, 2.0F);
	}

	@Override
	public double setMovementSpeed() {
		return 0.3F;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity e) {
		boolean attacked = super.attackEntityAsMob(e);
		if(attacked) {
			e.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F)) * 4, 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F)) * 4);
		}
		return attacked;
	}
	
	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.baseNetherHealth;
	}

	@Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_COW_AMBIENT;
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_COW_HURT;
    }
	
	@Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_COW_DEATH;
    }
	
	@Override
	public Item getItemDropped() {
		return JourneyItems.flamingHide;
	}
	
	@Override
	public void onDeath(DamageSource d) {
		super.onDeath(d);
		if(d.getImmediateSource() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)d.getImmediateSource();
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

        if(itemstack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild()) {
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            itemstack.shrink(1);
            if(itemstack.isEmpty()) {
                player.setHeldItem(hand, new ItemStack(Items.LAVA_BUCKET));
            }
            else if(!player.inventory.addItemStackToInventory(new ItemStack(Items.LAVA_BUCKET))) {
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
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(4) == 0) dropItem(JourneyItems.blood, 1);
		if(rand.nextInt(5) == 0) dropItem(JourneyItems.blood, 2);
		if(rand.nextInt(2) == 0) dropItem(JourneyItems.flamingHide, 2);
		if(rand.nextInt(30) == 0) dropItem(JourneyItems.horn, 1);
		if(rand.nextInt(40) == 0) dropItem(JourneyItems.horn, 2);
		if (this.isBurning()) {
			if(rand.nextInt(4) == 0)dropItem(JourneyConsumables.flamingBeefCooked, 2);
			if(rand.nextInt(1) == 0)dropItem(JourneyConsumables.flamingBeefCooked, 1); 
			}
		else {
			if(rand.nextInt(4) == 0)dropItem(JourneyConsumables.flamingBeef, 2); 
			if(rand.nextInt(1) == 0)dropItem(JourneyConsumables.flamingBeef, 1); 
			}
		super.dropFewItems(b, j);
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.EMPTY;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.EMPTY;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.EMPTY;
	}
}