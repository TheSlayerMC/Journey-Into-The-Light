package net.jitl.common.item.gear.abilities.mekyum;

import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gear.abilities.IAbility;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class MekyumSwordAbility implements IAbility {

    @Override
    public void attackTarget(LivingEntity attacker, ItemStack stack, LivingHurtEvent event) {
        LivingEntity entity = event.getEntityLiving();
        int time = entity.invulnerableTime;
        event.setCanceled(true);
        entity.invulnerableTime = 0;
        entity.hurt(DamageSource.MAGIC, event.getAmount());
        entity.invulnerableTime = time;
    }

    @Override
    public void fillTooltips(ItemStack stack, List<Component> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "mekyum_sword");
        filler.addOverview();
    }
}
