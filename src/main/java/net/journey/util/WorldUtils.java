package net.journey.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldUtils {
	@SideOnly(Side.CLIENT)
	public static void spawnParticle(Particle particle, World world) {
		if (world.isRemote) {
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(particle);
		}
	}

	/**
	 * Validates the existence of this tile entity and returns it in the case of success.
	 * Throws exception, if tile entity is null or doesn't inherit provided {@code tileClass}
	 */
	public static <T extends TileEntity> T getValidTile(World world, BlockPos pos, IBlockState state, Class<T> tileClass) {
		if (state.getBlock().hasTileEntity(state)) {
			TileEntity tileEntity = world.getTileEntity(pos);
			if (tileClass.isInstance(tileEntity)) {
				return tileClass.cast(tileEntity);
			} else {
				Class<?> invalidClass = tileEntity != null ? tileEntity.getClass() : null;
				throw new IllegalStateException("Can't get Tile Entity for block " + state.getBlock() + ". Requested: " + tileClass + ", current: " + invalidClass);
			}
		} else {
			throw new IllegalStateException("Block " + state.getBlock() + " can't have a Tile Entity");
		}
	}
}
