package net.jitl.common.item.gear.shadium;

import net.jitl.common.helper.JToolTiers;
import net.jitl.common.item.gear.IEquipUpdateItem;
import net.jitl.common.item.gear.JTieredItemAbility;
import net.jitl.common.item.gear.base.JSwordItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.UUID;

public class ShadiumSwordItem extends JSwordItem implements IEquipUpdateItem, JTieredItemAbility {
    private static UUID ID = UUID.fromString("6f56284c-ac30-4490-a06a-b11517d87e91");

    public ShadiumSwordItem(JToolTiers tier) {
        super(tier);
    }

    @Override
    public void tick(LivingEntity entity, World world, ItemStack stack) {
        if (!world.isClientSide() && entity.getMainHandItem() == stack) {
            ModifiableAttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attribute.getModifier(ID) != null) {
                attribute.removeModifier(ID);
            }
            attribute.addTransientModifier(new AttributeModifier(ID,
                    "Shadium sword",
                    5 * (1 - entity.getBrightness()),
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
                        5 * (1 - entity.getBrightness()),
                        AttributeModifier.Operation.ADDITION));
            }
            System.out.println("Equip");
        }
    }

    @Override
    public void unEquip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {
        if (entity.getMainHandItem() == stack) {
            ModifiableAttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attribute.getModifier(ID) != null) {
                attribute.removeModifier(ID);
            }
        }
    }
}
