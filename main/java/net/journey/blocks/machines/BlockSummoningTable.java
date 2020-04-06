package net.journey.blocks.machines;

import java.util.List;
import java.util.Random;

import net.journey.JITL;
import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.journey.blocks.tileentity.TileEntitySummoningTable;
import net.journey.blocks.tileentity.container.ContainerCrafting;
import net.journey.blocks.tileentity.container.ContainerSummoningTable;
import net.journey.client.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.tileentity.container.BlockModContainer;

public class BlockSummoningTable extends BlockModContainer {
	
	public BlockSummoningTable(String name, String f) {
		super(EnumMaterialTypes.STONE, name, f, 2.0F, JourneyTabs.machineBlocks);
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
		if(worldIn.getBlockState(
				//base
				pos.add(hitX, hitY - 1, hitZ)).getBlock() == JourneyBlocks.bloodRock && worldIn.getBlockState(
				pos.add(hitX, hitY - 1, hitZ - 1)).getBlock() == JourneyBlocks.bloodRock && worldIn.getBlockState(
				pos.add(hitX, hitY - 1, hitZ + 1)).getBlock() == JourneyBlocks.bloodRock && worldIn.getBlockState(
				pos.add(hitX - 1, hitY - 1, hitZ)).getBlock() == JourneyBlocks.bloodRock && worldIn.getBlockState(
				pos.add(hitX + 1, hitY - 1, hitZ)).getBlock() == JourneyBlocks.bloodRock && worldIn.getBlockState(
						
				pos.add(hitX - 2, hitY - 1, hitZ)).getBlock() == JourneyBlocks.bloodRune && worldIn.getBlockState(
				pos.add(hitX + 2, hitY - 1, hitZ)).getBlock() == JourneyBlocks.bloodRune && worldIn.getBlockState(
				pos.add(hitX, hitY - 1, hitZ - 2)).getBlock() == JourneyBlocks.bloodRune && worldIn.getBlockState(
				pos.add(hitX, hitY - 1, hitZ + 2)).getBlock() == JourneyBlocks.bloodRune && worldIn.getBlockState(
						
				pos.add(hitX - 1, hitY - 1, hitZ + 2)).getBlock() == JourneyBlocks.bloodRock && worldIn.getBlockState(
				pos.add(hitX - 1, hitY - 1, hitZ - 2)).getBlock() == JourneyBlocks.bloodRock && worldIn.getBlockState(
				pos.add(hitX + 1, hitY - 1, hitZ + 2)).getBlock() == JourneyBlocks.bloodRock && worldIn.getBlockState(
				pos.add(hitX + 1, hitY - 1, hitZ - 2)).getBlock() == JourneyBlocks.bloodRock && worldIn.getBlockState(
						
				pos.add(hitX - 2, hitY - 1, hitZ - 1)).getBlock() == JourneyBlocks.bloodRock && worldIn.getBlockState(
				pos.add(hitX + 2, hitY - 1, hitZ - 1)).getBlock() == JourneyBlocks.bloodRock && worldIn.getBlockState(
				pos.add(hitX + 2, hitY - 1, hitZ + 1)).getBlock() == JourneyBlocks.bloodRock && worldIn.getBlockState(
				pos.add(hitX - 2, hitY - 1, hitZ + 1)).getBlock() == JourneyBlocks.bloodRock && worldIn.getBlockState(
						
				//pillars
				pos.add(hitX - 2, hitY, hitZ - 2)).getBlock() == JourneyBlocks.bloodPillar && worldIn.getBlockState(
				pos.add(hitX + 2, hitY, hitZ + 2)).getBlock() == JourneyBlocks.bloodPillar && worldIn.getBlockState(
				pos.add(hitX + 2, hitY, hitZ - 2)).getBlock() == JourneyBlocks.bloodPillar && worldIn.getBlockState(
				pos.add(hitX - 2, hitY, hitZ + 2)).getBlock() == JourneyBlocks.bloodPillar && worldIn.getBlockState(
						
				pos.add(hitX - 2, hitY + 1, hitZ - 2)).getBlock() == JourneyBlocks.bloodPillar && worldIn.getBlockState(
				pos.add(hitX + 2, hitY + 1, hitZ + 2)).getBlock() == JourneyBlocks.bloodPillar && worldIn.getBlockState(
				pos.add(hitX + 2, hitY + 1, hitZ - 2)).getBlock() == JourneyBlocks.bloodPillar && worldIn.getBlockState(
				pos.add(hitX - 2, hitY + 1, hitZ + 2)).getBlock() == JourneyBlocks.bloodPillar && worldIn.getBlockState(
						
				pos.add(hitX - 2, hitY + 2, hitZ - 2)).getBlock() == JourneyBlocks.carvedBloodRock && worldIn.getBlockState(
				pos.add(hitX + 2, hitY + 2, hitZ + 2)).getBlock() == JourneyBlocks.carvedBloodRock && worldIn.getBlockState(
				pos.add(hitX + 2, hitY + 2, hitZ - 2)).getBlock() == JourneyBlocks.carvedBloodRock && worldIn.getBlockState(
				pos.add(hitX - 2, hitY + 2, hitZ + 2)).getBlock() == JourneyBlocks.carvedBloodRock && worldIn.getBlockState(
				
				//top layer
				pos.add(hitX - 1, hitY + 2, hitZ - 1)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
				pos.add(hitX + 1, hitY + 2, hitZ + 1)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
				pos.add(hitX + 1, hitY + 2, hitZ - 1)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
				pos.add(hitX - 1, hitY + 2, hitZ + 1)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(

				pos.add(hitX - 2, hitY + 2, hitZ - 1)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
				pos.add(hitX - 2, hitY + 2, hitZ + 1)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
				pos.add(hitX + 2, hitY + 2, hitZ - 1)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
				pos.add(hitX + 2, hitY + 2, hitZ + 1)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(

				pos.add(hitX - 1, hitY + 2, hitZ - 2)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
				pos.add(hitX - 1, hitY + 2, hitZ + 2)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
				pos.add(hitX + 1, hitY + 2, hitZ - 2)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
				pos.add(hitX + 1, hitY + 2, hitZ + 2)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
						
				//roof
				pos.add(hitX - 1, hitY + 3, hitZ - 1)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
				pos.add(hitX + 1, hitY + 3, hitZ + 1)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
				pos.add(hitX + 1, hitY + 3, hitZ - 1)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
				pos.add(hitX - 1, hitY + 3, hitZ + 1)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(

				pos.add(hitX - 1, hitY + 3, hitZ)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
				pos.add(hitX + 1, hitY + 3, hitZ)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
				pos.add(hitX, hitY + 3, hitZ - 1)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
				pos.add(hitX, hitY + 3, hitZ + 1)).getBlock() == JourneyBlocks.bloodBricks && worldIn.getBlockState(
						
				pos.add(hitX, hitY + 2, hitZ)).getBlock() == JourneyBlocks.obelisk) { 

			playerIn.openGui(SlayerAPI.MOD_ID, GuiHandler.summoning, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
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
	
	private IBlockState getStructureBlocks(IBlockState state, World worldIn, BlockPos pos,float hitX, float hitY, float hitZ) {
		return state;
		
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntitySummoningTable tile = (TileEntitySummoningTable)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tile);
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySummoningTable();
	}
}