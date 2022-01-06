package net.jitl.init.internal

import net.jitl.JITL
import net.jitl.client.render.JBlockStateResources
import net.jitl.common.block.GuardianTowerBrainBlock
import net.jitl.common.block.base.XZFacedBlock
import net.jitl.init.JTabs
import net.jitl.util.JBlockProperties
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraftforge.registries.RegistryObject
import ru.timeconqueror.timecore.api.client.resource.BlockModels
import ru.timeconqueror.timecore.api.client.resource.BlockStateResources
import ru.timeconqueror.timecore.api.client.resource.location.TextureLocation
import ru.timeconqueror.timecore.api.registry.BlockRegister
import ru.timeconqueror.timecore.api.registry.util.*

object KBlockRegistrator {
    @AutoRegistrable
    private val REGISTER = BlockRegister(JITL.MODID)

    @AutoRegistrable.Init
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

            var gildedDungeonBricks: RegistryObject<Block>? = null
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
                gildedDungeonBricks =
                    "gilded_dungeon_bricks" represents { Block(dungeonBlockProps()) } with {
                        name("Gilded Dungeon Bricks")
                    }
                "dungeon_floor" represents { Block(dungeonBlockProps()) } with {
                    name("Dungeon Floor")
                }
                "dungeon_lamp" represents { Block(dungeonLampProps()) } with {
                    name("Dungeon Lamp")
                }
            }

            registerStairs(
                "gilded_dungeon_stairs",
                "Gilded Dungeon Stairs",
                gildedDungeonBricks!!,
                dungeonBlockProps()
            )

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
            val shieldedGildedDungeonBricks =
                "shielded_gilded_dungeon_bricks" represents { Block(shieldedDungeonBlockProps()) } with {
                    name("Shielded Gilded Dungeon Bricks")
                    oneVariantState(bml("gilded_dungeon_bricks"))
                    defaultBlockItem(JTabs.BLOCKS, bml("gilded_dungeon_bricks"))
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

            registerStairs(
                "shielded_gilded_dungeon_stairs",
                "Shielded Gilded Dungeon Stairs",
                shieldedGildedDungeonBricks,
                shieldedDungeonBlockProps(),
                sourceBlockTexture = JITL.blockTl(gildedDungeonBricks!!.id.path)
            )

            "guardian_tower_brain" represents { GuardianTowerBrainBlock() } with {
                name("Guardian Tower Brain")
                clientSideOnly {
                    oneVariantState(bml("chiseled_dungeon_bricks"))
                }
            }
        }
    }

    /**
     * Registers a 'stairs' block
     */
    @JvmOverloads
    fun registerStairs(
        name: String,
        enName: String,
        sourceBlock: RegistryObject<out Block>,
        properties: BlockBehaviour.Properties,
        sourceBlockTexture: TextureLocation = TextureLocation(sourceBlock.id.namespace, "block/" + sourceBlock.id.path)
    ) {
        REGISTER {
            name represents { StairBlock({ sourceBlock.get().defaultBlockState() }, properties) } with {
                defaultBlockItem(JTabs.BLOCKS)
                name(enName)

                val stairs = bml(name)
                val innerStairs = bml("$name/inner")
                val outerStairs = bml("$name/outer")
                model(stairs, BlockModels.stairsModel(sourceBlockTexture, sourceBlockTexture, sourceBlockTexture))
                model(innerStairs, BlockModels.stairsInnerModel(sourceBlockTexture, sourceBlockTexture, sourceBlockTexture))
                model(outerStairs, BlockModels.stairsOuterModel(sourceBlockTexture, sourceBlockTexture, sourceBlockTexture))
                val state = BlockStateResources.stairs(stairs, innerStairs, outerStairs)
                state(state)
            }
        }
    }
}