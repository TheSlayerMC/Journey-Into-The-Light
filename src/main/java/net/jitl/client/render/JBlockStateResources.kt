package net.jitl.client.render

import net.jitl.common.block.base.XZFacedBlock
import ru.timeconqueror.timecore.api.client.resource.BlockStateResource
import ru.timeconqueror.timecore.api.client.resource.JSONTimeResource
import ru.timeconqueror.timecore.api.client.resource.location.BlockModelLocation
import ru.timeconqueror.timecore.api.util.json
import ru.timeconqueror.timecore.api.util.x
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
    fun orientableStateAllSides(modelLocation: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                "facing=north" {
                    "model" set modelLocation.toString()
                    x = 90
                }
                "facing=south" {
                    "model" set modelLocation.toString()
                    x = -90
                }
                "facing=east" {
                    "model" set modelLocation.toString()
                    y = 90
                    x = 90
                }
                "facing=west" {
                    "model" set modelLocation.toString()
                    y = 270
                    x = 90
                }
                "facing=up" { "model" set modelLocation.toString() }
                "facing=down" {
                    "model" set modelLocation.toString()
                    x = 180
                }
            }
        })
    }

    @JvmStatic
    fun campfireState(modelLocationOn: BlockModelLocation, modelLocationOff: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                "facing=east,lit=false" {
                    "model" set modelLocationOff.toString()
                    y = 270
                }
                "facing=east,lit=true" {
                    "model" set modelLocationOn.toString()
                    y = 270
                }
                "facing=north,lit=false" {
                    "model" set modelLocationOff.toString()
                    y = 180
                }
                "facing=north,lit=true" {
                    "model" set modelLocationOn.toString()
                    y = 180
                }
                "facing=south,lit=false" {
                    "model" set modelLocationOff.toString()
                }
                "facing=south,lit=true" {
                    "model" set modelLocationOn.toString()
                }
                "facing=west,lit=false" {
                    "model" set modelLocationOff.toString()
                    y = 90
                }
                "facing=west,lit=true" {
                    "model" set modelLocationOn.toString()
                    y = 90
                }
            }
        })
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