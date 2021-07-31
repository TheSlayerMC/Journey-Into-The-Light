package net.jitl.common.helper;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class TooltipFiller {
    private final List<ITextComponent> tooltip;
    private final String key;
    private int line = 0;
    private final int startPoint;

    public TooltipFiller(List<ITextComponent> text, String itemKey) {
        tooltip = text;
        key = itemKey;
        startPoint = -1;
    }

    public TooltipFiller(List<ITextComponent> text, String itemKey, int start) {
        tooltip = text;
        key = itemKey;
        startPoint = start;
    }

    public void addTooltip(TextFormatting color) {
        if (startPoint == -1) {
            tooltip.add(new TranslationTextComponent("jitl.tooltip." + key + "." + line++).withStyle(color));
        } else {
            tooltip.add(startPoint + line, new TranslationTextComponent("jitl.tooltip." + key + "." + line++).withStyle(color));
        }
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

    public void addBreak() {
        tooltip.add(new TranslationTextComponent(" "));
    }
}
