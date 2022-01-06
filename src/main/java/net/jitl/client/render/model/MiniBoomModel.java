package net.jitl.client.render.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

import Entity;

public class MiniBoomModel<T extends Entity> extends ListModel<T> {

    private ModelPart head;
    private ModelPart body;
    private ModelPart rightarm;
    private ModelPart leftarm;
    private ModelPart rightleg;
    private ModelPart leftleg;
    private ModelPart leftlegBack;
    private ModelPart rightlegBack;

    public MiniBoomModel() {
        this(0.0F);
    }

    public MiniBoomModel(float overlay) {
        texHeight = 64;
        texWidth = 64;
        head = new ModelPart(this, 0, 0);
        head.addBox(-4F, -4F, -4F, 8, 8, 8, overlay);
        head.setPos(0F, -8F, 0F);
        head.mirror = true;
        body = new ModelPart(this, 0, 31);
        body.addBox(0F, 0F, 0F, 16, 16, 16, overlay);
        body.setPos(-8F, -4F, -8F);
        body.mirror = true;
        rightarm = new ModelPart(this, 40, 1);
        rightarm.addBox(0F, 0F, 0F, 4, 21, 4, overlay);
        rightarm.setPos(-12F, -4F, -1F);
        rightarm.mirror = true;
        leftarm = new ModelPart(this, 40, 1);
        leftarm.addBox(0F, 0F, 0F, 4, 21, 4, overlay);
        leftarm.setPos(8F, -4F, -1F);
        leftarm.mirror = true;
        rightleg = new ModelPart(this, 0, 16);
        rightleg.addBox(0F, 0F, 0F, 3, 12, 3, overlay);
        rightleg.setPos(-7F, 12F, -7F);
        rightleg.mirror = true;
        leftleg = new ModelPart(this, 0, 16);
        leftleg.addBox(0F, 0F, 0F, 3, 12, 3, overlay);
        leftleg.setPos(4F, 12F, -7F);
        leftleg.mirror = true;
        leftlegBack = new ModelPart(this, 0, 16);
        leftlegBack.addBox(0F, 0F, 0F, 3, 12, 3, overlay);
        leftlegBack.setPos(4F, 12F, 4F);
        leftlegBack.mirror = true;
        rightlegBack = new ModelPart(this, 0, 16);
        rightlegBack.addBox(0F, 0F, 0F, 3, 12, 3, overlay);
        rightlegBack.setPos(-7F, 12F, 4F);
        rightlegBack.mirror = true;
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(
            head,
            body,
            rightarm,
            leftarm,
            rightleg,
            leftleg,
            leftlegBack,
            rightlegBack
        );
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = headPitch / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);
        this.rightleg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftleg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.rightlegBack.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leftlegBack.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftarm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
    }
}