package net.jitl.common.scroll;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

/*
 * Code by TimeConqueror
 */
public class TextDescComponent implements IDescComponent {
    private final ITextComponent langKey;
    private int contentHeight;
    private List<IReorderingProcessor> wrappedText;

    public TextDescComponent(ITextComponent langKey) {
        this.langKey = langKey;
    }

    @Override
    public int getContentPartHeight() {
        return contentHeight + 5;
    }

    @Override
    public void drawContentPart(MatrixStack matrixStack, int x0, int y0, int width) {
        int i = y0;

        for (IReorderingProcessor s : wrappedText) {
            Minecraft.getInstance().font.draw(matrixStack, s, x0, i, 0x0000F);
            i += Minecraft.getInstance().font.lineHeight;
        }
    }

    @Override
    public void determineContentPartHeight(int width) {
        wrappedText = Minecraft.getInstance().font.split(new TranslationTextComponent(String.valueOf(langKey)), width);
        contentHeight = Minecraft.getInstance().font.lineHeight * wrappedText.size();
    }
}