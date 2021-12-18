package net.jitl.common.entity.frozen;

import net.jitl.common.block.tileentity.PedestalTile;
import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.init.JBlocks;
import net.jitl.init.JEntities;
import net.jitl.init.JItems;
import net.jitl.init.JSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
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
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class FrozenGuardianEntity extends CreatureEntity {
    private static final DataParameter<Boolean> DATA_IS_ACTIVATED = EntityDataManager.defineId(FrozenGuardianEntity.class, DataSerializers.BOOLEAN);

    private int DEATH_TIMER;
    private int COUNTED_FULL_PEDESTALS;

    public FrozenGuardianEntity(EntityType<? extends FrozenGuardianEntity> entityType, World world) {
        super(entityType, world);
        this.DEATH_TIMER = 50;
        this.COUNTED_FULL_PEDESTALS = 0;
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
            this.DEATH_TIMER--;
            this.level.addParticle(ParticleTypes.CLOUD, this.getX() - 0.5D + random.nextDouble(), this.getY() + 0.5D + random.nextDouble(), this.getZ() - 0.5D + random.nextDouble(), this.random.nextGaussian() * 0.05D, 0.15D, this.random.nextGaussian() * 0.05D);
            if(DEATH_TIMER <= 0){
                this.kill();
                boolean spawnedItem = false;
                if(!level.isClientSide && spawnedItem == false) {
                    this.level.addFreshEntity(new ItemEntity(level, this.position().x + 0.5F, this.position().y + 1.4F, this.position().z + 0.5F, new ItemStack(JItems.STAFF_OF_CONJURING)));
                    spawnedItem = true;
                }
                this.COUNTED_FULL_PEDESTALS = 0;
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
        compound.putInt("counted_fill_pedestals", 0);
        compound.putInt("death_timer", 5);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        compound.getBoolean("activated");
        compound.getInt("counted_fill_pedestals");
        compound.getInt("death_timer");
    }

    public void setActivated(boolean activated) {
        this.entityData.set(DATA_IS_ACTIVATED, activated);
    }

    public boolean isActivated() {
        return this.entityData.get(DATA_IS_ACTIVATED);
    }

    @Override
    protected ActionResultType mobInteract(PlayerEntity playerEntity, Hand hand) {
        int check_radius = 8;
        final World world = this.level;
        final BlockPos entityPos = new BlockPos(this.position());
        for (int x = -check_radius; x <= check_radius; x++) {
            for (int z = -check_radius; z <= check_radius; z++) {
                for (int y = -check_radius; y <= check_radius; y++) {
                    final BlockPos pos = entityPos.offset(x, y, z);
                    final Block block = world.getBlockState(pos).getBlock();
                    if (block == JBlocks.FROZEN_PEDISTAL) {
                        PedestalTile tile = (PedestalTile) world.getBlockEntity(pos);
                        if (tile != null && tile.getItem(0).getItem().equals(JItems.SAPPHIRE)) {
                            this.COUNTED_FULL_PEDESTALS++;
                                EssenciaBoltEntity bolt = new EssenciaBoltEntity(JEntities.ESSENCIA_BOLT_TYPE, level);
                                bolt.setPos(pos.getX(), pos.getY() + 1.2, pos.getZ());
                                bolt.setARGB(0x5acbff);
                                bolt.setVisualOnly(true);
                                if(!level.isClientSide)
                                    this.level.addFreshEntity(bolt);
                            tile.setItem(0, ItemStack.EMPTY);
                        }
                    }
                }
            }
        }
        if(this.COUNTED_FULL_PEDESTALS >= 8) {
            this.playSound(JSounds.FROZEN_GUARDIAN_DEATH.get(), 1.5F, 1.0F);
            setActivated(true);
        }
        System.out.println(COUNTED_FULL_PEDESTALS);
        return super.mobInteract(playerEntity, hand);
    }


}
