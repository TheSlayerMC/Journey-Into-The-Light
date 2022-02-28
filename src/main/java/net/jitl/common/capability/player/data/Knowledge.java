package net.jitl.common.capability.player.data;

import net.jitl.client.render.overlay.internal.KnowledgeToast;
import net.jitl.common.helper.EnumKnowledgeType;
import net.jitl.common.knowledge.KnowledgeStorageImpl;
import net.minecraft.client.Minecraft;
import ru.timeconqueror.timecore.common.capability.property.container.PropertyContainer;

import java.util.EnumMap;


public class Knowledge extends PropertyContainer {

    private final EnumMap<EnumKnowledgeType, KnowledgeStorageImpl> knowledgeMap;

    public Knowledge() {
        this.knowledgeMap = new EnumMap<>(EnumKnowledgeType.class);

        for(EnumKnowledgeType type : EnumKnowledgeType.values()) {
            KnowledgeStorageImpl container = container(type.getName(), new KnowledgeStorageImpl());
            this.knowledgeMap.put(type, container);
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

    public void addXP(EnumKnowledgeType type, float xp, boolean showXPToast) {
        knowledgeMap.get(type).add(xp, type);
        if(showXPToast)
            Minecraft.getInstance().getToasts().addToast(new KnowledgeToast(type, true));
    }

    public void addXP(EnumKnowledgeType type, float xp) {
        this.addXP(type, xp, false);
    }
}
