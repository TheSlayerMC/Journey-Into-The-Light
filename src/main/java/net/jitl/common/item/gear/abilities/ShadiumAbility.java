package net.jitl.common.item.gear.abilities;

import com.mojang.datafixers.util.Pair;
import net.jitl.common.helper.TooltipFiller;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public interface ShadiumAbility {
    default float scaleWithDarkness(Entity entity, float original) {
        return original * (1 - entity.getBrightness());
    }

    //TODO: tooltip isn't working properly; client player may not have access to sky light. Store darkness in nbt
    class ShadiumSwordAbility implements IAbility.INBTUpdateAbility, ShadiumAbility {
        private static final UUID ID = UUID.fromString("6f56284c-ac30-4490-a06a-b11517d87e91");

        @Override
        public void tick(LivingEntity entity, Level world, ItemStack stack) {
            if (!world.isClientSide() && entity.getMainHandItem() == stack) {
                float bonus = scaleWithDarkness(entity, 4F);
                AttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
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
        public void equip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
            if (!stack.hasTag()) stack.setTag(new CompoundTag());
            if (slot == EquipmentSlot.MAINHAND) {
                float bonus = scaleWithDarkness(entity, 4F);
                AttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
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
        public void unEquip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
            if (slot == EquipmentSlot.MAINHAND) {
                entity.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ID);
                System.out.println("Unequip");
            }
        }

        @Override
        public void fillTooltips(ItemStack stack, List<Component> tooltip) {
            TooltipFiller filler = new TooltipFiller(tooltip, "shadium_sword");
            filler.addOverview();
            filler.addDetail();
            Player player = Minecraft.getInstance().player;
            if (player != null && player.getMainHandItem().equals(stack)) {
                filler.addBreak();
                filler.addValue(Math.floor(stack.getTag().getFloat("darkness") * 100) / 100);
            }
        }
    }

    class ShadiumToolAbility implements IAbility.INBTUpdateAbility, ShadiumAbility {
        @Override
        public void tick(LivingEntity entity, Level world, ItemStack stack) {
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
        public void fillTooltips(ItemStack stack, List<Component> tooltip) {
            TooltipFiller filler = new TooltipFiller(tooltip, "shadium_tool");
            filler.addOverview();
            filler.addDetail();
            Player player = Minecraft.getInstance().player;
            if (player != null && player.getMainHandItem().equals(stack)) {
                filler.addBreak();
                filler.addValue((int) (stack.getTag().getFloat("darkness") * 100));
            }
        }
    }

    class ShadiumArmorAbility implements IAbility, ShadiumAbility {
        private static final Pair<String, UUID>[] IDS = new Pair[] {
                new Pair("Shadium Boots", UUID.fromString("1c4e5a9a-10fe-4be1-b088-1652400848e4")),
                new Pair("Shadium Legs", UUID.fromString("c122608d-543f-4f66-b6c1-1ccc95ab4258")),
                new Pair("Shadium Chestplate", UUID.fromString("c6b9597c-856a-4e65-b0c0-9e897a739947")),
                new Pair("Shadium Helmet", UUID.fromString("27f1c88d-bd06-45f8-a821-d8dc8b58ddab"))
        };

        @Override
        public void tick(LivingEntity entity, Level world, ItemStack stack) {
            float bonus = scaleWithDarkness(entity, 3.75F);
            AttributeInstance defense = entity.getAttribute(Attributes.ARMOR_TOUGHNESS);
            Pair<String, UUID> pair = getPair(stack);
            defense.removeModifier(pair.getSecond());
            defense.addTransientModifier(new AttributeModifier(pair.getSecond(),
                    pair.getFirst(),
                    bonus,
                    AttributeModifier.Operation.ADDITION));
        }

        @Override
        public void equip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                float bonus = scaleWithDarkness(entity, 3.75F);
                AttributeInstance defense = entity.getAttribute(Attributes.ARMOR_TOUGHNESS);
                Pair<String, UUID> pair = getPair(stack);
                if (defense.getModifier(pair.getSecond()) == null) {
                    defense.addTransientModifier(new AttributeModifier(pair.getSecond(),
                            pair.getFirst(),
                            bonus,
                            AttributeModifier.Operation.ADDITION));
                }
                System.out.println("Equip " + defense.getValue());
            }
        }

        @Override
        public void unEquip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                Objects.requireNonNull(entity.getAttribute(Attributes.ARMOR_TOUGHNESS)).removeModifier(Objects.requireNonNull(entity.getAttribute(Attributes.ARMOR_TOUGHNESS).getModifier(getPair(stack).getSecond())));
            }
        }

        @Override
        public void fillTooltips(ItemStack stack, List<Component> tooltip) {
            TooltipFiller filler = new TooltipFiller(tooltip, "shadium_armor");
            filler.addOverview();
            filler.addDetail();
            Player player = Minecraft.getInstance().player;
            EquipmentSlot slot = ((ArmorItem) stack.getItem()).getSlot();
            if (player != null && player.getItemBySlot(slot).equals(stack)) {
                AttributeInstance attribute = player.getAttribute(Attributes.ARMOR_TOUGHNESS);
                if (attribute != null) {
                    filler.addBreak();
                    filler.addValue(Math.floor(
                            attribute.getModifier(IDS[slot.getIndex()]
                                            .getSecond())
                                    .getAmount() * 100) / 100);
                }
            }
        }

        private Pair<String, UUID> getPair(ItemStack stack) {
            return IDS[((ArmorItem) stack.getItem()).getSlot().getIndex()];
        }
    }
}


