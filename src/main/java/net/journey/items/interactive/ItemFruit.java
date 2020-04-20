package net.journey.items.interactive;

import net.journey.blocks.crop.base.BlockFruitCrop;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.item.ItemModFood;

public class ItemFruit extends ItemModFood {

    public BlockFruitCrop fruitblock;
    public Block log;

    public ItemFruit(String name, String f, int food, float sat, boolean wolfFood) {
        super(name, f, food, sat, wolfFood);
    }

    public ItemFruit(String name, String f, int food, float sat, boolean wolfFood, BlockFruitCrop crop, Block log) {
        super(name, f, food, sat, wolfFood);
        this.fruitblock = crop;
        this.log = log;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItemMainhand();
        if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
            return EnumActionResult.FAIL;
        } else {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (block == log) {
                if (facing == EnumFacing.DOWN || facing == EnumFacing.UP) {
                    return EnumActionResult.FAIL;
                }

                pos = pos.offset(facing);

                if (worldIn.isAirBlock(pos)) {
                    IBlockState iblockstate1 = fruitblock.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, 0, player, hand);
                    worldIn.setBlockState(pos, iblockstate1, 10);

                    if (!player.capabilities.isCreativeMode) {
                        stack.shrink(1);
                    }

                    return EnumActionResult.SUCCESS;
                }
            }
            return EnumActionResult.FAIL;
        }
    }
}
