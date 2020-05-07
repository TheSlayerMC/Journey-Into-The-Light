package net.journey.util;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

//from forge 1.14.4 DistExecutor
public final class SideExecutor {

	/**
	 * Run the callable in the supplier only on the specified {@link Side}
	 *
	 * @param side  The physical side to run on
	 * @param toRun A supplier of the callable to run (Supplier wrapper to ensure classloading only on the appropriate side)
	 * @param <T>   The return type from the callable
	 * @return The callable's result
	 */
	public static <T> T callWhenOn(Side side, Supplier<Callable<T>> toRun) {
		if (side == FMLCommonHandler.instance().getSide()) {
			try {
				return toRun.get().call();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	public static void runWhenOn(Side side, Supplier<Runnable> toRun) {
		if (FMLCommonHandler.instance().getSide() == side) {
			toRun.get().run();
		}
	}

	public static <T> T runForDist(Supplier<Supplier<T>> clientTarget, Supplier<Supplier<T>> serverTarget) {
		switch (FMLCommonHandler.instance().getSide()) {
			case CLIENT:
				return clientTarget.get().get();
			case SERVER:
				return serverTarget.get().get();
			default:
				throw new IllegalArgumentException("UNSIDED?");
		}
	}
}