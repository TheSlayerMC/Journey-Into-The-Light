package net.journey.client.server;

import net.journey.JITL;
import net.journey.event.message.MessageDarkEnergyBar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class DarkEnergyBar implements ICapabilityProvider  {

	private int darkEnergy, regenDelay;
	private final EntityPlayer player;
	public final static String PROP = "DarkEnergyProperties";

	public DarkEnergyBar(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public void saveNBTData(NBTTagCompound n) {
		NBTTagCompound tag = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		n.setInteger("darkEnergy", 9);
		n.setInteger("regen", 20);
		player.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, tag);
	}

	@Override
	public void loadNBTData(NBTTagCompound n) {
		NBTTagCompound tag = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		if(!tag.hasKey("darkEnergy")) return;
		this.darkEnergy = n.getInteger("darkEnergy");
		this.regenDelay = n.getInteger("regen");
		player.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, tag);
	}
	
	public static void addProperties(EntityPlayer player) {
		player.registerExtendedProperties(PROP, new DarkEnergyBar(player));
	}
	
	public static DarkEnergyBar getProperties(EntityPlayer player) {
		return (DarkEnergyBar)player.getExtendedProperties(PROP);
	}

	public void updateAllBars() {
		darkEnergy += 1;
		if(player instanceof EntityPlayerMP) JITL.wrapper.sendTo(new MessageDarkEnergyBar(darkEnergy, regenDelay == 0), (EntityPlayerMP)player);
	}                

	public boolean useBar(int amount) {
		if(darkEnergy < amount) {
			regenDelay = 10;
			return false;
		}
		darkEnergy -= amount;
		regenDelay = 10;
		if(player instanceof EntityPlayerMP) JITL.wrapper.sendTo(new MessageDarkEnergyBar(darkEnergy, regenDelay == 0), (EntityPlayerMP)player);
		return true;
	}

	public void regen(int amount) {
		if(regenDelay == 0) darkEnergy += amount;
		else regenDelay--;
		if(player instanceof EntityPlayerMP) JITL.wrapper.sendTo(new MessageDarkEnergyBar(darkEnergy, regenDelay == 0), (EntityPlayerMP)player);
	}

	public void mainUpdate() {
		if(darkEnergy >= 10) darkEnergy = 10;
		if(player instanceof EntityPlayerMP) JITL.wrapper.sendTo(new MessageDarkEnergyBar(darkEnergy, regenDelay == 0), (EntityPlayerMP)player);
	}

	public int getBarValue() {
		return darkEnergy;
	}
	
	public void setBarValue(int val) {
		darkEnergy = val;
	}

	public void addBarPoints(int i) {
		darkEnergy += i;
	}

	public void removeBarPoints(int i) {
		regenDelay = 10;
		darkEnergy -= i;
		if(player instanceof EntityPlayerMP) JITL.wrapper.sendTo(new MessageDarkEnergyBar(darkEnergy, regenDelay == 0), (EntityPlayerMP)player);
	}

	@Override
	public void init(Entity entity, World world) { }
}