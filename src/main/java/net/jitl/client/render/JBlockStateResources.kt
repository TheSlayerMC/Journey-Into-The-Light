package net.jitl.client.render

import net.jitl.common.block.base.XZFacedBlock
import ru.timeconqueror.timecore.api.client.resource.BlockStateResource
import ru.timeconqueror.timecore.api.client.resource.JSONTimeResource
import ru.timeconqueror.timecore.api.client.resource.location.BlockModelLocation
import ru.timeconqueror.timecore.api.util.json
import ru.timeconqueror.timecore.api.util.y

object JBlockStateResources {
    /**
     * For [XZFacedBlock]s
     */
    @JvmStatic
    fun horizontalState(modelLocation: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                "axis=z" { "model" set modelLocation.toString() }
                "axis=x" {
                    "model" set modelLocation.toString()
                    y = 90
                }
            }
        })
    }

    @JvmStatic
    fun orientableState(location: BlockModelLocation): BlockStateResource {
        val json = JSONTimeResource.`object`(
            null, JSONTimeResource.listOf(
                JSONTimeResource.`object`(
                    "variants", JSONTimeResource.listOf(
                        JSONTimeResource.`object`(
                            "facing=north", JSONTimeResource.listOf(
                                JSONTimeResource.property("model", location.toString())
                            )
                        ),
                        JSONTimeResource.`object`(
                            "facing=south", JSONTimeResource.listOf(
                                JSONTimeResource.property("model", location.toString()),
                                JSONTimeResource.property("y", 180)
                            )
                        ),
                        JSONTimeResource.`object`(
                            "facing=west", JSONTimeResource.listOf(
                                JSONTimeResource.property("model", location.toString()),
                                JSONTimeResource.property("y", 270)
                            )
                        ),
                        JSONTimeResource.`object`(
                            "facing=east", JSONTimeResource.listOf(
                                JSONTimeResource.property("model", location.toString()),
                                JSONTimeResource.property("y", 90)
                            )
                        )
                    )
                )
            )
        )
        return BlockStateResource.fromJson(json)
    }

    @JvmStatic
    fun rotatablePillarState(location: BlockModelLocation): BlockStateResource {
        val json = JSONTimeResource.`object`(
            null, JSONTimeResource.listOf(
                JSONTimeResource.`object`(
                    "variants", JSONTimeResource.listOf(
                        JSONTimeResource.`object`(
                            "axis=y", JSONTimeResource.listOf(
                                JSONTimeResource.property("model", location.toString())
                            )
                        ),
                        JSONTimeResource.`object`(
                            "axis=z", JSONTimeResource.listOf(
                                JSONTimeResource.property("model", location.toString()),
                                JSONTimeResource.property("x", 90)
                            )
                        ),
                        JSONTimeResource.`object`(
                            "axis=x", JSONTimeResource.listOf(
                                JSONTimeResource.property("model", location.toString()),
                                JSONTimeResource.property("x", 90),
                                JSONTimeResource.property("y", 90)
                            )
                        )
                    )
                )
            )
        )
        return BlockStateResource.fromJson(json)
    }

    @JvmStatic
    fun doublePlantState(bottom: BlockModelLocation, top: BlockModelLocation): BlockStateResource {
        val json = JSONTimeResource.`object`(
            null, JSONTimeResource.listOf(
                JSONTimeResource.`object`(
                    "variants", JSONTimeResource.listOf(
                        JSONTimeResource.`object`(
                            "half=lower", JSONTimeResource.listOf(
                                JSONTimeResource.property("model", bottom.toString())
                            )
                        ),
                        JSONTimeResource.`object`(
                            "half=upper", JSONTimeResource.listOf(
                                JSONTimeResource.property("model", top.toString())
                            )
                        )
                    )
                )
            )
        )
        return BlockStateResource.fromJson(json)
    }
}