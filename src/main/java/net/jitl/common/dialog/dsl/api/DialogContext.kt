package net.jitl.common.dialog.dsl.api

import net.jitl.common.dialog.DialogCharacter
import net.jitl.common.dialog.api.IDialogAction
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity

interface DialogContext {
    fun define(shortcut: String, character: DialogCharacter)//TODO CharacterContext
    fun define(shortcut: String, type: EntityType<out LivingEntity>) {
        define(shortcut, DialogCharacter(type))
    }

    fun marker(name: String)
    fun phrase(text: String)
    fun answer(text: String)
    fun action(action: IDialogAction)
    fun options(block: OptionsContext.() -> Unit)
    fun end()
    fun goto(marker: String)
    fun character(shortcut: String)

    val end get() = end()
}