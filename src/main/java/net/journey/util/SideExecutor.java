package net.journey.util;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * Utility class to run things only in provided side.
 * <br>
 * <font color=yellow>It does NOT save code from causing class casting exceptions.</font>
 */
public final class SideExecutor {

	/**
	 * Run the callable only on the specified {@link Side}
	 *
	 * @param side     The physical side to run on
	 * @param callable callable to run
	 * @param <T>      The return type from the callable
	 * @return The callable's result
	 */
	public static <T> T callWhenOn(Side side, Callable<T> callable) {
		if (side == FMLCommonHandler.instance().getSide()) {
			try {
				return callable.call();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	/**
	 * Run the runnable only on the specified {@link Side}
	 *
	 * @param side     The physical side to run on
	 * @param runnable runnable to run
	 */
	public static void runWhenOn(Side side, Runnable runnable) {
		if (FMLCommonHandler.instance().getSide() == side) {
			runnable.run();
		}
	}

	public static <T> T runForDist(Supplier<T> clientTarget, Supplier<T> serverTarget) {
		switch (FMLCommonHandler.instance().getSide()) {
			case CLIENT:
				return clientTarget.get();
			case SERVER:
				return serverTarget.get();
			default:
				throw new IllegalArgumentException("UNSIDED?");
		}
	}
}