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

    @JvmStatic
    val MAGE: Dialog = dialog(JITL.rl("test")) {
        define("mage", JEntities.MAGE_TYPE)
        character("mage")
        -"Bla bla bla."
        -"Wanna learn about Essencia?"
        options {
            "What's Essencia?" {
                -"Essencia is something cool and awesome and magical."
                -"You can make cool wands and summon monsters and shit."
                options {
                    "Woah that sounds cool!" {
                        -"Yeah it's sick"
                    }
                    "No thanks, that sounds stupid" {
                        -"I know :("
                    }
                }
            }
            "No thanks." {}
            "Can we trade?" {
                action { world, player ->
                    //Open trading menu
                }
            }
        }
        -"I know! :/"
    }
        @JvmName("mage") get
}