package net.jitl.client.render.model.frozen;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.common.entity.base.JEntityAction;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import Entity;

public class FrozenTrollModel<T extends Entity> extends ListModel<T> implements ArmedModel {
    private final ModelPart head;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart arm1;
    private final ModelPart arm2;
    private final ModelPart body;
    private final ModelPart body_r1;

    public FrozenTrollModel() {
        texWidth = 64;
        texHeight = 64;

        head = new ModelPart(this);
        head.setPos(0.0F, 10.625F, 1.75F);
        head.texOffs(0, 0).addBox(-5.0F, -8.625F, -2.75F, 2.0F, 2.0F, 1.0F, 0.0F, true);
        head.texOffs(32, 30).addBox(-3.0F, 0.375F, -6.25F, 6.0F, 5.0F, 2.0F, 0.0F, false);
        head.texOffs(32, 11).addBox(-2.0F, 5.375F, -5.75F, 4.0F, 4.0F, 1.0F, 0.0F, false);
        head.texOffs(0, 0).addBox(-4.0F, -7.625F, -6.75F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.texOffs(0, 0).addBox(3.0F, -8.625F, -2.75F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        leg1 = new ModelPart(this);
        leg1.setPos(2.25F, 17.5F, 2.0F);
        leg1.texOffs(16, 30).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, true);

        leg2 = new ModelPart(this);
        leg2.setPos(-2.25F, 17.5F, 2.0F);
        leg2.texOffs(32, 0).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

        arm1 = new ModelPart(this);
        arm1.setPos(6.0F, 9.0F, 1.0F);
        arm1.texOffs(24, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, true);

        arm2 = new ModelPart(this);
        arm2.setPos(-6.0F, 9.0F, 1.0F);
        arm2.texOffs(0, 29).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);

        body = new ModelPart(this);
        body.setPos(0.0F, 24.0F, 0.0F);


        body_r1 = new ModelPart(this);
        body_r1.setPos(0.0F, -11.5F, 2.0F);
        body.addChild(body_r1);
        setRotationAngle(body_r1, 0.1745F, 0.0F, 0.0F);
        body_r1.texOffs(0, 16).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 9.0F, 4.0F, 0.0F, false);
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
        if (entityIn instanceof FrozenTrollEntity) {
            FrozenTrollEntity frozenTrollEntity = (FrozenTrollEntity) entityIn;
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

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
