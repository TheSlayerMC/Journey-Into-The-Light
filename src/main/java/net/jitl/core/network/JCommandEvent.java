package net.jitl.core.network;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.jitl.core.init.world.Dimensions;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class JCommandEvent {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        LiteralArgumentBuilder<CommandSourceStack> jCommand = Commands.literal("jitl").requires((com) -> com.hasPermission(1));

        jCommand.then(Commands.literal("dimension")
                .then(Commands.literal("euca").executes(command -> changeDim(Objects.requireNonNull(command.getSource().getEntity()), Dimensions.EUCA))));

        dispatcher.register(jCommand);
    }

    public static int changeDim(Entity entity, ResourceKey<Level> destination) {
        MinecraftServer minecraftserver = entity.getServer();
        if(minecraftserver != null) {
            ServerLevel destinationWorld = minecraftserver.getLevel(destination);
            if (destinationWorld != null && minecraftserver.isNetherEnabled() && !entity.isPassenger()) {
                entity.level.getProfiler().push(Objects.requireNonNull(destination.getRegistryName()).toString());
                entity.setPortalCooldown();
                entity.changeDimension(destinationWorld);
                entity.level.getProfiler().pop();
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}