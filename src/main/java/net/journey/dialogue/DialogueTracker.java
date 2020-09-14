package net.journey.dialogue;

import net.journey.common.JManagers;
import net.journey.common.network.NetworkHandler;
import net.journey.common.network.dialogue.S2COpenDialogueGuiMsg;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.UUID;

public class DialogueTracker {
	private final UUID playerId;
	private final Class<? extends EntityLivingBase> npcClass;
	private DialogueNode currentNode;

	public DialogueTracker(UUID playerId, Class<? extends EntityLivingBase> npcClass, DialogueNode currentNode) {
		this.playerId = playerId;
		this.npcClass = npcClass;
		this.currentNode = currentNode;
	}

	public UUID getPlayerId() {
		return playerId;
	}

	public DialogueNode getCurrentNode() {
		return currentNode;
	}

	public void pressOption(EntityPlayerMP player, int optionIndex) throws DialogueSystemException {
		if (optionIndex >= currentNode.getOptions().size()) { // this can be achieved when someone try to use cheaty exploits
			throw new DialogueSystemException("Tracker received the index " + optionIndex + "that doesn't fit options list size (" + currentNode.getOptions().size() + "). Problem node: " + currentNode);
		}

		DialogueNode.Option option = currentNode.getOptions().get(optionIndex);
		option.onClickAction(player.world, player);

		currentNode = option.getNextNode();

		if (currentNode == DialogueNode.END) {
			JManagers.DIALOGUE_MANAGER.removeTracker(this);
		} else {
			openGuiWithCurrentNode();
		}
	}

	void openGuiWithCurrentNode() {
		EntityPlayerMP player = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(playerId);
		NetworkHandler.INSTANCE.sendTo(new S2COpenDialogueGuiMsg(npcClass, currentNode), player);
	}
}
