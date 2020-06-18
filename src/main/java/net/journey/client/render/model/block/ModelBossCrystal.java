package net.journey.client.render.model.block;// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelBossCrystal extends ModelBase {
    private final ModelRenderer crystal;

    public ModelBossCrystal() {
        textureWidth = 32;
        textureHeight = 32;

        crystal = new ModelRenderer(this);
        crystal.setRotationPoint(-3.0F, 11.0F, 3.0F);
        setRotationAngle(crystal, 0.5236F, -0.5236F, 0.5236F);
        crystal.cubeList.add(new ModelBox(crystal, 0, 0, -2.377F, -7.7955F, -5.7291F, 6, 8, 7, 0.0F, false));
    }

    public void render(float f5, boolean rotate) {
        this.crystal.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}