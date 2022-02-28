package net.jitl.common.knowledge;

import net.jitl.common.helper.EnumKnowledgeType;

public interface PlayerStats {

    void addSentacoin(int amount);

    int getSentacoinValue();

    boolean useCoins(int amount);

    /**
     * Adds knowledge to the player.
     */
    void addKnowledge(EnumKnowledgeType type, float amount);

    /**
     * Removes knowledge from the player.
     *
     * @return amount, that can't be removed due to storage contained smaller amount than requested.
     */
    float removeKnowledge(EnumKnowledgeType type, float amount);

    KnowledgeStorage getKnowledge(EnumKnowledgeType type);

    interface KnowledgeStorage {
        /**
         * Adds knowledge to the player.
         */
        void add(float amount, EnumKnowledgeType type);

        void addLevel(int amount, EnumKnowledgeType type);

        /**
         * Removes knowledge from the player.
         *
         * @return amount, that can't be removed due to storage contained smaller amount than requested.
         */
        float remove(float amount);

        float getLevelCapacity(int level);

        int getLevelCount();

        float getAmountOnCurrentLevel();

        float getTotal();

    }
}