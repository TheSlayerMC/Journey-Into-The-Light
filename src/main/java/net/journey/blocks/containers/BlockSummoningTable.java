package net.journey.blocks.containers;

import net.journey.JITL;
import net.journey.blocks.BlockAncientCatalyst;
import net.journey.blocks.BlockAncientSocket;
import net.journey.blocks.tileentity.TileEntitySummoningTable;
import net.journey.client.handler.GuiHandler;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
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

import com.google.common.base.Predicates;

public class BlockSummoningTable extends BlockModContainer {

	private static BlockPattern firstLayer;
	private static BlockPattern secondLayer;
	private static BlockPattern thirdLayer;
	private static BlockPattern fourthLayer;
	private static BlockPattern fifthLayer;
	
	public BlockSummoningTable(String name, String f) {
        super(EnumMaterialTypes.STONE, name, f, 2.0F, JourneyTabs.MACHINE_BLOCKS);
    }

    public static BlockPattern getFirstLayer() {
        if (firstLayer == null) {
        	firstLayer = FactoryBlockPattern.start().aisle(
            "?vrv?", 
            "v?v?v", 
            "rvvvr", 
            "v?v?v", 
            "?vrv?").where(
            '?', BlockWorldState.hasState(BlockStateMatcher.ANY)).where(
            'v', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.bloodRock))).where(
            'r', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.bloodRune))).build();
        }
        return firstLayer;
    }
    public static BlockPattern getSecondLayer() {
        if (secondLayer == null) {
        	secondLayer = FactoryBlockPattern.start().aisle(
            "v???v", 
            "?????", 
            "?????", 
            "?????", 
            "v???v").where(
            '?', BlockWorldState.hasState(BlockStateMatcher.ANY)).where(
            'v', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.bloodPillar))).build();
        }
        return secondLayer;
    }
    public static BlockPattern getThirdLayer() {
        if (thirdLayer == null) {
        	thirdLayer = FactoryBlockPattern.start().aisle(
            "v???v", 
            "?????", 
            "?????", 
            "?????", 
            "v???v").where(
            '?', BlockWorldState.hasState(BlockStateMatcher.ANY)).where(
            'v', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.bloodPillar))).build();
        }
        return thirdLayer;
    }
    public static BlockPattern getFourthLayer() {
        if (fourthLayer == null) {
        	fourthLayer = FactoryBlockPattern.start().aisle(
            "vb?bv", 
            "bb?bb", 
            "??o??", 
            "bb?bb", 
            "vb?bv").where(
            '?', BlockWorldState.hasState(BlockStateMatcher.ANY)).where(
            'v', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.carvedBloodRock))).where(
            'b', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.bloodBricks))).where(
            'o', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.obelisk))).build();
        }
        return fourthLayer;
    }
    public static BlockPattern getFifthLayer() {
        if (fifthLayer == null) {
        	fifthLayer = FactoryBlockPattern.start().aisle(
            "?????", 
            "?bbb?", 
            "?bbb?", 
            "?bbb?", 
            "?????").where(
            '?', BlockWorldState.hasState(BlockStateMatcher.ANY)).where(
            'b', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.bloodBricks))).build();
        }
        return fifthLayer;
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
        BlockPattern.PatternHelper firstLayer = this.getFirstLayer().match(worldIn, pos.add(0, -1, 0));
        BlockPattern.PatternHelper secondLayer = this.getSecondLayer().match(worldIn, pos.add(0, 0, 0));
        BlockPattern.PatternHelper thirdLayer = this.getThirdLayer().match(worldIn, pos.add(0, 1, 0));
        BlockPattern.PatternHelper fourthLayer = this.getFourthLayer().match(worldIn, pos.add(0, 2, 0));
        BlockPattern.PatternHelper fifthLayer = this.getFifthLayer().match(worldIn, pos.add(0, 3, 0));

        if (firstLayer != null && secondLayer != null && thirdLayer != null && fourthLayer != null && fifthLayer != null) {
        	playerIn.openGui(JITL.MOD_ID, GuiHandler.summoning, worldIn, pos.getX(), pos.getY(), pos.getZ());
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

    private IBlockState getStructureBlocks(IBlockState state, World worldIn, BlockPos pos, float hitX, float hitY, float hitZ) {
        return state;

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