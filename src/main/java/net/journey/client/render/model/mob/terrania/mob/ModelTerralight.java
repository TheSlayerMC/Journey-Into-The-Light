package net.journey.client.render.model.mob.terrania.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * terraLight - Dizzlepop12
 * Created using Tabula 5.1.0
 */
public class ModelTerralight extends ModelBase {
    public ModelRenderer shape1;

    public ModelTerralight() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.shape1 = new ModelRenderer(this, 10, 10);
        this.shape1.setRotationPoint(-2.0F, 16.0F, 0.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.shape1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
