package net.journey.items;

import java.util.List;
import java.util.Set;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.ItemDescription;
import net.journey.util.EssenceToolMaterial;
import net.journey.util.LangHelper;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import com.google.common.collect.Sets;

public class ItemMultiTool extends ItemTool {

	private static final Set<Block> blocksEffectiveAgainst = Sets.newHashSet(Block.blockRegistry);
	protected EssenceToolMaterial mat;

    public ItemMultiTool(String name, String f, EssenceToolMaterial tool, int damage) {
        super(0, tool.getToolMaterial(), blocksEffectiveAgainst);
        LangRegistry.addItem(name, f);
		mat = tool;
		this.setMaxDamage(damage);
        setUnlocalizedName(name);
        setCreativeTab(JourneyTabs.tools);
        setHarvestLevel("pickaxe", tool.getHarvestLevel());
        JourneyItems.itemNames.add(name);
        GameRegistry.registerItem(this, name);
	}

	@Override
	public boolean getIsRepairable(ItemStack i, ItemStack i1) {
		boolean canRepair = mat.getRepairItem() != null;
		if(canRepair) return mat.getRepairItem() == i1.getItem() ? true : super.getIsRepairable(i, i1);
		return super.getIsRepairable(i, i1);
	}
	
	@Override
	public boolean isItemTool(ItemStack i) {
		return true;
	}

	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack) {
		return block.getBlockHardness(null, null) != -1.0F;
	}

	@Override
	public boolean canHarvestBlock(Block block) {
		return isEfficient(block);
	}
	
    public float getCorrectEfficiency(ItemStack is, Block b) {
        return this.toolMaterial.getEfficiencyOnProperMaterial();
    }

	protected boolean isEfficient(Block block) {
		return block == Blocks.obsidian ? this.toolMaterial.getHarvestLevel() == 3 :
			(block != Blocks.diamond_block && block != Blocks.diamond_ore ?
			(block != Blocks.emerald_ore && block != Blocks.emerald_block ?
			(block != Blocks.gold_block && block != Blocks.gold_ore ?
			(block != Blocks.iron_block && block != Blocks.iron_ore ?
			(block != Blocks.lapis_block && block != Blocks.lapis_ore ?
			(block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore ?
			(block.getMaterial() == Material.rock ? true :
			(block.getMaterial() == Material.iron ? true :
			block.getMaterial() == Material.anvil)) :
			this.toolMaterial.getHarvestLevel() >= 2) :
			this.toolMaterial.getHarvestLevel() >= 1) :
			this.toolMaterial.getHarvestLevel() >= 1) :
			this.toolMaterial.getHarvestLevel() >= 2) :
			this.toolMaterial.getHarvestLevel() >= 2) :
			this.toolMaterial.getHarvestLevel() >= 2);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing fa, float par8, float par9, float par10) {
		if (!player.canPlayerEdit(pos, fa, stack)) return false;

		UseHoeEvent event = new UseHoeEvent(player, stack, world, pos);
		if (MinecraftForge.EVENT_BUS.post(event)) return false;
		if (event.getResult() == Result.ALLOW) {
			stack.damageItem(1, player);
			return true;
		}

		Block block = world.getBlockState(pos).getBlock();

		if (fa != EnumFacing.DOWN && world.getBlockState(new BlockPos(pos.up())).getBlock().isAir(world, new BlockPos(pos.up())) && (block == Blocks.grass || block == Blocks.dirt)) {
			Block block1 = Blocks.farmland;
			world.playSoundEffect((double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY() + 0.5F), (double) ((float) pos.getZ() + 0.5F), block1.stepSound.getStepSound(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.frequency * 0.8F);

			if (world.isRemote) return true;

			world.setBlockState(pos, block1.getDefaultState());
			stack.damageItem(1, player);
			return true;
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer player, List infoList, boolean par4) {
		ItemDescription.addInformation(item, player, infoList);
		if(item.getMaxDamage() != -1) infoList.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
	}
}
