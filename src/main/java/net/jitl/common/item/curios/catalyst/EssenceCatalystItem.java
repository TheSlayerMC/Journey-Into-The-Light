package net.jitl.common.item.curios.catalyst;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.jitl.JITL;
import net.jitl.common.item.curios.JCurioItem;
import net.jitl.init.JAttributes;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class EssenceCatalystItem extends JCurioItem {
    public float maxEssence;

    public EssenceCatalystItem(Properties properties, String ability, String negativeEffects) {
        super(properties, ability, negativeEffects);
    }

    public EssenceCatalystItem essence(int maxEssence) {
        this.maxEssence = maxEssence;
        return this;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = LinkedHashMultimap.create();
        modifierMultimap.put(JAttributes.MAX_ESSENCE.get(), new AttributeModifier(uuid, JITL.MODID + ":max_essence_modifier", maxEssence, AttributeModifier.Operation.ADDITION));
        return modifierMultimap;
    }

}
