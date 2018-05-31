package net.journey.items;

import java.util.List;

import net.journey.JourneyTabs;
import net.journey.client.server.DarkEnergyBar;
import net.journey.client.server.EssenceBar;
import net.journey.entity.projectile.EntityBasicProjectile;
import net.journey.entity.projectile.EntityBouncingProjectile;
import net.journey.entity.projectile.EntityChaosProjectile;
import net.journey.entity.projectile.EntityNetherPlasma;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

public class ItemChaosCannon extends ItemMod {

	public int damage;
	public int bounces;
	public String ability;
	public ItemChaosCannon(String name, String f, int damage, int bounces, String ability) {
		super(name, f, JourneyTabs.staves);
		this.ability = ability;
		this.bounces = bounces;
		this.damage = damage;
		setMaxStackSize(1);
		setMaxDamage(500);
		setFull3D();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getActiveItemStack();
		if(EssenceBar.getProperties(player).useBar(2)) {
			world.spawnEntity(new EntityBouncingProjectile(world, player, damage, bounces));
			EnumSounds.playSound(EnumSounds.CANNON, world, player);
			stack.damageItem(1, player);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);	
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);	
	}

	@Override
	public void addInformation(ItemStack i, List l) {
		l.add("Infinite ammo");
		l.add("Uses 2 Essence");
		l.add(SlayerAPI.Colour.GOLD + "Ability: " + ability);
		l.add(SlayerAPI.Colour.AQUA + "Damage: " + damage + " ranged damage");
		l.add(i.getMaxDamage() - i.getItemDamage() + SlayerAPI.Colour.DARK_GREEN + " Uses remaining");
	}
}