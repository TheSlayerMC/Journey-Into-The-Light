package net.jitl.client.render.model.frozen;

import com.google.common.collect.ImmutableList;
import net.jitl.common.entity.frozen.CapybaraEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class CapybaraModel<T extends CapybaraEntity> extends AgeableListModel<T> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart ear2_r1;
    private final ModelPart ear1_r1;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;

    public CapybaraModel() {
        texWidth = 64;
        texHeight = 64;

        body = new ModelPart(this);
        body.setPos(0.0F, 11.0F, 2.0F);
        body.texOffs(0, 0).addBox(-6.0F, -4.0F, -10.0F, 12.0F, 12.0F, 19.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPos(0.0F, 12.0F, -6.0F);
        head.texOffs(0, 31).addBox(-4.0F, -7.0F, -12.0F, 8.0F, 9.0F, 12.0F, 0.0F, false);

        ear2_r1 = new ModelPart(this);
        ear2_r1.setPos(-4.0F, -6.1491F, -2.0358F);
        head.addChild(ear2_r1);
        ear2_r1.texOffs(0, 14).addBox(-0.5F, -2.5F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);

        ear1_r1 = new ModelPart(this);
        ear1_r1.setPos(4.0F, -6.0F, -3.0F);
        head.addChild(ear1_r1);
        ear1_r1.texOffs(6, 14).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);

        leg0 = new ModelPart(this);
        leg0.setPos(3.0F, 14.0F, 9.0F);
        leg0.texOffs(40, 31).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);

        leg1 = new ModelPart(this);
        leg1.setPos(-3.0F, 14.0F, 9.0F);
        leg1.texOffs(0, 0).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);

        leg2 = new ModelPart(this);
        leg2.setPos(3.0F, 18.0F, -5.0F);
        leg2.texOffs(40, 45).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        leg3 = new ModelPart(this);
        leg3.setPos(-3.0F, 18.0F, -6.0F);
        leg3.texOffs(43, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(head);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(body, leg0, leg1, leg2, leg3);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = 0.3054F + headPitch * ((float) Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);

        this.leg0.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg1.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg2.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg3.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }
}
