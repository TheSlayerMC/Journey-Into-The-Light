package net.jitl.common.helper;

import net.minecraft.ChatFormatting;

public class ArgbColor {
    public static final int BLUE = 0x006cff;

    public static final int TURQUOISE = 0x00ffc6;

    public static final int DARK_YELLOW = 0xb3ad00;

    public static final int LIGHT_BROWN = 0xffc18a;
    public static final int BROWN = 0xb36100;
    public static final int DARK_BROWN = 0x55402e;

    public static final int LIGHT_GRAY = 0xc8c8c8;

    /**
     * Returns color from provided {@link ChatFormatting}
     * Throws {@link IllegalArgumentException} if provided format is not color.
     */
    @SuppressWarnings("ConstantConditions")
    public static int from(ChatFormatting format) {
        if (format.isColor()) {
            return format.getColor();
        } else {
            throw new IllegalArgumentException(String.format("Provided format which is not a color: %s", format.toString()));
        }
    }
}