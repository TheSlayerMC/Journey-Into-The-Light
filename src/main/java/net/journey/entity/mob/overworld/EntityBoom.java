package net.journey.entity.mob.overworld;

import net.journey.entity.AI.EntityAIBoomSwell;
import net.journey.entity.MobStats;
import net.journey.init.JourneyLootTables;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.entity.JEntityMob;

import org.jetbrains.annotations.NotNull;

public class EntityBoom extends JEntityMob {

    private static final DataParameter<Integer> STATE = EntityDataManager.createKey(EntityBoom.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> POWERED = EntityDataManager.createKey(EntityBoom.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IGNITED = EntityDataManager.createKey(EntityBoom.class, DataSerializers.BOOLEAN);
    private int fuseTime = 40, lastActiveTime, timeSinceIgnited, explosionRadius = 4;

    public EntityBoom(World par1World) {
        super(par1World);
        this.tasks.addTask(1, new EntityAIBoomSwell(this));
        this.tasks.addTask(2, new EntityAIRestrictSun(this));
        addMeleeAttackingAI();
        this.setSize(1.0F, 2.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CREEPER_PRIMED;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return SoundEvents.ENTITY_CREEPER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CREEPER_DEATH;
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
                    this.setFire(8);
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
                this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).isFullBlock() && this.dimension == 0;
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.BOOM_BOOM;
    }

    @Override
    public int getMaxFallHeight() {
        return this.getAttackTarget() == null ? 3 : 3 + (int) (this.getHealth() - 1.0F);
    }

    @Override
    public void fall(float f, float f1) {
        super.fall(f, f1);
        this.timeSinceIgnited = (int) (this.timeSinceIgnited + f * 1.5F);

        if (this.timeSinceIgnited > this.fuseTime - 5) {
            this.timeSinceIgnited = this.fuseTime - 5;
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(STATE, Integer.valueOf(-1));
        this.dataManager.register(POWERED, Boolean.valueOf(false));
        this.dataManager.register(IGNITED, Boolean.valueOf(false));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);

        if (this.dataManager.get(POWERED).booleanValue()) {
            nbt.setBoolean("powered", true);
        }

        nbt.setShort("Fuse", (short) this.fuseTime);
        nbt.setByte("ExplosionRadius", (byte) this.explosionRadius);
        nbt.setBoolean("ignited", this.hasIgnited());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.dataManager.set(POWERED, Boolean.valueOf(nbt.getBoolean("powered")));

        if (nbt.hasKey("Fuse", 99)) {
            this.fuseTime = nbt.getShort("Fuse");
        }

        if (nbt.hasKey("ExplosionRadius", 99)) {
            this.explosionRadius = nbt.getByte("ExplosionRadius");
        }

        if (nbt.getBoolean("ignited")) {
            this.ignite();
        }
    }

    @Override
    public void onUpdate() {
        if (this.isEntityAlive()) {
            this.lastActiveTime = this.timeSinceIgnited;

            if (this.hasIgnited()) {
                this.setBoomBoomState(1);
            }

            int i = this.getBoomBoomState();

            if (i > 0 && this.timeSinceIgnited == 0) {
                this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
            }

            this.timeSinceIgnited += i;

            if (this.timeSinceIgnited < 0) {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime) {
                this.timeSinceIgnited = this.fuseTime;
                this.explode();
            }
        }

        super.onUpdate();
    }

    @Override
    public boolean attackEntityAsMob(Entity e) {
        return true;
    }

    public boolean getPowered() {
        return this.dataManager.get(POWERED).booleanValue();
    }

    @SideOnly(Side.CLIENT)
    public float getFlashIntensity(float f) {
        return (this.lastActiveTime + (this.timeSinceIgnited - this.lastActiveTime) * f) / (this.fuseTime - 2);
    }

    public int getBoomBoomState() {
        return this.dataManager.get(STATE).intValue();
    }

    public void setBoomBoomState(int state) {
        this.dataManager.set(STATE, Integer.valueOf(state));
    }

    @Override
    public void onStruckByLightning(EntityLightningBolt e) {
        super.onStruckByLightning(e);
        this.dataManager.set(POWERED, Boolean.valueOf(true));
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
            this.world.playSound(player, this.posX, this.posY, this.posZ, SoundEvents.ITEM_FLINTANDSTEEL_USE, this.getSoundCategory(), 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
            player.swingArm(hand);

            if (!this.world.isRemote) {
                this.ignite();
                itemstack.damageItem(1, player);
                return true;
            }
        }

        return super.processInteract(player, hand);
    }

    private void explode() {
        if (!this.world.isRemote) {
            boolean flag = this.world.getGameRules().getBoolean("mobGriefing");

            if (this.getPowered()) {
                this.world.createExplosion(this, this.posX, this.posY, this.posZ, (float) (this.explosionRadius * 2), flag);
            } else {
                this.world.createExplosion(this, this.posX, this.posY, this.posZ, (float) this.explosionRadius, flag);
            }

            this.setDead();
        }
    }

    public boolean hasIgnited() {
        return this.dataManager.get(IGNITED).booleanValue();
    }

    public void ignite() {
        this.dataManager.set(IGNITED, Boolean.valueOf(true));
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.BOOM;
    }
}