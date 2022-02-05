package net.jitl.client.render.overlay.internal;

public enum EssencePosition {
    OVER_EXP_BAR(91, 29),
    ABOVE_HUNGER_BAR(-10, 49),
    LEFT_OF_TOOLBAR(175, 13),
    RIGHT_OF_TOOLBAR(-96, 13);

    private final int x;
    private final int y;

    EssencePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}