package net.journey.blocks.containers;

import net.journey.JITL;
import net.journey.blocks.tileentity.TileEntitySummoningTable;
import net.journey.client.handler.GuiHandler;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.WorldUtils;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.tileentity.container.BlockModContainer;

import java.util.Random;

public class BlockSummoningTable extends BlockModContainer {
	public static final BlockPattern STRUCTURE_PATTERN = FactoryBlockPattern.start()
			.aisle(
					"_kek_",
					"l___l",
					"l___l",
					"cs_sc",
					"_____"
			).aisle(
					"k_k_k",
					"_____",
					"_____",
					"ss_ss",
					"_sss_"

			).aisle(
					"ekkke",
					"_____",
					"_____",
					"__o__",
					"_sss_"

			).aisle(
					"k_k_k",
					"_____",
					"_____",
					"ss_ss",
					"_sss_"

			)
			.aisle(
					"_kek_",
					"l___l",
					"l___l",
					"cs_sc",
					"_____"
			)
			.where('_', BlockWorldState.hasState(BlockStateMatcher.ANY))
			.where('k', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.bloodRock)))
			.where('e', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.bloodRune)))
			.where('l', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.bloodPillar)))
			.where('c', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.carvedBloodRock)))
			.where('s', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.bloodBricks)))
			.where('o', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.obelisk)))
			.build();

	public BlockSummoningTable(String name, String f) {
        super(EnumMaterialTypes.STONE, name, f, 2.0F, JourneyTabs.INTERACTIVE_BLOCKS);
    }

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return SlayerAPI.toItem(JourneyBlocks.summoningTable);
	}

	@Override
	public ItemStack getItem(World w, BlockPos pos, IBlockState state) {
		return new ItemStack(JourneyBlocks.summoningTable);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			TileEntitySummoningTable tile = WorldUtils.getValidTile(worldIn, pos, state, TileEntitySummoningTable.class);
			tile.onServerBlockClicked();
		}

		if (STRUCTURE_PATTERN.match(worldIn, pos.down(1).north(2).west(2)) != null) {
			playerIn.openGui(JITL.MOD_ID, GuiHandler.SUMMONING_TABLE.get(), worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntitySummoningTable tile = (TileEntitySummoningTable) worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tile);
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySummoningTable();
	}
}