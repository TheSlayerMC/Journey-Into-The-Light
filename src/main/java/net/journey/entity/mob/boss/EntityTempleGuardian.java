package net.journey.entity.mob.boss;

import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.util.EntityBossCrystal;
import net.journey.init.JAnimations;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.animation.ActionManagerBuilder;
import ru.timeconqueror.timecore.animation.AnimationManagerBuilder;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.api.animation.ActionManager;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;
import ru.timeconqueror.timecore.api.animation.BlendType;

public class EntityTempleGuardian extends EntityEssenceBoss implements AnimationProvider<EntityTempleGuardian> {

    private final ActionManager<EntityTempleGuardian> actionManager;

    private static final String LAYER_WALKING = "walking";

    private int ticks;

    public EntityTempleGuardian(World par1World) {
        super(par1World);
        setSize(2.0F, 3.8F);
        actionManager = ActionManagerBuilder.<EntityTempleGuardian>create(
                AnimationManagerBuilder.create()
                        .addLayer(LAYER_WALKING, BlendType.ADDING, 1F)
                        .addWalkingAnimationHandling(new AnimationStarter(JAnimations.TEMPLE_GUARDIAN_WALK).setSpeed(1F), LAYER_WALKING)
        ).build(this, world);
    }

    @Override
    public @NotNull ActionManager<EntityTempleGuardian> getActionManager() {
        return actionManager;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        addMeleeAttackingAI();
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        EntityAttributesHelper.setMaxHealth(this, MobStats.TEMPLE_GUARDIAN_HEALTH);
        EntityAttributesHelper.setAttackDamage(this, MobStats.TEMPLE_GUARDIAN_DAMAGE);
        EntityAttributesHelper.setKnockbackResistance(this, MobStats.TEMPLE_GUARDIAN_KNOCKBACK_RESISTANCE);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.getImmediateSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) source.getImmediateSource();
            Item heldItem = player.inventory.getCurrentItem().getItem();
            if (this.isEntityInvulnerable(source)) {
                return false;
            } else if (source == DamageSource.DROWN) {
                return false;
            } else {
                Entity entity;
                {
                    entity = source.getImmediateSource();
                    if (entity instanceof EntityArrow) {
                        return false;
                    } else if (entity instanceof EntityThrowable) {
                        return false;
                    } else if (!(heldItem instanceof ItemPickaxe) || !(heldItem.getRegistryName().toString().contains("pickaxe"))) {
                        return false;
                    }
                }
            }
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.SENTRY_AMBIENT_1;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.ROCK;
    }

    @Override
    public float getSoundVolume() {
        return 1.0F;
    }

    @Override
    public float getSoundPitch() {
        return 0.6F;
    }

    @Override
    protected @Nullable ResourceLocation getLootTable() {
        return JourneyLootTables.TEMPLE_GUARDIAN;
    }

    @Override
    protected @NotNull EntityBossCrystal.Type getDeathCrystalType() {
        return EntityBossCrystal.Type.COMMON;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }
}