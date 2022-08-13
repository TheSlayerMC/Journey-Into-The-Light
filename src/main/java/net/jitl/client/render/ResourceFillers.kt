package net.jitl.client.render

import ru.timeconqueror.timecore.api.client.resource.BlockModel
import ru.timeconqueror.timecore.api.client.resource.BlockStateResource
import ru.timeconqueror.timecore.api.client.resource.location.BlockModelLocation
import ru.timeconqueror.timecore.api.registry.BlockRegister
import ru.timeconqueror.timecore.api.util.json.*

object ResourceFillers {
    /**
     * Generates standard block state with random models, which depend on the place position.
     */
    @JvmStatic
    fun randomModeled(chain: BlockRegister.BlockRegisterChain<*>, vararg models: BlockModel) {
        fun modelLocation(chain: BlockRegister.BlockRegisterChain<*>, i: Int) =
            BlockModelLocation(chain.modId, "${chain.name}_$i")

        for ((index, model) in models.withIndex()) {
            chain.model(modelLocation(chain, index), model)
        }

        val state = json {
            "variants" {
                array("") {
                    for (index in models.indices) {
                        obj {
                            "model" set modelLocation(chain, index).toString()
                        }
                    }
                }
            }
        }

        chain.state(BlockStateResource.fromJson(state))
    }
}
