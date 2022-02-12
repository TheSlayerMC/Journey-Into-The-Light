package net.jitl.common.dialog.instruction;

public class BreakInstruction implements DialogInstruction {
    public static final BreakInstruction INSTANCE = new BreakInstruction();

    private BreakInstruction() {
    }

    @Override
    public void visit(DialogInstructionVisitor visitor) {
        visitor.visitBreak(this);
    }
}
