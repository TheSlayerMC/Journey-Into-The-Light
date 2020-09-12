package net.journey.init;

import net.journey.dialogue.DialogueBuilder;
import net.journey.dialogue.DialogueNode;

public class DialogueRegistry {
	public static final DialogueNode TEST = DialogueBuilder.createDialog(DialogueBuilder.NodeBuilder.create("Hello, player!")
			.addOption(DialogueBuilder.OptionBuilder.create("Hello, mob!")
					.leadsTo(DialogueNode.Action.newDialogNode(DialogueBuilder.NodeBuilder.create("How are you, player?")
							.addOption(DialogueBuilder.OptionBuilder.create("I'm fine!")
									.leadsTo(DialogueNode.Action.END_DIALOGUE)
							)
					))
			)
			.addOption(DialogueBuilder.OptionBuilder.create("Bye, mob!")
					.leadsTo(DialogueNode.Action.END_DIALOGUE)
			)
			.addOption(DialogueBuilder.OptionBuilder.create("Bye, mob 2!")
					.leadsTo(DialogueNode.Action.END_DIALOGUE)
			)
			.addOption(DialogueBuilder.OptionBuilder.create("Bye, mob 3!")
					.leadsTo(DialogueNode.Action.END_DIALOGUE)
			)
	);
}
