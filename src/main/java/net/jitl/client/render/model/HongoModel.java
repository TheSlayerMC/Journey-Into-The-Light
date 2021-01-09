package net.jitl.client.render.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class HongoModel<T extends Entity> extends SegmentedModel<T> {

    private final ModelRenderer body;
    private final ModelRenderer leftFrontLeg;
    private final ModelRenderer rightFrontLeg;
    private final ModelRenderer backLeftLeg;
    private final ModelRenderer backRightLeg;
    private final ModelRenderer bottomHat;
    private final ModelRenderer topHat;

    public HongoModel() {
        texHeight = 64;
        texWidth = 256;
        body = new ModelRenderer(this, 0, 38);
        body.addBox(0F, 0F, 0F, 12, 14, 11);
        body.setPos(-6F, 2F, -4F);
        body.mirror = true;
        leftFrontLeg = new ModelRenderer(this, 49, 38);
        leftFrontLeg.addBox(-3F, 0F, -6F, 6, 9, 6);
        leftFrontLeg.setPos(-6F, 15F, -1F);
        leftFrontLeg.mirror = true;
        rightFrontLeg = new ModelRenderer(this, 49, 38);
        rightFrontLeg.addBox(-3F, 0F, -6F, 6, 9, 6);
        rightFrontLeg.setPos(6F, 15F, -1F);
        rightFrontLeg.mirror = true;
        backLeftLeg = new ModelRenderer(this, 76, 38);
        backLeftLeg.addBox(-3F, 0F, 0F, 6, 8, 6);
        backLeftLeg.setPos(-6F, 16F, 4F);
        backLeftLeg.mirror = true;
        backRightLeg = new ModelRenderer(this, 76, 38);
        backRightLeg.addBox(-3F, 0F, 0F, 6, 8, 6);
        backRightLeg.setPos(6F, 16F, 4F);
        backRightLeg.mirror = true;
        bottomHat = new ModelRenderer(this, 1, 1);
        bottomHat.addBox(0F, 0F, 0F, 21, 7, 21);
        bottomHat.setPos(-10.5F, -4F, -8.5F);
        bottomHat.mirror = true;
        topHat = new ModelRenderer(this, 92, 1);
        topHat.addBox(0F, 0F, 0F, 18, 5, 16);
        topHat.setPos(-9F, -7F, -5.8F);
        topHat.mirror = true;
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(
                body,
                leftFrontLeg,
                rightFrontLeg,
                backLeftLeg,
                backRightLeg,
                bottomHat,
                topHat
        );
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.leftFrontLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rightFrontLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.backLeftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.backRightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }
}
