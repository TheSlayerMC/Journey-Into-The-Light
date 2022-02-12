package net.jitl.common.dialog.instruction;

public interface DialogInstruction {
    void visit(DialogInstructionVisitor visitor);
}
