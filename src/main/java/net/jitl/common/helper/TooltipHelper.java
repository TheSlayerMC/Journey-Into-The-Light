package net.jitl.common.helper;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class TooltipHelper {
    private final List<ITextComponent> tooltip;
    private final String key;
    private int line = 1;

    public TooltipHelper(List<ITextComponent> text, String itemKey) {
        tooltip = text;
        key = itemKey;
    }

    public void addTooltip(TextFormatting color) {
        tooltip.add(new TranslationTextComponent("jitl.tooltip." + key + "_" + line++).withStyle(color));
    }

    public void addOverview() {
        addTooltip(TextFormatting.GOLD);
    }

    public void addDetail() {
        addTooltip(TextFormatting.AQUA);
    }

    public void addDrawback() {
        addTooltip(TextFormatting.RED);
    }
}
