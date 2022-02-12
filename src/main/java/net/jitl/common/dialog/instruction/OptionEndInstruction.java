package net.jitl.common.dialog.instruction;

import net.jitl.common.dialog.compiler.LazyAddress;

public class OptionEndInstruction implements DialogInstruction {
    private final LazyAddress optionsEnd;

    public OptionEndInstruction(LazyAddress optionsEnd) {
        this.optionsEnd = optionsEnd;
    }

    public int getEndAddress() {
        return optionsEnd.get();
    }

    @Override
    public void visit(DialogInstructionVisitor visitor) {
        visitor.visitOptionEnd(this);
    }
}
