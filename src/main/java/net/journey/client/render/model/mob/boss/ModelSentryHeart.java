package net.journey.client.render.model.mob.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSentryHeart extends ModelBase {

    public ModelRenderer heart_main;
    public ModelRenderer heart_face_north;
    public ModelRenderer heart_face_south;
    public ModelRenderer bottom_shape_connector;
    public ModelRenderer bottom_connector;
    public ModelRenderer top_connector;
    public ModelRenderer heart_face_east;
    public ModelRenderer heart_face_west;
    public ModelRenderer top_tubes;
    public ModelRenderer bottom_tubes;
    public ModelRenderer heart_face_north_1;

    public ModelSentryHeart() {
    	this.textureWidth = 292;
        this.textureHeight = 256;
        this.top_connector = new ModelRenderer(this, 0, 196);
        this.top_connector.setRotationPoint(0.0F, -48.0F, 0.0F);
        this.top_connector.addBox(-12.0F, -30.0F, -4.0F, 24, 6, 24, 0.0F);
        this.heart_main = new ModelRenderer(this, 0, 10);
        this.heart_main.setRotationPoint(0.0F, -48.0F, 0.0F);
        this.heart_main.addBox(-24.0F, -24.0F, -24.0F, 48, 48, 48, 0.0F);
        this.heart_face_north_1 = new ModelRenderer(this, 122, 117);
        this.heart_face_north_1.setRotationPoint(0.0F, -48.0F, 0.0F);
        this.heart_face_north_1.addBox(-8.0F, -2.5F, -32.0F, 16, 2, 2, 0.0F);
        this.bottom_shape_connector = new ModelRenderer(this, 144, 0);
        this.bottom_shape_connector.setRotationPoint(0.0F, -48.0F, 0.0F);
        this.bottom_shape_connector.addBox(-22.0F, 24.0F, -18.0F, 32, 10, 40, 0.0F);
        this.top_tubes = new ModelRenderer(this, 0, 128);
        this.top_tubes.setRotationPoint(3.0F, -110.0F, 14.0F);
        this.top_tubes.addBox(-18.0F, 0.0F, -2.0F, 24, 32, 0, 0.0F);
        this.setRotateAngle(top_tubes, 0.0F, -0.7853981633974483F, 0.0F);
        this.heart_face_south = new ModelRenderer(this, 158, 128);
        this.heart_face_south.setRotationPoint(0.0F, -48.0F, 0.0F);
        this.heart_face_south.addBox(-16.0F, -16.0F, 24.0F, 32, 32, 6, 0.0F);
        this.bottom_connector = new ModelRenderer(this, 0, 164);
        this.bottom_connector.setRotationPoint(0.0F, -48.0F, 0.0F);
        this.bottom_connector.addBox(-18.0F, 34.0F, -10.0F, 24, 6, 24, 0.0F);
        this.heart_face_east = new ModelRenderer(this, 100, 189);
        this.heart_face_east.setRotationPoint(0.0F, -48.0F, 0.0F);
        this.heart_face_east.addBox(-30.0F, -16.0F, -16.0F, 6, 32, 32, 0.0F);
        this.heart_face_north = new ModelRenderer(this, 158, 174);
        this.heart_face_north.setRotationPoint(0.0F, -48.0F, 0.0F);
        this.heart_face_north.addBox(-16.0F, -16.0F, -30.0F, 32, 32, 6, 0.0F);
        this.heart_face_west = new ModelRenderer(this, 100, 189);
        this.heart_face_west.setRotationPoint(0.0F, -48.0F, 0.0F);
        this.heart_face_west.addBox(24.0F, -16.0F, -16.0F, 6, 32, 32, 0.0F);
        this.bottom_tubes = new ModelRenderer(this, 64, 128);
        this.bottom_tubes.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.bottom_tubes.addBox(-18.0F, 0.0F, -2.0F, 24, 32, 0, 0.0F);
        this.setRotateAngle(bottom_tubes, 0.0F, 0.7853981633974483F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.top_connector.render(f5);
        this.heart_main.render(f5);
        this.heart_face_north_1.render(f5);
        this.bottom_shape_connector.render(f5);
        this.top_tubes.render(f5);
        this.heart_face_south.render(f5);
        this.bottom_connector.render(f5);
        this.heart_face_east.render(f5);
        this.heart_face_north.render(f5);
        this.heart_face_west.render(f5);
        this.bottom_tubes.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
