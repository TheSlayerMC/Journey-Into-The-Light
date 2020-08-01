package ru.timeconqueror.timecore.animation;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.animation.ActionManager;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;
import ru.timeconqueror.timecore.util.SingleUseBuilder;

import java.util.Objects;

public class ActionManagerBuilder<T extends Entity & AnimationProvider<T>> extends SingleUseBuilder {
	private final AnimationManagerBuilder animationManagerBuilder;

	public ActionManagerBuilder(AnimationManagerBuilder animationManagerBuilder) {
		this.animationManagerBuilder = animationManagerBuilder;
	}

	public static <T extends Entity & AnimationProvider<T>> ActionManagerBuilder<T> create(AnimationManagerBuilder animationManagerBuilder) {
		return new ActionManagerBuilder<>(animationManagerBuilder);
	}

	public ActionManager<T> build(T entity, @NotNull World world) {
		Objects.requireNonNull(world);
		verifyNotUsed();

		BaseAnimationManager animationManager = animationManagerBuilder.build(!world.isRemote);

		ActionManagerImpl<T> actionManager = new ActionManagerImpl<>(animationManager, entity);

		animationManagerBuilder.init(animationManager, actionManager);

		setUsed();
		return actionManager;
	}
}
