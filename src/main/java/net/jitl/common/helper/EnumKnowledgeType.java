package net.jitl.common.helper;

import net.jitl.client.render.displayinfo.KnowledgeXPDisplay;
import net.jitl.core.JITL;
import net.jitl.core.init.JItems;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.network.FriendlyByteBuf;

import java.util.HashMap;

public enum EnumKnowledgeType {
    OVERWORLD("overworld", new KnowledgeXPDisplay("Overworld", JItems.OVERWORLD_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), false)
            , new KnowledgeXPDisplay("Overworld", JItems.OVERWORLD_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), true)),

    NETHER("nether", new KnowledgeXPDisplay("The Nether", JItems.NETHER_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), false)
            , new KnowledgeXPDisplay("The Nether", JItems.NETHER_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), true)),

    END("end", new KnowledgeXPDisplay("The End", JItems.END_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), false)
            , new KnowledgeXPDisplay("The End", JItems.END_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), true)),

    EUCA("euca", new KnowledgeXPDisplay("Euca", JItems.EUCA_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), false)
            , new KnowledgeXPDisplay("Euca", JItems.EUCA_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), true)),

    BOIL("boil", new KnowledgeXPDisplay("Boiling Point", JItems.BOIL_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), false)
            , new KnowledgeXPDisplay("Boiling Point", JItems.BOIL_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), true)),

    FROZEN("frozen", new KnowledgeXPDisplay("Frozen", JItems.FROZEN_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), false)
            , new KnowledgeXPDisplay("Frozen", JItems.FROZEN_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), true)),

    DEPTHS("depths", new KnowledgeXPDisplay("The Depths", JItems.DEPTHS_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), false)
            , new KnowledgeXPDisplay("The Depths", JItems.DEPTHS_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), true)),

    CORBA("corba", new KnowledgeXPDisplay("Corba", JItems.CORBA_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), false)
            , new KnowledgeXPDisplay("Corba", JItems.CORBA_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), true)),

    CLOUDIA("cloudia", new KnowledgeXPDisplay("Cloudia", JItems.CLOUDIA_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), false)
            , new KnowledgeXPDisplay("Cloudia", JItems.CLOUDIA_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), true)),

    TERRANIA("terrania", new KnowledgeXPDisplay("Terrania", JItems.TERRANIA_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), false)
            , new KnowledgeXPDisplay("Terrania", JItems.TERRANIA_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), true)),

    SENTERIAN("senterian", new KnowledgeXPDisplay("Senterian", JItems.SENTERIAN_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), false)
            , new KnowledgeXPDisplay("Senterian", JItems.SENTERIAN_KNOWLEDGE, JITL.rl("textures/gui/toasts.png"), true));

    private static final HashMap<String, EnumKnowledgeType> BY_NAME = new HashMap<>();

    static {
        for (EnumKnowledgeType value : values()) {
            BY_NAME.put(value.getName(), value);
        }
    }

    private final String name;
    private DisplayInfo xp, level;

    EnumKnowledgeType(String name, DisplayInfo xp, DisplayInfo level) {
        this.name = name;
        this.xp = xp;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public DisplayInfo getXPDisplay() {
        return xp;
    }

    public DisplayInfo getLevelDisplay() {
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