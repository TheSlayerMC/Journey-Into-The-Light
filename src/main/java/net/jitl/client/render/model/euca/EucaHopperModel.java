package net.jitl.client.render.model.euca;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jitl.common.entity.euca.EucaHopperEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class EucaHopperModel<T extends EucaHopperEntity> extends EntityModel<T> {

    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart front_left_leg;
    private final ModelPart front_right_leg;
    private final ModelPart back_left_leg;
    private final ModelPart back_right_leg;

    public EucaHopperModel(ModelPart root) {
        this.body = root.getChild("body");
        this.tail = root.getChild("tail");
        this.front_left_leg = root.getChild("front_left_leg");
        this.front_right_leg = root.getChild("front_right_leg");
        this.back_left_leg = root.getChild("back_left_leg");
        this.back_right_leg = root.getChild("back_right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -9.0F, -4.0F, 6.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition spike2_r1 = body.addOrReplaceChild("spike2_r1", CubeListBuilder.create().texOffs(1, 15).addBox(0.0F, -1.5F, -0.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -10.5F, -2.0F, -0.6545F, 0.0F, 0.0F));
        PartDefinition spike1_r1 = body.addOrReplaceChild("spike1_r1", CubeListBuilder.create().texOffs(1, 15).addBox(-0.5F, -2.1686F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5814F, -0.321F, -0.9599F, 0.0F, 0.0F));
        PartDefinition horn2_r1 = body.addOrReplaceChild("horn2_r1", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.9497F, -9.1924F, -1.0F, 0.0F, 0.0F, 0.7854F));
        PartDefinition horn1_r1 = body.addOrReplaceChild("horn1_r1", CubeListBuilder.create().texOffs(0, 22).addBox(-5.0F, -5.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8284F, -8.4853F, -1.0F, 0.0F, 0.0F, -0.7854F));
        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 26).addBox(-1.5F, -1.0F, -0.5F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 3.0F));
        PartDefinition front_left_leg = partdefinition.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 20.0F, -2.5F));
        PartDefinition front_right_leg = partdefinition.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 20.0F, -2.5F));
        PartDefinition back_left_leg = partdefinition.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 20.0F, 1.5F));
        PartDefinition back_right_leg = partdefinition.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 20.0F, 1.5F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, buffer, packedLight, packedOverlay);
        tail.render(poseStack, buffer, packedLight, packedOverlay);
        front_left_leg.render(poseStack, buffer, packedLight, packedOverlay);
        front_right_leg.render(poseStack, buffer, packedLight, packedOverlay);
        back_left_leg.render(poseStack, buffer, packedLight, packedOverlay);
        back_right_leg.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(EucaHopperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.front_left_leg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.front_right_leg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.back_left_leg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.back_right_leg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        if(entity.isTame() && entity.isInSittingPose()) {
            this.tail.xRot = -0.5F;
        }
    }
}
