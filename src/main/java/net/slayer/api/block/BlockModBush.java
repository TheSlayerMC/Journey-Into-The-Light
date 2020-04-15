package net.slayer.api.block;

import net.journey.init.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.slayer.api.EnumMaterialTypes;

import java.util.Random;

public class BlockModBush extends BlockMod implements IPlantable, IGrowable {

    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 2);
    public static final AxisAlignedBB smallbush = new AxisAlignedBB(0.5F - 0.3F, 0.0F, 0.5F - 0.3F, 0.5F + 0.3F, 0.3F * 1.0F, 0.5F + 0.3F);
    public static final AxisAlignedBB fullbush = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    private boolean isNether;
    private Item berry;

    public BlockModBush(String name, String finalName, Item berry, boolean isNether) {
        super(EnumMaterialTypes.LEAVES, name, finalName, 1.0F);
        this.berry = berry;
        this.isNether = isNether;
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
        this.setTickRandomly(true);
        this.setLightOpacity(-100000);
        this.setCreativeTab(JourneyTabs.crops);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess access, BlockPos pos) {
        float f = 0.3F;
        if (state.getValue(AGE).intValue() == 0) {
            return smallbush;
        }

        if (state.getValue(AGE).intValue() == 1) {
            return fullbush;
        }

        if (state.getValue(AGE).intValue() == 2) {
            return fullbush;
        }
        return fullbush;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return null;
    }

    @Override
    public boolean canPlaceBlockAt(World w, BlockPos pos) {
        Block block = w.getBlockState(pos.down()).getBlock();
        if (isNether) {
            return block == Blocks.NETHERRACK;
        }
        if (!isNether) {
            return block == Blocks.GRASS || block == Blocks.DIRT;
        }
        return false;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(AGE).intValue();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AGE);
    }


    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return null;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return this.getDefaultState();
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return false;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(state.getValue(AGE).intValue() + 1)), 2);
    }

    @Override
    public void updateTick(World w, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(w, pos, state, rand);
        if (w.rand.nextInt(5) == 0) {
            int age = state.getValue(AGE).intValue();
            if (age < 2) {
                w.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(age + 1)), 2);
            }
        }
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        if (plantable instanceof BlockModBush && state.getPropertyKeys().contains(AGE)) {
            return (state.getValue(AGE) > 2);
        }

        return super.canSustainPlant(state, world, pos, direction, plantable);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean onBlockActivated(World w, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        double
                x = player.posX,
                y = player.posY,
                z = player.posZ;
        if (state.getValue(AGE) == 2 && !w.isRemote) {
            EntityItem drop = new EntityItem(w, x, y, z, new ItemStack(berry));
            System.out.print(berry);
            w.spawnEntity(drop);
            w.setBlockState(pos, state.withProperty(AGE, 0), 1);
            return true;
        }
        return false;
    }
}