package net.slayer.api.item;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.ItemDescription;
import net.journey.util.JourneyToolMaterial;
import net.journey.util.LangHelper;
import net.journey.util.LangRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;

public class ItemModSword extends ItemSword {

    protected JourneyToolMaterial mat;

    public ItemModSword(String name, String f, JourneyToolMaterial toolMaterial) {
        super(toolMaterial.getToolMaterial());
        LangRegistry.addItem(name, f);
        setTranslationKey(name);
        mat = toolMaterial;
        setCreativeTab(JourneyTabs.weapons);
        JourneyItems.itemNames.add(SlayerAPI.PREFIX + name);
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
        if (canRepair) return mat.getRepairItem() == i1.getItem() || super.getIsRepairable(i, i1);
        return super.getIsRepairable(i, i1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, World player, List<String> infoList, ITooltipFlag par4) {
        ItemDescription.addInformation(item, infoList);
        if (item.getMaxDamage() != -1)
            infoList.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
        else infoList.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
    }
}