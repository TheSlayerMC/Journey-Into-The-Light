package net.jitl.init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class JFoods {

	public static final Food BRADBERRY = (new Food.Builder()).nutrition(1).saturationMod(0.25F).fast().build();

	public static final Food FLORO_PEDAL = (new Food.Builder()).nutrition(3).saturationMod(0.3F).effect(new EffectInstance(Effects.CONFUSION, 100, 3), 0.7F).build();

	public static final Food HONGOSROOM = (new Food.Builder()).nutrition(4).saturationMod(0.5F).fast().build();
	public static final Food RED_HONGLOWSROOM = (new Food.Builder()).nutrition(4).saturationMod(0.5F).effect(new EffectInstance(Effects.REGENERATION, 100, 1), 1.0F).build();
	public static final Food BLUE_HONGLOWSROOM = (new Food.Builder()).nutrition(4).saturationMod(0.5F).effect(new EffectInstance(Effects.MOVEMENT_SPEED, 100, 1), 1.0F).build();
	public static final Food GREEN_HONGLOWSROOM = (new Food.Builder()).nutrition(4).saturationMod(0.5F).effect(new EffectInstance(Effects.JUMP, 100, 1), 1.0F).build();
}
