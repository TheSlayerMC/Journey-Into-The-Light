package net.jitl.common.scroll;

import com.mojang.blaze3d.matrix.MatrixStack;

/*
 * Code by TimeConqueror
 */
public interface IDescComponent {
    int getContentPartHeight();

    void drawContentPart(MatrixStack matrixStack, int x0, int y0, int width);

    /**
     * Must be called before drawingContentPart and doing any mechanics with
     */
    void determineContentPartHeight(int width);
}