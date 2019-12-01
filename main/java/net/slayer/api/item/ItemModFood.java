package net.slayer.api.item;

import java.util.List;

import net.journey.JourneyConsumables;
import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.ItemDescription;
import net.journey.util.LangRegistry;
import net.journey.util.PotionEffects;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemModFood extends ItemFood {

	private int time = 32;
	private boolean op = false;

	public ItemModFood(String name, String f, int food, float sat, boolean wolfFood) {
		super(food, sat, wolfFood);
		LangRegistry.addItem(name, f);
		setUnlocalizedName(name);
		setCreativeTab(JourneyTabs.crops);
		JourneyItems.itemNames.add(SlayerAPI.PREFIX + name);
		JourneyItems.items.add(this);
		setRegistryName(SlayerAPI.MOD_ID, name);
	}

	public ItemModFood(String name, String f, int food, float sat, int timeToEat, boolean wolfFood) {
		this(name, f, food, sat, wolfFood);
		time = timeToEat;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return time;
	}

	public ItemModFood(String name, String f, int food, float sat, boolean wolfFood, PotionEffect potionID, float potionEffectProbability) {
		this(name, f, food, sat, wolfFood);
		setPotionEffect(potionID, potionEffectProbability);
	}

	public ItemModFood(String name, String actual, int heal, float f, boolean sat, boolean b) {
		super(heal, sat);
		LangRegistry.addItem(name, actual);
		setUnlocalizedName(name);
		JourneyItems.items.add(this);
		JourneyItems.itemNames.add(SlayerAPI.PREFIX + name);
		setCreativeTab(JourneyTabs.crops);
		setRegistryName(SlayerAPI.MOD_ID, name);
		this.op = b;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack i) {
		return op;
	}

	@Override
	protected void onFoodEaten(ItemStack i, World w, EntityPlayer p) {
		if(this == JourneyConsumables.honglowShroom || this == JourneyConsumables.redHonglowShroom || this == JourneyConsumables.greenHonglowShroom || this == JourneyConsumables.blueHonglowShroom) 
			p.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 2400, 0)));
		super.onFoodEaten(i, w, p);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		ItemDescription.addInformation(stack, tooltip);
		tooltip.add("Fills " + (double) getHealAmount(stack) / 2 + " Hunger Bars");
		tooltip.add(getSaturationModifier(stack) + " Saturation");
		if(time <= 32) tooltip.add("Faster eating");
	}
}