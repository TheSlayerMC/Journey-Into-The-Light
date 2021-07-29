package net.jitl.common.item.gear.abilities.korite;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.player.data.Essence;
import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gear.abilities.IAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class KoriteSwordAbility implements IAbility {
    @Override
    public void attackTarget(LivingEntity attacker, ItemStack stack, LivingHurtEvent event) {
        if (attacker instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) attacker;
            Essence essence = JPlayer.from(player).essence;
            System.out.println("Essence: " + essence.getCurrentEssence());
            float bonus = Math.min(essence.getCurrentEssence(), 5.0F);
            System.out.println("Extra damage: " + bonus);
            if (essence.consumeEssence(player, bonus)) event.setAmount(event.getAmount() + bonus);
        }
    }

    @Override
    public void fillTooltips(ItemStack stack, List<ITextComponent> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "korite_sword");
        filler.addOverview();
        filler.addDrawback();
    }
}
