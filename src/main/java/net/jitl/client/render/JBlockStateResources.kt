package net.jitl.client.render

import net.jitl.common.block.base.XZFacedBlock
import ru.timeconqueror.timecore.api.client.resource.BlockStateResource
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
                "axis=z" {
                    "model" set modelLocation.toString()
                }
                "axis=x" {
                    "model" set modelLocation.toString()
                    y = 90
                }
            }
        })
    }

    @JvmStatic
    fun orientableState(location: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                "facing=north" {
                    "model" set location
                }
                "facing=south" {
                    "model" set location
                    y = 180
                }
                "facing=west"{
                    "model" set location
                    y = 270
                }
                "facing=east" {
                    "model" set location
                    y = 90
                }
            }
        })
    }

    @JvmStatic
    fun basicPortalState(locationEW: BlockModelLocation, locationNS: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                "axis=x" {
                    "model" set locationNS
                }
                "axis=z" {
                    "model" set locationEW
                }
            }
        })
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
                    "model" set modelLocationOff
                    y = 270
                }
                "facing=east,lit=true" {
                    "model" set modelLocationOn
                    y = 270
                }
                "facing=north,lit=false" {
                    "model" set modelLocationOff
                    y = 180
                }
                "facing=north,lit=true" {
                    "model" set modelLocationOn
                    y = 180
                }
                "facing=south,lit=false" {
                    "model" set modelLocationOff
                }
                "facing=south,lit=true" {
                    "model" set modelLocationOn
                }
                "facing=west,lit=false" {
                    "model" set modelLocationOff
                    y = 90
                }
                "facing=west,lit=true" {
                    "model" set modelLocationOn
                    y = 90
                }
            }
        })
    }

    @JvmStatic
    fun rotatablePillarState(location: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                "axis=y" {
                    "model" set location
                }
                "axis=z" {
                    "model" set location
                    x = 90
                }
                "axis=x" {
                    "model" set location
                    x = 90
                    y = 90
                }
            }
        })
    }

    @JvmStatic
    fun doublePlantState(bottom: BlockModelLocation, top: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                "half=lower" {
                    "model" set bottom
                }
                "half=upper" {
                    "model" set top
                }
            }
        })
    }

    @JvmStatic
    fun randomizedRotated(model: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                ""[
                        {
                            "model" set model
                        },
                        {
                            "model" set model
                            y = 90
                        },
                        {
                            "model" set model
                            y = 180
                        },
                        {
                            "model" set model
                            y = 270
                        }
                ]
            }
        })
    }
}