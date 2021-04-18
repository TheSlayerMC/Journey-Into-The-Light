package net.jitl.common.scroll;

/*
 * Code by TimeConqueror
 */
public interface IDescComponent {
    int getContentPartHeight();

    void drawContentPart(int x0, int y0, int width);

    /**
     * Must be called before drawingContentPart and doing any mechanics with
     */
    void determineContentPartHeight(int width);
}