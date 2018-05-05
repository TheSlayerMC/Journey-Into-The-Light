package net.journey.client.server;

import net.journey.JITL;
import net.journey.event.message.MessagePowerBar;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PowerBar implements IExtendedEntityProperties {

	private int power, regenDelay;
	private final EntityPlayer player;
	public final static String PROP = "PowerProperties";

	public PowerBar(EntityPlayer player) {
		this.player = player;
	}
	
	@Override
	public void saveNBTData(NBTTagCompound n) {
		NBTTagCompound tag = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		n.setInteger("power", 9);
		n.setInteger("regen", 20);
		player.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, tag);
	}

	@Override
	public void loadNBTData(NBTTagCompound n) {
		NBTTagCompound tag = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		if(!tag.hasKey("power")) return;
		this.power = n.getInteger("power");
		this.regenDelay = n.getInteger("regen");
		player.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, tag);
	}
	
	public static void addProperties(EntityPlayer player) {
		player.registerExtendedProperties(PROP, new PowerBar(player));
	}
	
	public static PowerBar getProperties(EntityPlayer player) {
		return (PowerBar)player.getExtendedProperties(PROP);
	}

	public void updateAllBars() {
		power += 1;
		if(player instanceof EntityPlayerMP) JITL.wrapper.sendTo(new MessagePowerBar(power, regenDelay == 0), (EntityPlayerMP)player);
	}                

	public boolean useBar(int amount) {
		if(power < amount) {
			regenDelay = 10;
			return false;
		}
		power -= amount;
		regenDelay = 10;
		if(player instanceof EntityPlayerMP) JITL.wrapper.sendTo(new MessagePowerBar(power, regenDelay == 0), (EntityPlayerMP)player);
		return true;
	}

	public void regen(int amount) {
		if(regenDelay == 0) power += amount;
		else regenDelay--;
		if(player instanceof EntityPlayerMP) JITL.wrapper.sendTo(new MessagePowerBar(power, regenDelay == 0), (EntityPlayerMP)player);
	}

	public void mainUpdate() {
		if(power >= 10) power = 10;
		if(player instanceof EntityPlayerMP) JITL.wrapper.sendTo(new MessagePowerBar(power, regenDelay == 0), (EntityPlayerMP)player);
	}

	public int getBarValue() {
		return power;
	}

	public void addBarPoints(int i) {
		power += i;
	}
	
	public void setBarValue(int val) {
		power = val;
	}

	public void removeBarPoints(int i) {
		regenDelay = 10;
		power -= i;
		if(player instanceof EntityPlayerMP) JITL.wrapper.sendTo(new MessagePowerBar(power, regenDelay == 0), (EntityPlayerMP)player);
	}

	@Override
	public void init(Entity entity, World world) { }
		
	}