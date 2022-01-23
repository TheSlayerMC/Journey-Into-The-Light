package net.jitl.client.render

import net.jitl.core.JITL
import ru.timeconqueror.timecore.api.client.resource.BlockModel
import ru.timeconqueror.timecore.api.client.resource.BlockModels
import ru.timeconqueror.timecore.api.client.resource.location.TextureLocation
import ru.timeconqueror.timecore.api.util.json

object JBlockModels {
    @JvmStatic
    fun emissive(normal: BlockModel?, emissive: BlockModel?): BlockModel {
        return emissiveModel(normal, emissive)
    }

    @JvmStatic
    fun emissiveCubeAll(normalTexture: TextureLocation, emissiveTexture: TextureLocation): BlockModel {
        return emissive(BlockModels.cubeAllModel(normalTexture), BlockModels.cubeAllModel(emissiveTexture))
    }

    @JvmStatic
    fun emissiveModel(normal: BlockModel?, emissive: BlockModel?): BlockModel {
        return BlockModel(json {
            "parent" set "block/block"
            "loader" set JITL.MODID + ":emissive"
            "particle" set "#normal"

            if (normal != null) {
                "normal" setRaw normal.toJson()
            }

            if (emissive != null) {
                "emissive" setRaw emissive.toJson()
            }
        })
    }

    @JvmStatic
    fun empty(): BlockModel {
        return BlockModel(json { })
    }

    @JvmStatic
    fun campfireOn(texture: TextureLocation, fireTexture: TextureLocation) = BlockModel(json {
        "parent" set "block/template_campfire"
        "textures" {
            "fire" set fireTexture.toString()
            "lit_log" set texture.toString()
        }
    })

    @JvmStatic
    fun campfireOff(texture: TextureLocation) = BlockModel(json {
        "parent" set "block/template_campfire"
        "textures" {
            "fire" set "jitl:block/empty"
            "lit_log" set texture.toString()
        }
    })


    @JvmStatic
    fun slab(texture: TextureLocation) = BlockModel(json {
        "parent" set "block/slab"
        "textures" {
            "top" set texture.toString()
            "bottom" set texture.toString()
            "side" set texture.toString()
        }
    })

    @JvmStatic
    fun slabTop(texture: TextureLocation) = BlockModel(json {
        "parent" set "block/slab_top"
        "textures" {
            "top" set texture.toString()
            "bottom" set texture.toString()
            "side" set texture.toString()
        }
    })

    @JvmStatic
    fun gate(tex: TextureLocation) = BlockModel(json {
        "parent" set "block/template_fence_gate"
        "textures" {
            "texture" set tex.toString()
        }
    })

    @JvmStatic
    fun fencePost(tex: TextureLocation) = BlockModel(json {
        "parent" set "minecraft:block/fence_post"
        "textures" {
            "texture" set tex.toString()
        }
    })

    @JvmStatic
    fun fenceSide(tex: TextureLocation) = BlockModel(json {
        "parent" set "minecraft:block/fence_side"
        "textures" {
            "texture" set tex.toString()
        }
    })

    @JvmStatic
    fun gateOpen(tex: TextureLocation) = BlockModel(json {
        "parent" set "block/template_fence_gate_open"
        "textures" {
            "texture" set tex.toString()
        }
    })

    @JvmStatic
    fun gateWall(tex: TextureLocation) = BlockModel(json {
        "parent" set "block/template_fence_gate_wall"
        "textures" {
            "texture" set tex.toString()
        }
    })

    @JvmStatic
    fun gateWallOpen(tex: TextureLocation) = BlockModel(json {
        "parent" set "block/template_fence_gate_wall_open"
        "textures" {
            "texture" set tex.toString()
        }
    })

    @JvmStatic
    fun doorTop(top: TextureLocation, bottom: TextureLocation) = BlockModel(json {
        "parent" set "block/door_top"
        "textures" {
            "top" set top.toString()
            "bottom" set bottom.toString()
        }
    })

    @JvmStatic
    fun doorBottom(top: TextureLocation, bottom: TextureLocation) = BlockModel(json {
        "parent" set "block/door_bottom"
        "textures" {
            "top" set top.toString()
            "bottom" set bottom.toString()
        }
    })

    @JvmStatic
    fun doorTopHinge(top: TextureLocation, bottom: TextureLocation) = BlockModel(json {
        "parent" set "block/door_top_rh"
        "textures" {
            "top" set top.toString()
            "bottom" set bottom.toString()
        }
    })

    @JvmStatic
    fun doorBottomHinge(top: TextureLocation, bottom: TextureLocation) = BlockModel(json {
        "parent" set "block/door_bottom_rh"
        "textures" {
            "top" set top.toString()
            "bottom" set bottom.toString()
        }
    })

    @JvmStatic
    fun button(texture: TextureLocation) = BlockModel(json {
        "parent" set "block/button"
        "textures" {
            "texture" set texture.toString()
        }
    })

    @JvmStatic
    fun buttonPressed(texture: TextureLocation) = BlockModel(json {
        "parent" set "block/button_pressed"
        "textures" {
            "texture" set texture.toString()
        }
    })

    @JvmStatic
    fun buttonInventory(texture: TextureLocation) = BlockModel(json {
        "parent" set "block/button_inventory"
        "textures" {
            "texture" set texture.toString()
        }
    })

    @JvmStatic
    fun trapDoorTop(texture: TextureLocation) = BlockModel(json {
        "parent" set "block/template_trapdoor_top"
        "textures" {
            "texture" set texture.toString()
        }
    })

    @JvmStatic
    fun pressurePlate(texture: TextureLocation) = BlockModel(json {
        "parent" set "block/pressure_plate_up"
        "textures" {
            "texture" set texture.toString()
        }
    })

    @JvmStatic
    fun pressurePlateDown(texture: TextureLocation) = BlockModel(json {
        "parent" set "block/pressure_plate_down"
        "textures" {
            "texture" set texture.toString()
        }
    })

    @JvmStatic
    fun trapDoorBottom(texture: TextureLocation) = BlockModel(json {
        "parent" set "block/template_trapdoor_bottom"
        "textures" {
            "texture" set texture.toString()
        }
    })

    @JvmStatic
    fun trapDoorOpen(texture: TextureLocation) = BlockModel(json {
        "parent" set "block/template_trapdoor_open"
        "textures" {
            "texture" set texture.toString()
        }
    })

    @JvmStatic
    fun crop(textureCrop: TextureLocation) = BlockModel(json {
        "parent" set "block/crop"
        "textures" {
            "crop" set textureCrop.toString()
        }
    })

    @JvmStatic
    fun portalEW(texture: TextureLocation) = BlockModel(json {
        "textures" {
            "particle" set texture.toString()
            "texture" set texture.toString()
        }
        "elements"[
                {
                    //"from" [
                    //]
                }
        ]
    })
}