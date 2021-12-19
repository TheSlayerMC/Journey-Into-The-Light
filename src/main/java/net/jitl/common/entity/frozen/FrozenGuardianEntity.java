package net.jitl.common.entity.frozen;

import net.jitl.common.block.tileentity.PedestalTile;
import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.init.JBlocks;
import net.jitl.init.JEntities;
import net.jitl.init.JItems;
import net.jitl.init.JSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class FrozenGuardianEntity extends CreatureEntity {
    private static final DataParameter<Boolean> DATA_IS_ACTIVATED = EntityDataManager.defineId(FrozenGuardianEntity.class, DataSerializers.BOOLEAN);

    private int death_timer;
    private int counted_full_pedestals;

    public FrozenGuardianEntity(EntityType<? extends FrozenGuardianEntity> entityType, World world) {
        super(entityType, world);
        this.death_timer = 50;
        this.counted_full_pedestals = 0;
    }

    @Override
    protected void registerGoals() { }

    public static boolean canSpawn(EntityType<? extends CreatureEntity> entityType, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return false;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance difficultyIn) {
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(JItems.STAFF_OF_CONJURING));
    }

    @Override
    public @NotNull IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        super.tick();
        if(isActivated()) {
            this.death_timer--;
            this.level.addParticle(ParticleTypes.CLOUD,
                    this.getX() - 0.5D + random.nextDouble(),
                    this.getY() + 0.5D + random.nextDouble(),
                    this.getZ() - 0.5D + random.nextDouble(),
                    this.random.nextGaussian() * 0.05D,
                    0.15D,
                    this.random.nextGaussian() * 0.05D);
            if (death_timer <= 0) {
                for (int i = 0; i < 24; ++i) {
                    this.level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                            this.getX() - MathHelper.nextDouble(random, -0.45D, 0.75D),
                            this.getY() + MathHelper.nextDouble(random, 0.5D, 2.0D),
                            this.getZ() - MathHelper.nextDouble(random, -0.45D, 0.75D),
                            this.random.nextGaussian() * 0.05D,
                            0.15D,
                            this.random.nextGaussian() * 0.05D);
                }
                this.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);
                this.remove();
                
                if (!level.isClientSide) {
                    this.level.addFreshEntity(new ItemEntity(level, this.position().x + 0.5F, this.position().y + 1.4F, this.position().z + 0.5F, new ItemStack(JItems.STAFF_OF_CONJURING, 1)));
                }
                this.death_timer = 100;
                this.counted_full_pedestals = 0;
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IS_ACTIVATED, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("activated", this.entityData.get(DATA_IS_ACTIVATED));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        setActivated(compound.getBoolean("activated"));
    }

    public void setActivated(boolean activated) {
        this.entityData.set(DATA_IS_ACTIVATED, activated);
    }

    public boolean isActivated() {
        return this.entityData.get(DATA_IS_ACTIVATED);
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean canBeLeashed(PlayerEntity player) {
        return false;
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public void push(Entity entity) { }

    @Override
    protected void doPush(Entity entityIn) { }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected ActionResultType mobInteract(PlayerEntity playerEntity, Hand hand) {
        int check_radius = 8;
        int totalPedestals = 0;
        final World world = this.level;
        final BlockPos entityPos = new BlockPos(this.position());
        for (int x = -check_radius; x <= check_radius; x++) {
            for (int z = -check_radius; z <= check_radius; z++) {
                for (int y = -check_radius; y <= check_radius; y++) {
                    final BlockPos pos = entityPos.offset(x, y, z);
                    final Block block = world.getBlockState(pos).getBlock();
                    if (block == JBlocks.FROZEN_PEDESTAL) {
                        PedestalTile tile = (PedestalTile) world.getBlockEntity(pos);
                        if (tile != null && tile.getItem(0).getItem().equals(JItems.SAPPHIRE)) {
                            if (isActivated()) {
                                if (!level.isClientSide) {
                                    summonLightning(pos);
                                    disableFogDensity();
                                }
                                tile.setItem(0, ItemStack.EMPTY);
                            }
                            totalPedestals++;
                            this.counted_full_pedestals++;
                        }
                    }
                }
            }
        }
        if (totalPedestals >= 8) {
            this.playSound(JSounds.FROZEN_GUARDIAN_DEATH.get(), 1.5F, 1.0F);
            setActivated(true);
        }
        return super.mobInteract(playerEntity, hand);
    }

    public void summonLightning(BlockPos pos) {
        EssenciaBoltEntity bolt = new EssenciaBoltEntity(JEntities.ESSENCIA_BOLT_TYPE, level);
        bolt.setPos(pos.getX(), pos.getY() + 1.2, pos.getZ());
        bolt.setARGB(0x5acbff);
        bolt.setVisualOnly(true);
        if(!level.isClientSide)
            this.level.addFreshEntity(bolt);
    }

    public void disableFogDensity() {
        int playerArea = 10;
        AxisAlignedBB axisalignedbb = AxisAlignedBB.unitCubeFromLowerCorner(this.position()).inflate(-playerArea, 10.0D, -playerArea);
        for(PlayerEntity player : this.level.getEntitiesOfClass(PlayerEntity.class, axisalignedbb)) {
            JPlayer capability = JPlayer.from(player);
            if(capability != null) {
                capability.fogDensity.setDensityEnabled(true);
                capability.detectAndSendChanges();
            }
        }
    }
}
