package net.jitl.common.item.gear.shadium;

import net.jitl.common.helper.JToolTiers;
import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.gear.IAbility;
import net.jitl.common.item.gear.ILightGear;
import net.jitl.common.item.gear.JGear;
import net.jitl.common.item.gear.JSwordItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class ShadiumSwordItem {
    /*private static UUID ID = UUID.fromString("6f56284c-ac30-4490-a06a-b11517d87e91");

    public ShadiumSwordItem(JToolTiers tier) {
        super(tier);
    }

    public void tick(LivingEntity entity, World world, ItemStack stack) {
        if (!world.isClientSide() && entity.getMainHandItem() == stack) {
            ModifiableAttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attribute.getModifier(ID) != null) {
                attribute.removeModifier(ID);
            }
            attribute.addTransientModifier(new AttributeModifier(ID,
                    "Shadium sword",
                    scaleWithDarkness(entity, 4),
                    AttributeModifier.Operation.ADDITION));
            System.out.println(attribute.getValue());
        }
    }

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

    public void unEquip(LivingEntity entity, EquipmentSlotType slot, ItemStack stack) {
        if (entity.getMainHandItem() == stack) {
            ModifiableAttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attribute.getModifier(ID) != null) {
                attribute.removeModifier(ID);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        TooltipFiller filler = new TooltipFiller(tooltip, "shadium_sword");
        filler.addOverview();
        filler.addDetail();
    }*/
}
