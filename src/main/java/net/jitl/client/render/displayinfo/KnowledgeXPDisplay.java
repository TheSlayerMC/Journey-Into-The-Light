package net.jitl.client.render.displayinfo;

import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class KnowledgeXPDisplay extends DisplayInfo {

    public KnowledgeXPDisplay(String name, Item item, @Nullable ResourceLocation background, boolean isLevel) {
        super(new ItemStack(item),
                new TextComponent(name + " Knowledge"), isLevel ? new TextComponent("Level Up!") : new TextComponent("XP Received!")
                , background, isLevel ? FrameType.CHALLENGE : FrameType.TASK, true, false, false);
    }
}
