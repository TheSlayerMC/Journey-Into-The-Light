package net.jitl.client.render.model.frozen;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class ShattererModel <T extends Entity> extends ListModel<T> {

    private final ModelPart body;
    private final ModelPart bRStalk;
    private final ModelPart bLStalk;
    private final ModelPart lTPin;
    private final ModelPart rTPin;

    public ShattererModel(ModelPart root) {
        this.body = root.getChild("body");
        this.bRStalk = root.getChild("bRStalk");
        this.bLStalk = root.getChild("bLStalk");
        this.lTPin = root.getChild("lTPin");
        this.rTPin = root.getChild("rTPin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -24.0F, 0.0F, 10.0F, 10.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).addBox(-3.0F, -6.0F, 1.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).addBox(13.0F, -22.0F, 1.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).addBox(-19.0F, -22.0F, 1.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).addBox(-3.0F, -38.0F, 1.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 32).addBox(5.0F, -20.0F, 1.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(3, 35).addBox(-1.0F, -32.0F, 1.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 32).addBox(-13.0F, -20.0F, 1.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(3, 35).addBox(-1.0F, -14.0F, 1.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bRStalk = partdefinition.addOrReplaceChild("bRStalk", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bRStalk_r1 = bRStalk.addOrReplaceChild("bRStalk_r1", CubeListBuilder.create().texOffs(3, 35).addBox(-1.6275F, 3.6275F, -0.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.6864F, 1.5F, 0.0F, 0.0F, -0.7854F));

        PartDefinition bLStalk = partdefinition.addOrReplaceChild("bLStalk", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bLStalk_r1 = bLStalk.addOrReplaceChild("bLStalk_r1", CubeListBuilder.create().texOffs(3, 35).addBox(-0.3725F, 3.6275F, -0.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.6864F, 1.5F, 0.0F, 0.0F, 0.7854F));

        PartDefinition lTPin = partdefinition.addOrReplaceChild("lTPin", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition lTPin_r1 = lTPin.addOrReplaceChild("lTPin_r1", CubeListBuilder.create().texOffs(3, 35).addBox(-0.3725F, -11.3725F, -0.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.6864F, 1.5F, 0.0F, 0.0F, 0.7854F));

        PartDefinition rTPin = partdefinition.addOrReplaceChild("rTPin", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition rTPin_r1 = rTPin.addOrReplaceChild("rTPin_r1", CubeListBuilder.create().texOffs(3, 35).addBox(-1.6275F, -11.3725F, -0.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.6864F, 1.5F, 0.0F, 0.0F, -0.7854F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(body, bRStalk, bLStalk, rTPin, lTPin);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = 8;
        this.body.zRot = ageInTicks / f;
        //this.spike1.zRot = -2.4F + ageInTicks / f; //FIXME Test rotation when game runs
        //this.spike2.zRot = 2.4F + ageInTicks / f;
        //this.arm1.zRot = ageInTicks / f;
        //this.arm2.zRot = 4.7F + ageInTicks / f;
    }
}
