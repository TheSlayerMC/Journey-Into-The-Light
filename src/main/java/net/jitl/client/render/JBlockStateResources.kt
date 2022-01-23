package net.jitl.client.render

import net.jitl.common.block.base.XZFacedBlock
import ru.timeconqueror.timecore.api.client.resource.BlockStateResource
import ru.timeconqueror.timecore.api.client.resource.location.BlockModelLocation
import ru.timeconqueror.timecore.api.util.json
import ru.timeconqueror.timecore.api.util.uvlock
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
    fun slabState(bottom: BlockModelLocation, doubleSlab: BlockModelLocation, top: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                "type=bottom" {
                    "model" set bottom
                }
                "type=double" {
                    "model" set doubleSlab
                }
                "type=top" {
                    "model" set top
                }
            }
        })
    }

    @JvmStatic
    fun trapDoorState(bottom: BlockModelLocation, top: BlockModelLocation, open: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                "facing=east,half=bottom,open=false" {
                    "model" set bottom
                }
                "facing=east,half=bottom,open=true" {
                    "model" set open
                    y = 90
                }
                "facing=east,half=top,open=false" {
                    "model" set top
                }
                "facing=east,half=top,open=true" {
                    "model" set open
                    y = 90
                }
                "facing=north,half=bottom,open=false" {
                    "model" set bottom
                }
                "facing=north,half=bottom,open=true" {
                    "model" set open
                }
                "facing=north,half=top,open=false" {
                    "model" set top
                }
                "facing=north,half=top,open=true" {
                    "model" set open
                }
                "facing=south,half=bottom,open=false" {
                    "model" set bottom
                }
                "facing=south,half=bottom,open=true" {
                    "model" set open
                    y = 180
                }
                "facing=south,half=top,open=false" {
                    "model" set top
                }
                "facing=south,half=top,open=true" {
                    "model" set open
                    y = 180
                }
                "facing=west,half=bottom,open=false" {
                    "model" set bottom
                }
                "facing=west,half=bottom,open=true" {
                    "model" set open
                    y = 270
                }
                "facing=west,half=top,open=false" {
                    "model" set top
                }
                "facing=west,half=top,open=true" {
                    "model" set open
                    y = 270
                }
            }
        })
    }

    @JvmStatic
    fun buttonState(button: BlockModelLocation, pressed: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                "face=ceiling,facing=east,powered=false" {
                    "model" set button
                    y = 270
                    x = 180
                }
                "face=ceiling,facing=east,powered=true" {
                    "model" set pressed
                    y = 270
                    x = 180
                }
                "face=ceiling,facing=north,powered=false" {
                    "model" set button
                    y = 180
                    x = 180
                }
                "face=ceiling,facing=north,powered=true" {
                    "model" set pressed
                    y = 180
                    x = 180
                }
                "face=ceiling,facing=south,powered=false" {
                    "model" set button
                    x = 180
                }
                "face=ceiling,facing=south,powered=true" {
                    "model" set pressed
                    x = 180
                }
                "face=ceiling,facing=west,powered=false" {
                    "model" set button
                    y = 90
                    x = 180
                }
                "face=ceiling,facing=west,powered=true" {
                    "model" set pressed
                    y = 90
                    x = 180
                }
                "face=floor,facing=east,powered=false" {
                    "model" set button
                    y = 90
                }
                "face=floor,facing=east,powered=true" {
                    "model" set pressed
                    y = 90
                }
                "face=floor,facing=north,powered=false" {
                    "model" set button
                }
                "face=floor,facing=north,powered=true" {
                    "model" set pressed
                }
                "face=floor,facing=south,powered=false" {
                    "model" set button
                    y = 180
                }
                "face=floor,facing=south,powered=true" {
                    "model" set pressed
                    y = 180
                }
                "face=floor,facing=west,powered=false" {
                    "model" set button
                    y = 270
                }
                "face=floor,facing=west,powered=true" {
                    "model" set pressed
                    y = 270
                }
                "face=wall,facing=east,powered=false" {
                    "model" set button
                    y = 90
                    x = 90
                    uvlock to true
                }
                "face=wall,facing=east,powered=true" {
                    "model" set pressed
                    y = 90
                    x = 90
                    uvlock to true
                }
                "face=wall,facing=north,powered=false" {
                    "model" set button
                    x = 90
                    uvlock to true
                }
                "face=wall,facing=north,powered=true" {
                    "model" set pressed
                    x = 90
                    uvlock to true
                }
                "face=wall,facing=south,powered=false" {
                    "model" set button
                    y = 180
                    x = 90
                    uvlock to true
                }
                "face=wall,facing=south,powered=true" {
                    "model" set pressed
                    y = 180
                    x = 90
                    uvlock to true
                }
                "face=wall,facing=west,powered=false" {
                    "model" set button
                    y = 270
                    x = 90
                    uvlock to true
                }
                "face=wall,facing=west,powered=true" {
                    "model" set pressed
                    y = 270
                    x = 90
                    uvlock to true
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
    fun randomizedRotatedTop(model: BlockModelLocation): BlockStateResource {
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

    @JvmStatic
    fun randomizedTexture(model: BlockModelLocation, model1: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                ""[
                        {
                            "model" set model
                        },
                        {
                            "model" set model1
                        }
                ]
            }
        })
    }

    @JvmStatic
    fun randomizedRotatedAll(model: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                ""[
                        {
                            "model" set model
                        },
                        {
                            "model" set model
                            x = 90
                        },
                        {
                            "model" set model
                            x = 180
                        },
                        {
                            "model" set model
                            x = 270
                        },
                        {
                            "model" set model
                            y = 90
                        },
                        {
                            "model" set model
                            y = 90
                            x = 90
                        },
                        {
                            "model" set model
                            y = 90
                            x = 180
                        },
                        {
                            "model" set model
                            y = 90
                            x = 270
                        },
                        {
                            "model" set model
                            y = 180
                        },
                        {
                            "model" set model
                            y = 180
                            x = 90
                        },
                        {
                            "model" set model
                            y = 180
                            x = 180
                        },
                        {
                            "model" set model
                            y = 180
                            x = 270
                        },
                        {
                            "model" set model
                            y = 270
                        },
                        {
                            "model" set model
                            y = 270
                            x = 90
                        },
                        {
                            "model" set model
                            y = 270
                            x = 180
                        },
                        {
                            "model" set model
                            y = 270
                            x = 270
                        }
                ]
            }
        })
    }
}