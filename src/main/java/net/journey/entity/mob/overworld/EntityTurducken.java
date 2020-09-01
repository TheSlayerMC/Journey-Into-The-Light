package net.journey.entity.mob.overworld;

import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.init.JAnimations;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityPeacefullUntillAttacked;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.ActionManagerBuilder;
import ru.timeconqueror.timecore.animation.AnimationManagerBuilder;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.api.animation.ActionManager;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;
import ru.timeconqueror.timecore.api.animation.BlendType;

public class EntityTurducken extends EntityPeacefullUntillAttacked implements AnimationProvider<EntityTurducken> {

    private final ActionManager<EntityTurducken> actionManager;

    private static final String LAYER_ANGRY = "angry";
    private static final String LAYER_WALKING = "walking";

    public EntityTurducken(World w) {
        super(w);
        setSize(0.7F, 1.0F);
        actionManager = ActionManagerBuilder.<EntityTurducken>create(
                AnimationManagerBuilder.create()
                        .addLayer(LAYER_WALKING, BlendType.ADDING, 1F)
                        .addWalkingAnimationHandling(new AnimationStarter(JAnimations.TURDUCKEN_WALK).setSpeed(1F), LAYER_WALKING)
        ).build(this, world);
    }

    @Override
    public @NotNull ActionManager<EntityTurducken> getActionManager() {
        return actionManager;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, MobStats.TURDUCKEN_HEALTH);
        EntityAttributesHelper.setAttackDamage(this, MobStats.TURDUCKEN_DAMAGE);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.BIRD;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.BIRD_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.BIRD_DEATH;
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.TURDUCKEN;
    }

    @Override
    public boolean getCanSpawnHere() {
        return
                this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.GRASS ||
                        this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.LEAVES ||
                        this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.SAND ||
                        this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.DIRT && this.dimension == 0;
    }

    @Override
    protected void dropFewItems(boolean b, int j) {
        if (rand.nextInt(2) == 0) dropItem(JourneyItems.rocFeather, rand.nextInt(4));
        if (this.isBurning()) {
            if (rand.nextInt(2) == 0) dropItem(JourneyConsumables.cookedRocMeat, 2);
        } else {
            if (rand.nextInt(2) == 0) dropItem(JourneyConsumables.rocMeat, 2);
        }
        super.dropFewItems(b, j);
    }
}