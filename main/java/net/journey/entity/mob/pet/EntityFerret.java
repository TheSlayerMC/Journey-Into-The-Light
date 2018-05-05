package net.journey.entity.mob.pet;

import com.google.common.base.Predicate;

import net.journey.JourneyAchievements;
import net.journey.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOcelotAttack;
import net.minecraft.entity.ai.EntityAIOcelotSit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModTameable;

public class EntityFerret extends EntityModTameable{
    private EntityAIAvoidEntity<EntityPlayer> avoidEntity;
    private EntityAITempt aiTempt;

    public EntityFerret(World worldIn){
        super(worldIn);
        this.setSize(0.6F, 0.7F);
        ((PathNavigateGround)this.getNavigator()).setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, this.aiTempt = new EntityAITempt(this, 0.6D, JourneyItems.floroPedal, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
        this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3F));
        this.tasks.addTask(8, new EntityAIOcelotAttack(this));
        this.tasks.addTask(9, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(10, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.targetTasks.addTask(1, new EntityAITargetNonTamed(this, EntityChicken.class, false, (Predicate)null));
    }

    @Override
    protected void entityInit(){
        super.entityInit();
        this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
    }

    @Override
    public void updateAITasks(){
        if (this.getMoveHelper().isUpdating()){
            double d0 = this.getMoveHelper().getSpeed();

            if (d0 == 0.6D){
                this.setSneaking(true);
                this.setSprinting(false);
            }
            else if (d0 == 1.33D){
                this.setSneaking(false);
                this.setSprinting(true);
            }
            else{
                this.setSneaking(false);
                this.setSprinting(false);
            }
        }
        else{
            this.setSneaking(false);
            this.setSprinting(false);
        }
    }

    @Override
    protected boolean canDespawn(){
        return !this.isTamed();
    }

    @Override
    protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
    }
    
    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Override
    public void fall(float distance, float damageMultiplier){}

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund){
        super.readEntityFromNBT(tagCompund);
    }

    @Override
    protected String getLivingSound(){
        return this.isTamed() ? (this.isInLove() ? "mob.cat.purr" : (this.rand.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow")) : "";
    }

    @Override
    protected String getHurtSound(){
        return "mob.cat.hitt";
    }

    @Override
    protected String getDeathSound(){
        return "mob.cat.hitt";
    }

    @Override
    protected float getSoundVolume(){
        return 0.4F;
    }

    @Override
    protected Item getDropItem(){
        return Items.leather;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn){
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount){
        if (this.isEntityInvulnerable(source)){
            return false;
        }
        else{
            this.aiSit.setSitting(false);
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_){}

    @Override
    public boolean interact(EntityPlayer player){
        ItemStack itemstack = player.inventory.getCurrentItem();

        if (this.isTamed()){
            if (this.isOwner(player) && !this.worldObj.isRemote && !this.isBreedingItem(itemstack)){
                this.aiSit.setSitting(!this.isSitting());
            }
        }
        else if (this.aiTempt.isRunning() && itemstack != null && itemstack.getItem() == JourneyItems.floroPedal && player.getDistanceSqToEntity(this) < 9.0D){
            if (!player.capabilities.isCreativeMode){
                --itemstack.stackSize;
            }

            if (itemstack.stackSize <= 0){
                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            }

            if (!this.worldObj.isRemote){
                if (this.rand.nextInt(3) == 0){
                    this.setTamed(true);
                    this.setOwnerId(player.getUniqueID().toString());
                    player.triggerAchievement(JourneyAchievements.achievementPet);
                    this.playTameEffect(true);
                    this.aiSit.setSitting(true);
                    this.worldObj.setEntityState(this, (byte)7);
                }
                else{
                    this.playTameEffect(false);
                    this.worldObj.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        return super.interact(player);
    }

    @Override
    public EntityFerret createChild(EntityAgeable ageable){
        EntityFerret entityferret = new EntityFerret(this.worldObj);

        if (this.isTamed()){
            entityferret.setOwnerId(this.getOwnerId());
            entityferret.setTamed(true);
        }

        return entityferret;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack){
        return stack != null && stack.getItem() == JourneyItems.floroPedal;
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal){
        if (otherAnimal == this){
            return false;
        }else if (!this.isTamed()){
            return false;
        }else if (!(otherAnimal instanceof EntityFerret)){
            return false;
        }else{
            EntityFerret entityferret = (EntityFerret)otherAnimal;
            return !entityferret.isTamed() ? false : this.isInLove() && entityferret.isInLove();
        }
    }

    @Override
    public boolean getCanSpawnHere(){
        return this.worldObj.rand.nextInt(3) != 0;
    }
    
    @Override
    public boolean isNotColliding(){
        if (this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(this.getEntityBoundingBox())){
            BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

            if (blockpos.getY() < this.worldObj.getSeaLevel()){
                return false;
            }

            Block block = this.worldObj.getBlockState(blockpos.down()).getBlock();

            if (block == Blocks.grass || block.isLeaves(worldObj, blockpos.down())){
                return true;
            }
        }

        return false;
    }

    @Override
    public void setTamed(boolean tamed){
        super.setTamed(tamed);
    }

    @Override
    protected void setupTamedAI(){
        if (this.avoidEntity == null){
            this.avoidEntity = new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 0.8D, 1.33D);
        }

        this.tasks.removeTask(this.avoidEntity);

        if (!this.isTamed()){
            this.tasks.addTask(4, this.avoidEntity);
        }
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata){
        livingdata = super.onInitialSpawn(difficulty, livingdata);

        if (this.worldObj.rand.nextInt(7) == 0){
            for (int i = 0; i < 2; ++i){
                EntityFerret entityferret = new EntityFerret(this.worldObj);
                entityferret.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                entityferret.setGrowingAge(-24000);
                this.worldObj.spawnEntityInWorld(entityferret);
            }
        }

        return livingdata;
    }

	@Override
	public String setLivingSound() {
		return null;
	}

	@Override
	public String setHurtSound() {
		return null;
	}

	@Override
	public String setDeathSound() {
		return null;
	}
}