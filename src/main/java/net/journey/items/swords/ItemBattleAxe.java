package net.journey.items.swords;

import net.journey.init.JourneyTabs;
import net.journey.util.JourneyToolMaterial;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemModAxe;

import java.util.List;

public class ItemBattleAxe extends ItemModAxe {


    public ItemBattleAxe(String name, String f, JourneyToolMaterial m) {
        super(name, f, m);
        setTranslationKey(name);
        setCreativeTab(JourneyTabs.WEAPONS);
        attackSpeed = -3.3F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World player, List<String> list, ITooltipFlag par4) {
        list.add(SlayerAPI.Colour.AQUA + "Battle Axe");
        list.add(SlayerAPI.Colour.BLUE + "Efficiency: " + toolMaterial.getEfficiency());
        if (stack.getMaxDamage() != -1) list.add(stack.getMaxDamage() - stack.getItemDamage() + " Uses");
        else list.add(SlayerAPI.Colour.GREEN + "Infinite Uses");
    }

    @Override
    public EnumAction getItemUseAction(ItemStack i) {
        return EnumAction.BLOCK;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack i) {
        return 72000;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}