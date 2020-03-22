package net.journey.items;

import java.util.List;

import javax.annotation.Nullable;

import net.journey.util.JourneyToolMaterial;
import net.journey.util.LangHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemModHoe;

public class ItemBonemealHoe extends ItemModHoe {

	public ItemBonemealHoe(String name, String f, JourneyToolMaterial tool) {
		super(name, f, tool);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = player.getHeldItem(hand);
		if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
			return EnumActionResult.FAIL;
			
		} if (player.isSneaking()) {
			this.applyBonemeal(itemstack, worldIn, pos, player, hand);
			if (!worldIn.isRemote) {
				worldIn.playEvent(2005, pos, 0);
			}
			return EnumActionResult.SUCCESS;
			
		} else {
			int hook = ForgeEventFactory.onHoeUse(itemstack, player, worldIn, pos);
			if (hook != 0)
				return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up())) {
				if (block == Blocks.GRASS || block == Blocks.GRASS_PATH) {
					this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
					return EnumActionResult.SUCCESS;
				}
				if (block == Blocks.DIRT) {
					switch ((BlockDirt.DirtType) iblockstate.getValue(BlockDirt.VARIANT)) {
					case DIRT:
						this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
						return EnumActionResult.SUCCESS;
					case COARSE_DIRT:
						this.setBlock(itemstack, player, worldIn, pos,
								Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
						return EnumActionResult.SUCCESS;
					default:
						break;
					}
				}
			}
			return EnumActionResult.PASS;
		}
	}
    
    public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos target) {
        if (worldIn instanceof WorldServer)
            return applyBonemeal(stack, worldIn, target, FakePlayerFactory.getMinecraft((WorldServer)worldIn), null);
        return false;
    }
    
	public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos target, EntityPlayer player, @Nullable EnumHand hand) {
		IBlockState iblockstate = worldIn.getBlockState(target);
		int hook = ForgeEventFactory.onApplyBonemeal(player, worldIn, target, iblockstate,
				stack, hand);
		if (hook != 0)
			return hook > 0;
		if (iblockstate.getBlock() instanceof IGrowable) {
			IGrowable igrowable = (IGrowable) iblockstate.getBlock();
			if (igrowable.canGrow(worldIn, target, iblockstate, worldIn.isRemote)) {
				if (!worldIn.isRemote) {
					if (igrowable.canUseBonemeal(worldIn, worldIn.rand, target, iblockstate)) {
						igrowable.grow(worldIn, worldIn.rand, target, iblockstate);
						stack.damageItem(1, player);
					}
				}
				return true;
			}
		}
		return false;
	}

	@SideOnly(Side.CLIENT)
	public static void spawnBonemealParticles(World worldIn, BlockPos pos, int amount) {
		if (amount == 0) {
			amount = 15;
		}
		if (!worldIn.isAirBlock(pos)) {
			IBlockState iblockstate = worldIn.getBlockState(pos);
			for (int i = 0; i < amount; ++i) {
				double d0 = itemRand.nextGaussian() * 0.02D;
				double d1 = itemRand.nextGaussian() * 0.02D;
				double d2 = itemRand.nextGaussian() * 0.02D;
				worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY,
						(double) ((float) pos.getX() + itemRand.nextFloat()),
						(double) pos.getY()
								+ (double) itemRand.nextFloat() * iblockstate.getBoundingBox(worldIn, pos).maxY,
						(double) ((float) pos.getZ() + itemRand.nextFloat()), d0, d1, d2);
			}
		} else {
			for (int i1 = 0; i1 < amount; ++i1) {
				double d0 = itemRand.nextGaussian() * 0.02D;
				double d1 = itemRand.nextGaussian() * 0.02D;
				double d2 = itemRand.nextGaussian() * 0.02D;
				worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY,
						(double) ((float) pos.getX() + itemRand.nextFloat()),
						(double) pos.getY() + (double) itemRand.nextFloat() * 1.0f,
						(double) ((float) pos.getZ() + itemRand.nextFloat()), d0, d1, d2, new int[0]);
			}
		}
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World player, List<String> list, ITooltipFlag par4) {
    	list.add(SlayerAPI.Colour.GOLD + "Crouch + Right Click to Bonemeal, Right Click to Till");
        list.add(SlayerAPI.Colour.BLUE + "Efficiency: " + toolMaterial.getEfficiency());
        if (stack.getMaxDamage() != -1) list.add(stack.getMaxDamage() - stack.getItemDamage() + " Uses");
        else list.add(SlayerAPI.Colour.GREEN + "Infinite Uses");
    }
}