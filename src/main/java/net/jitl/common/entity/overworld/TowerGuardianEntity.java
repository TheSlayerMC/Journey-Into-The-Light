package net.jitl.common.entity.overworld;

import net.jitl.client.eventhandler.music.JMusicTicker;
import net.jitl.common.helper.JMusic;
import net.jitl.init.JAnimations;
import net.jitl.init.JSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.animation.AnimationSystem;
import ru.timeconqueror.timecore.api.animation.AnimatedObject;
import ru.timeconqueror.timecore.api.animation.BlendType;
import ru.timeconqueror.timecore.api.animation.builders.AnimationSystemBuilder;

public class TowerGuardianEntity extends MonsterEntity implements AnimatedObject<TowerGuardianEntity> {

	private static final String LAYER_WALKING = "walking";

	private final AnimationSystem<TowerGuardianEntity> animationSystem;

	public TowerGuardianEntity(EntityType<? extends TowerGuardianEntity> type, World world) {
		super(type, world);

		animationSystem = AnimationSystemBuilder.forEntity(this, world, builder ->
				builder.addLayer(LAYER_WALKING, BlendType.ADDING, 1F), predefinedAnimations ->
				predefinedAnimations.setWalkingAnimation(new AnimationStarter(JAnimations.towerGuardianWalk).setSpeed(1F), LAYER_WALKING));
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level.isClientSide()) {
			JMusicTicker.addTrack(new JMusic(JSounds.TEMPLE_GUARDIAN_MUSIC.get(), 2, 0, 0));
		}
	}

	@Override
	public @NotNull AnimationSystem<TowerGuardianEntity> getSystem() {
		return animationSystem;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.addBehaviourGoals();
	}

	protected void addBehaviourGoals() {
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglinEntity.class));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.15D);
	}
}
