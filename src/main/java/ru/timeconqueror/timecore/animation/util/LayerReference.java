package ru.timeconqueror.timecore.animation.util;

import ru.timeconqueror.timecore.animation.Layer;
import ru.timeconqueror.timecore.api.animation.BlendType;

import java.util.Objects;
import java.util.function.Function;

public class LayerReference {
	public static final LayerReference WALKING = new LayerReference("walking", refName -> new Layer(refName, 0, BlendType.OVERRIDE, 1F));

	private final String name;
	private final Function<String, Layer> defaultCreator;

	public LayerReference(String name, Function<String, Layer> defaultCreator) {
		this.name = name;
		this.defaultCreator = defaultCreator;
	}

	public Layer createLayerFromDefault() {
		return defaultCreator.apply(this.name);
	}

	public Layer createLayer(int priority, BlendType blendType, float weight) {
		return new Layer(this.name, priority, blendType, weight);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof LayerReference)) return false;
		LayerReference that = (LayerReference) o;
		return name.equals(that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return "LayerReference{" +
				"name='" + name + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}
}
