package net.jitl.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.jitl.JITL;
import net.jitl.client.render.entity.layer.MiniBoomChargeLayer;
import net.jitl.client.render.model.HongoModel;
import net.jitl.client.render.model.MiniBoomModel;
import net.jitl.common.entity.overworld.HongoEntity;
import net.jitl.common.entity.pet.MiniBoomEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CreeperChargeLayer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class MiniBoomRenderer extends MobRenderer<MiniBoomEntity, MiniBoomModel<MiniBoomEntity>> {

    public MiniBoomRenderer(EntityRendererManager e) {
        super(e, new MiniBoomModel(), 0.2F);
        this.addLayer(new MiniBoomChargeLayer(this));

    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull MiniBoomEntity entityIn) {
        return JITL.rl("textures/entity/overworld/boomboom.png");
    }

    @Override
    protected void scale(MiniBoomEntity boom, MatrixStack stack, float timer) {
        stack.scale(0.4F, 0.4F, 0.4F);

        float swell = boom.getSwelling(timer);
        float size = 1.0F + MathHelper.sin(swell * 100.0F) * swell * 0.01F;
        swell = MathHelper.clamp(swell, 0.0F, 1.0F);
        swell *= swell;
        swell *= swell;
        float x = (1.0F + swell * 0.4F) * size;
        float y = (1.0F + swell * 0.1F) / size;
        stack.scale(x, y, x);
    }

    @Override
    protected float getWhiteOverlayProgress(MiniBoomEntity boom, float amount) {
        float f = boom.getSwelling(amount);
        return (int)(f * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(f, 0.5F, 1.0F);
    }


}