package net.journey.event;

import net.slayer.api.SlayerAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class UpdateChecker {

    public static boolean isUpdateAvailable() throws IOException {
        BufferedReader versionFile = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/TheSlayerMC/Journey-1.12/master/VER.txt").openStream()));
        //BufferedReader changelogFile = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/TheSlayerMC/Essence/master/main/resources/assets/essence/ingame_changelog.txt").openStream()));
        String curVersion = versionFile.readLine();
        //String changelog = changelogFile.readLine();
        versionFile.close();
        //changelogFile.close();
        return !curVersion.contentEquals(SlayerAPI.MOD_VERSION);
    }

    public static boolean isOnline() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface interf = interfaces.nextElement();
            if (interf.isUp() && !interf.isLoopback()) {
                List<InterfaceAddress> adrs = interf.getInterfaceAddresses();
                for (Iterator<InterfaceAddress> iter = adrs.iterator(); iter.hasNext(); ) {
                    InterfaceAddress adr = iter.next();
                    InetAddress inadr = adr.getAddress();
                    if (inadr instanceof Inet4Address) return true;
                }
            }
        }
        return false;
    }
}