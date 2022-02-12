package net.jitl.common.dialog.instruction;

public class CharacterInstruction implements DialogInstruction {
    private final String shortcut;

    public CharacterInstruction(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getShortcut() {
        return shortcut;
    }

    @Override
    public void visit(DialogInstructionVisitor visitor) {
        visitor.visitCharacter(this);
    }
}
