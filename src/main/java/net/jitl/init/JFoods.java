package net.jitl.init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class JFoods {

	public static final Food BRADBERRY = (new Food.Builder()).nutrition(1).saturationMod(0.25F).fast().build();

	public static final Food FLORO_PEDAL = (new Food.Builder()).nutrition(3).saturationMod(0.3F).effect(new EffectInstance(Effects.CONFUSION, 100, 3), 0.5F).build();

	public static final Food HONGOSROOM = (new Food.Builder()).nutrition(4).saturationMod(0.5F).build();
	public static final Food HONGLOWSROOM = (new Food.Builder()).nutrition(4).saturationMod(0.5F).effect(new EffectInstance(Effects.NIGHT_VISION, 1000, 1), 0.5F).build();
}
