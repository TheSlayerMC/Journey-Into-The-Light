package net.journey.items;

import net.journey.enums.EnumKnowledge;
import net.journey.items.base.JItem;

public class ItemKnowledge extends JItem {

	private EnumKnowledge[] knowledge;

	public ItemKnowledge(String name, String f, EnumKnowledge... knowledge) {
		super(name, f);
		setCreativeTab(null);
		this.knowledge = knowledge;
	}

	private EnumKnowledge getPrefferedKnowledge() {
        return knowledge[0];
    }

    private EnumKnowledge[] getKnowledges() {
        return knowledge;
    }

    private EnumKnowledge getKnowledge(int index) {
        return knowledge[index];
    }
}