package net.jitl.client.render.model.frozen;

import com.google.common.collect.ImmutableList;
import net.jitl.common.entity.frozen.ShiveringRamEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ShiveringRamWoolModel<T extends ShiveringRamEntity> extends AgeableModel<T> {
    private float headXRot;

    private final ModelRenderer body_wool;
    private final ModelRenderer head_wool;
    private final ModelRenderer leg0_wool;
    private final ModelRenderer leg1_wool;
    private final ModelRenderer leg2_wool;
    private final ModelRenderer leg3_wool;

    public ShiveringRamWoolModel() {
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
    }

    @Override
    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.head_wool.y = 6.0F + entityIn.getHeadEatPositionScale(partialTick) * 9.0F;
        this.headXRot = entityIn.getHeadEatAngleScale(partialTick);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head_wool.xRot = headPitch * ((float) Math.PI / 180F);
        this.head_wool.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head_wool.xRot = this.headXRot;

        this.leg0_wool.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg1_wool.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg2_wool.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg3_wool.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(head_wool);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(
                body_wool,
                leg3_wool,
                leg2_wool,
                leg1_wool,
                leg0_wool);
    }
}