package net.journey.dimension;

import net.journey.util.Config;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class DimensionCommand extends CommandBase {

	@Override
	public String getName() {
		return "dimension";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/dimension";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayerMP playerMP = getCommandSenderAsPlayer(sender);
		if (!playerMP.world.isRemote) {
			if (args[0].equalsIgnoreCase("Overworld")) {
				if (playerMP.dimension != 0) {
					playerMP.changeDimension(0);
				}
			}

			if (args[0].equalsIgnoreCase("Nether")) {
				if (playerMP.dimension != -1) {
					playerMP.changeDimension(-1);
				}
			}

			if (args[0].equalsIgnoreCase("End")) {
				if (playerMP.dimension != 1) {
					playerMP.changeDimension(1);
				}
			}

			if (args[0].equalsIgnoreCase("Euca")) {
				if (playerMP.dimension != Config.euca) {
					playerMP.changeDimension(Config.euca);
				}
			}

			if (args[0].equalsIgnoreCase("BoilingPoint")) {
				if (playerMP.dimension != Config.boil) {
					playerMP.changeDimension(Config.boil);
				}
			}

			if (args[0].equalsIgnoreCase("Depths")) {
				if (playerMP.dimension != Config.depths) {
					playerMP.changeDimension(Config.depths);
				}
			}

			if (args[0].equalsIgnoreCase("FrozenLands")) {
				if (playerMP.dimension != Config.frozen) {
					playerMP.changeDimension(Config.frozen);
				}
			}

			if (args[0].equalsIgnoreCase("Corba")) {
				if (playerMP.dimension != Config.corba) {
					playerMP.changeDimension(Config.corba);
				}
			}

			if (args[0].equalsIgnoreCase("Terrania")) {
				if (playerMP.dimension != Config.terrania) {
					playerMP.changeDimension(Config.terrania);
				}
			}

			if (args[0].equalsIgnoreCase("Cloudia")) {
				if (playerMP.dimension != Config.cloudia) {
					playerMP.changeDimension(Config.cloudia);
				}
			}
		}

	}
}