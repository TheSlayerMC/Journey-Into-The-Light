package net.jitl.core.init

import net.jitl.common.dialog.Dialog
import net.jitl.common.dialog.dsl.dialog
import net.jitl.core.JITL

object JDialogs {
    @JvmStatic
    val TEST: Dialog = dialog(JITL.rl("test")) {
        -"Hi!"
        -"How are you?"
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