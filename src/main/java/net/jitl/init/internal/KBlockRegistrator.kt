package net.jitl.init.internal

import net.jitl.JITL
import net.jitl.client.render.JBlockModels
import net.jitl.client.render.JBlockStateResources
import net.jitl.common.block.GuardianTowerBrainBlock
import net.jitl.common.block.base.XZFacedBlock
import net.jitl.init.JTabs
import net.jitl.util.JBlockProperties
import net.minecraft.block.Block
import ru.timeconqueror.timecore.api.client.resource.BlockModels
import ru.timeconqueror.timecore.api.client.resource.location.BlockModelLocation
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
            "runic_connector" represents { XZFacedBlock(JBlockProperties.BRICK_PROPS.create()) } with {
                name("Runic Connector")
                defaultBlockItem(JTabs.BLOCKS)
                clientSideOnly {
                    state(JBlockStateResources.horizontalState(defaultBml))
                    model(
                        defaultBml,
                        BlockModels.cubeOrientableModel(
                            blockTl("runic_connector"),
                            blockTl("smooth_corrupted_blood_rock"),
                            blockTl("smooth_corrupted_blood_rock")
                        )
                    )
                }
            }

            groupSettings<Block> {
                defaultBlockItem(JTabs.BLOCKS)
                oneVarStateAndCubeAllModel()
            } applyFor {
                "dungeon_bricks" represents { Block(JBlockProperties.BRICK_PROPS.create()) } with {
                    name("Dungeon Bricks")
                }
                "carved_dungeon_bricks" represents { Block(JBlockProperties.BRICK_PROPS.create()) } with {
                    name("Carved Dungeon Bricks")
                }
                "chiseled_dungeon_bricks" represents { Block(JBlockProperties.BRICK_PROPS.create()) } with {
                    name("Chiseled Dungeon Bricks")
                }
                "cracked_dungeon_bricks" represents { Block(JBlockProperties.BRICK_PROPS.create()) } with {
                    name("Cracked Dungeon Bricks")
                }
                "dungeon_floor" represents { Block(JBlockProperties.BRICK_PROPS.create()) } with {
                    name("Dungeon Floor")
                }
                "dungeon_lamp" represents { Block(JBlockProperties.BRICK_PROPS.create().lightLevel { 14 }) } with {
                    name("Dungeon Lamp")
                }
            }

            "guardian_tower_brain" represents { GuardianTowerBrainBlock() } with {
                name("Guardian Tower Brain")
                clientSideOnly {
                    val modelLoc = BlockModelLocation(modId, name)
                    oneVariantState(modelLoc)
                    model(modelLoc, JBlockModels.empty())
                }
            }
        }
    }
}