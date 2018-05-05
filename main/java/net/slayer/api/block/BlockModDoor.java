package net.slayer.api.block;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;

public class BlockModDoor extends BlockMod {
	
    public static final PropertyDirection FACING_PROP = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool OPEN_PROP = PropertyBool.create("open");
    public static final PropertyEnum HINGEPOSITION_PROP = PropertyEnum.create("hinge", BlockModDoor.EnumHingePosition.class);
    public static final PropertyBool POWERED_PROP = PropertyBool.create("powered");
    public static final PropertyEnum HALF_PROP = PropertyEnum.create("half", BlockModDoor.EnumDoorHalf.class);

    public BlockModDoor(EnumMaterialTypes t, float h, String name, String finalName) {
        super(t, name, finalName, h);
        setCreativeTab(null);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING_PROP, EnumFacing.NORTH).withProperty(OPEN_PROP, Boolean.valueOf(false)).withProperty(HINGEPOSITION_PROP, BlockModDoor.EnumHingePosition.LEFT).withProperty(POWERED_PROP, Boolean.valueOf(false)).withProperty(HALF_PROP, BlockModDoor.EnumDoorHalf.LOWER));
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess blockAccess, BlockPos pos) {
        return func_176516_g(func_176515_e(blockAccess, pos));
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
        this.setBlockBoundsBasedOnState(worldIn, pos);
        return super.getSelectedBoundingBox(worldIn, pos);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
        this.setBlockBoundsBasedOnState(worldIn, pos);
        return super.getCollisionBoundingBox(worldIn, pos, state);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
        this.func_150011_b(func_176515_e(access, pos));
    }

    private void func_150011_b(int p_150011_1_)
    {
        float f = 0.1875F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
        EnumFacing enumfacing = func_176511_f(p_150011_1_);
        boolean flag = func_176516_g(p_150011_1_);
        boolean flag1 = func_176513_j(p_150011_1_);

        if (flag)
        {
            if (enumfacing == EnumFacing.EAST)
            {
                if (!flag1)
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
                }
                else
                {
                    this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
                }
            }
            else if (enumfacing == EnumFacing.SOUTH)
            {
                if (!flag1)
                {
                    this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                }
                else
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
                }
            }
            else if (enumfacing == EnumFacing.WEST)
            {
                if (!flag1)
                {
                    this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
                }
                else
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
                }
            }
            else if (enumfacing == EnumFacing.NORTH)
            {
                if (!flag1)
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
                }
                else
                {
                    this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                }
            }
        }
        else if (enumfacing == EnumFacing.EAST)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        }
        else if (enumfacing == EnumFacing.SOUTH)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        }
        else if (enumfacing == EnumFacing.WEST)
        {
            this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
        else if (enumfacing == EnumFacing.NORTH)
        {
            this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (this.blockMaterial == Material.iron) {
            return false; //Allow items to interact with the door
        } else {
            BlockPos blockpos1 = state.getValue(HALF_PROP) == BlockModDoor.EnumDoorHalf.LOWER ? pos : pos.down();
            IBlockState iblockstate1 = pos.equals(blockpos1) ? state : worldIn.getBlockState(blockpos1);

            if (iblockstate1.getBlock() != this) {
                return false;
            } else {
                state = iblockstate1.cycleProperty(OPEN_PROP);
                worldIn.setBlockState(blockpos1, state, 2);
                worldIn.markBlockRangeForRenderUpdate(blockpos1, pos);
                worldIn.playAuxSFXAtEntity(playerIn, state.getValue(OPEN_PROP).booleanValue() ? 1003 : 1006, pos, 0);
                return true;
            }
        }
    }

    public void func_176512_a(World worldIn, BlockPos p_176512_2_, boolean p_176512_3_) {
        IBlockState iblockstate = worldIn.getBlockState(p_176512_2_);

        if (iblockstate.getBlock() == this) {
            BlockPos blockpos1 = iblockstate.getValue(HALF_PROP) == BlockModDoor.EnumDoorHalf.LOWER ? p_176512_2_ : p_176512_2_.down();
            IBlockState iblockstate1 = p_176512_2_ == blockpos1 ? iblockstate : worldIn.getBlockState(blockpos1);

            if (iblockstate1.getBlock() == this && iblockstate1.getValue(OPEN_PROP).booleanValue() != p_176512_3_) {
                worldIn.setBlockState(blockpos1, iblockstate1.withProperty(OPEN_PROP, Boolean.valueOf(p_176512_3_)), 2);
                worldIn.markBlockRangeForRenderUpdate(blockpos1, p_176512_2_);
                worldIn.playAuxSFXAtEntity((EntityPlayer)null, p_176512_3_ ? 1003 : 1006, p_176512_2_, 0);
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        if (state.getValue(HALF_PROP) == BlockModDoor.EnumDoorHalf.UPPER) {
            BlockPos blockpos1 = pos.down();
            IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

            if (iblockstate1.getBlock() != this) {
                worldIn.setBlockToAir(pos);
            }
            else if (neighborBlock != this) {
                this.onNeighborBlockChange(worldIn, blockpos1, iblockstate1, neighborBlock);
            }
        } else {
            boolean flag1 = false;
            BlockPos blockpos2 = pos.up();
            IBlockState iblockstate2 = worldIn.getBlockState(blockpos2);

            if (iblockstate2.getBlock() != this) {
                worldIn.setBlockToAir(pos);
                flag1 = true;
            }

            if (!World.doesBlockHaveSolidTopSurface(worldIn, pos.down())) {
                worldIn.setBlockToAir(pos);
                flag1 = true;

                if (iblockstate2.getBlock() == this) {
                    worldIn.setBlockToAir(blockpos2);
                }
            }

            if (flag1) {
                if (!worldIn.isRemote) {
                    this.dropBlockAsItem(worldIn, pos, state, 0);
                }
            } else {
                boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(blockpos2);

                if ((flag || neighborBlock.canProvidePower()) && neighborBlock != this && flag != iblockstate2.getValue(POWERED_PROP).booleanValue()) {
                    worldIn.setBlockState(blockpos2, iblockstate2.withProperty(POWERED_PROP, Boolean.valueOf(flag)), 2);

                    if (flag != state.getValue(OPEN_PROP).booleanValue()) {
                        worldIn.setBlockState(pos, state.withProperty(OPEN_PROP, Boolean.valueOf(flag)), 2);
                        worldIn.markBlockRangeForRenderUpdate(pos, pos);
                        worldIn.playAuxSFXAtEntity((EntityPlayer)null, flag ? 1003 : 1006, pos, 0);
                    }
                }
            }
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return state.getValue(HALF_PROP) == BlockModDoor.EnumDoorHalf.UPPER ? null : getItem();
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World worldIn, BlockPos pos, Vec3 start, Vec3 end) {
        this.setBlockBoundsBasedOnState(worldIn, pos);
        return super.collisionRayTrace(worldIn, pos, start, end);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return pos.getY() >= worldIn.getHeight() - 1 ? false : World.doesBlockHaveSolidTopSurface(worldIn, pos.down()) && super.canPlaceBlockAt(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos.up());
    }

    @Override
    public int getMobilityFlag() {
        return 1;
    }

    public static int func_176515_e(IBlockAccess p_176515_0_, BlockPos p_176515_1_) {
        IBlockState iblockstate = p_176515_0_.getBlockState(p_176515_1_);
        int i = iblockstate.getBlock().getMetaFromState(iblockstate);
        boolean flag = func_176518_i(i);
        IBlockState iblockstate1 = p_176515_0_.getBlockState(p_176515_1_.down());
        int j = iblockstate1.getBlock().getMetaFromState(iblockstate1);
        int k = flag ? j : i;
        IBlockState iblockstate2 = p_176515_0_.getBlockState(p_176515_1_.up());
        int l = iblockstate2.getBlock().getMetaFromState(iblockstate2);
        int i1 = flag ? i : l;
        boolean flag1 = (i1 & 1) != 0;
        boolean flag2 = (i1 & 2) != 0;
        return func_176510_b(k) | (flag ? 8 : 0) | (flag1 ? 16 : 0) | (flag2 ? 32 : 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos) {
        return getItem();
    }
    
    private Item getItem() {
    	return /*this == EssenceBlocks.hotDoor ? EssenceItems.hotDoorItem :*/ null;
    }
    
    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
        BlockPos blockpos1 = pos.down();
        if (playerIn.capabilities.isCreativeMode && state.getValue(HALF_PROP) == BlockModDoor.EnumDoorHalf.UPPER && worldIn.getBlockState(blockpos1).getBlock() == this) {
            worldIn.setBlockToAir(blockpos1);
        }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        IBlockState iblockstate1;
        if (state.getValue(HALF_PROP) == BlockModDoor.EnumDoorHalf.LOWER) {
            iblockstate1 = worldIn.getBlockState(pos.up());
            if (iblockstate1.getBlock() == this) {
                state = state.withProperty(HINGEPOSITION_PROP, iblockstate1.getValue(HINGEPOSITION_PROP)).withProperty(POWERED_PROP, iblockstate1.getValue(POWERED_PROP));
            }
        } else {
            iblockstate1 = worldIn.getBlockState(pos.down());
            if (iblockstate1.getBlock() == this) {
                state = state.withProperty(FACING_PROP, iblockstate1.getValue(FACING_PROP)).withProperty(OPEN_PROP, iblockstate1.getValue(OPEN_PROP));
            }
        }
        return state;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return (meta & 8) > 0 ? this.getDefaultState().withProperty(HALF_PROP, BlockModDoor.EnumDoorHalf.UPPER).withProperty(HINGEPOSITION_PROP, (meta & 1) > 0 ? BlockModDoor.EnumHingePosition.RIGHT : BlockModDoor.EnumHingePosition.LEFT).withProperty(POWERED_PROP, Boolean.valueOf((meta & 2) > 0)) : this.getDefaultState().withProperty(HALF_PROP, BlockModDoor.EnumDoorHalf.LOWER).withProperty(FACING_PROP, EnumFacing.getHorizontal(meta & 3).rotateYCCW()).withProperty(OPEN_PROP, Boolean.valueOf((meta & 4) > 0));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.CUTOUT;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        byte b0 = 0;
        int i;

        if (state.getValue(HALF_PROP) == BlockModDoor.EnumDoorHalf.UPPER)
        {
            i = b0 | 8;

            if (state.getValue(HINGEPOSITION_PROP) == BlockModDoor.EnumHingePosition.RIGHT)
            {
                i |= 1;
            }

            if (state.getValue(POWERED_PROP).booleanValue())
            {
                i |= 2;
            }
        }
        else
        {
            i = b0 | state.getValue(FACING_PROP).rotateY().getHorizontalIndex();

            if (state.getValue(OPEN_PROP).booleanValue())
            {
                i |= 4;
            }
        }

        return i;
    }

    protected static int func_176510_b(int p_176510_0_)
    {
        return p_176510_0_ & 7;
    }

    public static boolean func_176514_f(IBlockAccess p_176514_0_, BlockPos p_176514_1_)
    {
        return func_176516_g(func_176515_e(p_176514_0_, p_176514_1_));
    }

    public static EnumFacing func_176517_h(IBlockAccess p_176517_0_, BlockPos p_176517_1_)
    {
        return func_176511_f(func_176515_e(p_176517_0_, p_176517_1_));
    }

    public static EnumFacing func_176511_f(int p_176511_0_)
    {
        return EnumFacing.getHorizontal(p_176511_0_ & 3).rotateYCCW();
    }

    protected static boolean func_176516_g(int p_176516_0_)
    {
        return (p_176516_0_ & 4) != 0;
    }

    protected static boolean func_176518_i(int p_176518_0_)
    {
        return (p_176518_0_ & 8) != 0;
    }

    protected static boolean func_176513_j(int p_176513_0_)
    {
        return (p_176513_0_ & 16) != 0;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {HALF_PROP, FACING_PROP, OPEN_PROP, HINGEPOSITION_PROP, POWERED_PROP});
    }

    public static enum EnumDoorHalf implements IStringSerializable
    {
        UPPER,
        LOWER;

        @Override
		public String toString()
        {
            return this.getName();
        }

        @Override
		public String getName()
        {
            return this == UPPER ? "upper" : "lower";
        }
    }

    public static enum EnumHingePosition implements IStringSerializable
    {
        LEFT,
        RIGHT;

        @Override
		public String toString()
        {
            return this.getName();
        }

        @Override
		public String getName()
        {
            return this == LEFT ? "left" : "right";
        }
    }
}