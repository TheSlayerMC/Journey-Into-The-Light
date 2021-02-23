package net.jitl.eventhandler;

import net.jitl.JITL;
import net.jitl.capabilities.armorability.ArmorSetProvider;
import net.jitl.capabilities.armorability.IArmorSetCapability;
import net.jitl.common.helper.JArmorMaterial;
import net.jitl.common.helper.JToolTiers;
import net.jitl.common.item.JArmorItem;
import net.jitl.common.item.JSwordItem;
import net.jitl.common.item.gearabilities.IGearAbilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class GearAbilityHandler {

    @SubscribeEvent()
    public static void handlePreHurt(LivingHurtEvent event) {
        System.out.println("Pre damage: " + event.getAmount());
        Entity attacker = event.getSource().getEntity();
        float damageModifier = 0;
        if (attacker instanceof LivingEntity) {
            Item heldItem = ((LivingEntity) attacker).getItemInHand(Hand.MAIN_HAND).getItem();
            if (heldItem instanceof JSwordItem) {
                damageModifier += ((JToolTiers) ((JSwordItem) heldItem).getTier()).getAbility().getSwordDamageModifier(event);
            }
        }
        event.setAmount(event.getAmount() + damageModifier);
    }

    @SubscribeEvent
    public static void handlePostHurt(LivingDamageEvent event) {
        System.out.println("Post damage: " + event.getAmount());
        float damageModifier = 0;
        Optional<IArmorSetCapability> optional = event.getEntityLiving().getCapability(ArmorSetProvider.ARMOR).resolve();
        if (optional.isPresent()) {
            IGearAbilities gear = optional.get().getArmor();
            if (gear != null) {
                damageModifier += gear.getArmorReduction(event);
            }
        }
        event.setAmount(event.getAmount() + damageModifier);
        System.out.println("Post effect: " + event.getAmount());
    }

    @SubscribeEvent()
    public static void armorChange(LivingEquipmentChangeEvent event) {
        LivingEntity entity = event.getEntityLiving();
        JArmorMaterial material = null;
        for (ItemStack currentStack : entity.getArmorSlots()) {
            if (currentStack.getItem() instanceof JArmorItem) { //current piece is part of a jitl set
                JArmorMaterial currentMaterial = (JArmorMaterial) ((JArmorItem) currentStack.getItem()).getMaterial();
                if (currentMaterial != material) { //is the current part different from the previous, or has the check simply not started?
                    if (material != null) { //a part of the set is different. Loop should end
                        material = null;
                        break;
                    } else {
                        material = currentMaterial; //changed the material
                    }
                }
            } else { //a jitl armor piece is not being worn. This makes it impossible to have a full set
                material = null;
                break;
            }
        }
        if (material != null) {
            Optional<IArmorSetCapability> optional = entity.getCapability(ArmorSetProvider.ARMOR).resolve();
            if (optional.isPresent()) {
                optional.get().setArmor(material.getAbilities());
            }
        }
    }
}
