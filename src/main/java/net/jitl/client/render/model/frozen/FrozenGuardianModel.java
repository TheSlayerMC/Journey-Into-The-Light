package net.jitl.client.render.model.frozen;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;

public class FrozenGuardianModel<T extends Entity> extends SegmentedModel<T> implements IHasArm {
    private final ModelRenderer arms;
    private final ModelRenderer sleeve2_r1;
    private final ModelRenderer armmiddle_r1;
    private final ModelRenderer arm2_r1;
    private final ModelRenderer body;
    private final ModelRenderer body3_r1;
    private final ModelRenderer body2_r1;
    private final ModelRenderer head;

    public FrozenGuardianModel() {
        texWidth = 128;
        texHeight = 128;

        arms = new ModelRenderer(this);
        arms.setPos(0.0F, -5.432F, -1.4626F);

        sleeve2_r1 = new ModelRenderer(this);
        sleeve2_r1.setPos(7.0F, 2.7887F, -3.5468F);
        arms.addChild(sleeve2_r1);
        setRotationAngle(sleeve2_r1, -1.1345F, 0.0F, 0.0F);
        sleeve2_r1.texOffs(16, 41).addBox(-2.0F, -4.5F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
        sleeve2_r1.texOffs(0, 41).addBox(-16.0F, -4.5F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
        sleeve2_r1.texOffs(0, 0).addBox(-2.0F, -3.5F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        armmiddle_r1 = new ModelRenderer(this);
        armmiddle_r1.setPos(14.0F, 6.5031F, -6.78F);
        arms.addChild(armmiddle_r1);
        setRotationAngle(armmiddle_r1, -1.1345F, 0.0F, 0.0F);
        armmiddle_r1.texOffs(44, 43).addBox(-19.0F, -3.0F, -3.0F, 10.0F, 2.0F, 2.0F, 0.0F, false);

        arm2_r1 = new ModelRenderer(this);
        arm2_r1.setPos(-7.0F, 2.7887F, -3.5468F);
        arms.addChild(arm2_r1);
        setRotationAngle(arm2_r1, -1.1345F, 0.0F, 0.0F);
        arm2_r1.texOffs(48, 47).addBox(0.0F, -3.5F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, -6.5218F, -1.5233F);
        body.texOffs(32, 23).addBox(-5.0F, -1.4782F, -2.4767F, 10.0F, 16.0F, 4.0F, 0.0F, false);

        body3_r1 = new ModelRenderer(this);
        body3_r1.setPos(0.0F, 23.896F, 5.2955F);
        body.addChild(body3_r1);
        setRotationAngle(body3_r1, 0.6981F, 0.0F, 0.0F);
        body3_r1.texOffs(32, 43).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

        body2_r1 = new ModelRenderer(this);
        body2_r1.setPos(0.0F, 18.5218F, 1.5233F);
        body.addChild(body2_r1);
        setRotationAngle(body2_r1, 0.3491F, 0.0F, 0.0F);
        body2_r1.texOffs(40, 8).addBox(-4.0F, -5.0F, -2.0F, 8.0F, 8.0F, 4.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, -9.6667F, -4.4167F);
        head.texOffs(0, 0).addBox(-5.0F, -7.3333F, -3.5833F, 10.0F, 13.0F, 10.0F, 0.0F, false);
        head.texOffs(0, 23).addBox(-4.0F, -6.3333F, -2.5833F, 8.0F, 10.0F, 8.0F, 0.0F, false);
        head.texOffs(0, 23).addBox(-1.0F, -0.3333F, -2.8333F, 2.0F, 5.0F, 0.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = MathHelper.cos(ageInTicks * 0.085F);
        float idle = (0.065F + 0.05F * f) * (float) Math.PI;
        this.head.xRot = idle / 6;
        this.body.xRot = idle;
        this.arms.xRot = idle / 2;

        ModelRenderer[] parts = {head, body, arms};
        for(ModelRenderer i : parts) {
            //move all parts here
        }
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        arms.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.head, this.body, this.arms);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void translateToHand(HandSide sideIn, MatrixStack matrixStackIn) {
        this.getArm().translateAndRotate(matrixStackIn);
    }

    private ModelRenderer getArm() {
        return this.armmiddle_r1;
    }
}