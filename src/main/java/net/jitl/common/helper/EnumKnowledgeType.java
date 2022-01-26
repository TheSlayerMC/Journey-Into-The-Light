package net.jitl.common.helper;

import net.jitl.client.render.displayinfo.KnowledgeXPDisplay;
import net.jitl.client.render.overlay.JDisplayInfo;
import net.jitl.core.init.JItems;
import net.minecraft.network.FriendlyByteBuf;

import java.util.HashMap;

public enum EnumKnowledgeType {
    OVERWORLD("overworld", new KnowledgeXPDisplay("Overworld", JItems.OVERWORLD_KNOWLEDGE, false)
            , new KnowledgeXPDisplay("Overworld", JItems.OVERWORLD_KNOWLEDGE, true)),

    NETHER("nether", new KnowledgeXPDisplay("The Nether", JItems.NETHER_KNOWLEDGE, false)
            , new KnowledgeXPDisplay("The Nether", JItems.NETHER_KNOWLEDGE, true)),

    END("end", new KnowledgeXPDisplay("The End", JItems.END_KNOWLEDGE, false)
            , new KnowledgeXPDisplay("The End", JItems.END_KNOWLEDGE, true)),

    EUCA("euca", new KnowledgeXPDisplay("Euca", JItems.EUCA_KNOWLEDGE, false)
            , new KnowledgeXPDisplay("Euca", JItems.EUCA_KNOWLEDGE, true)),

    BOIL("boil", new KnowledgeXPDisplay("Boiling Point", JItems.BOIL_KNOWLEDGE, false)
            , new KnowledgeXPDisplay("Boiling Point", JItems.BOIL_KNOWLEDGE, true)),

    FROZEN("frozen", new KnowledgeXPDisplay("Frozen", JItems.FROZEN_KNOWLEDGE, false)
            , new KnowledgeXPDisplay("Frozen", JItems.FROZEN_KNOWLEDGE, true)),

    DEPTHS("depths", new KnowledgeXPDisplay("The Depths", JItems.DEPTHS_KNOWLEDGE, false)
            , new KnowledgeXPDisplay("The Depths", JItems.DEPTHS_KNOWLEDGE, true)),

    CORBA("corba", new KnowledgeXPDisplay("Corba", JItems.CORBA_KNOWLEDGE, false)
            , new KnowledgeXPDisplay("Corba", JItems.CORBA_KNOWLEDGE, true)),

    CLOUDIA("cloudia", new KnowledgeXPDisplay("Cloudia", JItems.CLOUDIA_KNOWLEDGE, false)
            , new KnowledgeXPDisplay("Cloudia", JItems.CLOUDIA_KNOWLEDGE, true)),

    TERRANIA("terrania", new KnowledgeXPDisplay("Terrania", JItems.TERRANIA_KNOWLEDGE, false)
            , new KnowledgeXPDisplay("Terrania", JItems.TERRANIA_KNOWLEDGE, true)),

    SENTERIAN("senterian", new KnowledgeXPDisplay("Senterian", JItems.SENTERIAN_KNOWLEDGE, false)
            , new KnowledgeXPDisplay("Senterian", JItems.SENTERIAN_KNOWLEDGE, true));

    private static final HashMap<String, EnumKnowledgeType> BY_NAME = new HashMap<>();

    static {
        for (EnumKnowledgeType value : values()) {
            BY_NAME.put(value.getName(), value);
        }
    }

    private final String name;
    private JDisplayInfo xp, level;

    EnumKnowledgeType(String name, JDisplayInfo xp, JDisplayInfo level) {
        this.name = name;
        this.xp = xp;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public JDisplayInfo getXPDisplay() {
        return xp;
    }

    public JDisplayInfo getLevelDisplay() {
        return level;
    }

    public static EnumKnowledgeType getKnowledgeFromName(String name) {
        return switch (name.toLowerCase()) {
            case "overworld" -> OVERWORLD;
            case "nether" -> NETHER;
            case "end" -> END;
            case "euca" -> EUCA;
            case "boil" -> BOIL;
            case "frozen" -> FROZEN;
            case "depths" -> DEPTHS;
            case "corba" -> CORBA;
            case "cloudia" -> CLOUDIA;
            case "terrania" -> TERRANIA;
            case "senterian" -> SENTERIAN;
            default -> null;
        };
    }

    public static void writeToBuffer(EnumKnowledgeType type, FriendlyByteBuf buf) {
        buf.writeUtf(type.getName());
    }

    public static EnumKnowledgeType readFromBuffer(FriendlyByteBuf buf) {
        String name = buf.readUtf(Short.MAX_VALUE);

        return BY_NAME.get(name);
    }
}