package net.journey.misc;

import java.util.List;

import net.journey.JourneyBlocks;
import net.journey.client.server.DarkEnergyBar;
import net.journey.client.server.EssenceBar;
import net.journey.client.server.PowerBar;
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
import net.minecraft.world.Teleporter;

public class EssenceCommands extends CommandBase {

	@Override
	public String getCommandName() {
		return "essence";
	}
	
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/essence";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) throws CommandException {
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
				p.getFoodStats().addStats(20, 1);
			EssenceBar.getProperties(p).addBarPoints(10);
			DarkEnergyBar.getProperties(p).addBarPoints(10);
			PowerBar.getProperties(p).addBarPoints(10);
		}

		EntityPlayerMP playerMP = (EntityPlayerMP)var1;
		if(!playerMP.worldObj.isRemote) {
			if(var2[0].equalsIgnoreCase("Overworld")) {
				if(playerMP.dimension != 0) {
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, 0, new Teleporter(playerMP.mcServer.worldServerForDimension(0)));
				}
			}

			if(var2[0].equalsIgnoreCase("Nether")) {
				if(playerMP.dimension != -1) {
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, -1, new Teleporter(playerMP.mcServer.worldServerForDimension(-1)));
				}
			}

			if(var2[0].equalsIgnoreCase("End")) {
				if(playerMP.dimension != 1) {
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, 1, new Teleporter(playerMP.mcServer.worldServerForDimension(1)));
				}
			}

			if(var2[0].equalsIgnoreCase("Euca")) {
				if(playerMP.dimension != Config.euca) {
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, Config.euca, new ModTeleporter(playerMP.mcServer.worldServerForDimension(Config.euca), Config.euca, JourneyBlocks.eucaPortal, JourneyBlocks.eucaPortalFrame));
				}
			}

			if(var2[0].equalsIgnoreCase("BoilingPoint")) {
				if(playerMP.dimension != Config.boil) {
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, Config.boil, new ModTeleporter(playerMP.mcServer.worldServerForDimension(Config.boil), Config.boil, JourneyBlocks.boilPortal, JourneyBlocks.boilPortalFrame));
				}
			}

			if(var2[0].equalsIgnoreCase("Depths")) {
				if(playerMP.dimension != Config.depths) {
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, Config.depths, new TeleporterDepths(playerMP.mcServer.worldServerForDimension(Config.depths)));
				}
			}
			
			if(var2[0].equalsIgnoreCase("FrozenLands")) {
				if(playerMP.dimension != Config.frozen) {
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, Config.frozen, new ModTeleporter(playerMP.mcServer.worldServerForDimension(Config.frozen), Config.frozen, JourneyBlocks.frozenPortal, Blocks.snow));
				}
			}
			
			if(var2[0].equalsIgnoreCase("Corba")) {
				if(playerMP.dimension != Config.corba) {
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, Config.corba, new TeleporterCorba(playerMP.mcServer.worldServerForDimension(Config.corba)));
				}
			}
		}
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2, BlockPos pos) {
		return par2.length == 1 ? getListOfStringsMatchingLastWord(par2, new String[] {"heal", "overworld", "nether", "end", "euca", "boilingpoint", "depths", "frozenlands", "corba"}) : null;
	}
}