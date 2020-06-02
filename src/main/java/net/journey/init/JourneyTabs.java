package net.journey.init;

import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyArmory;
import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.journey.util.LangHelper;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

public class JourneyTabs extends CreativeTabs {

    public static final JourneyTabs BLOCKS = new JourneyTabs("Blocks", () -> new ItemStack(JourneyBlocks.eucaGrass));
    public static final JourneyTabs DECORATION = new JourneyTabs("Decoration", () -> new ItemStack(JourneyBlocks.boilingLamp));
    public static final JourneyTabs ITEMS = new JourneyTabs("Items", () -> new ItemStack(JourneyItems.koriteIngot));
    public static final JourneyTabs TOOLS = new JourneyTabs("Tools", () -> new ItemStack(JourneyArmory.multiToolOfEternalSmelting));
    public static final JourneyTabs WEAPONS = new JourneyTabs("Weapons", () -> new ItemStack(JourneyWeapons.coreMender));
    public static final JourneyTabs UTIL = new JourneyTabs("Utilities", () -> new ItemStack(JourneyItems.flameCoin));
    public static final JourneyTabs ARMOR = new JourneyTabs("Armor", () -> new ItemStack(JourneyArmory.blazehornHelmet));
    public static final JourneyTabs SPAWNERS = new JourneyTabs("Spawners", () -> new ItemStack(JourneyItems.sentryKingOrb));
    public static final JourneyTabs CROPS = new JourneyTabs("Crops", () -> new ItemStack(JourneyConsumables.hongoShroom));
    public static final JourneyTabs INTERACTIVE_BLOCKS = new JourneyTabs("Interactive Blocks", () -> new ItemStack(JourneyBlocks.summoningTable));
    public static final JourneyTabs PORTAL_BLOCKS = new JourneyTabs("Portal Blocks", () -> new ItemStack(JourneyBlocks.eucaPortal));

    public Supplier<ItemStack> iconSupplier;

    /**
     * @param enName localized name for tab
     *               "JITL: " prefix will be added to it.
     */
    public JourneyTabs(String enName, Supplier<ItemStack> iconSupplier) {
        super(LangHelper.withModPrefix(LangHelper.nameToKey(enName)));
        this.iconSupplier = iconSupplier;

        LangGeneratorFacade.addCreativeTabEntry(this, enName.contains("JITL:") ? enName : "JITL: " + enName);
    }

    @Override
    public ItemStack createIcon() {
        return iconSupplier.get();
    }
}
