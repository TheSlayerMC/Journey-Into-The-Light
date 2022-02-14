package net.jitl.common.capability.dialog;

import net.jitl.common.dialog.Dialog;
import net.jitl.common.dialog.DialogTracker;
import net.jitl.core.init.JCapabilities;
import net.jitl.core.network.JPacketHandler;
import net.jitl.core.network.dialogue.SCloseDialogGuiPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.common.capability.CoffeeCapabilityInstance;
import ru.timeconqueror.timecore.common.capability.owner.CapabilityOwner;
import ru.timeconqueror.timecore.common.capability.owner.serializer.CapabilityOwnerCodec;

public class DialogManager extends CoffeeCapabilityInstance<Entity> {
    private final ServerPlayer player;

    @Nullable
    private DialogTracker openedDialog = null;

    public DialogManager(ServerPlayer player) {
        this.player = player;
    }

    public void startDialog(Dialog dialog) {
        DialogTracker tracker = new DialogTracker(player, dialog);

        closeDialog(false);

        openedDialog = tracker;
        tracker.start();
    }

    /**
     * Presses the given option.
     *
     * @return true if the option choose was accepted, other cases return false
     * (index was out of bounds, was pressed in an appropriate time, etc)
     */
    public boolean pressOption(int optionIndex) {
        if (openedDialog != null) {
            return openedDialog.handleOptionClick(optionIndex);
        }

        return false;
    }

    public void closeDialog(boolean sync) {
        if (openedDialog != null) {
            openedDialog.save();
            if (sync) {
                JPacketHandler.sendToPlayer(player, new SCloseDialogGuiPacket());
            }
            openedDialog = null;
        }
    }

    @NotNull
    @Override
    public Capability<? extends CoffeeCapabilityInstance<Entity>> getCapability() {
        return JCapabilities.DIALOG_MANAGER;
    }

    @NotNull
    @Override
    public CapabilityOwnerCodec<Entity> getOwnerSerializer() {
        return CapabilityOwner.ENTITY.getSerializer();
    }

    @Override
    public void sendChangesToClients(@NotNull SimpleChannel simpleChannel, @NotNull Object o) {
        simpleChannel.send(PacketDistributor.PLAYER.with(() -> player), o);
    }

    public static DialogManager of(ServerPlayer player) {
        LazyOptional<DialogManager> cap = player.getCapability(JCapabilities.DIALOG_MANAGER);
        return cap.orElseThrow(IllegalStateException::new);
    }
}
