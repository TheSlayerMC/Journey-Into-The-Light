package net.jitl.client.render.model.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.jitl.client.render.tile.ObeliskTER;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ObeliskModel extends Model {

    public ModelRenderer main_block;
    public ModelRenderer top_block;

    public ObeliskModel() {
        super(ObeliskTER::obelisk);
        this.texWidth = 64;
        this.texHeight = 64;
        this.top_block = new ModelRenderer(this, 0, 32);
        this.top_block.setPos(0.0F, 0.0F, 0.0F);
        this.top_block.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
        this.main_block = new ModelRenderer(this, 0, 0);
        this.main_block.setPos(0.0F, 16.0F, 0.0F);
        this.main_block.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    }

    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, boolean rotate) {
        if (!rotate) {
            this.main_block.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        } else {
            this.top_block.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        }
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {

    }
}
