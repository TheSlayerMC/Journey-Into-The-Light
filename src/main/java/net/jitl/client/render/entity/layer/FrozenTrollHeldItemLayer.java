package net.jitl.client.render.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.common.entity.base.JEntityAction;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.HumanoidArm;
import com.mojang.math.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import EntityModel;
import LivingEntity;

@OnlyIn(Dist.CLIENT)
public class FrozenTrollHeldItemLayer<T extends LivingEntity, M extends EntityModel<T> & ArmedModel> extends RenderLayer<T, M> {
    public FrozenTrollHeldItemLayer(RenderLayerParent<T, M> entityRenderer_) {
        super(entityRenderer_);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = entitylivingbaseIn.getMainArm() == HumanoidArm.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.getOffhandItem() : entitylivingbaseIn.getMainHandItem();
        ItemStack itemstack1 = flag ? entitylivingbaseIn.getMainHandItem() : entitylivingbaseIn.getOffhandItem();
        if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
            matrixStackIn.pushPose();
            if (this.getParentModel().young) {
                matrixStackIn.translate(0.0D, 0.75D, 0.0D);
                matrixStackIn.scale(0.5F, 0.5F, 0.5F);
            }

            this.renderArmWithItem(entitylivingbaseIn, itemstack1, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, matrixStackIn, bufferIn, packedLightIn);
            this.renderArmWithItem(entitylivingbaseIn, itemstack, ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.popPose();
        }
    }

    private void renderArmWithItem(LivingEntity livingEntity_, ItemStack itemStack_, ItemTransforms.TransformType transformType_, HumanoidArm handSide_, PoseStack matrixStack_, MultiBufferSource renderTypeBuffer_, int int_) {
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
            boolean flag = handSide_ == HumanoidArm.LEFT;
            matrixStack_.translate((float) (flag ? -1 : 1) / 14.0F, 0.125D, -0.625D);
            Minecraft.getInstance().getItemInHandRenderer().renderItem(livingEntity_, itemStack_, transformType_, flag, matrixStack_, renderTypeBuffer_, int_);
            matrixStack_.popPose();
        }
    }
}