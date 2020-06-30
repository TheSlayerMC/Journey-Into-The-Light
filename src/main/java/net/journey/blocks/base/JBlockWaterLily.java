package net.journey.blocks.base;

import net.journey.api.block.GroundPredicate;
import net.journey.init.items.JourneyItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

//TODO remove itemblock registering, because it's already registered as JItemWaterLily
public class JBlockWaterLily extends JBlockPlant {

	protected static final AxisAlignedBB LILY_PAD_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.09375D, 0.9375D);

	public JBlockWaterLily(String name, String enName) {
		super(name, enName);
		setGroundPredicate(GroundPredicate.WATER);
		setBoundingBox(LILY_PAD_AABB);
		setCreativeTab(null);
		setSoundType(EnumMaterialTypes.GRASS.getSound());
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		if (!(entityIn instanceof EntityBoat)) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, LILY_PAD_AABB);
		}
	}

	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		super.onEntityCollision(worldIn, pos, state, entityIn);
		if (entityIn instanceof EntityBoat) {
			worldIn.destroyBlock(new BlockPos(pos), true);
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return JourneyItems.swampLily;
	}
}
