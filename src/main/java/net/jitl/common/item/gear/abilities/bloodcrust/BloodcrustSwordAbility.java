package net.jitl.common.item.gear.abilities.bloodcrust;

import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gear.abilities.IAbility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

import java.util.List;
import java.util.UUID;

public class BloodcrustSwordAbility implements IAbility {
    private static final UUID ID = UUID.fromString("4fe9a512-63c8-400a-90b2-afa53d4fa66a");

    @Override
    public void attackTarget(LivingEntity holder, ItemStack stack, LivingDamageEvent event) {
        System.out.println("Bloodcrust attack");
        System.out.println("Original damage is " + event.getAmount());
        Entity entity = event.getEntity();
        if (!stack.hasTag()) stack.setTag(new CompoundNBT());
        stack.getTag().putInt("Fire boost", entity.getRemainingFireTicks() / 20);
        entity.clearFire();
    }

    @Override
    public void equip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {
        if (entity.getMainHandItem() == stack) {
            if (stack.hasTag()) {
                ModifiableAttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
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
    public void unEquip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {
        entity.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ID);
    }

    @Override
    public void fillTooltips(ItemStack stack, List<ITextComponent> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "bloodcrust_sword");
        filler.addOverview();
        filler.addDetail();
    }
}
