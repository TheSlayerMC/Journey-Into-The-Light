package net.jitl.client.render.model.frozen;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.common.entity.base.JEntityAction;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import org.jetbrains.annotations.NotNull;

public class FrozenTrollModel<T extends Entity> extends ListModel<T> implements ArmedModel {
    private final ModelPart head;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart arm1;
    private final ModelPart arm2;
    private final ModelPart body;

    public FrozenTrollModel(ModelPart root) {
        this.head = root.getChild("head");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.arm1 = root.getChild("arm1");
        this.arm2 = root.getChild("arm2");
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5.0F, -7.625F, 0.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(32, 30).addBox(-3.0F, 1.375F, -3.25F, 6.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 11).addBox(-2.0F, 6.375F, -2.75F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -6.625F, -3.75F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(3.0F, -7.625F, 0.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.625F, 0.75F));

        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(16, 30).mirror().addBox(-2.0F, -0.5F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.25F, 17.5F, 2.0F));

        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.25F, 17.5F, 2.0F));

        PartDefinition arm1 = partdefinition.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(24, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.0F, 9.0F, 1.0F));

        PartDefinition arm2 = partdefinition.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(0, 29).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 9.0F, 1.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.5F, 2.0F, 0.1745F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.head, this.body, this.arm1, this.arm2, this.leg1, this.leg2);
    }

    @Override
    public void setupAnim(@NotNull T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.leg1.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leg2.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.arm1.yRot = 0.0F;
        this.arm2.yRot = 0.0F;
        this.arm2.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.arm1.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        if (entityIn instanceof FrozenTrollEntity frozenTrollEntity) {
            JEntityAction entityAction = frozenTrollEntity.getArmPose();
            if (entityAction == JEntityAction.ADMIRING_ITEM) {
                this.head.xRot = 0.5F;
                this.head.yRot = 0.0F;
                this.arm1.yRot = 0.3F;
                this.arm1.xRot = -0.9F;
                this.arm2.yRot = -0.3F;
                this.arm2.xRot = -0.9F;
            }
        }
    }

    @Override
    public void translateToHand(HumanoidArm sideIn, PoseStack matrixStackIn) {
        this.getArm(sideIn).translateAndRotate(matrixStackIn);
    }

    private ModelPart getArm(HumanoidArm handSide_) {
        return handSide_ == HumanoidArm.LEFT ? this.arm1 : this.arm2;
    }
}
