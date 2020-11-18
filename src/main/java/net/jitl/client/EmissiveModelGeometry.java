package net.jitl.client;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import kotlin.collections.ArraysKt;
import net.jitl.mixins.UnlockedBlockModelDeserializer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.MissingTextureSprite;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.CompositeModel;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.geometry.IModelGeometry;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.util.RandHelper;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class EmissiveModelGeometry implements IModelGeometry<EmissiveModelGeometry> {
    public static final int FULLBRIGHT = LightTexture.pack(15, 15);
    public static final String VANILLA_PARTICLE_NAME = "particle";
    public static final String PARTICLE_PROP_NAME = VANILLA_PARTICLE_NAME;

    @Nullable
    private final BlockModel emissiveModel;
    @Nullable
    private final BlockModel normalModel;
    private final BiFunction<EmissiveModelGeometry, IModelConfiguration, RenderMaterial> particleMaterialSupplier;

    public EmissiveModelGeometry(@Nullable BlockModel emissiveModel, @Nullable BlockModel normalModel, BiFunction<EmissiveModelGeometry, IModelConfiguration, RenderMaterial> particleMaterialSupplier) {
        this.emissiveModel = emissiveModel;
        this.normalModel = normalModel;
        this.particleMaterialSupplier = particleMaterialSupplier;
    }

    @Override
    public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation) {
        ImmutableMap.Builder<String, IBakedModel> builder = new ImmutableMap.Builder<>();

        RenderMaterial particleLocation = particleMaterialSupplier.apply(this, owner);
        TextureAtlasSprite particleSprite = spriteGetter.apply(particleLocation);

        if (normalModel != null) {
            IBakedModel bakedNormal = normalModel.bake(bakery, spriteGetter, modelTransform, modelLocation);
            if (bakedNormal != null) builder.put(ModelType.NORMAL.getName(), bakedNormal);
        }

        if (emissiveModel != null) {
            IBakedModel bakedEmissive = emissiveModel.bake(bakery, spriteGetter, modelTransform, modelLocation);
            if (bakedEmissive instanceof SimpleBakedModel) {
                List<BakedQuad> quads = bakedEmissive.getQuads(null, null, RandHelper.RAND, EmptyModelData.INSTANCE);
                quads = lightQuads(quads);

                Map<Direction, List<BakedQuad>> faceQuads = new EnumMap<>(Direction.class);

                for (Direction dir : Direction.values()) {
                    faceQuads.put(dir, lightQuads(bakedEmissive.getQuads(null, dir, RandHelper.RAND, EmptyModelData.INSTANCE)));
                }

                bakedEmissive = new SimpleBakedModel(quads,
                        faceQuads,
                        bakedEmissive.useAmbientOcclusion(),
                        bakedEmissive.usesBlockLight(),
                        bakedEmissive.isGui3d(),
                        bakedEmissive.getParticleIcon(),
                        bakedEmissive.getTransforms(),
                        bakedEmissive.getOverrides());

                builder.put(ModelType.EMISSIVE.getName(), bakedEmissive);
            } else if (bakedEmissive != null) {
                throw new UnsupportedOperationException("Emissive Model Geometry can't handle " + emissiveModel.getClass());
            }
        }

        return new CompositeModel(owner.isShadedInGui(), owner.isSideLit(), owner.useSmoothLighting(), particleSprite, builder.build(), owner.getCombinedTransform(), overrides);
    }

    private static List<BakedQuad> lightQuads(List<BakedQuad> oldQuads) {
        List<BakedQuad> newQuads = new ArrayList<>(oldQuads);

        int quadCount = newQuads.size();
        for (int i = 0; i < quadCount; i++) {
            BakedQuad oldQuad = newQuads.get(i);
            newQuads.set(i, lightQuad(oldQuad));
        }

        return newQuads;
    }

    private static BakedQuad lightQuad(BakedQuad quad) {
        int[] vertexData = quad.getVertices().clone();

        // set lighting to fullbright on all vertices
        vertexData[6] = FULLBRIGHT;
        vertexData[6 + 8] = FULLBRIGHT;
        vertexData[6 + 8 + 8] = FULLBRIGHT;
        vertexData[6 + 8 + 8 + 8] = FULLBRIGHT;

        return new BakedQuad(vertexData, quad.getTintIndex(), quad.getDirection(), quad.getSprite(), quad.isShade());
    }

    @Override
    public Collection<RenderMaterial> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        Set<RenderMaterial> materials = new HashSet<>();

        materials.add(particleMaterialSupplier.apply(this, owner));

        if (emissiveModel != null) {
            materials.addAll(emissiveModel.getMaterials(modelGetter, missingTextureErrors));
        }
        if (normalModel != null) {
            materials.addAll(normalModel.getMaterials(modelGetter, missingTextureErrors));
        }

        return materials;
    }

    public static class Loader implements IModelLoader<EmissiveModelGeometry> {
        public static final Loader INSTANCE = new Loader();

        private Loader() {
        }

        @Override
        public void onResourceManagerReload(IResourceManager resourceManager) {

        }

        @Override
        public EmissiveModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
            BlockModel emissiveModel = null;
            BlockModel normalModel = null;

            if (modelContents.has(ModelType.EMISSIVE.getName())) {
                emissiveModel = deserializationContext.deserialize(modelContents.getAsJsonObject(ModelType.EMISSIVE.getName()), BlockModel.class);
            }
            if (modelContents.has(ModelType.NORMAL.getName())) {
                normalModel = deserializationContext.deserialize(modelContents.getAsJsonObject(ModelType.NORMAL.getName()), BlockModel.class);
            }

            if (!modelContents.has(PARTICLE_PROP_NAME)) {
                throw new JsonParseException("Emissive Model Loader requires '" + PARTICLE_PROP_NAME + "' property.");
            }

            String particlesProp = modelContents.get(PARTICLE_PROP_NAME).getAsString();

            BiFunction<EmissiveModelGeometry, IModelConfiguration, RenderMaterial> particlesSupplier;
            if (particlesProp.startsWith("#")) {
                ModelType modelType = ModelType.fromName(particlesProp.substring(1));
                if (modelType == null)
                    throw new JsonParseException("Unknown import from 'particle' property: " + particlesProp.substring(1) + ". Allowed: " + ModelType.names());

                particlesSupplier = createParticleRenderMaterialSupplier(modelType);
            } else {
                particlesSupplier = (geometry, configuration) -> {
                    Either<RenderMaterial, String> textureLocation = UnlockedBlockModelDeserializer.parseTextureLocationOrReference(AtlasTexture.LOCATION_BLOCKS, particlesProp);

                    if (textureLocation.left().isPresent()) {
                        return textureLocation.left().get();
                    } else {
                        return new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, MissingTextureSprite.getLocation());
                    }
                };
            }

            return new EmissiveModelGeometry(emissiveModel, normalModel, particlesSupplier);
        }
    }

    private static BiFunction<EmissiveModelGeometry, IModelConfiguration, RenderMaterial> createParticleRenderMaterialSupplier(ModelType type) {
        return (geometry, configuration) -> {
            BlockModel model;

            if (type == ModelType.NORMAL) {
                model = geometry.normalModel;
            } else if (type == ModelType.EMISSIVE) {
                model = geometry.emissiveModel;
            } else {
                throw new UnsupportedOperationException();
            }

            if (model == null) {
                throw new RuntimeException("Can't handle import from 'particle' property: " + type.getName() + " because the requested model is null.");
            }

            return model.customData.resolveTexture(VANILLA_PARTICLE_NAME);
        };
    }

    public enum ModelType {
        EMISSIVE("emissive"),
        NORMAL("normal");

        private final String name;

        ModelType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Nullable
        public static ModelType fromName(String name) {
            ModelType type = null;

            for (ModelType value : values()) {
                if (value.getName().equals(name)) {
                    type = value;
                    break;
                }
            }

            return type;
        }

        public static List<String> names() {
            return ArraysKt.map(values(), ModelType::getName);
        }
    }
}
