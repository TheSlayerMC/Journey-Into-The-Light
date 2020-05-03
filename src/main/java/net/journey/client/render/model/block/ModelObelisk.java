package net.journey.client.render.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * Obelisk - Dizzlepop12
 * Created using Tabula 7.1.0
 */
public class ModelObelisk extends ModelBase {
    public ModelRenderer main_block;
    public ModelRenderer top_block;

    public ModelObelisk() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.top_block = new ModelRenderer(this, 0, 32);
        this.top_block.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.top_block.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
        this.main_block = new ModelRenderer(this, 0, 0);
        this.main_block.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.main_block.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    }

    public void render(float f5, boolean rotate) {
        if (!rotate) {
            this.main_block.render(f5);
        } else {
            this.top_block.render(f5);
        }
    }
}