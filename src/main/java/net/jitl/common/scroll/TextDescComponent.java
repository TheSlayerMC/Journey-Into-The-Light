package net.jitl.common.scroll;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;

/*
 * Code by TimeConqueror
 */
public class TextDescComponent implements IDescComponent {
    private final String langKey;
    private int contentHeight;
    private List<FormattedCharSequence> wrappedText;

    public TextDescComponent(String langKey) {
        this.langKey = langKey;
    }

    @Override
    public int getContentPartHeight() {
        return contentHeight + 5;
    }

    @Override
    public void drawContentPart(PoseStack matrixStack, int x0, int y0, int width) {
        int i = y0;

        for (FormattedCharSequence s : wrappedText) {
            Minecraft.getInstance().font.draw(matrixStack, s, x0, i, 0x0000F);
            i += Minecraft.getInstance().font.lineHeight;
        }
    }

    @Override
    public void determineContentPartHeight(int width) {
        wrappedText = Minecraft.getInstance().font.split(new TranslatableComponent(String.valueOf(langKey)), width);
        contentHeight = Minecraft.getInstance().font.lineHeight * wrappedText.size();
    }
}