package net.journey.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class UpdateCheckerEvent {

	private boolean hasSeen = false;

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onPlayerLogin(EntityJoinWorldEvent e) {
		if(e.entity instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)e.entity;
			if(p.worldObj.isRemote) {
				if(!hasSeen) {
					try {
						if(!UpdateChecker.isOnline()){
							SlayerAPI.addChatMessage(p, EnumChatFormatting.GOLD + "[|--------------------------------------------------|]"); 
							SlayerAPI.addChatMessageWithColour(p, EnumChatFormatting.GRAY, "[Version: " + SlayerAPI.MOD_VERSION + "]");
							SlayerAPI.addChatMessage(p, EnumChatFormatting.RED + "Unable to check for latest version. You may want to check your internet connection.");
							SlayerAPI.addChatMessage(p, EnumChatFormatting.GOLD + "[|--------------------------------------------------|]"); 
						}
						if(UpdateChecker.isUpdateAvailable() && UpdateChecker.isOnline()) {
							BufferedReader versionFile = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/Dizzlepop12/Journey-Into-the-Light/master/main/resources/assets/essence/version.txt").openStream()));
							String curVersion = versionFile.readLine();
							//BufferedReader changelogFile = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/TheSlayerMC/Essence/master/main/resources/assets/essence/ingame_changelog.txt").openStream()));
							//String changelog = changelogFile.readLine();
							
							SlayerAPI.addChatMessage(p, EnumChatFormatting.GOLD + "[|--------------------------------------------------|]"); 
							SlayerAPI.addChatMessage(p, EnumChatFormatting.GOLD + "[Version: " + SlayerAPI.MOD_VERSION + "]");
							SlayerAPI.addChatMessage(p, EnumChatFormatting.RED + "A Journey into the Light update is avaliable.");
							SlayerAPI.addChatMessage(p, EnumChatFormatting.AQUA + "[New Version: " + curVersion + "]"); 
							SlayerAPI.addChatMessageWithColour(p, EnumChatFormatting.YELLOW, "Follow @EssenceMod on twitter for update teasers.");
							SlayerAPI.addChatMessage(p, EnumChatFormatting.GOLD + "[|--------------------------------------------------|]"); 
						}
						if((!UpdateChecker.isUpdateAvailable()) && UpdateChecker.isOnline()) {
							SlayerAPI.addChatMessage(p, EnumChatFormatting.GOLD + "[|--------------------------------------------------|]"); 
							SlayerAPI.addChatMessage(p, EnumChatFormatting.GRAY + "[Journey into the Light] [Version: " + SlayerAPI.MOD_VERSION + "]");
							SlayerAPI.addChatMessageWithColour(p, EnumChatFormatting.GOLD, "Journey into the Light is up to date.");
							SlayerAPI.addChatMessageWithColour(p, EnumChatFormatting.YELLOW, "Follow @EssenceMod on twitter for update teasers.");
							SlayerAPI.addChatMessage(p, EnumChatFormatting.GOLD + "[|--------------------------------------------------|]"); 
						}
					} catch(MalformedURLException e1) {
						e1.printStackTrace();

					} catch(IOException e1) {
						e1.printStackTrace();
					}
				}
				hasSeen = true;
			}
		}
	} 
}