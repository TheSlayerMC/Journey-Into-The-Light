package net.journey.dialogue;

import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;

@ParametersAreNonnullByDefault
public class DialogueRegistry {
	private final HashMap<ResourceLocation, Dialogue> dialogues = new HashMap<>();

	public Dialogue register(Dialogue dialogue) {
		if (dialogues.put(dialogue.getId(), dialogue) != null) {
			throw new IllegalArgumentException("Dialogue with id " + dialogue.getId() + " already registered!");
		}

		return dialogue;
	}

	@Nullable
	public Dialogue getDialogue(ResourceLocation id) {
		return dialogues.get(id);
	}

	/**
	 * Verifies if dialogue was registered in a right way.
	 *
	 * @throws IllegalArgumentException if it was registered in a wrong way.
	 */
	public void verifyRegistration(Dialogue dialogue) throws IllegalArgumentException {
		if (dialogues.get(dialogue.getId()) == null) {
			throw new IllegalArgumentException("Dialogue " + dialogue.getId() + " isn't registered!");
		} else if (!dialogue.equals(dialogues.get(dialogue.getId()))) {
			throw new IllegalArgumentException("Dialogue with id " + dialogue.getId() + " is registered but it doesn't equal provided one. Why did you make new dialogue instance with the same id???");
		}
	}
}
