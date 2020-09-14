package net.journey.init;

import net.journey.JITL;
import net.journey.common.JRegistries;
import net.journey.dialogue.Dialogue;
import net.journey.dialogue.DialogueBuilder;
import net.minecraft.util.text.TextComponentString;

public class JDialogues {
	public static final Dialogue TEST = JRegistries.DIALOGUE_REGISTRY.register(
			DialogueBuilder.makeDialogue(JITL.rl("test"),
					DialogueBuilder.NodeBuilder.create("Hello, player!")
							.addOption(DialogueBuilder.OptionBuilder.create("Hello, mob!")
									.leadsTo(DialogueBuilder.NodeBuilder.create("How are you, player?")
											.addOption(DialogueBuilder.OptionBuilder.create("I'm fine!")
													.withAction((world, player) -> {
														if (!world.isRemote) {
															player.sendMessage(new TextComponentString("Woop-woop!"));
														}
													})
											)
									)
							)
							.addOption(DialogueBuilder.OptionBuilder.create("Bye, mob!"))
							.addOption(DialogueBuilder.OptionBuilder.create("Bye, mob 2!"))
							.addOption(DialogueBuilder.OptionBuilder.create("Bye, mob 3!"))
			)
	);
}
