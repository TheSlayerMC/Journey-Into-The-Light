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

/**
 * ##Sequental flow
 * ```
 * PHRASE("Hi!") -> PHRASE("How are you?") -> ANSWER("I'm fine!") -> ANSWER("What about you?") -> PHRASE("I'm fine too!")
 * ```
 * ```
 *  fun sequental() {
 *      dialog {
 *          phrase("Hi!")
 *          answer("How are you?")
 *          answer("I'm fine!")
 *          answer("What about you?")
 *          phrase("I'm fine too!")
 *      }
 *  }
 * ```
 *
 * ##Branching
 * ```
 * PHRASE("Hi!") -> PHRASE("How are you?")   -> 1. OPTION("I'm fine!") -> ANSWER("What about you?") -> PHRASE("I'm fine too!")
 *                                        |
 *                                        V  -> 2. OPTION("I'm too busy!") -> PHRASE("Well, ok, see you later!")
 *                                        |
 *                                        V  -> 3. OPTION("I feel bad!") -> PHRASE("You need a help!")
 * ```
 * ```
 * fun branching() {
 *      dialog {
 *          phrase("Hi!")
 *          phrase("How are you?")
 *          options {
 *              "I'm fine!" {
 *                  answer("What about you?")
 *                  phrase("I'm fine too!")
 *                  end
 *              }
 *              "I'm too busy!" {
 *              }
 *              "I feel bad!" {
 *                  phrase("You need a help!")
 *                  end
 *              }
 *          }
 *          phrase("Well, ok, see you later!")
 *      }
 * }
 * ```
 *
 *##Fake-Branching flow:
 *
 * ```
 * PHRASE("Hi!") -> PHRASE("How are you?")   -> 1. OPTION("I'm fine!") -> ANSWER("What about you?")    ->  PHRASE("As for me, I'm fine!")
 *                                        |                                                                ^
 *                                        V  -> 2. OPTION("I feel bad!") -> PHRASE("That's... sad :c") ->  |
 * ```
 * ```
 * fun fakeBranching() {
 *      dialog {
 *          phrase("Hi!")
 *          phrase("How are you?")
 *          options {
 *              "I'm fine!" {
 *                  answer("What about you?")
 *              }
 *              "I feel bad!" {
 *                  phrase("That's... sad :c")
 *              }
 *          }
 *          phrase("As for me, I'm fine!")
 *      }
 *}
 * ```
 * ##Flow with markers
 * ```
 * PHRASE("Hi!") -> PHRASE("How are you?")  -> 1. OPTION("I'm fine!") -> PHRASE("That's good!") -> PHRASE("I need to go now...")
 *    ^                                  |
 *    |                                  V  -> 2. OPTION("I feel bad!") -> PHRASE("That's... sad :c") -> |
 *    |                                                                                                  V
 *     <-------------------------------------------------------------------------------------------------
 * ```
 * ```
 * fun loop() {
 *      dialog {
 *          phrase("Hi!", marker = "start")
 *          phrase("How are you?")

 *          options(marker = "marker") {
 *              "I'm fine!" {
 *                  phrase("That's good!")
 *                  goto("exit")
 *                  phrase("So...") // Skipped
 *                  phrase("I need to go now...", marker = "exit")
 *              }
 *              "*bonk* Forget!" {
 *                  goto("start")
 *              }
 *          }
 *      }
 *}
 * ```
 */

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