package net.jitl.common.entity.overworld;

import net.jitl.common.helper.JMusic;
import net.jitl.client.music.JMusicTicker;
import net.jitl.init.JAnimations;
import net.jitl.init.JSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
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
				predefinedAnimations.setWalkingAnimation(new AnimationStarter(JAnimations.towerGuardianWalk).setSpeed(3F), LAYER_WALKING));
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

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.15D);
	}
}
