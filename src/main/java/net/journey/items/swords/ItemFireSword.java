package net.journey.items.swords;

import java.util.List;
import java.util.Random;

import net.journey.JITL;
import net.journey.client.render.particles.EntityModFireFX;
import net.journey.client.render.particles.EntityModLavaFX;
import net.journey.client.render.particles.EntityModSnowFX;
import net.journey.client.render.particles.EntityPoisionFX;
import net.journey.enums.EnumParticlesClasses;
import net.journey.util.JourneyToolMaterial;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemModSword;

public class ItemFireSword extends ItemModSword {

	public ItemFireSword(String name, String f , JourneyToolMaterial toolMaterial) {
		super(name, f, toolMaterial);
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase hit, EntityLivingBase player) {
		hit.setFire(10);
		addParticles(hit);
		return super.hitEntity(par1ItemStack, hit, player);
	}
	
	@SideOnly(Side.CLIENT)
	public void addParticles(EntityLivingBase hit) {
		Random r = new Random();
		for(int i = 0; i < 50; i++) {
			JITL.proxy.spawnParticle(EnumParticlesClasses.MOD_LAVA, hit.world, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, false);
			JITL.proxy.spawnParticle(EnumParticlesClasses.MOD_LAVA, hit.world, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, false);
		}
	}


	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, World player, List<String> infoList, ITooltipFlag par4) {
		infoList.add(SlayerAPI.Colour.DARK_RED + "On hit: Burns for 10 seconds");
		if(item.getMaxDamage() != -1) infoList.add(item.getMaxDamage() - item.getItemDamage() + " Uses Remaining");
	}
}