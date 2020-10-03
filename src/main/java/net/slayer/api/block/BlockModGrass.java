package net.slayer.api.block;

import net.journey.init.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.SlayerAPI;

import java.util.Random;

public class BlockModGrass extends BlockMod implements IGrowable {

    protected Block dirt;
    protected String tex;
    public Block path;

    public BlockModGrass(Block dirt, String name, String finalName) {
        super(EnumMaterialTypes.GRASS, name, finalName, 0.5F);
        this.dirt = dirt;
        setSoundType(SoundType.PLANT);
        setCreativeTab(JourneyTabs.BLOCKS);
        setTickRandomly(true);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
        if (!world.isRemote) {
            if (dirt != null) {
                int x = pos.getX(), y = pos.getY(), z = pos.getZ();
                if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockLightOpacity(pos.up()) > 2)
                    world.setBlockState(pos, dirt.getDefaultState(), 2);
                else if (world.getLightFromNeighbors(pos.up()) >= 9) {
                    for (int l = 0; l < 4; ++l) {
                        int i1 = x + random.nextInt(3) - 1;
                        int j1 = y + random.nextInt(5) - 3;
                        int k1 = z + random.nextInt(3) - 1;
                        BlockPos blockpos1 = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                        if (world.getBlockState(new BlockPos(i1, j1, k1)) == dirt.getDefaultState() && world.getBlockState(new BlockPos(i1, j1 + 1, k1)) == Blocks.AIR.getDefaultState() && world.getLightFromNeighbors(blockpos1.up()) >= 4 && world.getLightFromNeighbors(new BlockPos(i1, j1 + 1, k1)) <= 2)
                            world.setBlockState(new BlockPos(i1, j1, k1), getDefaultState());
                    }
                }
            }
        }
    }

    public BlockModGrass setGrassPath(Block path) {
        this.path = path;
        return this;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        if (this.path != null) {
            if (playerIn.getHeldItemMainhand().getItem() instanceof ItemSpade || playerIn.getHeldItemMainhand().getDisplayName().contains("Shovel")) {
                worldIn.setBlockState(pos, this.path.getDefaultState());
                worldIn.playSound(playerIn, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return true;
            }
        }
        return this.path != null && playerIn.getHeldItemMainhand().getItem() instanceof ItemSpade;
    }

    @Override
    public Item getItemDropped(IBlockState pos, Random par2, int par3) {
        return dirt == null ? null : Item.getItemFromBlock(dirt);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return SlayerAPI.toItemStack(this);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random r, BlockPos b, IBlockState i) {
        return true;
    }

    @Override
    public void grow(World worldIn, Random r, BlockPos b, IBlockState i) {
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }
}