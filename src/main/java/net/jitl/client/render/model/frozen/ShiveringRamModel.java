package net.jitl.client.render.model.frozen;

import com.google.common.collect.ImmutableList;
import net.jitl.common.entity.frozen.ShiveringRamEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ShiveringRamModel<T extends ShiveringRamEntity> extends AgeableModel<T> {
    private float headXRot;

    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;

    public ShiveringRamModel() {
        texWidth = 128;
        texHeight = 128;

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
    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.head.y = 6.0F + entityIn.getHeadEatPositionScale(partialTick) * 9.0F;
        this.headXRot = entityIn.getHeadEatAngleScale(partialTick);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = this.headXRot;

        this.leg0.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg1.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg3.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(head);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(
                body,
                leg3,
                leg2,
                leg1,
                leg0);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}