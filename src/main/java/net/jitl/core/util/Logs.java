package net.jitl.core.util;

import net.jitl.core.JITL;
import org.apache.logging.log4j.Logger;

public class Logs {

    private static final Logger LOGGER = JITL.LOGGER;

    public static void printReportMessage() {
        LOGGER.warn("Please report this to the Journey Into The Light official GitHub repository, here: https://github.com/TheSlayerMC/Journey-Into-The-Light/issues. " +
                "Or, report it to the official Discord server, here: https://discord.gg/xZpe3rRM");
    }
}
