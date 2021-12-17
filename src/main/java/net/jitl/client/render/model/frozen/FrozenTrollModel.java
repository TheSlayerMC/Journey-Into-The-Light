package net.jitl.client.render.model.frozen;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.jitl.common.entity.base.JEntityAction;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

public class FrozenTrollModel<T extends Entity> extends SegmentedModel<T> implements IHasArm {

    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer rightarm;
    private final ModelRenderer leftarm;
    private final ModelRenderer rightleg;
    private final ModelRenderer leftleg;
    private final ModelRenderer ear1;
    private final ModelRenderer ear2;
    private final ModelRenderer beard;

    public FrozenTrollModel() {
        texWidth = 64;
        texHeight = 64;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setPos(0F, 11F, 0F);
        head.mirror = true;
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 8, 12, 4);
        body.setPos(0F, 7F, 0F);
        body.mirror = true;
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-3F, -2F, -2F, 4, 10, 4);
        rightarm.setPos(-5F, 11F, 0F);
        rightarm.mirror = true;
        leftarm = new ModelRenderer(this, 40, 16);
        leftarm.addBox(-1F, -2F, -2F, 4, 10, 4);
        leftarm.setPos(5F, 11F, 0F);
        leftarm.mirror = true;
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.addBox(-2F, 0F, -2F, 4, 5, 4);
        rightleg.setPos(-2F, 19F, 0F);
        rightleg.mirror = true;
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.addBox(-2F, 0F, -2F, 4, 5, 4);
        leftleg.setPos(2F, 19F, 0F);
        leftleg.mirror = true;
        ear1 = new ModelRenderer(this, 0, 0);
        ear1.addBox(0F, -11F, 0F, 2, 2, 1);
        ear1.setPos(3F, 2F, -2F);
        ear1.mirror = true;
        ear2 = new ModelRenderer(this, 0, 0);
        ear2.addBox(0F, -11F, 0F, 2, 2, 1);
        ear2.setPos(-5F, 2F, -2F);
        ear2.mirror = true;
        beard = new ModelRenderer(this, 40, 33);
        beard.addBox(0F, 0F, 0F, 6, 6, 2);
        beard.setPos(-3F, 0F, -4F);
        beard.mirror = true;
        head.addChild(beard);
        head.addChild(ear1);
        head.addChild(ear2);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.head, this.body, this.rightarm, this.leftarm, this.rightleg, this.leftleg);
    }

    @Override
    public void setupAnim(@NotNull T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.rightleg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftleg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.leftarm.yRot = 0.0F;
        this.rightarm.yRot = 0.0F;
        this.rightarm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftarm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        if (entityIn instanceof FrozenTrollEntity) {
            FrozenTrollEntity frozenTrollEntity = (FrozenTrollEntity) entityIn;
            JEntityAction entityAction = frozenTrollEntity.getArmPose();
            if (entityAction == JEntityAction.ADMIRING_ITEM) {
                this.head.xRot = 0.5F;
                this.head.yRot = 0.0F;
                this.leftarm.yRot = 0.5F;
                this.leftarm.xRot = -0.9F;
                this.rightarm.yRot = -0.5F;
                this.rightarm.xRot = -0.9F;
            }
        }
    }

    @Override
    public void translateToHand(HandSide sideIn, MatrixStack matrixStackIn) {
        this.getArm(sideIn).translateAndRotate(matrixStackIn);
    }

    private ModelRenderer getArm(HandSide handSide_) {
        return handSide_ == HandSide.LEFT ? this.leftarm : this.rightarm;
    }
}
