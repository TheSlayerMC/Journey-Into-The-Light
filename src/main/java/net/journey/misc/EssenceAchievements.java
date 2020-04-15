package net.journey.misc;

import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class EssenceAchievements {

    public static Advancement getOverworldOre = addAchievement("journey.getModOre", 0, 0, JourneyBlocks.shadiumOre, null, false);
    public static Advancement getSapphire = addAchievement("journey.getSapphire", 0, 10, JourneyItems.sapphire, null, false);
    public static Advancement makeOreBlock = addAchievement("journey.getOreBlock", 0, 20, JourneyBlocks.shadiumOre, null, false);
    public static Advancement makeSpawner = addAchievement("journey.getSpawner", 0, 30, JourneyItems.spawnerBar, null, false);

    private static Advancement addAchievement(String name, int x, int y, Block image, Advancement haveFirst, boolean isSpecial) {
        return null;//(Advancement) (isSpecial ? new Advancement(name, name, x, y, image, (Advancement)haveFirst).registerStat() : new Achievement(name, name, x, y, image, (Achievement)haveFirst).registerStat());
    }

    private static Advancement addAchievement(String name, int x, int y, Item image, Advancement haveFirst, boolean isSpecial) {
        return null;//(Advancement) (isSpecial ? new Advancement(name, name, x, y, image, (Advancement)haveFirst).registerStat() : new Achievement(name, name, x, y, image, (Achievement)haveFirst).registerStat());
    }

    //public static AdvancementPage page = new AdvancementPage("Essence Of The Gods", getOverworldOre, getSapphire, makeOreBlock, makeSpawner);

    public static void init() {
        //AdvancementPage.registerAchievementPage(page);
    }
}