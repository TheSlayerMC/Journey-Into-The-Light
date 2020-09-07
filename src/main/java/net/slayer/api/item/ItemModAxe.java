package net.slayer.api.item;

import com.google.common.collect.Sets;
import net.journey.client.ItemDescription;
import net.journey.util.JourneyToolMaterial;
import net.journey.util.LangHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;
import java.util.Set;

public class ItemModAxe extends ItemTool {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);
    protected JourneyToolMaterial mat;

    public ItemModAxe(JourneyToolMaterial tool) {
        super(tool.getToolMaterial(), EFFECTIVE_ON);
        this.efficiency = tool.getToolMaterial().getEfficiency();
        this.attackDamage = tool.getToolMaterial().getAttackDamage();
        this.attackSpeed = -2.5F;
        mat = tool;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        Material material = state.getMaterial();
        return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state) : this.efficiency;
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
    public void addInformation(ItemStack item, World w, List<String> infoList, ITooltipFlag flagIn) {
        ItemDescription.addInformation(item, infoList);
        infoList.add(SlayerAPI.Colour.BLUE + LangHelper.getEfficiency() + ": " + toolMaterial.getEfficiency());
        if (item.getMaxDamage() != -1)
            infoList.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
        else infoList.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
    }
}