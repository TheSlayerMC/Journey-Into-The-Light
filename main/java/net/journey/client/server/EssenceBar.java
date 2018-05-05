package net.journey.client.server;

import net.journey.JITL;
import net.journey.event.message.MessageEssenceBar;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EssenceBar implements IExtendedEntityProperties {

	private int essence, regenDelay;
	private final EntityPlayer player;
	public final static String PROP = "EssenceProperties";

	public EssenceBar(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public void saveNBTData(NBTTagCompound n) {
		NBTTagCompound tag = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		n.setInteger("essence", 9);
		n.setInteger("regen", 20);
		player.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, tag);
	}

	@Override
	public void loadNBTData(NBTTagCompound n) {
		NBTTagCompound tag = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		if(!tag.hasKey("essence")) return;
		this.essence = n.getInteger("essence");
		this.regenDelay = n.getInteger("regen");
		player.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, tag);
	}
	
	public static void addProperties(EntityPlayer player) {
		player.registerExtendedProperties(PROP, new EssenceBar(player));
	}
	
	public static EssenceBar getProperties(EntityPlayer player) {
		return (EssenceBar)player.getExtendedProperties(PROP);
	}

	public void updateAllBars() {
		essence += 1;
		if(player instanceof EntityPlayerMP) JITL.wrapper.sendTo(new MessageEssenceBar(essence, regenDelay == 0), (EntityPlayerMP)player);
	}                

	public boolean useBar(int amount) {
		if(essence < amount) {
			regenDelay = 10;
			return false;
		}
		essence -= amount;
		regenDelay = 10;
		if(player instanceof EntityPlayerMP) JITL.wrapper.sendTo(new MessageEssenceBar(essence, regenDelay == 0), (EntityPlayerMP)player);
		return true;
	}

	public void regen(int amount) {
		if(regenDelay == 0) essence += amount;
		else regenDelay--;
		if(player instanceof EntityPlayerMP) JITL.wrapper.sendTo(new MessageEssenceBar(essence, regenDelay == 0), (EntityPlayerMP)player);
	}

	public void mainUpdate() {
		if(essence >= 10) essence = 10;
		if(player instanceof EntityPlayerMP) JITL.wrapper.sendTo(new MessageEssenceBar(essence, regenDelay == 0), (EntityPlayerMP)player);
	}

	public int getBarValue() {
		return essence;
	}

	public void addBarPoints(int i) {
		essence += i;
	}
	
	public void setBarValue(int val) {
		essence = val;
	}

	public void removeBarPoints(int i) {
		regenDelay = 10;
		essence -= i;
		if(player instanceof EntityPlayerMP) JITL.wrapper.sendTo(new MessageEssenceBar(essence, regenDelay == 0), (EntityPlayerMP)player);
	}

	@Override
	public void init(Entity entity, World world) { }
}