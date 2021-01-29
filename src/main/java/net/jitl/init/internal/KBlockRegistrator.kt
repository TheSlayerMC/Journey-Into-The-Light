package net.jitl.init.internal

import net.jitl.JITL
import net.jitl.client.render.JBlockStateResources
import net.jitl.common.block.base.XZFacedBlock
import net.jitl.init.JTabs
import net.jitl.util.JBlockProperties
import ru.timeconqueror.timecore.api.client.resource.BlockModels
import ru.timeconqueror.timecore.api.registry.BlockRegister
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable.InitMethod
import ru.timeconqueror.timecore.api.registry.util.defaultBml
import ru.timeconqueror.timecore.api.registry.util.invoke

object KBlockRegistrator {
    @AutoRegistrable
    private val REGISTER = BlockRegister(JITL.MODID)

    @InitMethod
    private fun register() {
        REGISTER {
            "runic_connector" represents {
                XZFacedBlock(JBlockProperties.BRICK_PROPS.create())
            } with {
                name("Runic Connector")
                defaultBlockItem(JTabs.BLOCKS)
                clientSideOnly {
                    state(JBlockStateResources.horizontalState(defaultBml))
                    model(defaultBml,
                        BlockModels.cubeOrientableModel(blockTl("runic_connector"),
                            blockTl("smooth_corrupted_blood_rock"),
                            blockTl("smooth_corrupted_blood_rock")))
                }
            }
        }
    }
}