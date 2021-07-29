package net.jitl.common.item.gear.abilities.mekyum;

import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gear.abilities.IAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class MekyumSwordAbility implements IAbility {

    @Override
    public void attackTarget(LivingEntity attacker, ItemStack stack, LivingHurtEvent event) {
        //TODO: make effect consistent with damage boosts (and change tooltip accordingly)
        LivingEntity entity = event.getEntityLiving();
        int time = entity.invulnerableTime;
        event.setCanceled(true);
        entity.invulnerableTime = 0;
        entity.hurt(DamageSource.MAGIC, event.getAmount());
        entity.invulnerableTime = time;
    }

    @Override
    public void fillTooltips(ItemStack stack, List<ITextComponent> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "mekyum_sword");
        filler.addOverview();
        filler.addDrawback();
    }
}
