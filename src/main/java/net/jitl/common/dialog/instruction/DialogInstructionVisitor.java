package net.jitl.common.dialog.instruction;

public interface DialogInstructionVisitor {
    void visitPhrase(PhraseInstruction phrase);

    void visitOptions(OptionsInstruction options);

    void visitOptionEnd(OptionEndInstruction optionsEnd);

    void visitJump(JumpInstruction jump);

    void visitBreak(BreakInstruction breakInst);

    void visitCharacter(CharacterInstruction character);

    void visitAction(ActionInstruction action);
}
