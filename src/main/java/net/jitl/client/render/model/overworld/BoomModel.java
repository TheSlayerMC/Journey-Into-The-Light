package net.jitl.client.render.model.overworld;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

public class BoomModel<T extends Entity> extends ListModel<T> {

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart frL;
    private final ModelPart fll;
    private final ModelPart bll;
    private final ModelPart brl;
    private final ModelPart lArm;
    private final ModelPart rArm;

    public BoomModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.frL = root.getChild("frL");
        this.fll = root.getChild("fll");
        this.bll = root.getChild("bll");
        this.brl = root.getChild("brl");
        this.lArm = root.getChild("lArm");
        this.rArm = root.getChild("rArm");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 31).addBox(-8.0F, -28.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -36.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition frL = partdefinition.addOrReplaceChild("frL", CubeListBuilder.create().texOffs(0, 16).addBox(4.0F, -12.0F, -7.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition fll = partdefinition.addOrReplaceChild("fll", CubeListBuilder.create().texOffs(0, 16).addBox(-7.0F, -12.0F, -7.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bll = partdefinition.addOrReplaceChild("bll", CubeListBuilder.create().texOffs(0, 16).addBox(-7.0F, -12.0F, 4.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition brl = partdefinition.addOrReplaceChild("brl", CubeListBuilder.create().texOffs(0, 16).addBox(4.0F, -12.0F, 4.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition lArm = partdefinition.addOrReplaceChild("lArm", CubeListBuilder.create().texOffs(40, 1).addBox(-12.0F, -26.0F, -2.0F, 4.0F, 21.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition rArm = partdefinition.addOrReplaceChild("rArm", CubeListBuilder.create().texOffs(40, 1).addBox(8.0F, -26.0F, -2.0F, 4.0F, 21.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(
            head,
            body,
            rArm,
            lArm,
            frL,
            fll,
            bll,
            brl
        );
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = headPitch / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);
        this.frL.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.fll.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.brl.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.bll.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.lArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
    }
}