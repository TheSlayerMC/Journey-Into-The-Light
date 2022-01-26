package net.jitl.common.helper;

import net.minecraft.network.FriendlyByteBuf;

import java.util.HashMap;
import java.util.Locale;

public enum EnumKnowledgeType {
    OVERWORLD("overworld"),
    NETHER("nether"),
    END("end"),
    EUCA("euca"),
    BOIL("boil"),
    FROZEN("frozen"),
    DEPTHS("depths"),
    CORBA("corba"),
    CLOUDIA("cloudia"),
    TERRANIA("terrania"),
    SENTERIAN("senterian");

    private static final HashMap<String, EnumKnowledgeType> BY_NAME = new HashMap<>();

    static {
        for (EnumKnowledgeType value : values()) {
            BY_NAME.put(value.getName(), value);
        }
    }

    private final String name;

    EnumKnowledgeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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