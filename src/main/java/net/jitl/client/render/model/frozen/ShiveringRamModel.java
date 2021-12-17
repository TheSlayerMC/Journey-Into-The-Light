package net.jitl.client.render.model.frozen;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ShiveringRamModel<T extends Entity> extends SegmentedModel<T> {
    private final ModelRenderer body_wool;
    private final ModelRenderer head_wool;
    private final ModelRenderer leg0_wool;
    private final ModelRenderer leg1_wool;
    private final ModelRenderer leg2_wool;
    private final ModelRenderer leg3_wool;
    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;

    public ShiveringRamModel() {
        texWidth = 128;
        texHeight = 128;

        body_wool = new ModelRenderer(this);
        body_wool.setPos(0.0F, 5.0F, 2.0F);
        body_wool.texOffs(0, 24).addBox(-4.0F, 2.0859F, -9.1072F, 8.0F, 6.0F, 16.0F, 1.75F, false);

        head_wool = new ModelRenderer(this);
        head_wool.setPos(0.0F, 6.0F, -8.0F);
        head_wool.texOffs(32, 24).addBox(-3.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, 0.6F, false);

        leg0_wool = new ModelRenderer(this);
        leg0_wool.setPos(3.0F, 12.0F, 7.0F);
        leg0_wool.texOffs(0, 58).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F, false);

        leg1_wool = new ModelRenderer(this);
        leg1_wool.setPos(-3.0F, 12.0F, 7.0F);
        leg1_wool.texOffs(56, 30).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F, false);

        leg2_wool = new ModelRenderer(this);
        leg2_wool.setPos(3.0F, 12.0F, -5.0F);
        leg2_wool.texOffs(52, 52).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F, false);

        leg3_wool = new ModelRenderer(this);
        leg3_wool.setPos(-3.0F, 12.0F, -5.0F);
        leg3_wool.texOffs(36, 52).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 5.0F, 2.0F);
        body.texOffs(0, 0).addBox(-4.0F, 1.0F, -9.0F, 8.0F, 8.0F, 16.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, 6.0F, -8.0F);
        head.texOffs(32, 0).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F, 0.0F, false);
        head.texOffs(0, 46).addBox(3.0F, -4.0F, -4.0F, 4.0F, 6.0F, 6.0F, 0.0F, false);
        head.texOffs(42, 40).addBox(-7.0F, -4.0F, -4.0F, 4.0F, 6.0F, 6.0F, 0.0F, false);
        head.texOffs(56, 40).addBox(-7.0F, -1.0F, -7.0F, 4.0F, 3.0F, 3.0F, 0.0F, false);
        head.texOffs(52, 0).addBox(3.0F, -1.0F, -7.0F, 4.0F, 3.0F, 3.0F, 0.0F, false);

        leg0 = new ModelRenderer(this);
        leg0.setPos(3.0F, 12.0F, 7.0F);
        leg0.texOffs(50, 14).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        leg1 = new ModelRenderer(this);
        leg1.setPos(-3.0F, 12.0F, 7.0F);
        leg1.texOffs(20, 46).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        leg2 = new ModelRenderer(this);
        leg2.setPos(3.0F, 12.0F, -5.0F);
        leg2.texOffs(0, 24).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        leg3 = new ModelRenderer(this);
        leg3.setPos(-3.0F, 12.0F, -5.0F);
        leg3.texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);

        this.head_wool.xRot = headPitch * ((float) Math.PI / 180F);
        this.head_wool.yRot = netHeadYaw * ((float) Math.PI / 180F);

        this.leg0.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg1.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg3.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.leg0_wool.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg1_wool.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg2_wool.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg3_wool.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body_wool.render(matrixStack, buffer, packedLight, packedOverlay);
        head_wool.render(matrixStack, buffer, packedLight, packedOverlay);
        leg0_wool.render(matrixStack, buffer, packedLight, packedOverlay);
        leg1_wool.render(matrixStack, buffer, packedLight, packedOverlay);
        leg2_wool.render(matrixStack, buffer, packedLight, packedOverlay);
        leg3_wool.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        leg0.render(matrixStack, buffer, packedLight, packedOverlay);
        leg1.render(matrixStack, buffer, packedLight, packedOverlay);
        leg2.render(matrixStack, buffer, packedLight, packedOverlay);
        leg3.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(
                body_wool,
                body,
                leg3,
                leg2,
                leg1,
                leg0,
                leg3_wool,
                leg2_wool,
                leg1_wool,
                leg0_wool,
                head_wool,
                head);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}