package net.jitl.common.item.pickaxe;

import net.jitl.common.helper.JToolTiers;
import net.jitl.common.helper.TooltipFiller;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LuniumPickaxeItem extends JPickaxeItem {
    public LuniumPickaxeItem(JToolTiers tier) {
        super(tier);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        CompoundNBT tag = stack.hasTag() ? stack.getTag() : new CompoundNBT();
        if (tag.contains("cooldown")) {
            if (tag.getFloat("cooldown") == 0) {
                stack.setDamageValue(stack.getDamageValue() - 1);
                tag.putFloat("cooldown", 100);
            } else {
                tag.putFloat("cooldown", Math.max(tag.getFloat("cooldown") - entityIn.getBrightness(), 0));
            }
        } else {
            tag.putFloat("cooldown", 0);
        }
        stack.setTag(tag);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        TooltipFiller filler = new TooltipFiller(tooltip, "lunium_gear");
        filler.addOverview();
        filler.addDrawback();
    }
}
