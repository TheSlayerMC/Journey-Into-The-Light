package net.jitl.common.item.gear.abilities.celestium;

import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gear.abilities.FullArmorAbility;
import net.jitl.common.item.gear.abilities.IAbility;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class CelestiumArmorAbility implements IAbility {
    @Override
    public FullArmorAbility getFullAbility(CompoundNBT nbt) {
        return new CelestiumFullAbility(nbt);
    }

    @Override
    public void fillTooltips(ItemStack stack, List<ITextComponent> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "celestium_armor");
        filler.addOverview();
        filler.addDetail();
        filler.addDrawback();
    }
}
