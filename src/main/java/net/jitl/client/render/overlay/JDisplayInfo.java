package net.jitl.client.render.overlay;

import javax.annotation.Nullable;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class JDisplayInfo {

    private final Component description;
    private final JFrameType frame;

    public JDisplayInfo(Component description, JFrameType frame) {
        this.description = description;
        this.frame = frame;
    }

    public Component getDescription() {
        return this.description;
    }

    public JFrameType getFrame() {
        return this.frame;
    }

}