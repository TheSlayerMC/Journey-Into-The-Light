package net.jitl.common.dialog.dsl.api

interface OptionsContext {
    operator fun String.invoke(block: DialogContext.() -> Unit)
}