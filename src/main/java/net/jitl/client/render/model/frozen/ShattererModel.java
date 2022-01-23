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

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -4.9531F, -1.5F, 10.0F, 10.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).addBox(-3.0F, 13.0469F, -0.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).addBox(13.0F, -2.9531F, -0.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).addBox(-19.0F, -2.9531F, -0.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).addBox(-3.0F, -18.9531F, -0.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 32).addBox(5.0F, -0.9531F, -0.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(3, 35).addBox(-1.0F, -12.9531F, -0.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 32).addBox(-13.0F, -0.9531F, -0.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(3, 35).addBox(-1.0F, 5.0469F, -0.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.9531F, 1.5F));

        PartDefinition bRStalk_r1 = body.addOrReplaceChild("bRStalk_r1", CubeListBuilder.create().texOffs(3, 35).addBox(-1.6275F, 3.6275F, -0.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(3, 35).addBox(-1.6275F, -11.3725F, -0.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.6395F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition bLStalk_r1 = body.addOrReplaceChild("bLStalk_r1", CubeListBuilder.create().texOffs(3, 35).addBox(-0.3725F, 3.6275F, -0.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(3, 35).addBox(-0.3725F, -11.3725F, -0.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.6395F, 0.0F, 0.0F, 0.0F, 0.7854F));

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
