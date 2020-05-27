package net.journey.init;

import java.util.ArrayList;
import java.util.List;

import net.journey.JITL;
import net.journey.api.item.scroll.IDescComponent;
import net.journey.api.item.scroll.ScrollAPI;
import net.journey.api.item.scroll.ScrollCategory;
import net.journey.api.item.scroll.ScrollEntry;
import net.journey.api.item.scroll.StringDescComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/*
 * Code by TimeConqueror
 */
public class ScrollRegistry {

    private static final ResourceLocation BASICS = new ResourceLocation(JITL.MOD_ID, "textures/gui/bg1.png");

    public static void register() {

        ScrollAPI.registerCategory(new ScrollCategory("BASICS", BASICS));
        ScrollAPI.registerCategory(new ScrollCategory("TEST", BASICS));

        List<IDescComponent> desc = new ArrayList<>();

        desc.add(new StringDescComponent("Test"));
        desc.add(new StringDescComponent("test"));
        desc.add(new StringDescComponent("TEST"));

        for (int i = 0; i < 3; i++)
            ScrollAPI.registerEntry("BASICS", new ScrollEntry("Test Entry" + i, "Jocky joke", new ItemStack(Blocks.BARRIER), desc, 10 + i * 10, 10 + i * 10));
        ScrollAPI.registerEntry("TEST", new ScrollEntry("Test Entry_", "Jocky joke", new ItemStack(Blocks.BRICK_BLOCK), desc, 10 + 10, 10 + 10));
        desc.clear();
    }
}
