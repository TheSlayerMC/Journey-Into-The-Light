package net.jitl.client.render.model.frozen;

import com.google.common.collect.ImmutableList;
import net.jitl.common.entity.frozen.ShiveringRamEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class ShiveringRamModel<T extends ShiveringRamEntity> extends AgeableListModel<T> {
    private float headXRot;

    private final ModelPart body_wool;
    private final ModelPart head_wool;
    private final ModelPart leg0_wool;
    private final ModelPart leg1_wool;
    private final ModelPart leg2_wool;
    private final ModelPart leg3_wool;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;

    public ShiveringRamModel(ModelPart root) {
        this.body_wool = root.getChild("body_wool");
        this.head_wool = root.getChild("head_wool");
        this.leg0_wool = root.getChild("leg0_wool");
        this.leg1_wool = root.getChild("leg1_wool");
        this.leg2_wool = root.getChild("leg2_wool");
        this.leg3_wool = root.getChild("leg3_wool");
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leg0 = root.getChild("leg0");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body_wool = partdefinition.addOrReplaceChild("body_wool", CubeListBuilder.create().texOffs(0, 24).addBox(-4.0F, 2.0859F, -9.1072F, 8.0F, 6.0F, 16.0F, new CubeDeformation(1.75F)), PartPose.offset(0.0F, 5.0F, 2.0F));

        PartDefinition head_wool = partdefinition.addOrReplaceChild("head_wool", CubeListBuilder.create().texOffs(32, 24).addBox(-3.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 6.0F, -8.0F));

        PartDefinition leg0_wool = partdefinition.addOrReplaceChild("leg0_wool", CubeListBuilder.create().texOffs(0, 58).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(3.0F, 12.0F, 7.0F));

        PartDefinition leg1_wool = partdefinition.addOrReplaceChild("leg1_wool", CubeListBuilder.create().texOffs(56, 30).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-3.0F, 12.0F, 7.0F));

        PartDefinition leg2_wool = partdefinition.addOrReplaceChild("leg2_wool", CubeListBuilder.create().texOffs(52, 52).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(3.0F, 12.0F, -5.0F));

        PartDefinition leg3_wool = partdefinition.addOrReplaceChild("leg3_wool", CubeListBuilder.create().texOffs(36, 52).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-3.0F, 12.0F, -5.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 1.0F, -9.0F, 8.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 2.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 0).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 46).addBox(3.0F, -4.0F, -4.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(42, 40).addBox(-7.0F, -4.0F, -4.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(56, 40).addBox(-7.0F, -1.0F, -7.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(52, 0).addBox(3.0F, -1.0F, -7.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, -8.0F));

        PartDefinition leg0 = partdefinition.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(50, 14).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 12.0F, 7.0F));

        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(20, 46).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 12.0F, 7.0F));

        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 24).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 12.0F, -5.0F));

        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 12.0F, -5.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.head.y = 6.0F + entityIn.getHeadEatPositionScale(partialTick) * 9.0F;
        this.headXRot = entityIn.getHeadEatAngleScale(partialTick);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = this.headXRot;

        this.leg0.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg1.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg2.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg3.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(head);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(
                body,
                leg3,
                leg2,
                leg1,
                leg0);
    }
}