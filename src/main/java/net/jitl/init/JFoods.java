package net.jitl.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class JFoods {

    public static final FoodProperties CRYSTAL_APPLE = (new FoodProperties.Builder()).nutrition(8).saturationMod(2.2F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 800, 2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 4000, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 4000, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 4000, 2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 10, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1400, 4), 1.0F)
            .effect(() -> new MobEffectInstance(JEffects.FROSTBURN.get(), 600, 1), 1.0F)
            .alwaysEat().build();

    public static final FoodProperties REDCURRANT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.25F).fast().alwaysEat().build();
    public static final FoodProperties TARTBERRY = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.25F).fast().alwaysEat().build();
    public static final FoodProperties HONGOSROOM = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.5F).build();
    public static final FoodProperties HONGLOWSROOM = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.5F).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 1), 0.5F).build();

    public static final FoodProperties FLORO_PEDAL = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 100, 3), 0.5F).build();
    public static final FoodProperties TOMATO = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.6F).build();

    public static final FoodProperties FRIED_GHAST_TENTACLE = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).meat().effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 5, 1), 1.0F).build();
    public static final FoodProperties FLAMING_GHAST_TENTACLE = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.6F).meat().effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 5, 1), 1.0F).build();
    public static final FoodProperties FRIED_FLAMING_GHAST_TENTACLE = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).meat().build();
    public static final FoodProperties GHAST_TENTACLE = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.6F).meat().build();

	public static final FoodProperties FRIED_EGG = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.6F).build();

	public static final FoodProperties MINT_CANDY_CANE = (new FoodProperties.Builder()).nutrition(3).saturationMod(1.6F).fast().effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 100, 1), 1.0F).build();
	public static final FoodProperties FRUITY_CANDY_CANE = (new FoodProperties.Builder()).nutrition(3).saturationMod(1.6F).fast().effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 100, 1), 1.0F).build();
	public static final FoodProperties CHERRY_CANDY_CANE = (new FoodProperties.Builder()).nutrition(3).saturationMod(1.6F).fast().effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 100, 1), 1.0F).build();

	public static final FoodProperties PEPPERMINT = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).fast().build();
	public static final FoodProperties JELLYBEANS = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).fast().build();
	public static final FoodProperties CHOCOLATE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).fast().build();
	public static final FoodProperties VANILLA_WAFER = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).fast().build();

	public static final FoodProperties EUCA_MEAT = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).meat().fast().build();

}
