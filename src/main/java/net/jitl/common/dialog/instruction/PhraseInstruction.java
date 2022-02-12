package net.jitl.common.dialog.instruction;

public class PhraseInstruction implements DialogInstruction {
    private final String text;

    public PhraseInstruction(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public void visit(DialogInstructionVisitor visitor) {
        visitor.visitPhrase(this);
    }
}
