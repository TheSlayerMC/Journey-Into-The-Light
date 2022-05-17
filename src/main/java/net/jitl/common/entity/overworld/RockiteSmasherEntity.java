package net.jitl.common.entity.overworld;

import net.jitl.client.render.gui.BossBarRenderer;
import net.jitl.common.entity.base.IJourneyBoss;
import net.jitl.common.helper.JBossInfo;
import net.jitl.common.helper.JMusic;
import net.jitl.core.JITL;
import net.jitl.core.init.JSounds;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RockiteSmasherEntity extends Monster implements IJourneyBoss {

    private int attackAnimationTick;
    private final ServerBossEvent BOSS_INFO = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.NOTCHED_6);
    private final BossBarRenderer BOSS_BAR = new BossBarRenderer(this, JITL.tl("gui/bossbars/rockite_smasher.png").fullLocation());

    public RockiteSmasherEntity(EntityType<? extends RockiteSmasherEntity> entityType, Level world) {
        super(entityType, world);

    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, null));
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
        return JSounds.ROCKITE_SMASHER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JSounds.ROCKITE_SMASHER_DEATH.get();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public int getAttackAnimationTick() {
        return this.attackAnimationTick;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.attackAnimationTick > 0) {
            --this.attackAnimationTick;
        }
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        if(source.getEntity() instanceof Player) {
            Player player = (Player)source.getEntity();
            if(player.getMainHandItem().getItem() instanceof PickaxeItem) {
                return super.hurt(source, amount);
            }
        }
        return false;
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        this.attackAnimationTick = 10;
        this.level.broadcastEntityEvent(this, (byte)4);
        float damage = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f1 = (int)damage > 0 ? damage / 2.0F + (float)this.random.nextInt((int)damage) : damage;
        boolean hurt = entity.hurt(DamageSource.mobAttack(this), f1);
        if(hurt) {
            entity.setDeltaMovement(entity.getDeltaMovement().add(0.0D, (double)0.4F, 0.0D));
            this.doEnchantDamageEffects(this, entity);
        }
        this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return hurt;
    }

    @Override
    public void handleEntityEvent(byte id) {
        if(id == 4) {
            this.attackAnimationTick = 10;
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        }
        super.handleEntityEvent(id);
    }

    @Override
    public BossBarRenderer getBossBar() {
        return this.BOSS_BAR;
    }

    @Override
    public JMusic getBossMusic() {
        return null;
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer player) {
        super.startSeenByPlayer(player);
        JBossInfo.addInfo(player, BOSS_INFO, this);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        JBossInfo.removeInfo(player, BOSS_INFO, this);
    }

}