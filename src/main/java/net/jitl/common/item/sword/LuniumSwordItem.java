package net.jitl.common.item.sword;

import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.common.capability.morphingnbt.IMorphingNBTCapability;
import net.jitl.common.capability.morphingnbt.MorphingNBTCapability;
import net.jitl.common.helper.JToolTiers;
import net.jitl.common.helper.TooltipFiller;
import net.jitl.common.item.LiveNBTUpdateItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class LuniumSwordItem extends JSwordItem implements LiveNBTUpdateItem {
    public LuniumSwordItem(JToolTiers tier) {
        super(tier);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        IMorphingNBTCapability cap = getCap(stack);
        if (cap != null) {
            CompoundNBT tag = cap.getNBT();
            if (tag.contains("cooldown")) {
                if (tag.getFloat("cooldown") == 0) {
                    stack.setDamageValue(stack.getDamageValue() - 1);
                    tag.putFloat("cooldown", 100);
                } else {
                    tag.putFloat("cooldown", Math.max(tag.getFloat("cooldown") - entityIn.getBrightness(), 0));
                }
            } else {
                tag.putFloat("cooldown", 0);
            }
            System.out.println(tag.getFloat("cooldown"));
            cap.setNBT(tag);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        TooltipFiller filler = new TooltipFiller(tooltip, "lunium_gear");
        filler.addOverview();
        filler.addDrawback();
    }

    @Override
    public IMorphingNBTCapability getCap(ItemStack stack) {
        Optional<IMorphingNBTCapability> optional = stack.getCapability(JCapabilityProvider.NBT).resolve();
        return optional.orElse(null);
    }
}
