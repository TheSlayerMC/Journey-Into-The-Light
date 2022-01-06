package net.jitl.common.entity.projectile;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AbstractKnifeEntity extends AbstractArrow {

    private boolean dealtDamage;

    public AbstractKnifeEntity(EntityType<? extends AbstractArrow> type, Level world) {
        super(type, world);
    }

    public AbstractKnifeEntity(EntityType<? extends AbstractArrow> entityType, Level worldIn, LivingEntity player) {
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
                if(!this.level.isClientSide && this.pickup == Pickup.ALLOWED) {
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
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
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
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("damage dealt", dealtDamage);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        dealtDamage = compound.getBoolean("damage dealt");
    }
}
