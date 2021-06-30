package net.jitl.common.item.gear.abilities;

import javafx.util.Pair;
import net.jitl.common.helper.TooltipFiller;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public interface ShadiumAbility {
    default double scaleWithDarkness(Entity entity, double original) {
        return original * (1 - entity.getBrightness());
    }

    class ShadiumSwordAbility implements IAbility, ShadiumAbility {
        private static UUID ID = UUID.fromString("6f56284c-ac30-4490-a06a-b11517d87e91");

        @Override
        public void tick(LivingEntity entity, World world, ItemStack stack) {
            if (!world.isClientSide() && entity.getMainHandItem() == stack) {
                ModifiableAttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
                attribute.removeModifier(ID);
                attribute.addTransientModifier(new AttributeModifier(ID,
                        "Shadium sword",
                        scaleWithDarkness(entity, 4),
                        AttributeModifier.Operation.ADDITION));
                System.out.println(attribute.getValue());
            }
        }

        @Override
        public void equip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {
            if (entity.getMainHandItem() == stack) {
                ModifiableAttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
                if (attribute.getModifier(ID) == null) {
                    attribute.addTransientModifier(new AttributeModifier(ID,
                            "Shadium sword",
                            scaleWithDarkness(entity, 4),
                            AttributeModifier.Operation.ADDITION));
                }
                System.out.println("Equip " + attribute.getValue());
            }
        }

        @Override
        public void unEquip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {
            if (entity.getMainHandItem() == stack) {
                entity.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ID);
            }
        }

        @Override
        public void fillTooltips(ItemStack stack, List<ITextComponent> tooltip) {
            TooltipFiller filler = new TooltipFiller(tooltip, "shadium_sword");
            filler.addOverview();
            filler.addDetail();
        }
    }

    class ShadiumToolAbility implements IAbility.INBTUpdateAbility, ShadiumAbility {
        @Override
        public void tick(LivingEntity entity, World world, ItemStack stack) {
            if (!world.isClientSide() && entity.getMainHandItem() == stack) {
                stack.getTag().putFloat("darkness", (float) scaleWithDarkness(entity, 0.5));
            }
        }

        @Override
        public float blockBreakSpeed(ItemStack stack, BlockState state, float original) {
            System.out.println(original);
            System.out.println(stack.getTag().getFloat("darkness"));
            System.out.println(original * (1 + stack.getTag().getFloat("darkness")));
            return original * (1 + stack.getTag().getFloat("darkness"));
        }

        @Override
        public void fillTooltips(ItemStack stack, List<ITextComponent> tooltip) {
            TooltipFiller filler = new TooltipFiller(tooltip, "shadium_tool");
            filler.addOverview();
            filler.addDetail();
        }
    }

    class ShadiumArmorAbility implements IAbility, ShadiumAbility {
        private final Pair<String, UUID>[] IDS = new Pair[] {
                new Pair("Shadium Boots", UUID.fromString("1c4e5a9a-10fe-4be1-b088-1652400848e4")),
                new Pair("Shadium Legs", UUID.fromString("c122608d-543f-4f66-b6c1-1ccc95ab4258")),
                new Pair("Shadium Chestplate", UUID.fromString("c6b9597c-856a-4e65-b0c0-9e897a739947")),
                new Pair("Shadium Helmet", UUID.fromString("27f1c88d-bd06-45f8-a821-d8dc8b58ddab"))
        };

        @Override
        public void tick(LivingEntity entity, World world, ItemStack stack) {
            ModifiableAttributeInstance defense = entity.getAttribute(Attributes.ARMOR_TOUGHNESS);
            Pair<String, UUID> pair = getPair(stack);
            defense.removeModifier(pair.getValue());
            defense.addTransientModifier(new AttributeModifier(pair.getValue(),
                    pair.getKey(),
                    scaleWithDarkness(entity, 3.75),
                    AttributeModifier.Operation.ADDITION));
            System.out.println(defense.getValue());
        }

        @Override
        public void equip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {
            if (slot.getType() == EquipmentSlotType.Group.ARMOR) {
                ModifiableAttributeInstance defense = entity.getAttribute(Attributes.ARMOR_TOUGHNESS);
                Pair<String, UUID> pair = getPair(stack);
                if (defense.getModifier(pair.getValue()) == null) {
                    defense.addTransientModifier(new AttributeModifier(pair.getValue(),
                            pair.getKey(),
                            scaleWithDarkness(entity, 3.75),
                            AttributeModifier.Operation.ADDITION));
                }
                System.out.println("Equip " + defense.getValue());
            }
        }

        @Override
        public void unEquip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {
            if (slot.getType() == EquipmentSlotType.Group.ARMOR) {
                entity.getAttribute(Attributes.ARMOR_TOUGHNESS).removeModifier(entity.getAttribute(Attributes.ARMOR_TOUGHNESS).getModifier(getPair(stack).getValue()));
            }
        }

        @Override
        public void fillTooltips(ItemStack stack, List<ITextComponent> tooltip) {
            TooltipFiller filler = new TooltipFiller(tooltip, "shadium_armor");
            filler.addOverview();
            filler.addDetail();
        }

        private Pair<String, UUID> getPair(ItemStack stack) {
            return IDS[((ArmorItem) stack.getItem()).getSlot().getIndex()];
        }
    }
}


