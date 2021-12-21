package net.jitl.init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class JFoods {

    public static final Food CRYSTAL_APPLE = (new Food.Builder()).nutrition(8).saturationMod(2.2F)
            .effect(() -> new EffectInstance(Effects.REGENERATION, 800, 2), 1.0F)
            .effect(() -> new EffectInstance(Effects.DAMAGE_RESISTANCE, 4000, 0), 1.0F)
            .effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 4000, 0), 1.0F)
            .effect(() -> new EffectInstance(Effects.MOVEMENT_SPEED, 600, 1), 1.0F)
            .effect(() -> new EffectInstance(Effects.DAMAGE_BOOST, 4000, 2), 1.0F)
            .effect(() -> new EffectInstance(Effects.HEAL, 10, 1), 1.0F)
            .effect(() -> new EffectInstance(Effects.ABSORPTION, 1400, 4), 1.0F)
            .effect(() -> new EffectInstance(JEffects.FROSTBURN.get(), 600, 4), 1.0F)
            .alwaysEat().build();

    public static final Food BRADBERRY = (new Food.Builder()).nutrition(1).saturationMod(0.25F).fast().alwaysEat().build();
    public static final Food HONGOSROOM = (new Food.Builder()).nutrition(4).saturationMod(0.5F).build();
    public static final Food HONGLOWSROOM = (new Food.Builder()).nutrition(4).saturationMod(0.5F).effect(new EffectInstance(Effects.NIGHT_VISION, 1000, 1), 0.5F).build();

    public static final Food FLORO_PEDAL = (new Food.Builder()).nutrition(3).saturationMod(0.3F).effect(new EffectInstance(Effects.CONFUSION, 100, 3), 0.5F).build();
    public static final Food TOMATO = (new Food.Builder()).nutrition(3).saturationMod(0.6F).build();

    public static final Food FRIED_GHAST_TENTACLE = (new Food.Builder()).nutrition(4).saturationMod(0.6F).meat().effect(new EffectInstance(Effects.FIRE_RESISTANCE, 5, 1), 1.0F).build();
    public static final Food FLAMING_GHAST_TENTACLE = (new Food.Builder()).nutrition(1).saturationMod(0.6F).meat().effect(new EffectInstance(Effects.FIRE_RESISTANCE, 5, 1), 1.0F).build();
    public static final Food FRIED_FLAMING_GHAST_TENTACLE = (new Food.Builder()).nutrition(4).saturationMod(0.6F).meat().build();
    public static final Food GHAST_TENTACLE = (new Food.Builder()).nutrition(1).saturationMod(0.6F).meat().build();

	public static final Food FRIED_EGG = (new Food.Builder()).nutrition(2).saturationMod(0.6F).build();

	public static final Food MINT_CANDY_CANE = (new Food.Builder()).nutrition(3).saturationMod(1.6F).fast().effect(new EffectInstance(Effects.DIG_SPEED, 100, 1), 1.0F).build();
	public static final Food FRUITY_CANDY_CANE = (new Food.Builder()).nutrition(3).saturationMod(1.6F).fast().effect(new EffectInstance(Effects.DIG_SPEED, 100, 1), 1.0F).build();
	public static final Food CHERRY_CANDY_CANE = (new Food.Builder()).nutrition(3).saturationMod(1.6F).fast().effect(new EffectInstance(Effects.DIG_SPEED, 100, 1), 1.0F).build();

	public static final Food PEPPERMINT = (new Food.Builder()).nutrition(1).saturationMod(0.1F).fast().build();
	public static final Food JELLYBEANS = (new Food.Builder()).nutrition(1).saturationMod(0.1F).fast().build();
	public static final Food CHOCOLATE = (new Food.Builder()).nutrition(2).saturationMod(0.1F).fast().build();
	public static final Food VANILLA_WAFER = (new Food.Builder()).nutrition(1).saturationMod(0.1F).fast().build();

	public static final Food EUCA_MEAT = (new Food.Builder()).nutrition(6).saturationMod(0.6F).meat().fast().build();

}
