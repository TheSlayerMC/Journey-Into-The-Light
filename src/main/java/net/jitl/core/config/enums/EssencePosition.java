package net.jitl.core.config.enums;

public enum EssencePosition {
    OVER_EXPERIENCE_BAR(91, 29),
    ABOVE_HUNGER_BAR(-10, 49),
    LEFT_OF_HOTBAR(175, 13),
    RIGHT_OF_HOTBAR(-96, 13),
    BELOW_CROSSHAIR(10, 10);

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