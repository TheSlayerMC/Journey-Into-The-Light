package net.jitl.common.eventhandler;

import net.jitl.JITL;
import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.common.capability.armorability.IArmorSetCapability;
import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gear.JArmorItem;
import net.jitl.common.item.gear.JGear;
import net.jitl.common.item.gear.abilities.FullArmorAbility;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.timeconqueror.timecore.api.common.event.LivingUpdateEndEvent;

import java.util.ArrayList;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class GearAbilityHandler {

    @SubscribeEvent()
    public static void handleTick(LivingUpdateEndEvent event) {
        LivingEntity entity = event.getEntityLiving();
        ItemStack hand = entity.getMainHandItem();
        Item item = hand.getItem();
        if (item instanceof JGear && !(item instanceof JArmorItem)) {
            ((JGear) hand.getItem()).getAbility().tick(entity, entity.level, hand);
        }
        hand = entity.getOffhandItem();
        item = hand.getItem();
        if (item instanceof JGear && !(item instanceof JArmorItem)) {
            ((JGear) hand.getItem()).getAbility().tick(entity, entity.level, hand);
        }
        IArmorSetCapability capability = JCapabilityProvider.getCapability(entity, JCapabilityProvider.ARMOR);
        if (capability != null) {
            ArrayList<ItemStack> stacks = capability.getArmor();
            if (stacks != null) {
                for (ItemStack stack : stacks) {
                    ((JArmorItem) stack.getItem()).getAbility().tick(entity, entity.level, stack);
                }
            }
            FullArmorAbility fullSet = capability.getFullArmor();
            if (fullSet != null) {
                fullSet.tick(entity);
            }
        }
    }

    @SubscribeEvent()
    public static void handleIncomingAttack(LivingHurtEvent event) {
        //System.out.println(event.getAmount());
        Entity entity = event.getSource().getDirectEntity();
        if (entity != null) {
            if (entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;
                ItemStack stack = living.getMainHandItem();
                Item item = stack.getItem();
                if (item instanceof JGear) {
                    ((JGear) item).getAbility().attackTarget(living, stack, event);
                }
            } else if (entity.getType() == EntityType.ARROW) {
                if (((Arrow) entity).getOwner() instanceof LivingEntity) {
                    for (ItemStack itemStack : ((Arrow) entity).getOwner().getArmorSlots()) {
                        Item current = itemStack.getItem();
                        if (!(current instanceof ArmorItem && ((ArmorItem) current).getMaterial() == ArmorMaterials.LEATHER))
                            return;
                    }
                    event.setAmount(event.getAmount() + 5F);
                }
            }
        }
        //System.out.println(event.getAmount());
    }

    @SubscribeEvent()
    public static void handleDamageDealt(LivingDamageEvent event) {
        Entity entity = event.getSource().getDirectEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) entity;
            ItemStack stack = living.getMainHandItem();
            Item item = stack.getItem();
            if (item instanceof JGear) {
                ((JGear) item).getAbility().damageTarget(living, stack, event);
            }
        }
        IArmorSetCapability capability = JCapabilityProvider.getCapability(event.getEntityLiving(), JCapabilityProvider.ARMOR);
        if (capability != null && capability.getFullArmor() != null) {
            capability.getFullArmor().hit(event);
        }
    }

    public static void onKeyPressed(Player player) {
        IArmorSetCapability capability = JCapabilityProvider.getCapability(player, JCapabilityProvider.ARMOR);
        if (capability != null) {
            FullArmorAbility armor = capability.getFullArmor();
            if (armor != null) armor.keyPressed(player);
        }
    }

    @SubscribeEvent()
    public static void addVanillaTooptips(ItemTooltipEvent event) {
        Item item = event.getItemStack().getItem();
        if (item instanceof ArmorItem) {
            ArmorMaterial material = ((ArmorItem) item).getMaterial();
            if (material == ArmorMaterials.LEATHER) {
                TooltipFiller filler = new TooltipFiller(event.getToolTip(), "leather_gear", 1);
                filler.addOverview();
            } else if (material == ArmorMaterials.CHAIN) {
                TooltipFiller filler = new TooltipFiller(event.getToolTip(), "chain_gear");
            }
        }
    }

    /*@SubscribeEvent()
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
            PieceArmorAbilities gear = optional.get().getArmor();
            if (gear != null) {
                damageModifier += gear.getArmorReduction(event);
            }
        }
        event.setAmount(event.getAmount() + damageModifier);
        System.out.println("Post effect: " + event.getAmount());
    }*/

    @SubscribeEvent()
    public static void equipmentChange(LivingEquipmentChangeEvent event) {
        Item item = event.getFrom().getItem();
        LivingEntity entity = event.getEntityLiving();
        EquipmentSlot slot = event.getSlot();
        if (item instanceof JGear) {
            ((JGear) item).getAbility().unEquip(entity, slot, event.getFrom());
        }
        item = event.getTo().getItem();
        if (item instanceof JGear) {
            ((JGear) item).getAbility().equip(entity, slot, event.getTo());
        }
        if (slot.getType() == EquipmentSlot.Type.ARMOR) {
            IArmorSetCapability capability = JCapabilityProvider.getCapability(entity, JCapabilityProvider.ARMOR);
            if (capability != null) capability.setArmor(entity.getArmorSlots().iterator());
        }
    }
}