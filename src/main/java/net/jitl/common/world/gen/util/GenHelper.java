package net.jitl.common.world.gen.util;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import ru.timeconqueror.timecore.util.MathUtils;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
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
            applyDirection(pos, x, y, 0, direction);
            generator.accept(pos);
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
                applyDirection(pos, x, y, depth, direction);
                generator.accept(pos);
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
                applyDirection(pos, x, line.getY(), 0, direction);
                generator.accept(pos);
            }
        });
    }

    public static void genFilledBorderedCircle(int radius, Direction.Axis direction, IBorderGenerator borderGenerator, Consumer<BlockPos> filledPartGenerator) {
        genHollowCircle(radius, direction, borderGenerator::gen);

        BlockPos.Mutable pos = new BlockPos.Mutable();

        fillConvexPolygon(0, 0, radius, (x, y) -> {
            applyDirection(pos, x, y, 0, direction);
            return borderGenerator.isBorder(pos);
        }, (x, y) -> filledPartGenerator.accept(applyDirection(pos, x, y, 0, direction)));
    }

    private static BlockPos.Mutable applyDirection(BlockPos.Mutable pos, int x, int y, int z, Direction.Axis direction) {
        switch (direction) {
            case X:
                return pos.set(z, y, x);
            case Y:
                return pos.set(x, z, y);
            case Z:
                return pos.set(x, y, z);
            default:
                throw new UnsupportedOperationException("Unsupported direction: " + direction);
        }
    }

    public interface IBorderGenerator {
        void gen(BlockPos relPos);

        boolean isBorder(BlockPos relPos);
    }

    private static void fillConvexPolygon(int startX, int startY, int fillingDistance, BiPredicate<Integer, Integer> stopCondition, BiConsumer<Integer, Integer> generator) {
        if (stopCondition.test(startX, startY)) return;

        generator.accept(startX, startY);

        int py = startY;
        int ny = startY - 1;

        while (py < startY + fillingDistance) {
            fill1Dimensional(startX, py, fillingDistance, stopCondition, generator);
            py++;
        }

        while (ny > startY - fillingDistance) {
            fill1Dimensional(startX, ny, fillingDistance, stopCondition, generator);
            ny--;
        }
    }

    private static void fill1Dimensional(int startX, int startY, int fillingRadius, BiPredicate<Integer, Integer> stopCondition, BiConsumer<Integer, Integer> generator) {
        int px = startX;
        int nx = startX - 1;

        while (px < startX + fillingRadius) {
            if (stopCondition.test(px, startY)) break;
            generator.accept(px, startY);
            px++;
        }

        while (nx > startX - fillingRadius) {
            if (stopCondition.test(nx, startY)) break;
            generator.accept(nx, startY);
            nx--;
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
