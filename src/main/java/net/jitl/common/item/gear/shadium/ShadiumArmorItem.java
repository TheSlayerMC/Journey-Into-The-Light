package net.jitl.common.item.gear.shadium;

import javafx.util.Pair;
import net.jitl.common.helper.JArmorMaterial;
import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.IEquipUpdateItem;
import net.jitl.common.item.gear.base.JArmorItem;
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

public class ShadiumArmorItem extends JArmorItem implements IEquipUpdateItem {
    private final Pair<String, UUID>[] IDS = new Pair[] {
            new Pair("Shadium Boots", UUID.fromString("1c4e5a9a-10fe-4be1-b088-1652400848e4")),
            new Pair("Shadium Legs", UUID.fromString("c122608d-543f-4f66-b6c1-1ccc95ab4258")),
            new Pair("Shadium Chestplate", UUID.fromString("c6b9597c-856a-4e65-b0c0-9e897a739947")),
            new Pair("Shadium Helmet", UUID.fromString("27f1c88d-bd06-45f8-a821-d8dc8b58ddab"))
    };

    public ShadiumArmorItem(JArmorMaterial materialIn, EquipmentSlotType slotIn) {
        super(materialIn, slotIn);
    }

    @Override
    public void armorTickAbility(LivingEntity entity, World world, ItemStack stack) {
        ModifiableAttributeInstance defense = entity.getAttribute(Attributes.ARMOR_TOUGHNESS);
        AttributeModifier modifier = defense.getModifier(getAttributeUUID());
        if (modifier != null) {
            defense.removeModifier(modifier);
        }
        defense.addTransientModifier(new AttributeModifier(getAttributeUUID(),
                getAttributeName(),
                3.75 * (1 - entity.getBrightness()),
                AttributeModifier.Operation.ADDITION));
        System.out.println(defense.getValue());
    }

    @Override
    public void equip(LivingEntity entity, EquipmentSlotType slot) {
        ModifiableAttributeInstance defense = entity.getAttribute(Attributes.ARMOR_TOUGHNESS);
        if (defense.getModifier(getAttributeUUID()) == null) {
            defense.addTransientModifier(new AttributeModifier(getAttributeUUID(),
                    getAttributeName(),
                    3.75 * (1 - entity.getBrightness()),
                    AttributeModifier.Operation.ADDITION));
        }
    }

    @Override
    public void unEquip(LivingEntity entity, EquipmentSlotType slot) {
        ModifiableAttributeInstance defense = entity.getAttribute(Attributes.ARMOR_TOUGHNESS);
        AttributeModifier modifier = defense.getModifier(getAttributeUUID());
        if (modifier != null) {
            defense.removeModifier(modifier);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        TooltipFiller filler = new TooltipFiller(tooltip, "shadium_gear");
        filler.addOverview();
        filler.addDetail();
    }

    private String getAttributeName() {
        return IDS[this.getSlot().getIndex()].getKey();
    }

    private UUID getAttributeUUID() {
        return IDS[this.getSlot().getIndex()].getValue();
    }
}
