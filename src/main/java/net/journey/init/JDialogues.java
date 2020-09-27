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
			DialogueBuilder.dialogue(JITL.rl("test"),
					DialogueBuilder.node("dialogue.jitl.test.hello.text")
							.addOption(DialogueBuilder.option("dialogue.jitl.test.hello.answer.to_nonsense")
									.leadsTo(DialogueBuilder.node("dialogue.jitl.test.nonsense.text")
											.addOption(DialogueBuilder.option("dialogue.jitl.test.nonsense.answer.wtf")
													.withAction((world, player) -> {
														if (!world.isRemote) {
															player.sendMessage(new TextComponentString("Woop-woop!"));
														}
													})
											)
									)
							)
							.addOption(DialogueBuilder.option("dialogue.jitl.test.hello.answer.bye"))
							.addOption(DialogueBuilder.option("dialogue.jitl.test.hello.answer.bye2"))
							.addOption(DialogueBuilder.option("dialogue.jitl.test.hello.answer.bye3"))
			)
	);
	public static final Dialogue THE_HOODED = JRegistries.DIALOGUE_REGISTRY.register(
			DialogueBuilder.dialogue(JITL.rl("the_hooded"),
					DialogueBuilder.node("What do you want? We don't have time for unworthy travelers.")
							.addOption(DialogueBuilder.option("What do you mean by 'unworthy' travelers?")
									.leadsTo(DialogueBuilder.node("All who are not loyal to the Divine Rock and Eye are not worthy of our time.")
											.addOption(DialogueBuilder.option("The Divine Rock and Eye?")
													.leadsTo(DialogueBuilder.node("He gives us life. He is the one true God. If you do not praise him, do not speak to me.")
															.addOption(DialogueBuilder.option("Where can I learn more about him?")
																	.leadsTo(DialogueBuilder.node("If you truly wish to become a divine follower, take this scroll. Otherwise, he will take care of you eventually..."))
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
															.addOption(DialogueBuilder.option("I don't belong to any Gods.")
																	.leadsTo(DialogueBuilder.node("Then I have no reason to speak to you. I have things to do here, now go away.")
																	)
															)
															.addOption(DialogueBuilder.option("I am loyal to him.")
																	.leadsTo(DialogueBuilder.node("You can't fool me. I know a true follower when I see one, and you look lost.")
																	)
															)
													)
											)
									)
							)
							.addOption(DialogueBuilder.option("I was hoping you could tell me more about this place.")
									.leadsTo(DialogueBuilder.node("This is the Divine Land Corba. Of all the realms, it is the most beautiful.")
											.addOption(DialogueBuilder.option("Yes it's... uh, beautiful.")
													.leadsTo(DialogueBuilder.node("...")
													)
											)
											.addOption(DialogueBuilder.option("The air here is putrid, I can hardly breathe.")
													.leadsTo(DialogueBuilder.node("...")
													)
											)
									)
							)
							.addOption(DialogueBuilder.option("Sorry for disturbing you... goodbye."))
			)
	);
}
