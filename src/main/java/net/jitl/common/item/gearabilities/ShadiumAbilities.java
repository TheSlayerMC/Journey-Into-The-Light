package net.jitl.common.item.gearabilities;

import net.jitl.common.helper.TooltipFiller;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ShadiumAbilities extends BaseAbilities {
    @Override
    public double getSwordDamageModifier(LivingHurtEvent event) {
        System.out.println("Extra damage: " + event.getAmount() * (1 - event.getSource().getEntity().getBrightness()) / 2);
        return event.getAmount() * (1 - event.getSource().getEntity().getBrightness()) / 2;
    }

    public double getArmorReduction(LivingDamageEvent event) {
        System.out.println("Darkness: " + (1 - event.getSource().getEntity().getBrightness()));
        System.out.println("Damage reduction: " + (event.getSource().isBypassArmor() ? 0 : (event.getAmount() / 5) * -(1 - event.getSource().getEntity().getBrightness())));
        return event.getSource().isBypassArmor() ? 0 : (event.getAmount() / 5) * -(1 - event.getSource().getEntity().getBrightness());
    }

    @Override
    public void fillSwordTooltip(TooltipFiller filler) {
        filler.addOverview();
        filler.addDetail();
    }

    @Override
    public void fillArmorTooltip(TooltipFiller filler) {
        filler.addOverview();
        filler.addDetail();
    }
}
