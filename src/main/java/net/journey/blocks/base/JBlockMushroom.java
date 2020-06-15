package net.journey.blocks.base;

import net.journey.api.block.GroundPredicate;
import net.journey.util.RandHelper;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.EnumMaterialTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * Base class for mushroom blocks (plants).
 * <p>
 * By default:
 * <ul>
 *     <li>Checks for ground to be placed and stay (default: {@link GroundPredicate#SOLID_SIDE})</li>
 *     <li>Drops itself</li>
 *     <li>Is NOT replaceable</li>
 *     <li>Has {@link Material#PLANTS} material unless it is  provided in a constructor</li>
 *     <li>Has no hardness (will be instantly broken)</li>
 * </ul>
 * <p>
 * You can also set:
 * <ul>
 *     <li>{@link #groundPredicate} - predicate that checks if plant can be placed and sustain on provided block</li>
 *     <li>{@link #plantDirection} - the side of ground block where plant can be placed and stay.</li>
 *     <li>{@link #boundingBox} - bounding box of the plant. Equals standard bush box by default.</li>
 *     <li>{@link #damageOnContact} - Determines if plant will cause damage on entity contact. Default: false</li>
 *     <li>{@link #bigMushroomGenerator} - generator, that can be called to spawn big mushroom. Default: null</li>
 * </ul>
 * <p>
 * The item model for it should be placed to "models/item/block/plant/" by default.
 */
@SuppressWarnings("JavadocReference")
public class JBlockMushroom extends JBlockPlant implements IGrowable {
	public static final AxisAlignedBB MUSHROOM_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.4000000059604645D, 0.699999988079071D);
	@Nullable
	private BigMushroomGenerator bigMushroomGenerator = null;

	public JBlockMushroom(String name, String enName) {
		super(name, enName);
		setBoundingBox(MUSHROOM_AABB);
		setGroundPredicate(GroundPredicate.SOLID_SIDE);
	}

	public JBlockMushroom(String name, String enName, CreativeTabs tab) {
		super(name, enName, tab);
	}

	public JBlockMushroom(EnumMaterialTypes type, String name, String enName, CreativeTabs tab) {
		super(type, name, enName, tab);
	}

	public JBlockMushroom setBigMushroomGenerator(BigMushroomGenerator bigMushroomGenerator) {
		this.bigMushroomGenerator = bigMushroomGenerator;
		return this;
	}

	public boolean generateBigMushroom(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		worldIn.setBlockToAir(pos);

		if (bigMushroomGenerator != null && bigMushroomGenerator.generate(worldIn, pos, state, rand)) {
			return true;
		} else {
			worldIn.setBlockState(pos, state, 3);
			return false;
		}
	}

	@Override
	public void updateTick(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, Random rand) {
		if (rand.nextInt(25) == 0) {
			int spreadLimit = 5;
			int spreadRadius = 4;

			for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-spreadRadius, -1, -spreadRadius), pos.add(spreadRadius, 1, spreadRadius))) {
				if (worldIn.getBlockState(blockpos).getBlock() == this) {
					--spreadLimit;

					if (spreadLimit <= 0) {
						return;
					}
				}
			}

			BlockPos randPos = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);

			for (int i = 0; i < 4; ++i) {
				if (worldIn.isAirBlock(randPos) && this.canBlockStay(worldIn, randPos, this.getDefaultState())) {
					pos = randPos;
				}

				randPos = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);
			}

			if (worldIn.isAirBlock(randPos) && this.canBlockStay(worldIn, randPos, this.getDefaultState())) {
				worldIn.setBlockState(randPos, this.getDefaultState(), 2);
			}
		}

		super.updateTick(worldIn, pos, state, rand);
	}

	@Override
	public boolean canGrow(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, boolean isClient) {
		return bigMushroomGenerator != null;
	}

	@Override
	public boolean canUseBonemeal(@NotNull World worldIn, Random rand, @NotNull BlockPos pos, @NotNull IBlockState state) {
		return (double) rand.nextFloat() < 0.4D;
	}

	@Override
	public void grow(@NotNull World worldIn, @NotNull Random rand, @NotNull BlockPos pos, @NotNull IBlockState state) {
		this.generateBigMushroom(worldIn, pos, state, rand);
	}

	public interface BigMushroomGenerator {
		boolean generate(World worldIn, BlockPos pos, IBlockState state, Random rand);

		static BigMushroomGenerator singleGenerator(WorldGenerator generator) {
			return (worldIn, pos, state, rand) -> generator.generate(worldIn, rand, pos);
		}

		static BigMushroomGenerator randomGenerator(WorldGenerator... generators) {
			return (worldIn, pos, state, rand) -> RandHelper.chooseEqual(rand, generators).generate(worldIn, rand, pos);
		}
	}
}
