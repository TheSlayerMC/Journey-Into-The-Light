package net.jitl.common.item.gear.abilities.korite;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.player.data.Essence;
import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gear.abilities.IAbility;
import net.jitl.init.JItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;
import java.util.UUID;

public class KoriteSwordAbility implements IAbility {
    private static final UUID ID = UUID.fromString("cb129eb4-c625-4a9a-b778-00176b83610d");

    @Override
    public void attackTarget(LivingEntity attacker, ItemStack stack, LivingHurtEvent event) {
        if (stack.hasTag()) {
            attacker.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ID);
            stack.getTag().putFloat("bonus", 0);
        }
    }

    @Override
    public void rightClick(PlayerEntity player, Hand hand, World world) {
        if (!world.isClientSide() && hand == Hand.MAIN_HAND) {
            ItemStack stack = player.getMainHandItem();
            if (!stack.hasTag()) stack.setTag(new CompoundNBT());
            CompoundNBT nbt = stack.getTag();
            Essence essence = JPlayer.from(player).essence;
            float bonus = Math.min(essence.getCurrentEssence(), 5.0F);
            if (nbt.getFloat("bonus") < bonus && essence.consumeEssence(player, bonus)) {
                nbt.putFloat("bonus", bonus);
                addModifier(player, bonus);
            }
        }
    }

    @Override
    public void equip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {
        if (slot == EquipmentSlotType.MAINHAND && stack.hasTag()) {
            float bonus = stack.getTag().getFloat("bonus");
            if (bonus > 0) {
                addModifier(entity, bonus);
            }
        }
    }

    @Override
    public void unEquip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {
        if (slot == EquipmentSlotType.MAINHAND) {
            entity.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ID);
        }
    }

    @Override
    public void fillTooltips(ItemStack stack, List<ITextComponent> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "korite_sword");
        filler.addOverview();
        filler.addDrawback();
    }

    private void addModifier(LivingEntity entity, float value) {
        ModifiableAttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        attribute.removeModifier(ID);
        attribute.addTransientModifier(new AttributeModifier(ID,
                "Korite sword",
                value,
                AttributeModifier.Operation.ADDITION));
    }
}
