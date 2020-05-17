package ru.timeconqueror.timecore.client.render.animation;

public class ApplyAnimationException extends Exception {
	public ApplyAnimationException() {
	}

	public ApplyAnimationException(String message) {
		super(message);
	}

	public ApplyAnimationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplyAnimationException(Throwable cause) {
		super(cause);
	}

	public ApplyAnimationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
