package net.jitl.common.dialog;

import net.jitl.common.dialog.instruction.DialogInstruction;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class Dialog {
    private final ResourceLocation name;
    private final DialogInstruction[] instructions;
    private final Map<String, Integer> markers;
    private final Map<String, DialogCharacter> characters;

    public Dialog(ResourceLocation name, DialogInstruction[] instructions, Map<String, Integer> markers, Map<String, DialogCharacter> characters) {
        this.instructions = instructions;
        this.markers = markers;
        this.characters = characters;
        this.name = name;
    }

    public int instructionCount() {
        return instructions.length;
    }

    public int getMarker(String name) {
        return markers.getOrDefault(name, -1);
    }

    public DialogCharacter getCharacter(String shortcut) {
        return characters.get(shortcut);
    }

    public DialogInstruction getInstruction(int index) {
        return instructions[index];
    }

    public ResourceLocation getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Dialog{" +
                "name=" + name +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dialog dialog)) return false;

        return name.equals(dialog.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
