package net.journey.items;

import java.util.List;
import java.util.Random;

import net.journey.JourneyTabs;
import net.journey.client.server.DarkEnergyBar;
import net.journey.client.server.EssenceBar;
import net.journey.entity.projectile.EntityAttractor;
import net.journey.entity.projectile.EntityDetractor;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
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
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		Random r = new Random();
		if (detracts) {
			if(!world.isRemote && DarkEnergyBar.getProperties(player).useBar(magic)) {
				EnumSounds.playSound(EnumSounds.HAMMER, world, player);
				EntityThrowable entity = new EntityDetractor(world, player);
				world.spawnEntityInWorld(entity);
			}
		}
		if (attracts) {
			if(!world.isRemote && DarkEnergyBar.getProperties(player).useBar(magic)) {
				EnumSounds.playSound(EnumSounds.HAMMER, world, player);
				EntityThrowable entity = new EntityAttractor(world, player);
				world.spawnEntityInWorld(entity);
			}
		}
		return stack;
	}

	@Override
	public void addInformation(ItemStack i, EntityPlayer p, List l) {
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