package net.jitl.common.world.gen.util;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import ru.timeconqueror.timecore.api.util.MathUtils;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GenHelper {

    /**
     * Generates hollow circle with provided radius.
     * Overall height of circle will be {@code 1 + radius * 2}.
     *
     * @param radius    circle radius
     * @param direction the front direction of generated circle
     * @param generator function, which will be called for every found position in order to draw a hollow circle.
     *                  Position 0, 0, 0 will be at the center of the circle.
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
     * Generates horizontal hollow cylinder with provided radius.
     * Overall width of cylinder will be {@code 1 + radius * 2}.
     *
     * @param radius    cylinder radius
     * @param height    cylinder height
     * @param generator function, which will be called for every found position in order to draw a hollow cylinder.
     *                  Position 0, 0, 0 will be at the center of the lowest circle of the cylinder.
     *                  <font color="yellow">Note, that these positions are RELATIVE, not absolute!</font>
     */
    public static void genHollowCyl(int radius, int height, Direction.Axis direction, Consumer<BlockPos> generator) {
        BlockPos.Mutable pos = new BlockPos.Mutable();
        genHollowCircle(radius, (x, y) -> {
            for (int depth = 0; depth < height; depth++) {
                switch (direction) {
                    case X:
                        generator.accept(pos.set(depth, y, x));
                        break;
                    case Y:
                        generator.accept(pos.set(x, depth, y));
                        break;
                    case Z:
                        generator.accept(pos.set(x, y, depth));
                        break;
                    default:
                        throw new UnsupportedOperationException("Unsupported direction: " + direction);
                }
            }
        });
    }

    /**
     * Generates filled circle with provided radius.
     * Overall height of circle will be {@code 1 + radius * 2}.
     *
     * @param radius    circle radius
     * @param direction the front direction of generated circle
     * @param generator function, which will be called for every found position in order to draw a filled circle.
     *                  Position 0, 0, 0 will be at the center of the circle.
     *                  <font color="yellow">Note, that these positions are RELATIVE, not absolute!</font>
     */
    public static void genFilledCircle(int radius, Direction.Axis direction, Consumer<BlockPos> generator) {
        BlockPos.Mutable pos = new BlockPos.Mutable();
        genFilledCircle(radius, (line) -> {
            for (int x = line.getX0(); x >= line.getX1(); x--) {
                switch (direction) {
                    case X:
                        generator.accept(pos.set(0, line.getY(), x));
                        break;
                    case Y:
                        generator.accept(pos.set(x, 0, line.getY()));
                        break;
                    case Z:
                        generator.accept(pos.set(x, line.getY(), 0));
                        break;
                    default:
                        throw new UnsupportedOperationException("Unsupported direction: " + direction);
                }
            }
        });
    }

    /**
     * Bresenham's Circle Drawing Algorithm.
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

    /**
     * Bresenham's Circle Drawing Algorithm.
     * <p>
     * Generates hollow circle with provided radius.
     * Overall width of circle will be {@code 1 + radius * 2}.
     *
     * @param radius    circle radius
     * @param generator function, which will be called for every found position (x, y) to draw a hollow circle.
     *                  Note, that 0, 0 is the center of the circle.
     */
    @SuppressWarnings("SuspiciousNameCombination")
    private static void genFilledCircle(int radius, Consumer<ILine> generator) {
        int x = radius;
        int y = 0;

        LineHelper line = new LineHelper(-1, -1, -1);

        // Printing the initial point on the axes
        // after translation
        generator.accept(line.set(x, -x, y));

        // y to max x
        Int2IntMap anotherQuarter = new Int2IntOpenHashMap();

        // Initialising the value of decisionParam
        int decisionParam = 1 - radius;
        while (x > y) {
            y++;

            // Mid-point is inside or on the perimeter
            if (decisionParam <= 0) {
                decisionParam += 2 * y + 1;
            } else {// Mid-point is outside the perimeter
                x--;
                decisionParam += 2 * (y - x) + 1;
            }

            // All the perimeter points have already been printed
            if (x < y) {
                break;
            }

            // Printing the generated point and its reflection
            // in the other octants after translation
            generator.accept(line.set(x, -x, y));
            generator.accept(line.set(x, -x, -y));

            // If the generated point is on the line x = y then
            // the perimeter points have already been printed
            if (x != y) {
                anotherQuarter.put(x, y); // since Y always increases, then there is no need to compare it with old value
            }
        }

        anotherQuarter.forEach((quarterX, maxY) -> {
            generator.accept(line.set(maxY, -maxY, quarterX));
            generator.accept(line.set(maxY, -maxY, -quarterX));
        });
    }

    public static int getAveragePlacementHeight(ChunkGenerator chunkGenerator, int x, int z, int x1, int z1) {
        int n1 = chunkGenerator.getFirstFreeHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
        int n2 = chunkGenerator.getFirstFreeHeight(x1, z, Heightmap.Type.WORLD_SURFACE_WG);
        int n3 = chunkGenerator.getFirstFreeHeight(x, z1, Heightmap.Type.WORLD_SURFACE_WG);
        int n4 = chunkGenerator.getFirstFreeHeight(x1, z1, Heightmap.Type.WORLD_SURFACE_WG);
        return MathUtils.average(n1, n2, n3, n4);
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

    public interface ILine {
        int getX0();

        int getX1();

        int getY();
    }

    private static class LineHelper implements ILine {
        private int x0;
        private int x1;
        private int y;

        public LineHelper(int x0, int x1, int y) {
            this.x0 = x0;
            this.x1 = x1;
            this.y = y;
        }

        public int getX0() {
            return x0;
        }

        public int getX1() {
            return x1;
        }

        public int getY() {
            return y;
        }

        /**
         * Sets provided coords.
         *
         * @return true if provided Y hasn't been already used.
         */
        private LineHelper set(int x0, int x1, int y) {
            this.x0 = x0;
            this.x1 = x1;
            this.y = y;
            return this;
        }

        @Override
        public String toString() {
            return "LineHelper{" +
                    "x0=" + x0 +
                    ", x1=" + x1 +
                    ", y=" + y +
                    '}';
        }
    }
}
