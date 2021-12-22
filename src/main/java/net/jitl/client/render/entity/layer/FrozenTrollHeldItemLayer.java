package net.jitl.client.render.entity.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.jitl.common.entity.base.JEntityAction;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FrozenTrollHeldItemLayer<T extends LivingEntity, M extends EntityModel<T> & IHasArm> extends LayerRenderer<T, M> {
    public FrozenTrollHeldItemLayer(IEntityRenderer<T, M> entityRenderer_) {
        super(entityRenderer_);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = entitylivingbaseIn.getMainArm() == HandSide.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.getOffhandItem() : entitylivingbaseIn.getMainHandItem();
        ItemStack itemstack1 = flag ? entitylivingbaseIn.getMainHandItem() : entitylivingbaseIn.getOffhandItem();
        if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
            matrixStackIn.pushPose();
            if (this.getParentModel().young) {
                matrixStackIn.translate(0.0D, 0.75D, 0.0D);
                matrixStackIn.scale(0.5F, 0.5F, 0.5F);
            }

            this.renderArmWithItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT, matrixStackIn, bufferIn, packedLightIn);
            this.renderArmWithItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.popPose();
        }
    }

    private void renderArmWithItem(LivingEntity livingEntity_, ItemStack itemStack_, ItemCameraTransforms.TransformType transformType_, HandSide handSide_, MatrixStack matrixStack_, IRenderTypeBuffer renderTypeBuffer_, int int_) {
        if (!itemStack_.isEmpty()) {
            matrixStack_.pushPose();
            this.getParentModel().translateToHand(handSide_, matrixStack_);
            matrixStack_.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            if (livingEntity_ instanceof FrozenTrollEntity) {
                FrozenTrollEntity frozenTrollEntity = (FrozenTrollEntity) livingEntity_;
                JEntityAction entityAction = frozenTrollEntity.getArmPose();
                if (entityAction == JEntityAction.ADMIRING_ITEM) {
                    matrixStack_.mulPose(Vector3f.YP.rotationDegrees(195.0F));
                    matrixStack_.mulPose(Vector3f.ZP.rotationDegrees(15.0F));
                    matrixStack_.translate(0.465F, -0.2, 0.03);
                } else {
                    matrixStack_.mulPose(Vector3f.YP.rotationDegrees(180.0F));
                }
            } else {
                matrixStack_.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            }
            boolean flag = handSide_ == HandSide.LEFT;
            matrixStack_.translate((float) (flag ? -1 : 1) / 14.0F, 0.125D, -0.625D);
            Minecraft.getInstance().getItemInHandRenderer().renderItem(livingEntity_, itemStack_, transformType_, flag, matrixStack_, renderTypeBuffer_, int_);
            matrixStack_.popPose();
        }
    }
}