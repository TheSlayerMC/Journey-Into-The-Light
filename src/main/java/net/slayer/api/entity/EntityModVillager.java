package net.slayer.api.entity;

import net.journey.JITL;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

public abstract class EntityModVillager extends EntityVillager implements INpc, IMerchant, IMob {

    private UUID lastBuyingPlayer;
    private EntityPlayer buyingPlayer;
    private MerchantRecipeList buyingList;
    private float buying;

    public EntityModVillager(World var1) {
        super(var1);
        this.setSize(1.0F, 2.0F);
        this.setCanPickUpLoot(false);
        this.addDefaultEquipmentAndRecipies(75);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(2, new EntityAITradePlayer(this));
        this.tasks.addTask(2, new EntityAILookAtTradePlayer(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.27D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.27D));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100000.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    protected void updateAITasks() {
    }

    @Override
    public void setProfession(int professionId) {
        super.setProfession(5);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        float f = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        int i = 0;

        if (entityIn instanceof EntityLivingBase) {
            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase) entityIn).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag) {
            if (i > 0 && entityIn instanceof EntityLivingBase) {
                ((EntityLivingBase) entityIn).knockBack(this, (float) i * 0.5F, MathHelper.sin(this.rotationYaw * 0.017453292F), -MathHelper.cos(this.rotationYaw * 0.017453292F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0) {
                entityIn.setFire(j * 4);
            }

            if (entityIn instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) entityIn;
                ItemStack itemstack = this.getHeldItemMainhand();
                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer)) {
                    float f1 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

                    if (this.rand.nextFloat() < f1) {
                        entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
                        this.world.setEntityState(entityplayer, (byte) 30);
                    }
                }
            }
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean processInteract(EntityPlayer var1, EnumHand hand) {
        if (!this.world.isRemote) {
            abstractInteract(var1);
            var1.openGui(JITL.instance, guiID(), this.world, getEntityId(), 0, 0);
            return true;
        } else {
            return super.processInteract(var1, hand);
        }
    }

    public abstract void abstractInteract(EntityPlayer p);

    public abstract int guiID();

    public abstract void addRecipies(MerchantRecipeList list);

    @Override
    public void writeEntityToNBT(NBTTagCompound var1) {
        super.writeEntityToNBT(var1);
        if (this.buyingList != null) {
            var1.setTag("Trades", this.buyingList.getRecipiesAsTags());
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound var1) {
        super.readEntityFromNBT(var1);
        if (var1.hasKey("Trades")) {
            NBTTagCompound var2 = var1.getCompoundTag("Trades");
            this.buyingList = new MerchantRecipeList(var2);
        }
    }

    @Override
    public void useRecipe(MerchantRecipe recipe) {
        recipe.incrementToolUses();

        if (recipe.getToolUses() == 1) {
            if (this.buyingPlayer != null) {
                this.lastBuyingPlayer = this.buyingPlayer.getUniqueID();
            } else {
                this.lastBuyingPlayer = null;
            }
        }
    }

    @Override
    public MerchantRecipeList getRecipes(EntityPlayer var1) {
        if (this.buyingList == null) {
            this.addDefaultEquipmentAndRecipies(30);
        }
        return this.buyingList;
    }

    private void addDefaultEquipmentAndRecipies(int par1) {
        if (this.buyingList != null) {
            this.buying = MathHelper.sqrt(this.buyingList.size()) * 0.2F;
        } else {
            this.buying = 0.0F;
        }

        MerchantRecipeList rec = new MerchantRecipeList();

        addRecipies(rec);

        if (this.buyingList == null) {
            this.buyingList = new MerchantRecipeList();
        }

        for (int var3 = 0; var3 < par1 && var3 < rec.size(); ++var3) {
            this.buyingList.add(rec.get(var3));
        }
    }
}