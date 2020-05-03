package net.journey.blocks;

import com.google.common.base.Predicates;

import net.journey.init.JourneySounds;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.util.RandUtils;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockAncientCatalyst extends BlockMod {

	private static BlockPattern pattern;
	
    public static final PropertyBool INSERT = PropertyBool.create("insert");
    protected static final AxisAlignedBB AABB_BLOCK = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_INSERT = new AxisAlignedBB(0.3125D, 0.8125D, 0.3125D, 0.6875D, 1.0D, 0.6875D);

    public BlockAncientCatalyst(String name, String f) {
        super(name, f, false);
        this.setDefaultState(this.blockState.getBaseState().withProperty(INSERT, Boolean.valueOf(false)));
    }
    
    public static BlockPattern getOrCreatepattern() {
        if (pattern == null) {
            pattern = FactoryBlockPattern.start().aisle(
            "v?v", 
            "???", 
            "v?v").where(
            '?', BlockWorldState.hasState(BlockStateMatcher.ANY)).where(
            'v', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.ANCIENT_SOCKET).where(INSERT, Predicates.equalTo(Boolean.valueOf(true))))).build();
        }
        return pattern;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB_BLOCK;
    }

    @Override
    public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        if ((state.getValue(INSERT))) {
            drops.add(new ItemStack(JourneyItems.ANCIENT_EYE_OF_OPENING, 1));
        }
        drops.add(new ItemStack(SlayerAPI.toItem(JourneyBlocks.ANCIENT_SOCKET)));
        return drops;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BLOCK);

        if (worldIn.getBlockState(pos).getValue(INSERT).booleanValue()) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_INSERT);
        }
    }

    /*@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        float x = hitX, y = hitY, z = hitZ;
        if (playerIn.getHeldItem(EnumHand.MAIN_HAND) != null && playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem() == insert) {
            worldIn.spawnParticle(EnumParticleTypes.SPELL, x, y, z, 2.0F, 2.0F, 2.0F, 2);
            worldIn.playSound(x, y, z, JourneySounds.SUMMON_TABLE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
            if (!worldIn.isRemote) {
                worldIn.setBlockState(pos.add(0, 0, 0), this.getDefaultState().withProperty(INSERT, Boolean.valueOf(true)));
                playerIn.getHeldItemMainhand().shrink(1);
            }
        }
		return true;
    } */

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return SlayerAPI.toItem(this);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(INSERT, Boolean.valueOf(false));
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(INSERT, Boolean.valueOf((meta & 1) != 0));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        if (state.getValue(INSERT).booleanValue()) {
            i = 1;
        }
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, INSERT);
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return blockState.getValue(INSERT).booleanValue() ? 15 : 0;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
    }
}