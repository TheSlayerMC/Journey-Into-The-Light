package net.jitl.common.item.gear.abilities.bloodcrust;

import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gear.abilities.IAbility;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

import java.util.List;
import java.util.UUID;

public class BloodcrustSwordAbility implements IAbility {
    private static final UUID ID = UUID.fromString("4fe9a512-63c8-400a-90b2-afa53d4fa66a");

    @Override
    public void damageTarget(LivingEntity holder, ItemStack stack, LivingDamageEvent event) {
        System.out.println("Bloodcrust attack");
        System.out.println("Original damage is " + event.getAmount());
        Entity entity = event.getEntity();
        if (!stack.hasTag()) stack.setTag(new CompoundTag());
        stack.getTag().putInt("Fire boost", entity.getRemainingFireTicks() / 20);
        entity.clearFire();
    }

    @Override
    public void equip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND) {
            if (stack.hasTag()) {
                AttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
                attribute.removeModifier(ID);
                double amount = stack.getTag().getInt("Fire boost");
                if (amount > 0) {
                    entity.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(new AttributeModifier(
                            ID,
                            "Bloodcrust sword",
                            amount,
                            AttributeModifier.Operation.ADDITION));
                }
            }
        }
    }

    @Override
    public void unEquip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND) entity.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ID);
    }

    @Override
    public void fillTooltips(ItemStack stack, List<Component> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "bloodcrust_sword");
        filler.addOverview();
        filler.addDetail();
        filler.addBreak();
        filler.addValue(stack.getTag().getInt("Fire boost"));
    }
}
