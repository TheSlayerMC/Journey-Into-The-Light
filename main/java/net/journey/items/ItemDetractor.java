package net.journey.items;

import java.util.List;
import java.util.Random;

import net.journey.JourneyTabs;
import net.journey.client.server.DarkEnergyBar;
import net.journey.entity.projectile.EntityAttractor;
import net.journey.entity.projectile.EntityDetractor;
import net.journey.enums.EnumSounds;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

public class ItemDetractor extends ItemMod {

	private int magic;
	public boolean attracts;
	public boolean detracts;

	public ItemDetractor(String name, String finalName, int magic, boolean attracts, boolean detracts) {
		super(name, finalName, JourneyTabs.staves);
		setMaxStackSize(1);
		this.magic = magic;
		this.attracts = attracts;
		this.detracts = detracts;
		this.setFull3D();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		Random r = new Random();
		if (detracts) {
			if(!world.isRemote && DarkEnergyBar.getProperties(player).useBar(magic)) {
				EnumSounds.playSound(EnumSounds.HAMMER, world, player);
				EntityThrowable entity = new EntityDetractor(world, player);
				world.spawnEntity(entity);
			}
		}
		if (attracts) {
			if(!world.isRemote && DarkEnergyBar.getProperties(player).useBar(magic)) {
				EnumSounds.playSound(EnumSounds.HAMMER, world, player);
				EntityThrowable entity = new EntityAttractor(world, player);
				world.spawnEntity(entity);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));	
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
		if (detracts) {
			l.add(SlayerAPI.Colour.AQUA + "Uses " + magic + " Dark Energy");
			l.add(SlayerAPI.Colour.AQUA + "Fires a mob away from you");
		}
		if (attracts) {
			l.add(SlayerAPI.Colour.AQUA + "Uses " + magic + " Dark Energy");
			l.add(SlayerAPI.Colour.AQUA + "Pulls a mob towards you");
		}
	}
}