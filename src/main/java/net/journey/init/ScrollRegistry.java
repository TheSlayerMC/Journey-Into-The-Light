package net.journey.init;

import net.journey.JITL;
import net.journey.api.scroll.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/*
 * Code by TimeConqueror
 */
public class ScrollRegistry {

    private static final ResourceLocation BASICS = new ResourceLocation(JITL.MOD_ID, "textures/gui/scroll_background.png");

    public static void register() {

        ScrollAPI.registerCategory(new ScrollCategory("BASICS", BASICS));
        ScrollAPI.registerCategory(new ScrollCategory("TEST", BASICS));

        List<IDescComponent> desc = new ArrayList<>();

        desc.add(new StringDescComponent("Test"));
        desc.add(new StringDescComponent("test"));
        desc.add(new StringDescComponent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin feugiat ultrices diam at commodo. Fusce interdum iaculis augue eget luctus. Nulla vel augue purus. In volutpat pellentesque felis, vitae luctus purus maximus eget. Vivamus diam lacus, bibendum ac dui in, scelerisque luctus velit. Nullam nisi elit, tempus a consequat eu, iaculis eu lacus. Fusce vehicula tellus in tellus dignissim bibendum. Maecenas ac rhoncus augue, ut lobortis tortor. Nunc maximus nunc vel dui consequat faucibus. Aliquam vel turpis eu quam congue molestie.\n" +
                "\n" +
                "Aliquam egestas ipsum in sem luctus pretium. Mauris arcu neque, consectetur vitae sagittis sed, dapibus et enim. Cras tristique justo magna, id tempus metus sollicitudin vitae. Praesent efficitur iaculis varius. Morbi in euismod metus, at sodales quam. Proin eget fermentum nibh. Sed semper dignissim ipsum et auctor. Vestibulum nulla ex, tempus in mattis id, sagittis quis dui. Donec quis magna neque. Duis fringilla erat odio, at dignissim nibh malesuada consequat. Donec tincidunt lorem vel sollicitudin tincidunt. Suspendisse sed enim fermentum, auctor sem hendrerit, malesuada ipsum. Cras hendrerit velit nulla, nec varius ipsum ultricies quis. Phasellus vel blandit ante, sed malesuada erat.\n" +
                "\n" +
                "Etiam semper scelerisque nulla elementum efficitur. Nulla sit amet aliquam metus, quis sagittis turpis. Suspendisse egestas ligula non lacinia aliquam. Praesent id laoreet nulla. Phasellus vehicula mattis tortor vitae porta. Donec ut commodo ipsum. Integer fermentum turpis faucibus massa sagittis, quis dignissim sapien tincidunt. Nulla facilisi.\n" +
                "\n" +
                "Praesent gravida sapien egestas sapien suscipit aliquam. Proin eleifend risus erat, in iaculis metus consectetur sit amet. Vivamus sed ultricies libero. Duis venenatis, justo sit amet aliquet cursus, enim arcu dapibus elit, sed ornare justo neque eget nunc. Nam porttitor ornare diam, vitae eleifend nulla. Ut imperdiet turpis vitae consectetur vestibulum. Suspendisse in gravida sapien. Vestibulum vitae tempus sapien. Quisque et vulputate nisl, non accumsan massa."));

        for (int i = 0; i < 3; i++)
            ScrollAPI.registerEntry("BASICS", new ScrollEntry("Test Entry" + i, "Jocky joke", new ItemStack(Blocks.BARRIER), desc, 10 + i * 10, 10 + i * 10));
        ScrollAPI.registerEntry("TEST", new ScrollEntry("Test Entry_", "Jocky joke", new ItemStack(Blocks.BRICK_BLOCK), desc, 10 + 10, 10 + 10));
        desc.clear();
    }
}
