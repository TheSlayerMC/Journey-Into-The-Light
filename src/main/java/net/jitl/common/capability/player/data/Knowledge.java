package net.jitl.common.capability.player.data;

import net.jitl.common.helper.EnumKnowledgeType;
import net.jitl.common.knowledge.KnowledgeStorageImpl;
import ru.timeconqueror.timecore.common.capability.property.CoffeeProperty;
import ru.timeconqueror.timecore.common.capability.property.container.PropertyContainer;

import java.util.HashMap;


public class Knowledge extends PropertyContainer {

    private final HashMap<EnumKnowledgeType, KnowledgeStorageImpl> knowledgeMap;

    public Knowledge() {
        this.knowledgeMap = new HashMap<>(EnumKnowledgeType.values().length, 1);

        for(EnumKnowledgeType type : EnumKnowledgeType.values()) {
            this.knowledgeMap.put(type, new KnowledgeStorageImpl());
        }
    }

    public int getLevel(EnumKnowledgeType knowledge) {
        return knowledgeMap.get(knowledge).getLevelCount();
    }

    public KnowledgeStorageImpl getKnowledge(EnumKnowledgeType knowledgeType) {
        return knowledgeMap.get(knowledgeType);
    }

    public float getXP(EnumKnowledgeType type) {
        return knowledgeMap.get(type).getAmountOnCurrentLevel();
    }

    public void addXP(EnumKnowledgeType type, float xp) {
        knowledgeMap.get(type).add(xp);
    }
}
