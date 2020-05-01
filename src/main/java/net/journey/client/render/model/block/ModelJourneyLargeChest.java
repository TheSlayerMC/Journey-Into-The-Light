package net.journey.client.render.model.block;

import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelJourneyLargeChest extends ModelJourneyChest {
    public ModelJourneyLargeChest() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.chestbase = new ModelRenderer(this, 0, 24);
        this.chestbase.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.chestbase.addBox(0.0F, 0.0F, 0.0F, 32, 10, 16, 0.0F);
        this.chestlid = new ModelRenderer(this, 0, 0);
        this.chestlid.setRotationPoint(16.0F, 6.0F, 16.0F);
        this.chestlid.addBox(-16.0F, -6.0F, -16.0F, 32, 6, 16, 0.0F);
        this.chestnotch = new ModelRenderer(this, 0, 0);
        this.chestnotch.setRotationPoint(16.0F, 6.0F, 16.0F);
        this.chestnotch.addBox(-1.0F, -3.0F, -17.0F, 2, 4, 1, 0.0F);
    }
}