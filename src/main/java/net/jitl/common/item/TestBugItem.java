package net.jitl.common.item;

import net.jitl.common.world.gen.util.GenHelper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class TestBugItem extends Item {
    public TestBugItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isClientSide()) {

            GenHelper.genHollowCircle(5, Direction.Axis.Y, blockPos -> {
                worldIn.setBlockAndUpdate(new BlockPos(playerIn.position()).offset(blockPos), Blocks.DIAMOND_BLOCK.defaultBlockState());
            });

            genFilledCircle(5, line -> {
                System.out.println(line);
                System.out.println("111");
                for (int i = line.getX0(); i >= line.getX1(); i--) {
                    System.out.println("222");
                    for (int j = line.getY0(); j >= line.getY1(); j--) {
                        System.out.println("333");
                        worldIn.setBlockAndUpdate(new BlockPos(playerIn.position()).above().offset(i, 0, j), Blocks.STONE.defaultBlockState());
                    }
                }
            });
        }

        return ActionResult.success(playerIn.getItemInHand(handIn));
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
    public static void genFilledCircle(int radius, Consumer<Line> generator) {
        int x = radius;
        int y = 0;

        Line line = new Line(-1, -1, -1, -1);

        // Printing the initial point on the axes
        // after translation
//        generator.accept(x, y);
        generator.accept(line.set(x, y, -x, y));

        // When radius is zero only a single
        // point will be printed
        if (radius > 0) {
//            generator.accept(-x, y);
//            generator.accept(y, -x);
//            generator.accept(-y, x);
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
            generator.accept(line.set(x, y, -x, y));
            generator.accept(line.set(x, -y, -x, -y));
//            generator.accept(x, y);
//            generator.accept(-x, y);
//            generator.accept(x, -y);
//            generator.accept(-x, -y);

            // If the generated point is on the line x = y then
            // the perimeter points have already been printed
            if (x != y) {
//                generator.accept(y, x);
//                generator.accept(-y, x);
//                generator.accept(y, -x);
//                generator.accept(-y, -x);
            }
        }
    }

    public static class Line {
        private int x0;
        private int y0;
        private int x1;
        private int y1;

        public Line(int x0, int y0, int x1, int y1) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
        }

        public int getX0() {
            return x0;
        }

        public int getY0() {
            return y0;
        }

        public int getX1() {
            return x1;
        }

        public int getY1() {
            return y1;
        }

        private Line set(int x0, int y0, int x1, int y1) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;

            return this;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "x0=" + x0 +
                    ", y0=" + y0 +
                    ", x1=" + x1 +
                    ", y1=" + y1 +
                    '}';
        }
    }
}
