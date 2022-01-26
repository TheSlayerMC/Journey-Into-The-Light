package net.jitl.client.render.overlay;

import javax.annotation.Nullable;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class JDisplayInfo {

    private final Component description;
    private final ItemStack icon;
    private final JFrameType frame;

    public JDisplayInfo(ItemStack icon, Component description, JFrameType frame) {
        this.description = description;
        this.icon = icon;
        this.frame = frame;
    }

    public Component getDescription() {
        return this.description;
    }

    public ItemStack getIcon() {
        return this.icon;
    }

    public JFrameType getFrame() {
        return this.frame;
    }

}