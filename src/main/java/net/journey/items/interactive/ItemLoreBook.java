package net.journey.items.interactive;

import net.journey.JITL;
import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyItems;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.item.ItemWrittenBook;

public class ItemLoreBook extends ItemWrittenBook {
	
	String name;
	
	public ItemLoreBook(String name, String enName) {
        this.name = name;

        setTranslationKey(name.toLowerCase());
        setCreativeTab(JourneyTabs.UTIL);
        JourneyItems.items.add(this);
        setRegistryName(JITL.MOD_ID, name.toLowerCase());

        LangGeneratorFacade.addItemEntry(this, enName);
	}
}
