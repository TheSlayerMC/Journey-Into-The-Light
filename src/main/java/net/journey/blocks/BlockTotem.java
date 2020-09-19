package net.journey.blocks;

import net.journey.client.render.particles.ParticleSwampFly;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyItems;
import net.journey.util.WorldUtils;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

import java.util.Random;

public class BlockTotem extends BlockMod {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool ACTIVATED = PropertyBool.create("activated");

	private final TotemType totemType;

	public BlockTotem(EnumMaterialTypes enumMaterialTypes, String name, String f, float hardness, TotemType totemType) {
		super(enumMaterialTypes, name, f, hardness);
		this.totemType = totemType;
		this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVATED, false).withProperty(FACING, EnumFacing.NORTH));
		this.setTickRandomly(true);
		this.setBlockUnbreakable();
		this.setResistance(100000F);
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(ACTIVATED, false).withProperty(FACING, placer.getHorizontalFacing());
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(ACTIVATED, (meta & 1) != 0).withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | state.getValue(FACING).getHorizontalIndex();

		if (state.getValue(ACTIVATED)) {
			i |= 4;
		}

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING, ACTIVATED);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.getDefaultState().withProperty(ACTIVATED, false);
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return blockState.getValue(ACTIVATED) ? 15 : 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (stateIn.getValue(ACTIVATED)) {
			renderParticle(worldIn, pos.getX(), pos.getY(), pos.getZ(), pos);
		}
	}

	@SideOnly(Side.CLIENT)
	private void renderParticle(World w, int x, int y, int z, BlockPos pos) {
		float r = 0.0F, g = 0.5F, b = 1.0F;
		double d0 = 0.0625D;
		Random random = w.rand;
		for (int l = 0; l < 6; ++l) {
			double d1 = x + random.nextFloat();
			double d2 = y + random.nextFloat();
			double d3 = z + random.nextFloat();
			if (l == 0 && !w.getBlockState(pos.up()).isOpaqueCube()) d2 = y + 1 + d0;
			if (l == 1 && !w.getBlockState(pos.down()).isOpaqueCube()) d2 = y + 0 - d0;
			if (l == 2 && !w.getBlockState(pos.south()).isOpaqueCube()) d3 = z + 1 + d0;
			if (l == 3 && !w.getBlockState(pos.north()).isOpaqueCube()) d3 = z + 0 - d0;
			if (l == 4 && !w.getBlockState(pos.east()).isOpaqueCube()) d1 = x + 1 + d0;
			if (l == 5 && !w.getBlockState(pos.west()).isOpaqueCube()) d1 = x + 0 - d0;
			if (d1 < x || d1 > x + 1 || d2 < 0.0D || d2 > y + 1 || d3 < z || d3 > z + 1) {
				WorldUtils.spawnParticle(new ParticleSwampFly(w, d1, d2, d3, r, g, b), w);
			}
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Item activator = null;
		if (totemType == TotemType.HAPPY) activator = JourneyItems.boilPowder;
		if (totemType == TotemType.MAD) activator = JourneyItems.hellstoneIngot;
		if (totemType == TotemType.SCARED) activator = JourneyItems.natureTablet;
		if (totemType == TotemType.SAD) activator = JourneyItems.enchantedLeaf;

		boolean flag = playerIn.getHeldItemMainhand().getItem() == activator;
		if (flag) {
			worldIn.playSound(playerIn, pos, JourneySounds.TOTEM_ACTIVATE, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!worldIn.isRemote) {
				worldIn.setBlockState(pos, state.withProperty(BlockTotem.ACTIVATED, true));
				worldIn.updateComparatorOutputLevel(pos, this);
			}
		}
		return flag;
	}

	public enum TotemType {
		HAPPY, MAD, SCARED, SAD
	}
}
