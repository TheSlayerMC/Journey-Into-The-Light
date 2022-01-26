package net.jitl.client.render.vehicle;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.vehicle.JBoatModel;
import net.jitl.common.entity.vehicle.JBoat;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.stream.Stream;

@OnlyIn(Dist.CLIENT)
public class JBoatRenderer extends EntityRenderer<JBoat> {

    private final Map<JBoat.Type, Pair<ResourceLocation, JBoatModel>> boatResources;

    public JBoatRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.8F;
        this.boatResources = Stream.of(JBoat.Type.values()).collect(ImmutableMap.toImmutableMap(
                (type) -> type,
                (JBoatType) -> Pair.of(JITL.rl("textures/entity/boat/" + JBoatType.getName() + ".png"), new JBoatModel(context.bakeLayer(JModelLayers.createBoatModelName(JBoatType))))));
    }

    @Override
    public void render(JBoat entity, float entityYaw, float partialTicks_, PoseStack matrixStack, @NotNull MultiBufferSource buffer, int packedLight) {
        matrixStack.pushPose();
        matrixStack.translate(0.0D, 0.375D, 0.0D);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
        float f = (float)entity.getHurtTime() - partialTicks_;
        float f1 = entity.getDamage() - partialTicks_;
        if(f1 < 0.0F)
            f1 = 0.0F;

        if(f > 0.0F)
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)entity.getHurtDir()));

        float f2 = entity.getBubbleAngle(partialTicks_);
        if(!Mth.equal(f2, 0.0F))
            matrixStack.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), entity.getBubbleAngle(partialTicks_), true));

        Pair<ResourceLocation, JBoatModel> pair = getModelWithLocation(entity);
        ResourceLocation resourcelocation = pair.getFirst();
        JBoatModel JBoatmodel = pair.getSecond();
        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        JBoatmodel.setupAnim(entity, partialTicks_, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = buffer.getBuffer(JBoatmodel.renderType(resourcelocation));
        JBoatmodel.renderToBuffer(matrixStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!entity.isUnderWater()) {
            VertexConsumer v = buffer.getBuffer(RenderType.waterMask());
            JBoatmodel.waterPatch().render(matrixStack, v, packedLight, OverlayTexture.NO_OVERLAY);
        }
        matrixStack.popPose();
        super.render(entity, entityYaw, partialTicks_, matrixStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull JBoat entity) {
        return getModelWithLocation(entity).getFirst();
    }

    public Pair<ResourceLocation, JBoatModel> getModelWithLocation(JBoat JBoat) {
        return this.boatResources.get(JBoat.getJBoatType());
    }
}
