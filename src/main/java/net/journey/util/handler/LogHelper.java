package net.journey.util.handler;

import net.journey.JITL;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogHelper {

	private static BufferedWriter writer;

	static {
		if (JITL.IN_JOURNEY_DEV) {
			File file = new File("./logs/jitl-debug.log");
			try {
				FileUtils.touch(file);
				writer = new BufferedWriter(new FileWriter(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void log(Level level, Object msg) {
		FMLLog.log(JITL.MOD_NAME, level, msg.toString());
		if (JITL.IN_JOURNEY_DEV) writeFile(msg);
	}

	public static void debug(Object msg) {
		if (JITL.IN_JOURNEY_DEV) log(Level.DEBUG, "[DEBUG] " + msg);
	}

    public static void error(Object msg) {
        log(Level.ERROR, msg);
    }

    public static void info(Object msg) {
        log(Level.INFO, msg);
    }

    public static void warn(Object msg) {
        log(Level.WARN, msg);
    }

    public static void dev(Object msg) {
        log(Level.INFO, msg);
    }

    public static void writeFile(Object msg) {
        try {
            writer.write(msg + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeFile() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}