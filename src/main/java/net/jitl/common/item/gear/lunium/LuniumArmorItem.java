package net.jitl.common.item.gear.lunium;

import net.jitl.common.helper.JArmorMaterial;
import net.jitl.common.item.gear.ILightGear;
import net.jitl.common.item.gear.base.JArmorItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LuniumArmorItem extends JArmorItem implements ILightGear {
    public LuniumArmorItem(JArmorMaterial materialIn, EquipmentSlotType slotIn) {
        super(materialIn, slotIn);
    }

    @Override
    public void armorTickAbility(LivingEntity entity, World world, ItemStack stack) {
        if (!world.isClientSide()) repair(entity, stack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        lightTooltip(tooltip);
    }
}
