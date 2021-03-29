package net.jitl.common.item.gearabilities.shadium;

import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gearabilities.BaseArmorAbilities;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ShadiumArmorAbilities extends BaseArmorAbilities {
    public double getArmorReduction(LivingDamageEvent event) {
        System.out.println("Darkness: " + (1 - event.getSource().getEntity().getBrightness()));
        System.out.println("Damage reduction: " + (event.getSource().isBypassArmor() ? 0 : (event.getAmount() / 5) * -(1 - event.getSource().getEntity().getBrightness())));
        return event.getSource().isBypassArmor() ? 0 : (event.getAmount() / 5) * -(1 - event.getSource().getEntity().getBrightness());
        //TODO: write system to make this work per-piece
    }

    @Override
    public void fillArmorTooltip(TooltipFiller filler) {
        filler.addOverview();
        filler.addDetail();
    }
}
