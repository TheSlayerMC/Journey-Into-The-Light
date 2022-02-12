package net.jitl.common.dialog.instruction;

public abstract class AbstractDialogInstructionVisitor implements DialogInstructionVisitor {
    abstract void visitInstruction(DialogInstruction instruction);

    @Override
    public void visitPhrase(PhraseInstruction phrase) {
        visitInstruction(phrase);
    }

    @Override
    public void visitOptions(OptionsInstruction options) {
        visitInstruction(options);
    }

    @Override
    public void visitOptionEnd(OptionEndInstruction optionsEnd) {
        visitInstruction(optionsEnd);
    }

    @Override
    public void visitJump(JumpInstruction jump) {
        visitInstruction(jump);
    }

    @Override
    public void visitBreak(BreakInstruction breakInst) {
        visitInstruction(breakInst);
    }

    @Override
    public void visitCharacter(CharacterInstruction character) {
        visitInstruction(character);
    }

    @Override
    public void visitAction(ActionInstruction action) {
        visitInstruction(action);
    }
}
