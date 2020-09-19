package net.journey.init;

import net.journey.JITL;
import net.journey.common.JRegistries;
import net.journey.dialogue.Dialogue;
import net.journey.dialogue.DialogueBuilder;
import net.minecraft.util.text.TextComponentString;

public class JDialogues {
	public static final Dialogue TEST = JRegistries.DIALOGUE_REGISTRY.register(
			DialogueBuilder.makeDialogue(JITL.rl("test"),
					DialogueBuilder.NodeBuilder.create("dialogue.jitl.test.hello.text")
							.addOption(DialogueBuilder.OptionBuilder.create("dialogue.jitl.test.hello.answer.to_nonsense")
									.leadsTo(DialogueBuilder.NodeBuilder.create("dialogue.jitl.test.nonsense.text")
											.addOption(DialogueBuilder.OptionBuilder.create("dialogue.jitl.test.nonsense.answer.wtf")
													.withAction((world, player) -> {
														if (!world.isRemote) {
															player.sendMessage(new TextComponentString("Woop-woop!"));
														}
													})
											)
									)
							)
							.addOption(DialogueBuilder.OptionBuilder.create("dialogue.jitl.test.hello.answer.bye"))
							.addOption(DialogueBuilder.OptionBuilder.create("dialogue.jitl.test.hello.answer.bye2"))
							.addOption(DialogueBuilder.OptionBuilder.create("dialogue.jitl.test.hello.answer.bye3"))
			)
	);
}
