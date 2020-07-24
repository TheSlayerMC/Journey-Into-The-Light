package ru.timeconqueror.timecore.animation;

import net.minecraft.entity.monster.EntityMob;
import ru.timeconqueror.timecore.api.animation.ActionManager;
import ru.timeconqueror.timecore.api.animation.AnimationConstants;
import ru.timeconqueror.timecore.api.animation.BlendType;
import ru.timeconqueror.timecore.util.SingleUseBuilder;

import java.util.LinkedHashMap;

public class AnimationManagerBuilder extends SingleUseBuilder {
	private final LinkedHashMap<String, Layer> animationLayers = new LinkedHashMap<>();
	private AnimationSetting walkingAnimationSetting;

	public static AnimationManagerBuilder create() {
		return new AnimationManagerBuilder();
	}

	public AnimationManagerBuilder addWalkingAnimationHandling(AnimationStarter walkingAnimationStarter, String layerName) {
		if (animationLayers.containsKey(layerName)) {
			walkingAnimationSetting = new AnimationSetting(layerName, walkingAnimationStarter);
			return this;
		} else {
			throw new IllegalStateException(String.format("You need to define layer %s before adding animation handlers to it.", layerName));
		}
	}

	public AnimationManagerBuilder addLayer(String name, BlendType blendType, float weight) {
		verifyNotUsed();
		Layer prev = animationLayers.put(name, new Layer(name, blendType, weight));
		if (prev != null)
			throw new IllegalArgumentException("Layer with name " + name + " already exist in provided animation manager.");
		return this;
	}

	public AnimationManagerBuilder addMainLayer() {
		verifyNotUsed();
		addLayer(AnimationConstants.MAIN_LAYER_NAME, BlendType.OVERRIDE, 1);

		return this;
	}

	private AnimationManagerBuilder addLayer(Layer layer) {
		verifyNotUsed();
		try {
			Layer prev = animationLayers.put(layer.getName(), layer.clone());
			if (prev != null)
				throw new IllegalArgumentException("Layer with name " + layer.getName() + " already exist in provided animation manager.");

			return this;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	BaseAnimationManager build(boolean serverSide) {
		BaseAnimationManager manager = serverSide ? new ServerAnimationManager<>(walkingAnimationSetting) : new ClientAnimationManager(walkingAnimationSetting);

		if (animationLayers.isEmpty()) {
			addMainLayer();
		}

		manager.setLayers(animationLayers);

		setUsed();

		return manager;
	}

	@SuppressWarnings("unchecked")
	<T extends EntityMob> void init(BaseAnimationManager manager, ActionManager<T> actionManager) {
		if (manager instanceof ServerAnimationManager) {
			((ServerAnimationManager<T>) manager).setActionManager((ActionManagerImpl<T>) actionManager);
		}
	}
}
