package net.jitl.client.render.model.frozen;

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
public class EskimoModel<T extends Entity> extends SegmentedModel<T> implements IHasHead, IHeadToggle {
    private final ModelRenderer head2;
    private final ModelRenderer nose2;
    private final ModelRenderer body2;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer arms2;
    private final ModelRenderer head;
    private final ModelRenderer nose;
    private final ModelRenderer body;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer arms;

    public EskimoModel(float scale) {
        this(scale, 64, 64);
    }

    public EskimoModel(float float_, int int_, int int1_) {
        texWidth = 128;
        texHeight = 128;

        head2 = new ModelRenderer(this);
        head2.setPos(0.0F, 0.0F, 0.0F);


        nose2 = new ModelRenderer(this);
        nose2.setPos(0.0F, -2.0F, 0.0F);


        body2 = new ModelRenderer(this);
        body2.setPos(0.0F, 0.0F, 0.0F);


        leg2 = new ModelRenderer(this);
        leg2.setPos(2.0F, 12.0F, 0.0F);


        leg3 = new ModelRenderer(this);
        leg3.setPos(-2.0F, 12.0F, 0.0F);


        arms2 = new ModelRenderer(this);
        arms2.setPos(0.0F, 2.0F, 0.0F);


        head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.texOffs(28, 20).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);
        head.texOffs(0, 0).addBox(-5.0F, -11.0F, -4.75F, 10.0F, 11.0F, 9.0F, 0.0F, false);
        head.texOffs(0, 0).addBox(-1.0F, -3.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        nose = new ModelRenderer(this);
        nose.setPos(0.0F, -2.0F, 0.0F);


        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.texOffs(38, 0).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, false);
        body.texOffs(0, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, 0.5F, false);

        leg0 = new ModelRenderer(this);
        leg0.setPos(2.0F, 12.0F, 0.0F);
        leg0.texOffs(16, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        leg1 = new ModelRenderer(this);
        leg1.setPos(-2.0F, 12.0F, 0.0F);
        leg1.texOffs(0, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        arms = new ModelRenderer(this);
        arms.setPos(0.0F, 2.0F, 0.0F);
        arms.texOffs(28, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
        arms.texOffs(48, 42).addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
        arms.texOffs(32, 46).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
        arms.texOffs(31, 58).addBox(-8.75F, -2.5F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F, false);
        arms.texOffs(51, 58).addBox(4.25F, -2.5F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F, false);
    }


    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.head, this.body, this.leg0, this.leg1, this.arms);
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = false;
        if (entityIn instanceof AbstractVillagerEntity) {
            flag = ((AbstractVillagerEntity) entityIn).getUnhappyCounter() > 0;
        }

        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        if (flag) {
            this.head.zRot = 0.3F * MathHelper.sin(0.45F * ageInTicks);
            this.head.xRot = 0.4F;
        } else {
            this.head.zRot = 0.0F;
        }

        this.arms.y = 3.0F;
        this.arms.z = -1.0F;
        this.arms.xRot = -0.75F;
        this.leg0.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.leg1.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.leg0.yRot = 0.0F;
        this.leg1.yRot = 0.0F;
    }

    public ModelRenderer getHead() {
        return this.head;
    }

    @Override
    public void hatVisible(boolean b) {
    }
}