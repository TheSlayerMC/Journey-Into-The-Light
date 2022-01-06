package net.jitl.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.common.entity.projectile.PiercerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.mojang.math.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class PiercerRenderer extends EntityRenderer<PiercerEntity> {
    private final ItemEntityRenderer itemRenderer;
    private final Random random = new Random();

    public PiercerRenderer(EntityRenderDispatcher renderManagerIn, net.minecraft.client.renderer.entity.ItemRenderer itemRendererIn) {
        super(renderManagerIn);
        this.itemRenderer = itemRendererIn;
        this.shadowRadius = 0.15F;
        this.shadowStrength = 0.75F;
    }

    @Override
    public void render(PiercerEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        ItemStack itemstack = entityIn.getItem();
        int i = itemstack.isEmpty() ? 187 : Item.getId(itemstack.getItem()) + itemstack.getDamageValue();
        this.random.setSeed(i);
        BakedModel ibakedmodel = this.itemRenderer.getModel(itemstack, entityIn.level, null);

        float f1 = ((float) entityIn.tickCount + partialTicks) / 10 * (float) (Math.PI * 2.0D);

        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.yRot) - 90.0F));
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entityIn.xRotO, entityIn.xRot) + 90.0F));

        if (!entityIn.isInGround()) {
            matrixStackIn.mulPose(Vector3f.ZP.rotation(f1));
        }
        matrixStackIn.pushPose();

        this.itemRenderer.render(itemstack, ItemTransforms.TransformType.GROUND, false, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, ibakedmodel);
        matrixStackIn.popPose();
        matrixStackIn.translate(0.0, 0.0, 0.09375F);

        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(PiercerEntity entityIn) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
