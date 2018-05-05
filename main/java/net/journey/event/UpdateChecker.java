package net.journey.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import net.slayer.api.SlayerAPI;

public class UpdateChecker {

	public static boolean isUpdateAvailable() throws IOException, MalformedURLException {
		BufferedReader versionFile = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/Dizzlepop12/Journey-Into-the-Light/master/main/resources/assets/essence/version.txt").openStream()));
		//BufferedReader changelogFile = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/TheSlayerMC/Essence/master/main/resources/assets/essence/ingame_changelog.txt").openStream()));
		String curVersion = versionFile.readLine();
		//String changelog = changelogFile.readLine();
		versionFile.close();
		//changelogFile.close();
		if(!curVersion.contentEquals(SlayerAPI.MOD_VERSION)) return true;
		return false;
	}

	public static boolean isOnline() throws SocketException {
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while(interfaces.hasMoreElements()) {
			NetworkInterface interf = interfaces.nextElement();
			if(interf.isUp() && !interf.isLoopback()) {
				List<InterfaceAddress> adrs = interf.getInterfaceAddresses();
				for(Iterator<InterfaceAddress> iter = adrs.iterator(); iter.hasNext();) {
					InterfaceAddress adr = iter.next();
					InetAddress inadr = adr.getAddress();
					if(inadr instanceof Inet4Address) return true;
				}
			}
		}
		return false;
	}
}