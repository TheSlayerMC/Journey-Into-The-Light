package net.jitl.common.item.gearabilities;

import net.jitl.common.helper.TooltipFiller;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public abstract class BaseArmorAbilities {
    public void doTickAbility(LivingEvent.LivingUpdateEvent event) {

    }

    public double getArmorReduction(LivingDamageEvent event) {
        return 0;
    }

    public void fillArmorTooltip(TooltipFiller filler) {

    }
}
