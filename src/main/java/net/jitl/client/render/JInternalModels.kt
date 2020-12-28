package net.jitl.client.render

import net.jitl.JITL
import ru.timeconqueror.timecore.api.client.resource.BlockModel
import ru.timeconqueror.timecore.api.util.json

object JInternalModels {
    @JvmStatic
    fun emissiveModel(normal: BlockModel?, emissive: BlockModel?): BlockModel {
        val json = json {
            "parent" set "block/block"
            "loader" set JITL.MODID + ":emissive"
            "particle" set "#normal"

            if (normal != null) {
                "normal" setRaw normal.toJson()
            }

            if (emissive != null) {
                "emissive" setRaw emissive.toJson()
            }
        }

        return BlockModel(json)
    }
}