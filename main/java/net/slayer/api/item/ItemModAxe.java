package net.slayer.api.item;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.ItemDescription;
import net.journey.util.EssenceToolMaterial;
import net.journey.util.LangHelper;
import net.journey.util.LangRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemModAxe extends ItemAxe {

	protected EssenceToolMaterial mat;

	public ItemModAxe(String name, String f, EssenceToolMaterial tool) {
        super(tool.getToolMaterial());
        LangRegistry.addItem(name, f);
		mat = tool;
		setUnlocalizedName(name);
		setCreativeTab(JourneyTabs.tools);
		JourneyItems.itemNames.add(name);
		JourneyItems.items.add(this);
		setRegistryName(SlayerAPI.MOD_ID, name);
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack i, ItemStack i1) {
		boolean canRepair = mat.getRepairItem() != null;
		if(canRepair) return mat.getRepairItem() == i1.getItem() ? true : super.getIsRepairable(i, i1);
		return super.getIsRepairable(i, i1);
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
        Material material = state.getMaterial();
        return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state) : this.efficiency;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, World w, List<String> infoList, ITooltipFlag flagIn) {
		ItemDescription.addInformation(item, infoList);
		infoList.add(SlayerAPI.Colour.BLUE + LangHelper.getEfficiency() + ": " + toolMaterial.getEfficiency());
		if(item.getMaxDamage() != -1) infoList.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
		else infoList.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
	}
}