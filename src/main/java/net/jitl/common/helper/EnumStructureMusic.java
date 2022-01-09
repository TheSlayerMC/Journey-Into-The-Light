package net.jitl.common.helper;

import net.jitl.init.JSounds;
import net.jitl.init.world.JStructures;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import ru.timeconqueror.timecore.api.util.lookups.EnumLookup;

public enum EnumStructureMusic {
    GUARDIAN_TOWER(JStructures.GUARDIAN_TOWER_HOLDER.getStructure(), new JMusic(JSounds.TOWER_THEME.get(), 1, 200, 1000), 1);

    private final StructureFeature<?> structure;
    private final JMusic music;
    private final int id;
    private static final EnumLookup<EnumStructureMusic, Integer> STRUCTURE_FINDER = EnumLookup.make(EnumStructureMusic.class, EnumStructureMusic::getID);

    EnumStructureMusic(StructureFeature<?> structureIn, JMusic structureMusic, int musicID) {
        structure = structureIn;
        music = structureMusic;
        id = musicID;
    }

    public StructureFeature<?> getStructure() {
            return structure;
        }

    public int getID() {
        return id;
    }

    public JMusic getMusicForStructure() {
        return music;
    }

    public static EnumStructureMusic getFromID(int id) {
        if (id == 0) {
            return null;
        } else {
            return STRUCTURE_FINDER.by(id);
        }
    }
}