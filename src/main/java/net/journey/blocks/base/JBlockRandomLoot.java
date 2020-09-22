package net.journey.blocks.base;

import net.journey.init.JourneyTabs;
import net.journey.util.LootHelper;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

public class JBlockRandomLoot extends BlockMod {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	protected ResourceLocation lootTable;
	LootType type;

	public JBlockRandomLoot(EnumMaterialTypes materialTypes, String name, String enName, ResourceLocation lootTable, LootType lootType) {
		super(materialTypes, name, enName, 0.1F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setCreativeTab(JourneyTabs.INTERACTIVE_BLOCKS);
		this.lootTable = lootTable;
		this.type = lootType;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.addAll(LootHelper.genFromLootTable(lootTable, ((WorldServer) world), builder -> builder.withLuck(fortune)));
	}

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
    }

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return type == LootType.POT ? BlockFaceShape.UNDEFINED : BlockFaceShape.SOLID;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return type == LootType.BOX;
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return type == LootType.BOX;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return type == LootType.BOX;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return type == LootType.POT ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
	}

	public enum LootType {
		POT, BOX
	}
}
