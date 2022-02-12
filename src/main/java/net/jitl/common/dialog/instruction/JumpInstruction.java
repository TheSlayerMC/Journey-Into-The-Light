package net.jitl.common.dialog.instruction;

public class JumpInstruction implements DialogInstruction {
    private final String marker;

    public JumpInstruction(String marker) {
        this.marker = marker;
    }

    public String getMarker() {
        return marker;
    }

    @Override
    public void visit(DialogInstructionVisitor visitor) {
        visitor.visitJump(this);
    }
}
