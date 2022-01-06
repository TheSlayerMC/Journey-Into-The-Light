package net.jitl.common.item.curios;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.jitl.JITL;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

import net.minecraft.world.item.Item.Properties;

public class HeartContainerItem extends JCurioItem {
    public int hearts;

    public HeartContainerItem(Properties properties) {
        super(properties);
    }

    public HeartContainerItem health(int hearts) {
        this.hearts = hearts;
        return this;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = LinkedHashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, JITL.MODID + ":health_modifier", hearts, AttributeModifier.Operation.ADDITION));
        return modifierMultimap;
    }
}