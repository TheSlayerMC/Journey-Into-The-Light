package net.jitl.common.entity.nether;

import net.jitl.JITL;
import net.jitl.client.render.gui.BossBarRenderer;
import net.jitl.client.render.gui.EyeBarRenderer;
import net.jitl.common.entity.base.BossCrystalEntity;
import net.jitl.common.entity.base.IJourneyBoss;
import net.jitl.common.entity.goal.IdleHealGoal;
import net.jitl.common.helper.JBossInfo;
import net.jitl.common.helper.JMusic;
import net.jitl.init.JAnimations;
import net.jitl.init.JSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.animation.AnimationSystem;
import ru.timeconqueror.timecore.api.animation.AnimatedObject;
import ru.timeconqueror.timecore.api.animation.BlendType;
import ru.timeconqueror.timecore.api.animation.builders.AnimationSystemBuilder;

import javax.annotation.Nullable;
import java.util.Collections;

public class SoulWatcherEntity extends FlyingEntity implements IJourneyBoss, AnimatedObject<SoulWatcherEntity>, IRangedAttackMob {
    private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.NOTCHED_6);
    private final BossBarRenderer BOSS_BAR = new EyeBarRenderer(this, JITL.tl("gui/bossbars/soul_watcher.png").fullLocation());
    private static final JMusic BOSS_TRACK = new JMusic(JSounds.TEMPLE_GUARDIAN_MUSIC.get(), 2, 0, 0);
    private final AnimationSystem<SoulWatcherEntity> animationSystem;
    private static final DataParameter<Boolean> CLOSED = EntityDataManager.defineId(SoulWatcherEntity.class, DataSerializers.BOOLEAN);

    private static final String LAYER_IDLE = "idle";
    private static final String LAYER_CLOSED = "closed";

    public SoulWatcherEntity(EntityType<? extends SoulWatcherEntity> type, World worldIn) {
        super(type, worldIn);
        animationSystem = AnimationSystemBuilder.forEntity(this, worldIn, builder -> {
            builder.addLayer(LAYER_IDLE, BlendType.OVERRIDE, 1F);
            builder.addLayer(LAYER_CLOSED, BlendType.ADDING, 1F);
        }, predefinedAnimations -> predefinedAnimations.setIdleAnimation(new AnimationStarter(JAnimations.soulWatcherIdle), LAYER_IDLE));

        this.moveControl = new FlyingMovementController(this, 1, true);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.15D);
    }

    @Override
    public BossBarRenderer getBossBar() {
        return BOSS_BAR;
    }

    @Override
    public JMusic getBossMusic() {
        return BOSS_TRACK;
    }

    public void setClosed(boolean closed) {
        this.entityData.set(CLOSED, closed);
    }

    public boolean isClosed() {
        return this.entityData.get(CLOSED);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CLOSED, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.5, 50, 60.0F));
        this.goalSelector.addGoal(1, new IdleHealGoal(this, 1200));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        double x = target.getX() - this.getX();
        double y = target.getY(0.5) - this.getY(0.5);
        double z = target.getZ() - this.getZ();
        for (int count = 0; count < 10; count++) {
            SmallFireballEntity fireball = new SmallFireballEntity(this.level, this, x / 10, y / 10, z / 10);
            fireball.setPos(this.getX(), this.getY(0.5), this.getZ());
            fireball.shoot(x, y, z, 0.5F, 10.25F);
            this.level.addFreshEntity(fireball);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (isClosed()) {
            return false;
        } else {
            float quarterHealth = this.getMaxHealth() / 4;
            int quarterBefore = (int) Math.floor((this.getMaxHealth() - this.getHealth()) / quarterHealth);
            boolean hurt = super.hurt(source, amount);
            int quarterAfter = (int) Math.floor((this.getMaxHealth() - this.getHealth()) / quarterHealth);
            if (quarterAfter > quarterBefore && this.getHealth() > 0) {
                this.setHealth(quarterHealth * (4 - quarterAfter));
                setClosed(true);
            }
            return hurt;
        }
    }

    @Nullable
    protected ResourceLocation getCrystalLootTable() {
        return LootTables.BASTION_HOGLIN_STABLE;
    }

    @Nullable
    protected BossCrystalEntity.Type getDeathCrystalType() {
        return BossCrystalEntity.Type.NETHER;
    }

    @Override
    public void onRemovedFromWorld() {
        if (!level.isClientSide()) {
            BossCrystalEntity.Type crystalType = getDeathCrystalType();
            if (crystalType != null) {
                ResourceLocation lootTable = getCrystalLootTable();
                BossCrystalEntity crystal;
                if (lootTable == null) {
                    crystal = BossCrystalEntity.create(level, getPosition(0), getDeathCrystalType(), Collections.emptyList());
                } else {
                    crystal = BossCrystalEntity.create((ServerWorld) level, getPosition(0), getDeathCrystalType(), null, lootTable, 0L);
                }

                level.addFreshEntity(crystal);
            }
        }
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.5F;
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        setClosed(false);
        return false;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("closed", isClosed());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("closed")) {
            setClosed(compound.getBoolean("closed"));
        }
    }

    @Override
    public void startSeenByPlayer(ServerPlayerEntity player) {
        super.startSeenByPlayer(player);
        JBossInfo.addInfo(player, bossInfo, this);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayerEntity player) {
        super.stopSeenByPlayer(player);
        JBossInfo.removeInfo(player, bossInfo, this);
    }

    @Override
    public @NotNull AnimationSystem<SoulWatcherEntity> getSystem() {
        return animationSystem;
    }
}
