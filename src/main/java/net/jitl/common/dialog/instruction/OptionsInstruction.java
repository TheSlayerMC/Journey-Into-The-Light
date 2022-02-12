package net.jitl.common.dialog.instruction;

import java.util.ArrayList;
import java.util.List;

public class OptionsInstruction implements DialogInstruction {
    private final List<Option> options;

    public OptionsInstruction(List<Option> options) {
        this.options = options;
    }

    public List<String> getOptions() {
        List<String> optionTexts = new ArrayList<>();
        for (Option option : options) {
            optionTexts.add(option.text);
        }

        return optionTexts;
    }

    public int getOptionCount() {
        return options.size();
    }

    public int getOptionAddress(int index) {
        return index < getOptionCount() ? options.get(index).address : -1;
    }

    @Override
    public void visit(DialogInstructionVisitor visitor) {
        visitor.visitOptions(this);
    }

    public record Option(String text, int address) {
    }
}
