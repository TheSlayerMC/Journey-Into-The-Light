package net.slayer.api.item;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.util.EssenceToolMaterial;
import net.journey.util.LangHelper;
import net.journey.util.LangRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemModShovel extends ItemSpade {

	protected EssenceToolMaterial mat;
	
    public ItemModShovel(String name, String f, EssenceToolMaterial tool) {
        super(tool.getToolMaterial());
        LangRegistry.addItem(name, f);
		mat = tool;
        setUnlocalizedName(name);
        setCreativeTab(JourneyTabs.tools);
        JourneyItems.itemNames.add(name);
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
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, World player, List<String> infoList, ITooltipFlag par4) {
		infoList.add(SlayerAPI.Colour.BLUE + LangHelper.getEfficiency() + ": " + toolMaterial.getEfficiency());
		if(item.getMaxDamage() != -1) infoList.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
		else infoList.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
	}
}