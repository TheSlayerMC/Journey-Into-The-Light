package net.jitl.client.render.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.VillagerHeadModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import Entity;

@OnlyIn(Dist.CLIENT)
public class MageModel<T extends Entity> extends ListModel<T> implements HeadedModel, VillagerHeadModel {

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart arms;
    private final ModelPart leg0;
    private final ModelPart leg1;

    public MageModel(float s) {
        texWidth = 64;
        texHeight = 128;

        head = new ModelPart(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, true);
        head.texOffs(0, 64).addBox(-5.0F, -10.05F, -5.0F, 10.0F, 2.0F, 10.0F, 0.0F, true);
        head.texOffs(0, 76).addBox(-3.75F, -13.5F, -3.0F, 7.0F, 4.0F, 7.0F, 0.0F, true);
        head.texOffs(0, 87).addBox(-2.5F, -16.5F, -1.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);
        head.texOffs(0, 95).addBox(-1.25F, -18.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.25F, true);
        head.texOffs(24, 0).addBox(-1.0F, -3.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);

        body = new ModelPart(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, true);
        body.texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, 0.5F, true);

        arms = new ModelPart(this);
        arms.setPos(0.0F, 2.0F, 0.0F);
        arms.texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, true);
        arms.texOffs(44, 22).addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, true);
        arms.texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, true);

        leg0 = new ModelPart(this);
        leg0.setPos(2.0F, 12.0F, 0.0F);
        leg0.texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        leg1 = new ModelPart(this);
        leg1.setPos(-2.0F, 12.0F, 0.0F);
        leg1.texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.head, this.body, this.arms, this.leg0, this.leg1);
    }

    @Override
    public ModelPart getHead() {
        return this.head;
    }

    @Override
    public void hatVisible(boolean b) { }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = false;
        if (entityIn instanceof AbstractVillager) {
            flag = ((AbstractVillager) entityIn).getUnhappyCounter() > 0;
        }

        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);

        if (flag) {
            this.head.zRot = 0.3F * Mth.sin(0.45F * ageInTicks);
            this.head.xRot = 0.4F;
        } else {
            this.head.zRot = 0.0F;
        }

        this.arms.y = 3.0F;
        this.arms.z = -1.0F;
        this.arms.xRot = -0.75F;
        this.leg0.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.leg1.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.leg0.yRot = 0.0F;
        this.leg1.yRot = 0.0F;
    }
}
