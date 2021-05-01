package net.jitl.common.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AbstractKnifeEntity extends AbstractArrowEntity {

    private boolean dealtDamage;

    public AbstractKnifeEntity(EntityType<? extends AbstractArrowEntity> type, World world) {
        super(type, world);
    }

    public AbstractKnifeEntity(EntityType<? extends AbstractArrowEntity> entityType, World worldIn, LivingEntity player) {
        super(entityType, player, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public void onClientTick() {
    }

    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        if((this.dealtDamage || this.isNoPhysics()) && entity != null) {
            if(!this.isAcceptibleReturnOwner()) {
                if(!this.level.isClientSide && this.pickup == AbstractArrowEntity.PickupStatus.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }
                this.remove();
            }
        }

        if(level.isClientSide) {
            onClientTick();
        }

        super.tick();
    }

    private boolean isAcceptibleReturnOwner() {
        System.out.println("Has owner");
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(this::pickupItem);
    }

    public abstract Item pickupItem();

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("damage dealt", dealtDamage);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        dealtDamage = compound.getBoolean("damage dealt");
    }
}
