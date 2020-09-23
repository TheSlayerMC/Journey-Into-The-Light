package net.journey.init;

import net.journey.JITL;
import net.journey.api.capability.JourneyPlayer;
import net.journey.common.JRegistries;
import net.journey.common.capability.JCapabilityManager;
import net.journey.common.knowledge.EnumKnowledgeType;
import net.journey.dialogue.Dialogue;
import net.journey.dialogue.DialogueBuilder;
import net.journey.init.items.JourneyItems;
import net.journey.items.interactive.ItemLoreScroll;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
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
	public static final Dialogue THE_HOODED = JRegistries.DIALOGUE_REGISTRY.register(
			DialogueBuilder.makeDialogue(JITL.rl("the_hooded"),
					DialogueBuilder.NodeBuilder.create("What do you want? We don't have time for unworthy travelers.")
							.addOption(DialogueBuilder.OptionBuilder.create("What do you mean by 'unworthy' travelers?")
									.leadsTo(DialogueBuilder.NodeBuilder.create("All who are not loyal to the Divine Rock and Eye are not worthy of our time.")
											.addOption(DialogueBuilder.OptionBuilder.create("The Divine Rock and Eye?")
													.leadsTo(DialogueBuilder.NodeBuilder.create("He gives us life. He is the one true God. If you do not praise him, do not speak to me.")
															.addOption(DialogueBuilder.OptionBuilder.create("Where can I learn more about him?")
																	.leadsTo(DialogueBuilder.NodeBuilder.create("If you truly wish to become a divine follower, take this scroll. Otherwise, he will take care of you eventually..."))
																	.withAction((world, player) -> {
																		if (!world.isRemote) {
																			JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(player);
																			journeyPlayer.getPlayerStats().addKnowledge(EnumKnowledgeType.CORBA, 100);
																			player.sendMessage(new TextComponentString("You've gained 100 knowledge"));
																			journeyPlayer.sendUpdates();

																			ItemStack scrollStack = new ItemStack(JourneyItems.loreScroll);
																			ItemLoreScroll.bindScrollEntry(scrollStack, ScrollRegistry.SENTERIAN_GOSPEL);

																			world.spawnEntity(new EntityItem(world, player.posX, player.posY, player.posZ, scrollStack));
																		}
																	})
															)
													)
											)
											.addOption(DialogueBuilder.OptionBuilder.create("I am loyal to him.")
													.leadsTo(DialogueBuilder.NodeBuilder.create("You can't fool me. I know a true follower when I see one")
													)
											)
									))
							.addOption(DialogueBuilder.OptionBuilder.create("I was hoping you could tell me more about this place."))
							.addOption(DialogueBuilder.OptionBuilder.create("Sorry for disturbing you... goodbye."))
			)
	);
}
