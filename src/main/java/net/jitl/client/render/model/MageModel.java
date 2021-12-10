package net.jitl.client.render.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.entity.model.IHeadToggle;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MageModel<T extends Entity> extends SegmentedModel<T> implements IHasHead, IHeadToggle {

    private final ModelRenderer head;
    private final ModelRenderer nose;
    private final ModelRenderer body;
    private final ModelRenderer arms;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer hat;
    private final ModelRenderer hat2;
    private final ModelRenderer hat3;
    private final ModelRenderer hat4;

    public MageModel(float s) {
        texWidth = 64;
        texHeight = 128;

        head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, true);

        nose = new ModelRenderer(this);
        nose.setPos(0.0F, -2.0F, 0.0F);
        nose.texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, true);
        body.texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, 0.5F, true);

        arms = new ModelRenderer(this);
        arms.setPos(0.0F, 2.0F, 0.0F);
        arms.texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, true);
        arms.texOffs(44, 22).addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, true);
        arms.texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, true);

        leg0 = new ModelRenderer(this);
        leg0.setPos(2.0F, 12.0F, 0.0F);
        leg0.texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        leg1 = new ModelRenderer(this);
        leg1.setPos(-2.0F, 12.0F, 0.0F);
        leg1.texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        hat = new ModelRenderer(this);
        hat.setPos(5.0F, -8.03F, -5.0F);
        hat.texOffs(0, 64).addBox(-10.0F, -2.02F, 0.0F, 10.0F, 2.0F, 10.0F, 0.0F, true);

        hat2 = new ModelRenderer(this);
        hat2.setPos(-1.75F, -8.0F, 2.0F);
        hat2.texOffs(0, 76).addBox(-2.0F, -5.5F, -5.0F, 7.0F, 4.0F, 7.0F, 0.0F, true);

        hat3 = new ModelRenderer(this);
        hat3.setPos(-1.75F, -11.0F, 2.0F);
        hat3.texOffs(0, 87).addBox(-0.75F, -5.5F, -3.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);

        hat4 = new ModelRenderer(this);
        hat4.setPos(-1.75F, -14.0F, 2.0F);
        hat4.texOffs(0, 95).addBox(0.5F, -4.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.25F, true);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.head, this.nose, this.body, this.arms, this.leg0, this.leg1, this.hat, this.hat2, this.hat3, this.hat4);
    }

    @Override
    public ModelRenderer getHead() {
        return this.head;
    }

    @Override
    public void hatVisible(boolean boolean_) {

    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = false;
        if (entityIn instanceof AbstractVillagerEntity) {
            flag = ((AbstractVillagerEntity) entityIn).getUnhappyCounter() > 0;
        }

        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);

        this.nose.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.nose.xRot = headPitch * ((float) Math.PI / 180F);

        this.hat.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.hat.xRot = headPitch * ((float) Math.PI / 180F);

        this.hat2.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.hat2.xRot = headPitch * ((float) Math.PI / 180F);

        this.hat3.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.hat3.xRot = headPitch * ((float) Math.PI / 180F);

        this.hat4.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.hat4.xRot = headPitch * ((float) Math.PI / 180F);

        if (flag) {
            this.head.zRot = 0.3F * MathHelper.sin(0.45F * ageInTicks);
            this.head.xRot = 0.4F;

            this.nose.zRot = 0.3F * MathHelper.sin(0.45F * ageInTicks);
            this.nose.xRot = 0.4F;
        } else {
            this.head.zRot = 0.0F;
            this.nose.zRot = 0.0F;
        }

        this.arms.y = 3.0F;
        this.arms.z = -1.0F;
        this.arms.xRot = -0.75F;
        this.leg0.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.leg1.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.leg0.yRot = 0.0F;
        this.leg1.yRot = 0.0F;
    }
}
