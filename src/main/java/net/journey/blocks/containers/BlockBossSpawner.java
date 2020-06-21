package net.journey.blocks.containers;

import net.journey.JITL;
import net.journey.blocks.tileentity.TileEntityBossSpawner;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.entity.tileentity.container.BlockModContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockBossSpawner extends BlockModContainer {

	public BlockBossSpawner(String name, String finalName) {
		super(name, finalName);
		setCreativeTab(JourneyTabs.INTERACTIVE_BLOCKS);
	}

	public static void setBlock(World world, BlockPos pos, Class<? extends Entity> entityToSpawnClass) {
		world.setBlockState(pos, JourneyBlocks.bossSpawner.getDefaultState());

		TileEntityBossSpawner tileEntity = getBossBlockTileEntity(world, pos);
		if (tileEntity != null) {
			tileEntity.setEntity(entityToSpawnClass);
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			TileEntityBossSpawner bossBlockTileEntity = getBossBlockTileEntity(worldIn, pos);
			if (bossBlockTileEntity != null) {
				bossBlockTileEntity.activate();
			}
		}

		return true;
	}


	@Nullable
	private static TileEntityBossSpawner getBossBlockTileEntity(World world, BlockPos pos) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof TileEntityBossSpawner) {
			return (TileEntityBossSpawner) tileEntity;
		} else {
			JITL.LOGGER.error("{} on {} can't be handled, because it's not a {}. Provided: {}", BlockBossSpawner.class, pos, TileEntityBossSpawner.class, tileEntity);
			return null;
		}
	}

	@Override
	public @NotNull EnumBlockRenderType getRenderType(@NotNull IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.SOLID;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(@NotNull World worldIn, int meta) {
		return new TileEntityBossSpawner();
	}
}