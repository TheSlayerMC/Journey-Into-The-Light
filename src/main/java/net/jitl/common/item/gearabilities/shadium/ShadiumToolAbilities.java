package net.jitl.common.item.gearabilities.shadium;

import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gearabilities.BaseToolAbilities;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ShadiumToolAbilities extends BaseToolAbilities {
    public static final ShadiumToolAbilities INSTANCE = new ShadiumToolAbilities();

    @Override
    public double getSwordDamageModifier(LivingHurtEvent event) {
        System.out.println("Extra damage: " + event.getAmount() * (1 - event.getSource().getEntity().getBrightness()) / 2);
        return event.getAmount() * (1 - event.getSource().getEntity().getBrightness()) / 2;
    }

    @Override
    public void fillSwordTooltip(TooltipFiller filler) {
        filler.addOverview();
        filler.addDetail();
    }
}
