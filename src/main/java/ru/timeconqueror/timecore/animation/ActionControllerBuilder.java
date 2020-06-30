package ru.timeconqueror.timecore.animation;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.animation.ActionController;
import ru.timeconqueror.timecore.util.SingleUseBuilder;

import java.util.Objects;

public class ActionControllerBuilder<T extends EntityMob> extends SingleUseBuilder {
	private final AnimationManagerBuilder animationManagerBuilder;

	public ActionControllerBuilder(AnimationManagerBuilder animationManagerBuilder) {
		this.animationManagerBuilder = animationManagerBuilder;
	}

	public ActionController<T> build(T entity, @NotNull World world) {
		Objects.requireNonNull(world);
		verifyNotUsed();

		BaseAnimationManager animationManager = animationManagerBuilder.build(!world.isRemote);

		ActionControllerImpl<T> actionController = new ActionControllerImpl<>(animationManager, entity);

		animationManagerBuilder.init(animationManager, actionController);

		setUsed();
		return actionController;
	}
}
