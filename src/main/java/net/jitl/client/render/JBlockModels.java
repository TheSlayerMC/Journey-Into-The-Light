package net.jitl.client.render;

import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.client.resource.BlockModel;
import ru.timeconqueror.timecore.api.client.resource.BlockModels;
import ru.timeconqueror.timecore.api.client.resource.location.TextureLocation;

import static ru.timeconqueror.timecore.api.client.resource.JSONTimeResource.*;

public class JBlockModels extends BlockModels {

    public static BlockModel cubeOrientableModel(TextureLocation endTexture, TextureLocation sideTexture, TextureLocation frontTexture) {
        String json =
                object(null, listOf(
                        property("parent", "block/orientable"),
                        object("textures", listOf(
                                property("top", endTexture.toString()),
                                property("front", frontTexture.toString()),
                                property("side", sideTexture.toString())
                        ))
                        )
                );
        return new BlockModel(json);
    }

    public static BlockModel emissive(@Nullable BlockModel normal, @Nullable BlockModel emissive) {
        return JInternalModels.emissiveModel(normal, emissive);
    }

    public static BlockModel emissiveCubeAll(TextureLocation normalTexture, TextureLocation emissiveTexture) {
        return emissive(BlockModels.cubeAllModel(normalTexture), BlockModels.cubeAllModel(emissiveTexture));
    }
}
