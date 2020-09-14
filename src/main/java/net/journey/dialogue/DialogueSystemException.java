package net.journey.dialogue;

public class DialogueSystemException extends Exception {
	public DialogueSystemException() {
	}

	public DialogueSystemException(String message) {
		super(message);
	}

	public DialogueSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public DialogueSystemException(Throwable cause) {
		super(cause);
	}

	public DialogueSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
