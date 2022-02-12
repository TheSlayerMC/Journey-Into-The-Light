package net.jitl.common.dialog.instruction;

import net.jitl.common.dialog.api.IDialogAction;

public class ActionInstruction implements DialogInstruction {
    private final IDialogAction action;

    public ActionInstruction(IDialogAction action) {
        this.action = action;
    }

    public IDialogAction getAction() {
        return action;
    }

    @Override
    public void visit(DialogInstructionVisitor visitor) {
        visitor.visitAction(this);
    }
}
