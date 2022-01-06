package net.jitl.common.helper;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.List;

public class TooltipFiller {
    private final List<Component> tooltip;
    private final String key;
    private int line = 0;
    private final int startPoint;

    public TooltipFiller(List<Component> text, String itemKey) {
        tooltip = text;
        key = itemKey;
        startPoint = -1;
    }

    public TooltipFiller(List<Component> text, String itemKey, int start) {
        tooltip = text;
        key = itemKey;
        startPoint = start;
    }

    public void addTooltip(ChatFormatting color) {
        if (startPoint == -1) {
            tooltip.add(new TranslatableComponent("jitl.tooltip." + key + "." + line++).withStyle(color));
        } else {
            tooltip.add(startPoint + line, new TranslatableComponent("jitl.tooltip." + key + "." + line++).withStyle(color));
        }
    }

    public void addOverview() {
        addTooltip(ChatFormatting.GOLD);
    }

    public void addDetail() {
        addTooltip(ChatFormatting.AQUA);
    }

    public void addDrawback() {
        addTooltip(ChatFormatting.RED);
    }

    public void addValue(Object... values) {
        //TODO: test
        tooltip.add(new TranslatableComponent("jitl.tooltip." + key + "." + line++, values).withStyle(ChatFormatting.GREEN));
    }

    public void addBreak() {
        tooltip.add(TextComponent.EMPTY);
    }
}
