package net.jitl.common.entity.projectile;

import net.jitl.init.JEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.util.Requirements;

import java.util.UUID;

public class CalciaMineEntity extends Entity {
    UUID owner;

    public CalciaMineEntity(EntityType<?> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public CalciaMineEntity(LivingEntity entity, Level world) {
        this(JEntities.CALCIA_MINE_TYPE, world);
        owner = entity.getUUID();
    }

    @Override
    public void tick() {
        int stage = tickCount * 3;
        if (stage <= 255) {
            if (this.level.isClientSide()) {
                this.level.addAlwaysVisibleParticle(ParticleTypes.ENTITY_EFFECT, getX(), getY() + (this.getBbHeight() / 2), getZ(), 1, 0,1 - ((double) stage / 255));
            }
        } else if (!this.level.isClientSide()) {
            for (int i = 0; i < 25; i++) {
                CalciaBurstEntity burst = new CalciaBurstEntity(this.level, getEntityFromUUID(owner), 5.0F);
                burst.setPos(this.getX(), this.getY(), this.getZ());
                double pitch = random.nextDouble() * Math.PI * 2;
                double y = Math.sin(pitch);
                double horizontal = Math.cos(pitch);
                double yaw = random.nextDouble() * Math.PI * 2;
                double x = Math.cos(yaw);
                double z = Math.sin(yaw);
                x *= horizontal;
                z *= horizontal;
                burst.shoot(x, y, z, 0.45F, 0F);
                this.level.addFreshEntity(burst);
            }
            this.kill();
        }
        super.tick();
    }

    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.fixed(3, 3);
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        this.tickCount = compound.getInt("time");
        this.owner = compound.getUUID("owner");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt("time", this.tickCount);
        compound.putUUID("owner", this.owner);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Nullable
    private LivingEntity getEntityFromUUID(UUID id) {
        Requirements.onServer(level);
        ServerLevel world = (ServerLevel) level;

        Entity entity = world.getEntity(id);
        // instance of is here because some mobs could have the same uuid
        // but even if there's another entity with the same uuid,
        // it's a very rare case and it won't cause any big troubles
        return entity instanceof LivingEntity ? (LivingEntity) entity : null;
    }
}
