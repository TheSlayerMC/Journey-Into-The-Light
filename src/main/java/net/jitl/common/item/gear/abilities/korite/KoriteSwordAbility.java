package net.jitl.common.item.gear.abilities.korite;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.player.data.Essence;
import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gear.abilities.IAbility;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
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
    public void rightClick(Player player, InteractionHand hand, Level world) {
        if (!world.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            ItemStack stack = player.getMainHandItem();
            if (!stack.hasTag()) stack.setTag(new CompoundTag());
            CompoundTag nbt = stack.getTag();
            Essence essence = JPlayer.from(player).essence;
            float bonus = Math.min(essence.getCurrentEssence(), 5.0F);
            if (nbt.getFloat("bonus") < bonus && essence.consumeEssence(player, bonus)) {
                nbt.putFloat("bonus", bonus);
                addModifier(player, bonus);
            }
        }
    }

    @Override
    public void equip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND && stack.hasTag()) {
            float bonus = stack.getTag().getFloat("bonus");
            if (bonus > 0) {
                addModifier(entity, bonus);
            }
        }
    }

    @Override
    public void unEquip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND) {
            entity.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ID);
        }
    }

    @Override
    public void fillTooltips(ItemStack stack, List<Component> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "korite_sword");
        filler.addOverview();
        filler.addDrawback();
        filler.addBreak();
        filler.addValue(Math.floor(stack.getTag().getFloat("bonus") * 100) / 100);
    }

    private void addModifier(LivingEntity entity, float value) {
        AttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        attribute.removeModifier(ID);
        attribute.addTransientModifier(new AttributeModifier(ID,
                "Korite sword",
                value,
                AttributeModifier.Operation.ADDITION));
    }
}
