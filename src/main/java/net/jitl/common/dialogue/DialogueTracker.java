package net.jitl.common.dialogue;

public class DialogueTracker {
    /*private final UUID playerId;
    private final Class<? extends LivingEntity> npcClass;
    private DialogueNode currentNode;

    public DialogueTracker(UUID playerId, Class<? extends LivingEntity> npcClass, DialogueNode currentNode) {
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

    public void start() {
        openGuiWithCurrentNode();
    }

    public void pressOption(ServerPlayerEntity player, int optionIndex) throws DialogueSystemException {
        if (optionIndex >= currentNode.getOptions().size()) { // this can be achieved when someone try to use cheaty exploits
            throw new DialogueSystemException("Tracker received the index " + optionIndex + "that doesn't fit options list size (" + currentNode.getOptions().size() + "). Problem node: " + currentNode);
        }

        DialogueNode.Option option = currentNode.getOptions().get(optionIndex);
        option.onClickAction(player.world, player);

        currentNode = option.getNextNode();

        if (currentNode == DialogueNode.END) {
            JManagers.DIALOGUE_MANAGER.removeTracker(this);
            closeGui();
        } else {
            openGuiWithCurrentNode();
        }
    }

    private void openGuiWithCurrentNode() {
        NetworkHandler.INSTANCE.sendTo(new S2COpenDialogueGuiMsg(npcClass, currentNode), getPlayer());
    }

    private void closeGui() {
        NetworkHandler.INSTANCE.sendTo(new S2CCloseDialogueGuiMsg(), getPlayer());
    }

    private ServerPlayerEntity getPlayer() {
        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(playerId);
    }*/
}