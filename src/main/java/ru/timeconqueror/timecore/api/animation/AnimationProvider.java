package ru.timeconqueror.timecore.api.animation;

import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.client.render.model.TimeEntityRenderer;

/**
 * An interface for entities to provide animation stuff.
 * You also need to use {@link TimeEntityRenderer} for animations to work.
 * <br>
 * Example of implementing:
 * <blockquote><pre>
 *  public class EntityFloro extends MonsterEntity implements AnimationProvider<EntityFloro> {
 *     private final ActionController<EntityFloro> actionController;
 *
 *     public EntityFloro(EntityType<? extends MonsterEntity> type, World worldIn) {
 *         super(type, worldIn);
 *
 *         actionController = new ActionControllerBuilder<EntityFloro>(
 *                 new AnimationManagerBuilder(true)
 *                         .addWalkingAnimationHandling(new AnimationStarter(TEntities.FLORO_WALK).setSpeed(2.0F), "walking")
 *                         .addLayer("attack", 1, BlendType.ADDING, 0.9F)
 *         ).build(this, world);
 *     }
 *
 *     public @NotNull ActionController<EntityFloro> getActionController() {
 *         return actionController;
 *     }
 * }
 * </pre></blockquote>
 *
 * @see TimeEntityRenderer
 */
public interface AnimationProvider<T extends Entity> {
	/**
	 * The entry point for accessing animation stuff.
	 */
	@NotNull
	ActionController<T> getActionController();
}
