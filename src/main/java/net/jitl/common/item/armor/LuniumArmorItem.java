package net.jitl.common.item.armor;

import net.jitl.common.helper.JArmorMaterial;
import net.jitl.common.helper.TooltipFiller;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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
    public void armorTickAbility(LivingEntity entity, World world, ItemStack stack) {
        CompoundNBT tag = stack.hasTag() ? stack.getTag() : new CompoundNBT();
        float value = tag.getFloat("cooldown");
        if (stack.getDamageValue() > 0 || value < 100) {
            if (value == 0) {
                tag.putFloat("cooldown", 100);
                stack.setDamageValue(stack.getDamageValue() - 1);
            } else {
                tag.putFloat("cooldown", Math.max(value - entity.getBrightness(), 0));
            }
            System.out.println(tag.getFloat("cooldown"));
            stack.setTag(tag);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        TooltipFiller filler = new TooltipFiller(tooltip, "lunium_gear");
        filler.addOverview();
        filler.addDrawback();
    }
}
