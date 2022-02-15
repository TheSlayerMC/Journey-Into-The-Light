package net.jitl.core.init

import net.jitl.common.dialog.Dialog
import net.jitl.common.dialog.dsl.dialog
import net.jitl.core.JITL

object JDialogs {
    @JvmStatic
    val TEST: Dialog = dialog(JITL.rl("test")) {
        define("illager_mech", JEntities.ILLAGER_MECH_TYPE)
        character("illager_mech")
        -"Hi!"
        -"How are you?"
        +"I'm fine!"
        +"This text is pretty long."
        +"This text is just a little bit longer."
        +"Wow, this text is honestly super duper long."
        +"This is some really long text. Wow. I mean, look at the size of this thing."
        +"How are you?"
        +"I'm fine!"
        +"How are you?"
        +"I'm fine!"
        +"Waaaaait..."
        options {
            "You're not real" {}
            "You're real" {}
        }
        -"I know! :/"
    }
        @JvmName("test") get
}