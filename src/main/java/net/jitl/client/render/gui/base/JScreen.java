package net.jitl.client.render.gui.base;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class JScreen extends Screen {
    protected int centerX;
    protected int centerY;

    protected JScreen(Component titleIn) {
        super(titleIn);
    }

    @Override
    public void init() {
        super.init();

        centerX = width / 2;
        centerY = height / 2;
    }
}
