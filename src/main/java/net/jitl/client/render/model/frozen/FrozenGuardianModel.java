package net.jitl.client.render.model.frozen;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jitl.JITL;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.util.Mth;

public class FrozenGuardianModel<T extends Entity> extends ListModel<T> implements ArmedModel {

    private final ModelPart arms;
    private final ModelPart body;
    private final ModelPart head;

    public FrozenGuardianModel(ModelPart root) {
        this.arms = root.getChild("arms");
        this.body = root.getChild("body");
        this.head = root.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition arms = partdefinition.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -5.432F, -1.4626F));

        PartDefinition sleeve2_r1 = arms.addOrReplaceChild("sleeve2_r1", CubeListBuilder.create().texOffs(16, 41).addBox(-2.0F, -4.5F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 41).addBox(-16.0F, -4.5F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.0F, -3.5F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 2.7887F, -3.5468F, -1.1345F, 0.0F, 0.0F));

        PartDefinition armmiddle_r1 = arms.addOrReplaceChild("armmiddle_r1", CubeListBuilder.create().texOffs(44, 43).addBox(-19.0F, -3.0F, -3.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.0F, 6.5031F, -6.78F, -1.1345F, 0.0F, 0.0F));

        PartDefinition arm2_r1 = arms.addOrReplaceChild("arm2_r1", CubeListBuilder.create().texOffs(48, 47).addBox(0.0F, -3.5F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 2.7887F, -3.5468F, -1.1345F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 23).addBox(-5.0F, -1.4782F, -2.4767F, 10.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.5218F, -1.5233F));

        PartDefinition body3_r1 = body.addOrReplaceChild("body3_r1", CubeListBuilder.create().texOffs(32, 43).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 23.896F, 5.2955F, 0.6981F, 0.0F, 0.0F));

        PartDefinition body2_r1 = body.addOrReplaceChild("body2_r1", CubeListBuilder.create().texOffs(40, 8).addBox(-4.0F, -5.0F, -2.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.5218F, 1.5233F, 0.3491F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -7.3333F, -3.5833F, 10.0F, 13.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).addBox(-4.0F, -6.3333F, -2.5833F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).addBox(-1.0F, -0.3333F, -2.8333F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.6667F, -4.4167F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }


    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = Mth.cos(ageInTicks * 0.085F);
        float idle = (0.065F + 0.05F * f) * (float) Math.PI;
        this.head.xRot = idle / 6;
        this.body.xRot = idle;
        this.arms.xRot = idle / 2;
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.head, this.body, this.arms);
    }

    @Override
    public void translateToHand(HumanoidArm sideIn, PoseStack matrixStackIn) {
        this.getArm().translateAndRotate(matrixStackIn);
    }

    private ModelPart getArm() {
        return this.arms;//FIXME Arm should be armmiddle_r1;
    }

}