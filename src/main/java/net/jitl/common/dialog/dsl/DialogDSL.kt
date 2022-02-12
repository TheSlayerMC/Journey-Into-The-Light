package net.jitl.common.dialog.dsl

import net.jitl.common.dialog.Dialog
import net.jitl.common.dialog.DialogCharacter
import net.jitl.common.dialog.api.IDialogAction
import net.jitl.common.dialog.compiler.DialogBuilder
import net.jitl.common.dialog.compiler.LazyAddress
import net.jitl.common.dialog.dsl.api.DialogContext
import net.jitl.common.dialog.dsl.api.OptionsContext
import net.jitl.common.dialog.instruction.*
import net.jitl.common.dialog.instruction.OptionsInstruction.Option
import net.minecraft.resources.ResourceLocation

class DialogDSL(private val dialogBuilder: DialogBuilder) : DialogContext {
    override fun define(shortcut: String, character: DialogCharacter) {
        dialogBuilder.pushCharacter(shortcut, character)
    }

    override fun marker(name: String) {
        dialogBuilder.pushMarker(name)
    }

    override fun character(shortcut: String) {
        dialogBuilder.pushInstruction(CharacterInstruction(shortcut))
    }

    override fun phrase(text: String) {
        dialogBuilder.pushInstruction(PhraseInstruction(text))
    }

    override fun answer(text: String) {
        options {
            text {}
        }
    }

    override fun action(action: IDialogAction) {
        dialogBuilder.pushInstruction(ActionInstruction(action))
    }

    override fun options(block: OptionsContext.() -> Unit) {
        val options = mutableListOf<Option>()
        dialogBuilder.pushInstruction(OptionsInstruction(options))
        val endAddress = LazyAddress()
        block(OptionsDSL(this, dialogBuilder, options, endAddress))
        endAddress.set(dialogBuilder.instructionCount())
    }

    override fun end() {
        dialogBuilder.pushInstruction(BreakInstruction.INSTANCE)
    }

    override fun goto(marker: String) {
        dialogBuilder.pushInstruction(JumpInstruction(marker))
    }

    internal fun copy(): DialogDSL {
        return DialogDSL(dialogBuilder)
    }
}

class OptionsDSL(
    private val dialogContext: DialogDSL,
    private val dialogBuilder: DialogBuilder,
    private val options: MutableList<Option>,
    private val endAddress: LazyAddress
) : OptionsContext {
    override fun String.invoke(block: DialogContext.() -> Unit) {
        val option = Option(this, dialogBuilder.instructionCount())
        options.add(option)
        block(dialogContext.copy())
        dialogBuilder.pushInstruction(OptionEndInstruction(endAddress))
    }
}

fun dialog(name: ResourceLocation, block: DialogContext.() -> Unit): Dialog {
    val dialogBuilder = DialogBuilder()
    val dsl = DialogDSL(dialogBuilder)
    block(dsl)
    return dialogBuilder.build(name)
}