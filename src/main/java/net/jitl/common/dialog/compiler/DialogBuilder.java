package net.jitl.common.dialog.compiler;

import net.jitl.common.dialog.Dialog;
import net.jitl.common.dialog.DialogCharacter;
import net.jitl.common.dialog.instruction.DialogInstruction;
import net.minecraft.resources.ResourceLocation;

import java.util.*;

public class DialogBuilder {
    private final Map<String, Integer> markers = new HashMap<>();
    private final Map<String, DialogCharacter> characters = new HashMap<>();
    private final List<DialogInstruction> instructions = new ArrayList<>();

    public int instructionCount() {
        return instructions.size();
    }

    public void pushMarker(String marker) {
        if (markers.put(marker, instructionCount()) != null) {
            throw new IllegalArgumentException("Marker '" + marker + "' has been already defined.");
        }
    }

    public void pushCharacter(String shortcut, DialogCharacter character) {
        if (characters.put(shortcut, character) != null) {
            throw new IllegalArgumentException("Shortcut '" + shortcut + "' has been already defined.");
        }
    }

    public int pushInstruction(DialogInstruction instruction) {
        instructions.add(instruction);
        return instructionCount() - 1;
    }

    public Dialog build(ResourceLocation name) {
        //FIXME validate markers
        //FIXME validate shortcuts
        return new Dialog(name, instructions.toArray(new DialogInstruction[0]), Collections.unmodifiableMap(markers), characters);
    }
}
