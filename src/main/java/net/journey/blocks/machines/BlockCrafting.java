package net.journey.blocks.machines;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.journey.blocks.tileentity.container.ContainerCrafting;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.Item;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.EnumToolType;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;

public class BlockCrafting extends BlockMod {

	public BlockCrafting(String name, String finalName) {
		super(name, finalName);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else {
			playerIn.displayGui(new BlockCrafting.InterfaceStoneCraftingTable(worldIn, pos));
            playerIn.addStat(StatList.CRAFTING_TABLE_INTERACTION);
            return true;
		}
	}

	public static class InterfaceStoneCraftingTable implements IInteractionObject {
		private final World world;
		private final BlockPos position;

		public InterfaceStoneCraftingTable(World w, BlockPos p) {
			this.world = w;
			this.position = p;
		}

		@Override
		public String getName() {
			return null;
		}

		@Override
		public boolean hasCustomName() {
			return false;
		}

		@Override
		public ITextComponent getDisplayName() {
			return new TextComponentTranslation(JourneyBlocks.stoneCraftingTable.getUnlocalizedName() + ".name",
					new Object[0]);
		}

		@Override
		public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
			return new ContainerCrafting(playerInventory, this.world, this.position);
		}

		@Override
		public String getGuiID() {
			return "minecraft:crafting_table";
		}
	}
}