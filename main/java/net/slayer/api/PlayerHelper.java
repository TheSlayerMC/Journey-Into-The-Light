package net.slayer.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerHelper {

	public static NBTTagCompound getPersistedpTag(EntityPlayer p) {
		NBTTagCompound n;
		if (!p.getEntityData().hasKey("PlayerPersisted")) {
			n = new NBTTagCompound();
			p.getEntityData().setTag("PlayerPersisted", n);
		} 
		else {
			n = p.getEntityData().getCompoundTag("PlayerPersisted");
		}
		return n;
	}
}