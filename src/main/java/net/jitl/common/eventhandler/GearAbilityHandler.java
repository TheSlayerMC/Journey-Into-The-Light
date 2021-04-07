package net.jitl.common.eventhandler;

import net.jitl.JITL;
import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.common.capability.armorability.IArmorSetCapability;
import net.jitl.common.helper.JArmorMaterial;
import net.jitl.common.helper.JToolTiers;
import net.jitl.common.item.JArmorItem;
import net.jitl.common.item.JSwordItem;
import net.jitl.common.item.gearabilities.BaseArmorAbilities;
import net.jitl.common.item.gearabilities.BaseToolAbilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class GearAbilityHandler {

    @SubscribeEvent()
    public static void handleTick(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        Optional<IArmorSetCapability> optional = entity.getCapability(JCapabilityProvider.ARMOR).resolve();
        if (optional.isPresent()) {
            BaseArmorAbilities gear = optional.get().getArmor();
            if (gear != null) {
                gear.doTickAbility(event);
            }
        }
    }

    @SubscribeEvent()
    public static void handlePlayerSwing(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();
        //Attempts to imitate the vanilla sweep check
        ItemStack stack = player.getMainHandItem();
        if (stack.getItem() instanceof JSwordItem) {
            if (player.getAttackStrengthScale(0.5F) > 0.9F && !player.isSprinting()) { //combines flag and flag1, since there's no reason not to
                System.out.println("Pre-swing");
                if (player.isOnGround() && player.walkDist - player.walkDistO < player.getSpeed()) { //flag3. flag2 is ignored as the isOnGround() call in flag3 automatically means flag2 will be false
                    System.out.println("Swing");
                    ((JToolTiers) ((JSwordItem) stack.getItem()).getTier()).getAbilities().onSweep(stack, event.getTarget(), player);
                }
            }
        }
    }

    @SubscribeEvent()
    public static void handlePreHurt(LivingHurtEvent event) {
        System.out.println("Pre damage: " + event.getAmount());
        Entity attacker = event.getSource().getDirectEntity();
        float damageModifier = 0;
        if (attacker instanceof LivingEntity) {
            Item heldItem = ((LivingEntity) attacker).getItemInHand(Hand.MAIN_HAND).getItem();
            if (heldItem instanceof JSwordItem) {
                BaseToolAbilities ability = ((JToolTiers) ((JSwordItem) heldItem).getTier()).getAbilities();
                if (ability != null) {
                    damageModifier += ability.getSwordDamageModifier(event);
                }
            }
        }
        event.setAmount(event.getAmount() + damageModifier);
    }

    @SubscribeEvent
    public static void handlePostHurt(LivingDamageEvent event) {
        System.out.println("Post damage: " + event.getAmount());
        float damageModifier = 0;
        Optional<IArmorSetCapability> optional = event.getEntityLiving().getCapability(JCapabilityProvider.ARMOR).resolve();
        if (optional.isPresent()) {
            BaseArmorAbilities gear = optional.get().getArmor();
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
        Optional<IArmorSetCapability> optional = entity.getCapability(JCapabilityProvider.ARMOR).resolve();
        if (optional.isPresent()) {
            optional.get().setArmor(material != null ? material.getAbilities() : null);
        }
    }
}
