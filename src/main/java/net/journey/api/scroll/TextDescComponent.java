package net.journey.api.scroll;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

import java.util.List;

/*
 * Code by TimeConqueror
 */
public class TextDescComponent implements IDescComponent {
    private final String langKey;
    private int contentHeight;
    private List<String> wrappedText;

    public TextDescComponent(String langKey) {
        this.langKey = langKey;
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
        wrappedText = Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(I18n.format(langKey), width);
        contentHeight = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * wrappedText.size();
    }
}
