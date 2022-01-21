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