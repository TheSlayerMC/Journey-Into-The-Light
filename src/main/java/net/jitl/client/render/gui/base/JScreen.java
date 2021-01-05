package net.jitl.client.render.gui.base;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;

public class JScreen extends Screen {
    protected int centerX;
    protected int centerY;

    protected JScreen(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    public void init() {
        super.init();

        centerX = width / 2;
        centerY = height / 2;
    }
}
