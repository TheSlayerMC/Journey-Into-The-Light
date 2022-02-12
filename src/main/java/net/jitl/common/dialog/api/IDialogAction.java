package net.jitl.common.dialog.api;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

@FunctionalInterface
public interface IDialogAction {
    IDialogAction EMPTY = (world, player) -> {
    };

    void onClick(Level world, ServerPlayer player);
}
