package net.jitl.common.entity.projectile;

import net.jitl.init.JEntities;
import net.jitl.init.JItems;
import net.jitl.init.JSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class KnifeEntity extends AbstractKnifeEntity implements IRendersAsItem {
    private static final DataParameter<ItemStack> STACK = EntityDataManager.defineId(KnifeEntity.class, DataSerializers.ITEM_STACK);

    public KnifeEntity(EntityType<KnifeEntity> type, World world) {
        super(type, world);
    }

    public KnifeEntity(LivingEntity owner, World worldIn, ItemStack stack, float damage) {
        super(JEntities.KNIFE_TYPE, worldIn, owner);
        setBaseDamage(damage);
        setStack(stack.copy());
    }

    @Override
    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return JSounds.KNIFE.get();
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult rt) {
        super.onHitEntity(rt);
    }

    public boolean isInGround() {
        return this.inGround;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.put("stack", getStack().save(new CompoundNBT()));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        setStack(ItemStack.of(nbt.getCompound("stack")));
        if (getStack().isEmpty()) remove();
    }

    private void setStack(ItemStack stack) {
        this.getEntityData().set(STACK, stack);
    }

    private ItemStack getStack() {
        return this.getEntityData().get(STACK);
    }

    @Override
    public Item pickupItem() {
        return getStack().copy().getItem();
    }

    @Override
    public @NotNull IPacket<?> getAddEntityPacket() {//TODO move tosuperclass
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public @NotNull ItemStack getItem() {
        ItemStack stack = getStack();
        return stack.isEmpty() ? new ItemStack(JItems.IRON_THROWING_KNIFE) : stack;
    }

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(STACK, ItemStack.EMPTY);
        super.defineSynchedData();
    }

    @Override
    public void onSyncedDataUpdated(DataParameter<?> key) {
        if (key == STACK) {
            getStack().setEntityRepresentation(this);
        }
        super.onSyncedDataUpdated(key);
    }
}
