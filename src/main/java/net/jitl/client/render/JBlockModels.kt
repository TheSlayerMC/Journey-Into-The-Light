package net.jitl.client.render

import net.jitl.JITL
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
}