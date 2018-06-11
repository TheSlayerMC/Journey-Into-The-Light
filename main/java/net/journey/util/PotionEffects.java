package net.journey.util;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionEffects {

	public static int fireResistance = Potion.getIdFromPotion(MobEffects.FIRE_RESISTANCE);
	public static int digSpeed = Potion.getIdFromPotion(MobEffects.HASTE);
	public static int nightVision = Potion.getIdFromPotion(MobEffects.NIGHT_VISION);
	public static int saturation = Potion.getIdFromPotion(MobEffects.SATURATION);
	public static int damageBoost = Potion.getIdFromPotion(MobEffects.STRENGTH);
	public static int waterBreathing = Potion.getIdFromPotion(MobEffects.WATER_BREATHING);
	public static int absorption = Potion.getIdFromPotion(MobEffects.ABSORPTION);
	public static int regeneration = Potion.getIdFromPotion(MobEffects.REGENERATION);
	public static int resistance = Potion.getIdFromPotion(MobEffects.RESISTANCE);
	public static int jump = Potion.getIdFromPotion(MobEffects.JUMP_BOOST);
	public static int moveSpeed = Potion.getIdFromPotion(MobEffects.SPEED);
	public static int moveSlow = Potion.getIdFromPotion(MobEffects.SLOWNESS);
	public static int blindness = Potion.getIdFromPotion(MobEffects.BLINDNESS);
	public static int poison = Potion.getIdFromPotion(MobEffects.POISON);
	public static int wither = Potion.getIdFromPotion(MobEffects.WITHER);
	public static int harm = Potion.getIdFromPotion(MobEffects.INSTANT_DAMAGE);


	public static PotionEffect setPotionEffect(int id, int durationIn, int amplifierIn) {
		return new PotionEffect(Potion.getPotionById(id), durationIn, amplifierIn);
	}
	
}