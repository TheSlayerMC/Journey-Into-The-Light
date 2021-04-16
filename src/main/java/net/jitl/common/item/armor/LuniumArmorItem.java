package net.jitl.common.item.armor;

import net.jitl.common.helper.JArmorMaterial;
import net.jitl.common.helper.TooltipFiller;
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

public class LuniumArmorItem extends JArmorItem {
    public LuniumArmorItem(JArmorMaterial materialIn, EquipmentSlotType slotIn) {
        super(materialIn, slotIn);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (this.getSlot().getType() == EquipmentSlotType.Group.ARMOR) {
            CompoundNBT tag = stack.hasTag() ? stack.getTag() : new CompoundNBT();
            if (tag.contains("Cooldown")) {
                if (tag.getFloat("Cooldown") == 0) {
                    stack.setDamageValue(stack.getDamageValue() - 1);
                    tag.putFloat("Cooldown", 100);
                } else {
                    tag.putFloat("Cooldown", Math.max(tag.getFloat("Cooldown") - entityIn.getBrightness(), 0));
                }
            } else {
                tag.putFloat("Cooldown", 0);
            }
            stack.setTag(tag);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        TooltipFiller filler = new TooltipFiller(tooltip, "lunium_armor");
        filler.addOverview();
        filler.addDrawback();
    }
}
