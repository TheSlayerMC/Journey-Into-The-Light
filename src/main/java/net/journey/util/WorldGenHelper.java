package net.journey.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * New version of WorldGenAPI
 */
public class WorldGenHelper {
	/**
	 * The optimized version of {@link World#setBlockState(BlockPos, IBlockState)} for generation.
	 * It doesn't send block updates and doesn't notify neighbours.
	 */
	public static void setStateFast(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, state, 2 | 16);
	}

	/**
	 * Generates horizontal hollow cylinder with provided radius.
	 * Overall width of cylinder will be {@code 1 + radius * 2}.
	 *
	 * @param firstCircleCenterPos the center position of the first circle to generate. Will be gradually moved into the provided direction.
	 * @param radius               cylinder radius
	 * @param generator            function, which will be called for every found position in order to draw a hollow cylinder.
	 *                             <font color="yellow">Note, the generator consumes MutableBlockPos, so don't change provided pos inside the generator, otherwise expect the mistakes!</font>
	 */
	public static void genHollowCylinder(BlockPos firstCircleCenterPos, int radius, int height, EnumFacing direction, Consumer<MutableBlockPos> generator) {
		BoundMutablePos centerPos = new BoundMutablePos(firstCircleCenterPos);
		for (int i = 0; i < height; i++) {
			genHollowCircle(centerPos.offsetFromBound(direction, i), radius, direction.getAxis(), generator);
		}
	}

	/**
	 * Generates vertical hollow circle with provided radius.
	 * Overall height of circle will be {@code 1 + radius * 2}.
	 *
	 * @param centerPos position of the future circle center
	 * @param radius    circle radius
	 * @param generator function, which will be called for every found position in order to draw a hollow circle
	 *                  <font color="yellow">Note, the generator consumes MutableBlockPos, so don't change provided pos inside the generator, otherwise expect the mistakes!</font>
	 */
	public static void genHollowCircle(BlockPos centerPos, int radius, Axis direction, Consumer<MutableBlockPos> generator) {
		BoundMutablePos pos = new BoundMutablePos(centerPos);
		genHollowCircle(radius, (x, y) -> {
			switch (direction) {
				case X:
					generator.accept(pos.addFromBound(0, y, x));
					break;
				case Y:
					generator.accept(pos.addFromBound(x, 0, y));
					break;
				case Z:
					generator.accept(pos.addFromBound(x, y, 0));
					break;
				default:
					throw new UnsupportedOperationException("Unsupported direction: " + direction);
			}
		});
	}

	/**
	 * Mid-Point Circle Drawing Algorithm.
	 * <p>
	 * Generates hollow circle with provided radius.
	 * Overall width of circle will be {@code 1 + radius * 2}.
	 *
	 * @param radius    circle radius
	 * @param generator function, which will be called for every found position (x, y) to draw a hollow circle.
	 *                  Note, that 0, 0 is the center of the circle.
	 */
	private static void genHollowCircle(int radius, BiConsumer<Integer, Integer> generator) {
		int x = radius;
		int y = 0;

		// Printing the initial point on the axes
		// after translation
		generator.accept(x, y);

		// When radius is zero only a single
		// point will be printed
		if (radius > 0) {
			generator.accept(-x, y);
			generator.accept(y, -x);
			generator.accept(-y, x);
		}

		// Initialising the value of P
		int P = 1 - radius;
		while (x > y) {
			y++;

			// Mid-point is inside or on the perimeter
			if (P <= 0)
				P += 2 * y + 1;

				// Mid-point is outside the perimeter
			else {
				x--;
				P += 2 * (y - x) + 1;
			}

			// All the perimeter points have already been printed
			if (x < y) {
				break;
			}

			// Printing the generated point and its reflection
			// in the other octants after translation
			generator.accept(x, y);
			generator.accept(-x, y);
			generator.accept(x, -y);
			generator.accept(-x, -y);

			// If the generated point is on the line x = y then
			// the perimeter points have already been printed
			if (x != y) {
				generator.accept(y, x);
				generator.accept(-y, x);
				generator.accept(y, -x);
				generator.accept(-y, -x);
			}
		}
	}

	public static class BoundMutablePos extends MutableBlockPos {
		private final BlockPos bound;

		public BoundMutablePos() {
			this(0, 0, 0);
		}

		public BoundMutablePos(BlockPos pos) {
			this(pos.getX(), pos.getY(), pos.getZ());
		}

		public BoundMutablePos(int x, int y, int z) {
			super(x, y, z);
			bound = new BlockPos(x, y, z);
		}

		public BoundMutablePos addFromBound(int x, int y, int z) {
			setPos(bound.getX() + x, bound.getY() + y, bound.getZ() + z);
			return this;
		}

		public BoundMutablePos offsetFromBound(EnumFacing offset, int n) {
			setPos(bound.getX() + offset.getXOffset() * n, bound.getY() + offset.getYOffset() * n, bound.getZ() + offset.getZOffset() * n);
			return this;
		}
	}
}
