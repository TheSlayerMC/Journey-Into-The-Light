package net.journey.items;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.ItemDescription;
import net.journey.util.EssenceToolMaterial;
import net.journey.util.LangHelper;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemMultiTool extends ItemTool {

	private static final Set<Block> blocksEffectiveAgainst = Sets.newHashSet(Block.REGISTRY);
	protected EssenceToolMaterial mat;

	public ItemMultiTool(String name, String f, EssenceToolMaterial tool, int damage) {
		super(tool.getToolMaterial(), blocksEffectiveAgainst);
		LangRegistry.addItem(name, f);
		mat = tool;
		this.setMaxDamage(damage);
		setUnlocalizedName(name);
		setCreativeTab(JourneyTabs.tools);
		setHarvestLevel("pickaxe", tool.getHarvestLevel());
		JourneyItems.itemNames.add(SlayerAPI.PREFIX + name);
		JourneyItems.items.add(this);
		setRegistryName(SlayerAPI.MOD_ID, name);
	}

	@Override
	public boolean getIsRepairable(ItemStack i, ItemStack i1) {
		boolean canRepair = mat.getRepairItem() != null;
		if(canRepair) return mat.getRepairItem() == i1.getItem() ? true : super.getIsRepairable(i, i1);
		return super.getIsRepairable(i, i1);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}

	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return true;
	}

	@Override
	public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
		return true;
	}

	public float getCorrectEfficiency(ItemStack is, Block b) {
		return this.toolMaterial.getEfficiency();
	}

	protected boolean isEfficient(Block block) {
		return block == Blocks.OBSIDIAN ? this.toolMaterial.getHarvestLevel() == 3 :
			(block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE ?
					(block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK ?
							(block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE ?
									(block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE ?
											(block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE ?
													(block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE ?
															(block.getMaterial(null) == Material.ROCK ? true :
																(block.getMaterial(null) == Material.IRON ? true :
																	block.getMaterial(null) == Material.ANVIL)) :
																		this.toolMaterial.getHarvestLevel() >= 2) :
																			this.toolMaterial.getHarvestLevel() >= 1) :
																				this.toolMaterial.getHarvestLevel() >= 1) :
																					this.toolMaterial.getHarvestLevel() >= 2) :
																						this.toolMaterial.getHarvestLevel() >= 2) :
																							this.toolMaterial.getHarvestLevel() >= 2);
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = player.getHeldItem(hand);

		if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
			return EnumActionResult.FAIL;
		} else {
			int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(itemstack, player, worldIn, pos);
			if (hook != 0) return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;

			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up())) {
				if (block == Blocks.GRASS || block == Blocks.GRASS_PATH) {
					this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
					return EnumActionResult.SUCCESS;
				}

				if (block == Blocks.DIRT) {
					switch ((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT)) {
					case DIRT:
						this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
						return EnumActionResult.SUCCESS;
					case COARSE_DIRT:
						this.setBlock(itemstack, player, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
						return EnumActionResult.SUCCESS;
					}
				}
			}

			return EnumActionResult.PASS;
		}
	}

	protected void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state) {
		worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

		if (!worldIn.isRemote) {
			worldIn.setBlockState(pos, state, 11);
			stack.damageItem(1, player);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, World worldIn, List<String> infoList, ITooltipFlag flagIn) {
		ItemDescription.addInformation(item, infoList);
		if(item.getMaxDamage() != -1) infoList.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
	}
}
