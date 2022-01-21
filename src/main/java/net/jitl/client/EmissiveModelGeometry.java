package net.jitl.client;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import kotlin.collections.ArraysKt;
import net.jitl.core.mixins.client.UnlockedBlockModelDeserializer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
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

/**
 * The highest "parent" property is for items, because for now they have weird behavior without this property.
 * //TODO Maybe add built-in support, so you don't need to specify parent at all, why not just use "block/block"?
 */
public class EmissiveModelGeometry implements IModelGeometry<EmissiveModelGeometry> {
    public static final int FULLBRIGHT = LightTexture.pack(15, 15);
    public static final String VANILLA_PARTICLE_NAME = "particle";
    public static final String PARTICLE_PROP_NAME = VANILLA_PARTICLE_NAME;

    @Nullable
    private final BlockModel emissiveModel;
    @Nullable
    private final BlockModel normalModel;
    private final BiFunction<EmissiveModelGeometry, IModelConfiguration, Material> particleMaterialSupplier;

    public EmissiveModelGeometry(@Nullable BlockModel emissiveModel, @Nullable BlockModel normalModel, BiFunction<EmissiveModelGeometry, IModelConfiguration, Material> particleMaterialSupplier) {
        this.emissiveModel = emissiveModel;
        this.normalModel = normalModel;
        this.particleMaterialSupplier = particleMaterialSupplier;
    }

    @Override
    public BakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation) {
        ImmutableMap.Builder<String, BakedModel> builder = new ImmutableMap.Builder<>();

        Material particleLocation = particleMaterialSupplier.apply(this, owner);
        TextureAtlasSprite particleSprite = spriteGetter.apply(particleLocation);

        if (normalModel != null) {
            BakedModel bakedNormal = normalModel.bake(bakery, spriteGetter, modelTransform, modelLocation);
            if (bakedNormal != null) builder.put(ModelType.NORMAL.getName(), bakedNormal);
        }

        if (emissiveModel != null) {
            BakedModel bakedEmissive = emissiveModel.bake(bakery, spriteGetter, modelTransform, modelLocation);
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
    public Collection<Material> getTextures(IModelConfiguration owner, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        Set<Material> materials = new HashSet<>();

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
        public void onResourceManagerReload(ResourceManager resourceManager) {

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

            BiFunction<EmissiveModelGeometry, IModelConfiguration, Material> particlesSupplier;
            if (particlesProp.startsWith("#")) {
                ModelType modelType = ModelType.fromName(particlesProp.substring(1));
                if (modelType == null)
                    throw new JsonParseException("Unknown import from 'particle' property: " + particlesProp.substring(1) + ". Allowed: " + ModelType.names());

                particlesSupplier = createParticleRenderMaterialSupplier(modelType);
            } else {
                particlesSupplier = (geometry, configuration) -> {
                    Either<Material, String> textureLocation = UnlockedBlockModelDeserializer.parseTextureLocationOrReference(TextureAtlas.LOCATION_BLOCKS, particlesProp);

                    if (textureLocation.left().isPresent()) {
                        return textureLocation.left().get();
                    } else {
                        return new Material(TextureAtlas.LOCATION_BLOCKS, MissingTextureAtlasSprite.getLocation());
                    }
                };
            }

            return new EmissiveModelGeometry(emissiveModel, normalModel, particlesSupplier);
        }
    }

    private static BiFunction<EmissiveModelGeometry, IModelConfiguration, Material> createParticleRenderMaterialSupplier(ModelType type) {
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
