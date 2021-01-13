package net.jitl.client.render;

import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.client.resource.BlockModel;
import ru.timeconqueror.timecore.api.client.resource.BlockModels;
import ru.timeconqueror.timecore.api.client.resource.location.TextureLocation;

public class JBlockModels extends BlockModels {
    public static BlockModel emissive(@Nullable BlockModel normal, @Nullable BlockModel emissive) {
        return InternalModels.emissiveModel(normal, emissive);
    }

    public static BlockModel emissiveCubeAll(TextureLocation normalTexture, TextureLocation emissiveTexture) {
        return emissive(BlockModels.cubeAllModel(normalTexture), BlockModels.cubeAllModel(emissiveTexture));
    }
}
