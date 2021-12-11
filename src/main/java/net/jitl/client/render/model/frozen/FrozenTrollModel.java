package net.jitl.client.render.model.frozen;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class FrozenTrollModel<T extends Entity> extends SegmentedModel<T> {

    private ModelRenderer head;
    private ModelRenderer body;
    private ModelRenderer rightarm;
    private ModelRenderer leftarm;
    private ModelRenderer rightleg;
    private ModelRenderer leftleg;
    private ModelRenderer ear1;
    private ModelRenderer ear2;
    private ModelRenderer beard;

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
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.rightleg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftleg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.rightarm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftarm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
    }
}
