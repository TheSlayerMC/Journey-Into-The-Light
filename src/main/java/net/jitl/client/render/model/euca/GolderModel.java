package net.jitl.client.render.model.euca;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jitl.common.entity.euca.GolderEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class GolderModel<T extends GolderEntity> extends EntityModel<T> {

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart back_left_leg;
    private final ModelPart back_right_leg;
    private final ModelPart front_right_leg;
    private final ModelPart front_left_leg;

    public GolderModel(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.back_left_leg = root.getChild("back_left_leg");
        this.back_right_leg = root.getChild("back_right_leg");
        this.front_right_leg = root.getChild("front_right_leg");
        this.front_left_leg = root.getChild("front_left_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, -4.0F));
        PartDefinition spike_r1 = head.addOrReplaceChild("spike_r1", CubeListBuilder.create().texOffs(0, 54).addBox(0.0F, -0.5F, -4.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.5F, -7.0F, 0.7418F, 0.0F, 0.0F));
        PartDefinition jaw_r1 = head.addOrReplaceChild("jaw_r1", CubeListBuilder.create().texOffs(32, 6).addBox(-8.01F, -8.0F, -2.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 12.0F, -3.0F, 0.4363F, 0.0F, 0.0F));
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 25.0F, 0.0F));
        PartDefinition s3_r1 = body.addOrReplaceChild("s3_r1", CubeListBuilder.create().texOffs(18, 45).addBox(-1.0F, -2.0F, 6.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(18, 45).addBox(-1.01F, -3.0F, 1.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(18, 45).addBox(-1.0F, -4.0F, -3.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5164F, -1.1285F, -0.1571F, 0.0F, 0.0F));
        PartDefinition b_r1 = body.addOrReplaceChild("b_r1", CubeListBuilder.create().texOffs(28, 18).addBox(-5.0F, -8.0F, 7.0F, 10.0F, 17.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.3963F, 0.0F, 0.0F));
        PartDefinition back_left_leg = partdefinition.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(0, 34).addBox(-2.0F, -1.0F, -2.25F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-2.0F, 3.0F, -5.25F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 19.0F, 7.25F));
        PartDefinition back_right_leg = partdefinition.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(0, 34).addBox(-2.0F, -1.0F, -2.25F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-2.0F, 3.0F, -5.25F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 19.0F, 7.25F));
        PartDefinition front_right_leg = partdefinition.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(0, 20).addBox(-2.0F, 0.0F, -0.25F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-2.0F, 6.0F, -3.25F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 16.0F, -4.75F));
        PartDefinition front_left_leg = partdefinition.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(0, 20).addBox(-2.0F, 0.0F, -2.25F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-2.0F, 6.0F, -5.25F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 16.0F, -2.75F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = headPitch / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);
        this.back_right_leg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.back_left_leg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.front_right_leg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.front_left_leg.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, buffer, packedLight, packedOverlay);
        body.render(poseStack, buffer, packedLight, packedOverlay);
        back_left_leg.render(poseStack, buffer, packedLight, packedOverlay);
        back_right_leg.render(poseStack, buffer, packedLight, packedOverlay);
        front_right_leg.render(poseStack, buffer, packedLight, packedOverlay);
        front_left_leg.render(poseStack, buffer, packedLight, packedOverlay);
    }
}