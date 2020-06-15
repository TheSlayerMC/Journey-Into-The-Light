package net.journey.blocks.base;

import net.journey.JITL;
import net.journey.api.block.GroundPredicate;
import net.journey.api.block.IHasCustomItemPath;
import net.journey.init.JourneyTabs;
import net.journey.util.StuffConstructor;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base class for plant blocks.
 * <p>
 * By default:
 * <ul>
 *     <li>Checks for ground to be placed and stay</li>
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
 * </ul>
 *
 * The item model for it should be placed to "models/item/block/plant/" by default.
 */
public abstract class JBlockPlant extends BlockBush implements IHasCustomItemPath {
	public static final AxisAlignedBB SMALL_PLANT_BB = BUSH_AABB;
	public static final AxisAlignedBB TALL_PLANT_BB = new AxisAlignedBB(BUSH_AABB.minX, 0, BUSH_AABB.minZ, BUSH_AABB.maxX, 1, BUSH_AABB.maxZ);
	public static final AxisAlignedBB BIG_PLANT_BB = new AxisAlignedBB(0.15, 0, 0.15, 0.85, 1, 0.85);

	public static final AxisAlignedBB SHORT_GRASS_BB = new AxisAlignedBB(0.15, 0, 0.15, 0.85, 0.35, 0.85);
	private AxisAlignedBB boundingBox = BUSH_AABB;
	/**
	 * Predicate that checks if plant can be placed and sustain on provided block.
	 */
	private GroundPredicate groundPredicate = GroundPredicate.GRASS_BLOCK;
	/**
	 * The side of ground block where plant can be placed and stay.
	 */
	private EnumFacing plantDirection = EnumFacing.UP;
	/**
	 * If equals true, then it will cause damage on contact.
	 */
	private boolean damageOnContact = false;

	public JBlockPlant(String name, String enName) {
		this(EnumMaterialTypes.PLANT, name, enName, JourneyTabs.DECORATION);
	}

	public JBlockPlant(String name, String enName, CreativeTabs tab) {
		this(EnumMaterialTypes.PLANT, name, enName, tab);
	}

	public JBlockPlant(EnumMaterialTypes type, String name, String enName, CreativeTabs tab) {
		super(type.getMaterial());
		setSoundType(type.getSound());
		StuffConstructor.regAndSetupBlock(this, name, enName, 0F, tab);
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

	public JBlockPlant setBoundingBox(AxisAlignedBB bb) {
		boundingBox = bb;
		return this;
	}

	public JBlockPlant enableDamageOnContact() {
		damageOnContact = true;
		return this;
	}

	@Override
	public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
		return boundingBox;
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

	@Override
	public void onEntityCollision(World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull Entity entityIn) {
		if (!worldIn.isRemote) {
			if (damageOnContact) {
				AxisAlignedBB boundingBox = state.getCollisionBoundingBox(worldIn, pos);

				if (boundingBox == NULL_AABB) {
					boundingBox = state.getBoundingBox(worldIn, pos);
				}

				if (boundingBox != null) {
					boundingBox = boundingBox.offset(pos);
				}

				if (boundingBox != null && entityIn.getEntityBoundingBox().intersects(boundingBox)) {
					entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0F);
				}
			}
		}
	}
}
