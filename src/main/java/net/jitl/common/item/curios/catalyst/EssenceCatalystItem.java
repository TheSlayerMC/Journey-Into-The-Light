package net.jitl.common.item.curios.catalyst;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.jitl.common.item.curios.JCurioItem;
import net.jitl.core.JITL;
import net.jitl.core.init.JAttributes;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class EssenceCatalystItem extends JCurioItem {
    public float maxEssence;

    public EssenceCatalystItem(Properties properties) {
        super(properties);
    }

    public EssenceCatalystItem essence(float maxEssence) {
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
