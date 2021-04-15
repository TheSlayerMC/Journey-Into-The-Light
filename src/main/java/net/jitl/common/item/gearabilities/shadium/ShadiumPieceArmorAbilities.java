package net.jitl.common.item.gearabilities.shadium;

import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gearabilities.PieceArmorAbilities;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class ShadiumPieceArmorAbilities extends PieceArmorAbilities {

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
