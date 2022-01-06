package net.jitl.common.item.gear.abilities.bloodcrust;

import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gear.abilities.FullArmorAbility;
import net.jitl.common.item.gear.abilities.IAbility;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

import java.util.List;

public class BloodcrustArmorAbility implements IAbility {
    @Override
    public FullArmorAbility getFullAbility(CompoundTag nbt) {
        return new BloodcrustFullAbility(nbt);
    }

    @Override
    public void fillTooltips(ItemStack stack, List<Component> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "bloodcrust_armor");
        filler.addOverview();
        filler.addDetail();
        filler.addDetail();
    }
}

