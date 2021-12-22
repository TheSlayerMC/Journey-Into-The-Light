package net.jitl.client.render.model.frozen;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class PhantasmModel<T extends Entity> extends SegmentedModel<T> {
    private final ModelRenderer outer;
    private final ModelRenderer inner;

    public PhantasmModel() {
        texWidth = 32;
        texHeight = 32;

        outer = new ModelRenderer(this);
        outer.setPos(0.0F, 0.0F, 0.0F);
        outer.texOffs(0, 0).addBox(-4.0F, 16.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        inner = new ModelRenderer(this);
        inner.setPos(0.0F, 0.0F, 0.0F);
        inner.texOffs(0, 16).addBox(-3.0F, 17.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(outer, inner);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}
