package net.jitl.client.render.model.euca;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jitl.common.entity.euca.ShimmererEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ShimmererModel<T extends ShimmererEntity> extends EntityModel<T> {

    private final ModelPart body;
    private final ModelPart top;
    private final ModelPart bottom;
    private final ModelPart left;
    private final ModelPart right;

    public ShimmererModel(ModelPart root) {
        this.body = root.getChild("body");
        this.top = root.getChild("top");
        this.bottom = root.getChild("bottom");
        this.left = root.getChild("left");
        this.right = root.getChild("right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -15.0F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition top = partdefinition.addOrReplaceChild("top", CubeListBuilder.create().texOffs(14, 14).addBox(-0.5F, -20.0F, -0.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition t2_r1 = top.addOrReplaceChild("t2_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-5.5F, -20.0F, -1.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(14, 14).addBox(-0.5F, -8.0F, -0.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition b2_r1 = bottom.addOrReplaceChild("b2_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-5.5F, -21.0F, -1.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition left = partdefinition.addOrReplaceChild("left", CubeListBuilder.create(), PartPose.offset(5.0F, 17.0F, 0.0F));

        PartDefinition l2_r1 = left.addOrReplaceChild("l2_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-5.5F, -21.5F, -4.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition l1_r1 = left.addOrReplaceChild("l1_r1", CubeListBuilder.create().texOffs(14, 14).addBox(0.0F, -3.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -5.5F, 0.5F, 0.0F, 0.0F, 1.5708F));

        PartDefinition right = partdefinition.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(-10.0F, 17.0F, 0.0F));

        PartDefinition r2_r1 = right.addOrReplaceChild("r2_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-5.5F, -21.5F, -4.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition r1_r1 = right.addOrReplaceChild("r1_r1", CubeListBuilder.create().texOffs(14, 14).addBox(0.0F, -6.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -5.5F, 0.5F, 0.0F, 0.0F, 1.5708F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, buffer, packedLight, packedOverlay);
        top.render(poseStack, buffer, packedLight, packedOverlay);
        bottom.render(poseStack, buffer, packedLight, packedOverlay);
        left.render(poseStack, buffer, packedLight, packedOverlay);
        right.render(poseStack, buffer, packedLight, packedOverlay);
    }
}