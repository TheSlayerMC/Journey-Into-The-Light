package net.journey.client.render.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelJourneyChest extends ModelBase {
    public ModelRenderer chestbase;
    public ModelRenderer chestlid;
    public ModelRenderer chestnotch;

    public ModelJourneyChest() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.chestlid = new ModelRenderer(this, 0, 0);
        this.chestlid.setRotationPoint(8.0F, 6.0F, 16.0F);
        this.chestlid.addBox(-8.0F, -6.0F, -16.0F, 16, 6, 16, 0.0F);
        this.chestnotch = new ModelRenderer(this, 0, 0);
        this.chestnotch.setRotationPoint(8.0F, 6.0F, 16.0F);
        this.chestnotch.addBox(-1.0F, -3.0F, -17.0F, 2, 4, 1, 0.0F);
        this.chestbase = new ModelRenderer(this, 0, 24);
        this.chestbase.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.chestbase.addBox(0.0F, 0.0F, 0.0F, 16, 10, 16, 0.0F);

    }

    public void renderAll() {
        this.chestnotch.rotateAngleX = this.chestlid.rotateAngleX;
        this.chestlid.render(0.0625F);
        this.chestnotch.render(0.0625F);
        this.chestbase.render(0.0625F);
    }
}