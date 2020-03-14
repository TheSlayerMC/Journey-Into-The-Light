package net.journey.misc;

import net.journey.JourneyBlocks;
import net.journey.blocks.portal.BlockBoilPortal;
import net.journey.blocks.portal.BlockEucaPortal;
import net.journey.blocks.portal.BlockFrozenPortal;
import net.journey.dimension.ModTeleporter;
import net.journey.dimension.corba.TeleporterCorba;
import net.journey.dimension.depths.TeleporterDepths;
import net.journey.util.Config;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.Teleporter;

public class JourneyCommands extends CommandBase {

	@Override
	public String getName() {
		return "journey";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/journey";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender var1, String[] var2) throws CommandException {
		EntityPlayerMP p = null;
		try {
			p = getCommandSenderAsPlayer(var1);
		} catch (PlayerNotFoundException e) {
			e.printStackTrace();
		}
		if(var2[0].equalsIgnoreCase("Heal")){
			if(p.getHealth() < p.getMaxHealth()) 
				p.heal(20);
			if(p.getFoodStats().needFood())
				p.getFoodStats().addStats(20, 1);}

		EntityPlayerMP playerMP = (EntityPlayerMP)var1;
		/*if(!playerMP.world.isRemote) {
			if(var2[0].equalsIgnoreCase("Overworld")) {
				if(playerMP.dimension != 0) {
					playerMP.mcServer.getPlayerList().transferPlayerToDimension(playerMP, 0, new Teleporter(playerMP.mcServer.getWorld(0)));
				}
			}

			if(var2[0].equalsIgnoreCase("Nether")) {
				if(playerMP.dimension != -1) {
					playerMP.mcServer.getPlayerList().transferPlayerToDimension(playerMP, -1, new Teleporter(playerMP.mcServer.getWorld(-1)));
				}
			}

			if(var2[0].equalsIgnoreCase("End")) {
				if(playerMP.dimension != 1) {
					playerMP.mcServer.getPlayerList().transferPlayerToDimension(playerMP, 1, new Teleporter(playerMP.mcServer.getWorld(1)));
				}
			}

			if(var2[0].equalsIgnoreCase("Euca")) {
				if(playerMP.dimension != Config.euca) {
					playerMP.mcServer.getPlayerList().transferPlayerToDimension(playerMP, Config.euca, new ModTeleporter(playerMP.mcServer.getWorld(Config.euca), Config.euca, JourneyBlocks.eucaPortal, JourneyBlocks.eucaPortalFrame, BlockEucaPortal.AXIS));
				}
			}

			if(var2[0].equalsIgnoreCase("BoilingPoint")) {
				if(playerMP.dimension != Config.boil) {
					playerMP.mcServer.getPlayerList().transferPlayerToDimension(playerMP, Config.boil, new ModTeleporter(playerMP.mcServer.getWorld(Config.boil), Config.boil, JourneyBlocks.boilPortal, JourneyBlocks.boilPortalFrame, BlockBoilPortal.AXIS));
				}
			}

			if(var2[0].equalsIgnoreCase("Depths")) {
				if(playerMP.dimension != Config.depths) {
					playerMP.mcServer.getPlayerList().transferPlayerToDimension(playerMP, Config.depths, new TeleporterDepths(playerMP.mcServer.getWorld(Config.depths)));
				}
			}
			
			if(var2[0].equalsIgnoreCase("FrozenLands")) {
				if(playerMP.dimension != Config.frozen) {
					playerMP.mcServer.getPlayerList().transferPlayerToDimension(playerMP, Config.frozen, new ModTeleporter(playerMP.mcServer.getWorld(Config.frozen), Config.frozen, JourneyBlocks.frozenPortal, JourneyBlocks.frozenPortalFrame, BlockFrozenPortal.AXIS));
				}
			}
			
			if(var2[0].equalsIgnoreCase("Corba")) {
				if(playerMP.dimension != Config.corba) {
					playerMP.mcServer.getPlayerList().transferPlayerToDimension(playerMP, Config.corba, new TeleporterCorba(playerMP.mcServer.getWorld(Config.corba)));
				}
			}
		}*/
	}
}