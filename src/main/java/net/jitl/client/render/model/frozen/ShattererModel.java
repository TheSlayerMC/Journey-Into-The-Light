package net.jitl.client.render.model.frozen;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class ShattererModel <T extends Entity> extends ListModel<T> {

    private final ModelPart body;

    public ShattererModel(ModelPart root) {
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.952F, -4.9995F, -1.5021F, 10.0F, 10.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).addBox(-2.952F, 13.0005F, -0.5021F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).addBox(13.048F, -2.9995F, -0.5021F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).addBox(-18.952F, -2.9995F, -0.5021F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).addBox(-2.952F, -18.9995F, -0.5021F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 32).addBox(5.048F, -0.9995F, -0.5021F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(3, 35).addBox(-0.952F, -12.9995F, -0.5021F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 32).addBox(-12.952F, -0.9995F, -0.5021F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(3, 35).addBox(-0.952F, 5.0005F, -0.5021F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.048F, 4.9995F, 1.5021F));
        PartDefinition s8_r1 = body.addOrReplaceChild("s8_r1", CubeListBuilder.create().texOffs(3, 35).addBox(1.0F, 0.0F, -0.49F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.8515F, -1.2127F, -0.0021F, 0.0F, 0.0F, 0.7854F));
        PartDefinition s1_r1 = body.addOrReplaceChild("s1_r1", CubeListBuilder.create().texOffs(3, 35).addBox(-2.0F, -0.8725F, -0.4955F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.4431F, 0.1114F, 0.0034F, 0.0F, 0.0F, -0.7854F));
        PartDefinition s2_r1 = body.addOrReplaceChild("s2_r1", CubeListBuilder.create().texOffs(3, 35).addBox(-2.1275F, -3.0F, -0.5045F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.4431F, 0.1114F, 0.0034F, 0.0F, 0.0F, 0.7854F));
        PartDefinition s3_r1 = body.addOrReplaceChild("s3_r1", CubeListBuilder.create().texOffs(3, 35).addBox(-1.0F, -2.0F, -0.49F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4623F, -9.698F, -0.0021F, 0.0F, 0.0F, 0.7854F));
        PartDefinition s4_r1 = body.addOrReplaceChild("s4_r1", CubeListBuilder.create().texOffs(3, 35).addBox(-3.0F, -4.0F, -0.499F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4623F, -9.698F, -0.0021F, 0.0F, 0.0F, -0.7854F));
        PartDefinition s5_r1 = body.addOrReplaceChild("s5_r1", CubeListBuilder.create().texOffs(3, 35).addBox(-1.0F, -2.0F, -0.49F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3662F, 9.6868F, -0.0021F, 0.0F, 0.0F, 0.7854F));
        PartDefinition s6_r1 = body.addOrReplaceChild("s6_r1", CubeListBuilder.create().texOffs(3, 35).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4623F, 9.6868F, -0.0011F, 0.0F, 0.0F, -0.7854F));
        PartDefinition s7_r1 = body.addOrReplaceChild("s7_r1", CubeListBuilder.create().texOffs(3, 35).addBox(-1.0F, -2.0F, -0.499F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.8515F, -1.2127F, -0.0021F, 0.0F, 0.0F, -0.7854F));
        PartDefinition rTPin_r1 = body.addOrReplaceChild("rTPin_r1", CubeListBuilder.create().texOffs(3, 35).addBox(-1.6275F, -11.3725F, -0.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(3, 35).addBox(-1.6275F, 3.6275F, -0.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.048F, -0.6859F, -0.0021F, 0.0F, 0.0F, -0.7854F));
        PartDefinition bLStalk_r1 = body.addOrReplaceChild("bLStalk_r1", CubeListBuilder.create().texOffs(3, 35).addBox(-0.3725F, 3.6275F, -0.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(3, 35).addBox(-0.3725F, -11.3725F, -0.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.048F, -0.6859F, -0.0021F, 0.0F, 0.0F, 0.7854F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(body);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = 8;
        this.body.zRot = ageInTicks / f;
    }
}
