package net.jitl.common.dialog.dsl.api

import net.jitl.common.dialog.DialogCharacter
import net.jitl.common.dialog.api.IDialogAction
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity

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

interface DialogContext {
    fun define(shortcut: String, character: DialogCharacter)//TODO CharacterContext
    fun define(shortcut: String, type: EntityType<out LivingEntity>) {
        define(shortcut, DialogCharacter(type))
    }

    fun marker(name: String)

    /**
     * NPC's text
     */
    fun phrase(text: String)

    /**
     * Player answer
     */
    fun answer(text: String)
    fun action(action: IDialogAction)

    /**
     * Player possible answers
     */
    fun options(block: OptionsContext.() -> Unit)
    fun end()
    fun goto(marker: String)
    fun character(shortcut: String)

    operator fun String.unaryMinus() = phrase(this)
    operator fun String.unaryPlus() = phrase(this)

    val end get() = end()
}