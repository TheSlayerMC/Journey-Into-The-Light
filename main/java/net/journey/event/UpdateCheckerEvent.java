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
import net.slayer.api.SlayerAPI.Colour;

public class UpdateCheckerEvent {

	private boolean hasSeen = false;

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onPlayerLogin(EntityJoinWorldEvent e) {
		if(e.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)e.getEntity();
			if(p.world.isRemote) {
				if(!hasSeen) {
					try {
						if(!UpdateChecker.isOnline()){ 
							SlayerAPI.addChatMessage(p, Colour.GOLD + "[|--------------------------------------------------|]"); 
							SlayerAPI.addChatMessageWithColour(p, Colour.GRAY, "[Version: " + SlayerAPI.MOD_VERSION + "]");
							SlayerAPI.addChatMessage(p, Colour.RED + "Unable to check for latest version. You may want to check your internet connection.");
							SlayerAPI.addChatMessage(p, Colour.GOLD + "[|--------------------------------------------------|]"); 
						}
						if(UpdateChecker.isUpdateAvailable() && UpdateChecker.isOnline()) {
							BufferedReader versionFile = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/TheSlayerMC/Journey-1.12/master/VER.txt").openStream()));
							String curVersion = versionFile.readLine();
							//BufferedReader changelogFile = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/TheSlayerMC/Essence/master/main/resources/assets/essence/ingame_changelog.txt").openStream()));
							//String changelog = changelogFile.readLine();
							
							SlayerAPI.addChatMessage(p, Colour.GOLD + "[|--------------------------------------------------|]"); 
							SlayerAPI.addChatMessage(p, Colour.GOLD + "[Version: " + SlayerAPI.MOD_VERSION + "]");
							SlayerAPI.addChatMessage(p, Colour.RED + "A Journey into the Light update is avaliable.");
							SlayerAPI.addChatMessage(p, Colour.AQUA + "[New Version: " + curVersion + "]"); 
							SlayerAPI.addChatMessageWithColour(p, Colour.YELLOW, "Follow @EssenceMod on twitter for update teasers.");
							SlayerAPI.addChatMessage(p, Colour.GOLD + "[|--------------------------------------------------|]"); 
						}
						if((!UpdateChecker.isUpdateAvailable()) && UpdateChecker.isOnline()) {
							SlayerAPI.addChatMessage(p, Colour.GOLD + "[|--------------------------------------------------|]"); 
							SlayerAPI.addChatMessage(p, Colour.GRAY + "[Journey into the Light] [Version: " + SlayerAPI.MOD_VERSION + "]");
							SlayerAPI.addChatMessageWithColour(p, Colour.GOLD, "Journey into the Light is up to date.");
							SlayerAPI.addChatMessageWithColour(p, Colour.YELLOW, "Follow @EssenceMod on twitter for update teasers.");
							SlayerAPI.addChatMessage(p, Colour.GOLD + "[|--------------------------------------------------|]"); 
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