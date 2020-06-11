package net.journey.api.scroll;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

import java.util.List;

/*
 * Code by TimeConqueror
 */
public class StringDescComponent implements IDescComponent {
    private final String content;
    private int contentHeight;
    private List<String> wrappedText;

    public StringDescComponent(String descLocalizationKey) {
        this.content = I18n.format(descLocalizationKey);
    }

    @Override
    public int getContentPartHeight() {
        return contentHeight + 5;
    }

    @Override
    public void drawContentPart(int x0, int y0, int width) {

        int i = y0;

        for (String s : wrappedText) {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(s, x0, i, 0xFFFFFF);
            i += Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
        }
    }

    @Override
    public void determineContentPartHeight(int width) {
        wrappedText = Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(content, width);
        contentHeight = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * wrappedText.size();
    }
}
