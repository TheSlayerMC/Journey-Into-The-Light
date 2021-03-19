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
import ru.timeconqueror.timecore.api.registry.util.*
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable.InitMethod

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

            val dungeonBlockProps = JBlockProperties.BRICK_PROPS
            val dungeonLampProps = BlockPropsFactory.of { dungeonBlockProps.create().lightLevel { 14 } }

            groupSettings<Block> {
                defaultBlockItem(JTabs.BLOCKS)
                oneVarStateAndCubeAllModel()
            } applyFor {
                "dungeon_bricks" represents { Block(dungeonBlockProps()) } with {
                    name("Dungeon Bricks")
                }
                "carved_dungeon_bricks" represents { Block(dungeonBlockProps()) } with {
                    name("Carved Dungeon Bricks")
                }
                "chiseled_dungeon_bricks" represents { Block(dungeonBlockProps()) } with {
                    name("Chiseled Dungeon Bricks")
                }
                "cracked_dungeon_bricks" represents { Block(dungeonBlockProps()) } with {
                    name("Cracked Dungeon Bricks")
                }
                "dungeon_floor" represents { Block(dungeonBlockProps()) } with {
                    name("Dungeon Floor")
                }
                "dungeon_lamp" represents { Block(dungeonLampProps()) } with {
                    name("Dungeon Lamp")
                }
            }

            val shieldedDungeonBlockProps = BlockPropsFactory.of { dungeonBlockProps().unbreakable() }
            val shieldedDungeonLampProps = BlockPropsFactory.of { dungeonLampProps().unbreakable() }

            "shielded_dungeon_bricks" represents { Block(shieldedDungeonBlockProps()) } with {
                name("Shielded Dungeon Bricks")
                oneVariantState(bml("dungeon_bricks"))
                defaultBlockItem(JTabs.BLOCKS, bml("dungeon_bricks"))
            }
            "shielded_carved_dungeon_bricks" represents { Block(shieldedDungeonBlockProps()) } with {
                name("Shielded Carved Dungeon Bricks")
                oneVariantState(bml("carved_dungeon_bricks"))
                defaultBlockItem(JTabs.BLOCKS, bml("carved_dungeon_bricks"))
            }
            "shielded_chiseled_dungeon_bricks" represents { Block(shieldedDungeonBlockProps()) } with {
                name("Shielded Chiseled Dungeon Bricks")
                oneVariantState(bml("chiseled_dungeon_bricks"))
                defaultBlockItem(JTabs.BLOCKS, bml("chiseled_dungeon_bricks"))
            }
            "shielded_cracked_dungeon_bricks" represents { Block(shieldedDungeonBlockProps()) } with {
                name("Shielded Cracked Dungeon Bricks")
                oneVariantState(bml("cracked_dungeon_bricks"))
                defaultBlockItem(JTabs.BLOCKS, bml("cracked_dungeon_bricks"))
            }
            "shielded_dungeon_floor" represents { Block(shieldedDungeonBlockProps()) } with {
                name("Shielded Dungeon Floor")
                oneVariantState(bml("dungeon_floor"))
                defaultBlockItem(JTabs.BLOCKS, bml("dungeon_floor"))
            }
            "shielded_dungeon_lamp" represents { Block(shieldedDungeonLampProps()) } with {
                name("Shielded Dungeon Lamp")
                oneVariantState(bml("dungeon_lamp"))
                defaultBlockItem(JTabs.BLOCKS, bml("dungeon_lamp"))
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