package net.jitl.client.render.model.overworld;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class BrownHongoModel<T extends Entity> extends ListModel<T> {
    private final ModelPart body;
    private final ModelPart blLeg;
    private final ModelPart fLLeg;
    private final ModelPart bRLeg;
    private final ModelPart fRLeg;

    public BrownHongoModel(ModelPart root) {
        this.body = root.getChild("body");
        this.blLeg = root.getChild("blLeg");
        this.fLLeg = root.getChild("fLLeg");
        this.bRLeg = root.getChild("bRLeg");
        this.fRLeg = root.getChild("fRLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(47, 29).addBox(-5.0F, -5.6667F, -5.6667F, 10.0F, 21.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 60).addBox(-12.0F, -12.6667F, -12.6667F, 24.0F, 7.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.3333F, 0.6667F));

        PartDefinition blLeg = partdefinition.addOrReplaceChild("blLeg", CubeListBuilder.create().texOffs(0, 60).addBox(-2.0F, 0.0F, -4.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 15.0F, 5.0F));

        PartDefinition fLLeg = partdefinition.addOrReplaceChild("fLLeg", CubeListBuilder.create().texOffs(0, 60).addBox(-2.0F, 0.0F, -3.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 15.0F, -4.0F));

        PartDefinition bRLeg = partdefinition.addOrReplaceChild("bRLeg", CubeListBuilder.create().texOffs(0, 60).addBox(-4.0F, 0.0F, -4.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 15.0F, 5.0F));

        PartDefinition fRLeg = partdefinition.addOrReplaceChild("fRLeg", CubeListBuilder.create().texOffs(0, 60).addBox(-4.0F, 0.0F, -3.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 15.0F, -4.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(
                body,
                blLeg,
                fLLeg,
                bRLeg,
                fRLeg
        );
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.fLLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.fRLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.blLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.bRLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }
}

