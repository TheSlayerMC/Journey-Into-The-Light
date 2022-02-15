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
        +"Waaaaait..."
        +"Very very very very very long text. This is super long."
        +"Very very very very very long text."
        +"I'm fine!"
        +"Waaaaait..."
        +"Very very very very very long text. This is super long."
        +"Very very very very very long text."
        options {
            "You're not real" {}
            "You're real" {}
        }
        -"I know! :/"
    }
        @JvmName("test") get
}