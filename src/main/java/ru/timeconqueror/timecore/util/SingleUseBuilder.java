package ru.timeconqueror.timecore.util;

public class SingleUseBuilder {
	private boolean used;

	protected void setUsed() {
		this.used = true;
	}

	protected void verifyNotUsed() {
		if (used) throw new IllegalStateException(getClass() + " can't be used more then once.");
	}
}
