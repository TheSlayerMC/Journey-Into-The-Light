package net.journey.blocks.base;

import net.journey.JITL;
import net.journey.api.block.GroundPredicate;
import net.journey.api.block.IHasCustomItemPath;
import net.journey.init.JourneyTabs;
import net.journey.util.StuffConstructor;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base class for plant blocks.
 * <p>
 * The item model for it should be placed to "models/item/block/plant/" by default.
 */
public class JBlockPlant extends BlockBush implements IHasCustomItemPath {
	/**
	 * Predicate that checks if plant can be placed and sustain on provided block.
	 */
	private GroundPredicate groundPredicate = GroundPredicate.SOLID_SIDE.and(GroundPredicate.GRASS_BLOCK);
	/**
	 * The direction of where plant is or should be placed, regarding to the ground.
	 */
	private EnumFacing plantDirection = EnumFacing.UP;

	public JBlockPlant(String name, String enName) {
		this(EnumMaterialTypes.PLANT, name, enName, JourneyTabs.DECORATION);
	}

	public JBlockPlant(String name, String enName, CreativeTabs tab) {
		this(EnumMaterialTypes.PLANT, name, enName, tab);
	}

	public JBlockPlant(EnumMaterialTypes type, String name, String enName, CreativeTabs tab) {
		super(type.getMaterial());
		setSoundType(type.getSound());
		StuffConstructor.regAndSetupBlock(this, name, enName, 0.2F, tab);
	}

	@NotNull
	@Override
	public ResourceLocation getItemModelResourceLocation() {
		return new ResourceLocation(JITL.MOD_ID, "block/plant/" + getRegistryName().getPath());
	}

	public JBlockPlant setGroundPredicate(@Nullable GroundPredicate groundPredicate) {
		this.groundPredicate = groundPredicate;
		return this;
	}

	public GroundPredicate getGroundPredicate() {
		return groundPredicate;
	}

	public JBlockPlant setPlantDirection(EnumFacing plantDirection) {
		this.plantDirection = plantDirection;
		return this;
	}

	public EnumFacing getPlantDirection() {
		return plantDirection;
	}

	/**
	 * Checks if this block can be placed exactly at the given position.
	 */
	@Override
	public boolean canPlaceBlockAt(World worldIn, @NotNull BlockPos pos) {
		return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && canBlockStay(worldIn, pos, worldIn.getBlockState(pos));
	}

	@Override
	public boolean canBlockStay(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state) {
		BlockPos groundPos = pos.offset(plantDirection.getOpposite());
		IBlockState groundState = worldIn.getBlockState(groundPos);
		return groundPredicate.testGround(worldIn, groundPos, groundState, plantDirection);
	}
}
