package net.jitl.common.item.gear.abilities;

import javafx.util.Pair;
import net.jitl.common.helper.TooltipFiller;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public interface ShadiumAbility {
    default float scaleWithDarkness(Entity entity, float original) {
        return original * (1 - entity.getBrightness());
    }

    //TODO: tooltip isn't working properly; client player may not have access to sky light. Store darkness in nbt
    class ShadiumSwordAbility implements IAbility.INBTUpdateAbility, ShadiumAbility {
        private static final UUID ID = UUID.fromString("6f56284c-ac30-4490-a06a-b11517d87e91");

        @Override
        public void tick(LivingEntity entity, World world, ItemStack stack) {
            if (!world.isClientSide() && entity.getMainHandItem() == stack) {
                float bonus = scaleWithDarkness(entity, 4F);
                ModifiableAttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
                attribute.removeModifier(ID);
                attribute.addTransientModifier(new AttributeModifier(ID,
                        "Shadium sword",
                        bonus,
                        AttributeModifier.Operation.ADDITION));
                //System.out.println(attribute.getValue());
                stack.getTag().putFloat("darkness", bonus);
            }
        }

        @Override
        public void equip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {
            if (!stack.hasTag()) stack.setTag(new CompoundNBT());
            if (slot == EquipmentSlotType.MAINHAND) {
                float bonus = scaleWithDarkness(entity, 4F);
                ModifiableAttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
                if (attribute.getModifier(ID) == null) {
                    attribute.addTransientModifier(new AttributeModifier(ID,
                            "Shadium sword",
                            bonus,
                            AttributeModifier.Operation.ADDITION));
                }
                stack.getTag().putFloat("darkness", bonus);
                System.out.println("Equip " + attribute.getValue());
            }
        }

        @Override
        public void unEquip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {
            if (slot == EquipmentSlotType.MAINHAND) {
                entity.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ID);
                System.out.println("Unequip");
            }
        }

        @Override
        public void fillTooltips(ItemStack stack, List<ITextComponent> tooltip) {
            TooltipFiller filler = new TooltipFiller(tooltip, "shadium_sword");
            filler.addOverview();
            filler.addDetail();
            PlayerEntity player = Minecraft.getInstance().player;
            if (player != null && player.getMainHandItem().equals(stack)) {
                filler.addBreak();
                filler.addValue(Math.floor(stack.getTag().getFloat("darkness") * 100) / 100);
            }
        }
    }

    class ShadiumToolAbility implements IAbility.INBTUpdateAbility, ShadiumAbility {
        @Override
        public void tick(LivingEntity entity, World world, ItemStack stack) {
            if (!world.isClientSide() && entity.getMainHandItem() == stack) {
                stack.getTag().putFloat("darkness", scaleWithDarkness(entity, 0.5F));
            }
        }

        @Override
        public float blockBreakSpeed(ItemStack stack, BlockState state, float original) {
            System.out.println(original);
            System.out.println(stack.getTag().getFloat("darkness"));
            if (isCorrectTool(stack, state)) original *= (1F + stack.getTag().getFloat("darkness"));
            System.out.println(original);
            return original;
        }

        @Override
        public void fillTooltips(ItemStack stack, List<ITextComponent> tooltip) {
            TooltipFiller filler = new TooltipFiller(tooltip, "shadium_tool");
            filler.addOverview();
            filler.addDetail();
            PlayerEntity player = Minecraft.getInstance().player;
            if (player != null && player.getMainHandItem().equals(stack)) {
                filler.addBreak();
                filler.addValue((int) (stack.getTag().getFloat("darkness") * 100));
            }
        }
    }

    class ShadiumArmorAbility implements IAbility.INBTUpdateAbility, ShadiumAbility {
        private static final Pair<String, UUID>[] IDS = new Pair[] {
                new Pair("Shadium Boots", UUID.fromString("1c4e5a9a-10fe-4be1-b088-1652400848e4")),
                new Pair("Shadium Legs", UUID.fromString("c122608d-543f-4f66-b6c1-1ccc95ab4258")),
                new Pair("Shadium Chestplate", UUID.fromString("c6b9597c-856a-4e65-b0c0-9e897a739947")),
                new Pair("Shadium Helmet", UUID.fromString("27f1c88d-bd06-45f8-a821-d8dc8b58ddab"))
        };

        @Override
        public void tick(LivingEntity entity, World world, ItemStack stack) {
            /*CompoundNBT tag = stack.getTag();
            float value = tag.getFloat("darkness");
            value = scaleWithDarkness(entity, 3.75F);
            tag.putFloat("darkness", value);
            System.out.println(tag.getFloat("darkness"));*/
            float bonus = scaleWithDarkness(entity, 3.75F);
            ModifiableAttributeInstance defense = entity.getAttribute(Attributes.ARMOR_TOUGHNESS);
            Pair<String, UUID> pair = getPair(stack);
            defense.removeModifier(pair.getValue());
            defense.addTransientModifier(new AttributeModifier(pair.getValue(),
                    pair.getKey(),
                    bonus,
                    AttributeModifier.Operation.ADDITION));
            stack.getTag().putFloat("darkness", bonus);
                //System.out.println(stack.getTag().getDouble("darkness"));
                //System.out.println(defense.getValue());
        }

        @Override
        public void equip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {
            if (!stack.hasTag()) stack.setTag(new CompoundNBT());
            if (slot.getType() == EquipmentSlotType.Group.ARMOR) {
                float bonus = scaleWithDarkness(entity, 3.75F);
                ModifiableAttributeInstance defense = entity.getAttribute(Attributes.ARMOR_TOUGHNESS);
                Pair<String, UUID> pair = getPair(stack);
                if (defense.getModifier(pair.getValue()) == null) {
                    defense.addTransientModifier(new AttributeModifier(pair.getValue(),
                            pair.getKey(),
                            bonus,
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
            PlayerEntity player = Minecraft.getInstance().player;
            if (player != null && player.getItemBySlot(((ArmorItem) stack.getItem()).getSlot()).equals(stack)) {
                filler.addBreak();
                filler.addValue(Math.floor(stack.getTag().getFloat("darkness") * 100) / 100); //is it broken?
            }
        }

        private Pair<String, UUID> getPair(ItemStack stack) {
            return IDS[((ArmorItem) stack.getItem()).getSlot().getIndex()];
        }
    }
}


