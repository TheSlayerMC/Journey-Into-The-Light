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

    public float getLevelCapacity(int level) {
        return level >= 5 ? 50 : level >= 10 ? 70 : level >= 15 ? 90 : level >= 20 ? 110 : level >= 30 ? 130 : level >= 40 ? 150 : 30;
    }

    public float getXP(EnumKnowledgeType type) {
        return knowledgeMap.get(type).getAmountOnCurrentLevel();
    }

    public void addXP(EnumKnowledgeType type, float xp) {
        knowledgeMap.get(type).add(xp);
    }
}
