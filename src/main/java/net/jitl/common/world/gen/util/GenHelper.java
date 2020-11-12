package net.jitl.common.world.gen.util;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GenHelper {

    /**
     * Generates vertical hollow circle with provided radius.
     * Overall height of circle will be {@code 1 + radius * 2}.
     *
     * @param radius    circle radius
     * @param direction the front direction of generated circle
     * @param generator function, which will be called for every found position in order to draw a hollow circle
     *                  <font color="yellow">Note, that these positions are RELATIVE, not absolute!</font>
     */
    public static void genHollowCircle(int radius, Direction.Axis direction, Consumer<BlockPos> generator) {
        BlockPos.Mutable pos = new BlockPos.Mutable();
        genHollowCircle(radius, (x, y) -> {
            switch (direction) {
                case X:
                    generator.accept(pos.set(0, y, x));
                    break;
                case Y:
                    generator.accept(pos.set(x, 0, y));
                    break;
                case Z:
                    generator.accept(pos.set(x, y, 0));
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
    public static void genHollowCircle(int radius, BiConsumer<Integer, Integer> generator) {
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

    public static class BoundMutablePos extends BlockPos.Mutable {
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
            set(bound.getX() + x, bound.getY() + y, bound.getZ() + z);
            return this;
        }

        public BoundMutablePos offsetFromBound(Direction offset, int n) {
            set(bound.getX() + offset.getStepX() * n, bound.getY() + offset.getStepY() * n, bound.getZ() + offset.getStepZ() * n);
            return this;
        }
    }
}
