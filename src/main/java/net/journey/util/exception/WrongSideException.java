package net.journey.util.exception;

import net.minecraftforge.fml.relauncher.Side;

public class WrongSideException extends RuntimeException {
	public WrongSideException(Side forbiddenSide) {
		super("Shouldn't be called on side: " + forbiddenSide);
	}
}
