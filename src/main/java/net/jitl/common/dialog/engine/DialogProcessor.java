package net.jitl.common.dialog.engine;

import net.jitl.common.dialog.Dialog;
import net.jitl.common.dialog.instruction.*;

public class DialogProcessor {
    private final Dialog dialog;
    private final Visitor visitor = new Visitor();
    private final DialogPresenter presenter;
    private int currentInstruction = -1;

    public DialogProcessor(Dialog dialog, DialogPresenter presenter) {
        this.dialog = dialog;
        this.presenter = presenter;
    }

    public void start() {
        invokeNextInstruction();
    }

    private void invokeNextInstruction() {
        invokeInstruction(currentInstruction + 1);
    }

    private void invokeInstruction(int address) {
        if (address == -1) {
            throw new IllegalArgumentException("Attempted to go to illegal address.");
        }

        currentInstruction = address;

        if (isFinished()) {
            presenter.onEnd();
            return;
        }

        DialogInstruction instruction = dialog.getInstruction(currentInstruction);
        instruction.visit(visitor);
    }

    public boolean isFinished() {
        return currentInstruction >= dialog.instructionCount();
    }

    private class Visitor implements DialogInstructionVisitor {
        @Override
        public void visitPhrase(PhraseInstruction phrase) {
            boolean hasOptions = currentInstruction < dialog.instructionCount() - 1 && dialog.getInstruction(currentInstruction + 1) instanceof OptionsInstruction;
            presenter.presentPhrase(phrase.getText(), hasOptions, DialogProcessor.this::invokeNextInstruction);
        }

        @Override
        public void visitOptions(OptionsInstruction options) {
            presenter.presentOptions(options.getOptions(), answer -> invokeInstruction(options.getOptionAddress(answer)));
        }

        @Override
        public void visitOptionEnd(OptionEndInstruction optionsEnd) {
            invokeInstruction(optionsEnd.getEndAddress());
        }

        @Override
        public void visitJump(JumpInstruction jump) {
            invokeInstruction(dialog.getMarker(jump.getMarker()));
        }

        @Override
        public void visitBreak(BreakInstruction breakInst) {
            invokeInstruction(Integer.MAX_VALUE);
        }

        @Override
        public void visitCharacter(CharacterInstruction character) {
            presenter.presentCharacter(dialog.getCharacter(character.getShortcut()));
            invokeNextInstruction();
        }

        @Override
        public void visitAction(ActionInstruction action) {
            presenter.presentAction(action.getAction());
        }
    }
}
