package net.jitl.client.render.displayinfo;

import net.jitl.client.render.overlay.JDisplayInfo;
import net.jitl.client.render.overlay.JFrameType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class KnowledgeXPDisplay extends JDisplayInfo {

    public KnowledgeXPDisplay(String name, Item item, boolean isLevel) {
        super(new ItemStack(item), new TextComponent(name + " Knowledge"), isLevel ? JFrameType.LEVEL : JFrameType.XP);
    }
}
