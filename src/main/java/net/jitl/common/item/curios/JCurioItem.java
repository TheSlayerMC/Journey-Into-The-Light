package net.jitl.common.item.curios;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;

public class JCurioItem extends Item implements ICurioItem {
    private final String ability;
    private final String negativeEffects;

    public JCurioItem(Properties properties, String ability, String negativeEffects) {
        super(properties);
        this.ability = ability;
        this.negativeEffects = negativeEffects;
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable World world, List<ITextComponent> text, @NotNull ITooltipFlag flag) {
        text.add(new TranslationTextComponent(ability).withStyle(TextFormatting.AQUA));
        if (negativeEffects != null) {
            text.add(new TranslationTextComponent(negativeEffects).withStyle(TextFormatting.RED));
        }
    }
}
