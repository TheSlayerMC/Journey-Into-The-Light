package net.jitl.client.render.model.overworld;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jitl.common.entity.overworld.RockiteSmasherEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class RockiteSmasherModel<T extends RockiteSmasherEntity> extends EntityModel<T> {

    private final ModelPart body;
    private final ModelPart waist;
    private final ModelPart arm1;
    private final ModelPart arm2;
    private final ModelPart leg2;
    private final ModelPart head;
    private final ModelPart leg1;

    public RockiteSmasherModel(ModelPart root) {
        this.body = root.getChild("body");
        this.waist = root.getChild("waist");
        this.arm1 = root.getChild("arm1");
        this.arm2 = root.getChild("arm2");
        this.leg2 = root.getChild("leg2");
        this.head = root.getChild("head");
        this.leg1 = root.getChild("leg1");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 40).addBox(-9.0F, -2.0F, -6.0F, 18.0F, 14.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -7.0F, 0.5463F, 0.0F, 0.0F));
        PartDefinition crystalbig = body.addOrReplaceChild("crystalbig", CubeListBuilder.create().texOffs(0, 86).addBox(0.0F, -1.7F, 0.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, -7.7F, 4.5F, -0.4554F, 0.6829F, 0.4554F));
        PartDefinition crystalsmall3 = body.addOrReplaceChild("crystalsmall3", CubeListBuilder.create().texOffs(32, 86).addBox(0.0F, -1.7F, 0.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, -4.8F, -0.5F, 0.182F, -0.0456F, 0.8196F));
        PartDefinition crystalsmall2 = body.addOrReplaceChild("crystalsmall2", CubeListBuilder.create().texOffs(32, 86).addBox(0.0F, -1.7F, 0.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.6F, -4.7F, 1.0F, 0.0F, -0.2276F, 0.4554F));
        PartDefinition crystalsmall1 = body.addOrReplaceChild("crystalsmall1", CubeListBuilder.create().texOffs(32, 86).addBox(0.0F, -1.7F, 0.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -6.0F, 3.0F, -0.5463F, -0.7285F, 0.4554F));
        PartDefinition waist = partdefinition.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(0, 70).addBox(-4.5F, -3.0F, -3.0F, 9.0F, 5.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 13.0F, 0.0F, 0.5463F, 0.0F, 0.0F));
        PartDefinition arm1 = partdefinition.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(60, 58).mirror().addBox(9.0F, -2.5F, -3.0F, 4.0F, 24.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 2.0F, -8.0F));
        PartDefinition arm2 = partdefinition.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(60, 21).addBox(-13.0F, -2.5F, -3.0F, 4.0F, 24.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -8.0F));
        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(48, 0).addBox(-3.5F, -3.0F, -3.0F, 6.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 17.0F, 0.0F));
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5354F, -2.8087F, -6.0783F, 14.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.4646F, -1.1913F, -9.4217F));
        PartDefinition crystalheadsmall1_r1 = head.addOrReplaceChild("crystalheadsmall1_r1", CubeListBuilder.create().texOffs(32, 100).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.206F, -3.0045F, -3.4891F, 0.0436F, 0.0F, 0.3491F));
        PartDefinition crystalheadbig_r1 = head.addOrReplaceChild("crystalheadbig_r1", CubeListBuilder.create().texOffs(0, 100).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6705F, -3.1868F, -3.4326F, 0.0436F, 0.0F, -0.3491F));
        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(74, 0).mirror().addBox(-3.5F, -3.0F, -3.0F, 6.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 17.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = 0.3054F + headPitch * ((float) Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.leg1.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / 2;
        this.leg2.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 2;
        //this.arm1.xRot = -Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / 2;
        //this.arm2.xRot = -Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 2;
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTick) {
        int i = entity.getAttackAnimationTick();
        if (i > 0) {
            this.arm1.xRot = -2.0F + 1.5F * Mth.triangleWave((float)i - partialTick, 10.0F);
            this.arm2.xRot = -2.0F + 1.5F * Mth.triangleWave((float)i - partialTick, 10.0F);
        } else {
            this.arm1.xRot = (-0.2F + 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
            this.arm2.xRot = (-0.2F - 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, buffer, packedLight, packedOverlay);
        waist.render(poseStack, buffer, packedLight, packedOverlay);
        arm1.render(poseStack, buffer, packedLight, packedOverlay);
        arm2.render(poseStack, buffer, packedLight, packedOverlay);
        leg2.render(poseStack, buffer, packedLight, packedOverlay);
        head.render(poseStack, buffer, packedLight, packedOverlay);
        leg1.render(poseStack, buffer, packedLight, packedOverlay);
    }
}