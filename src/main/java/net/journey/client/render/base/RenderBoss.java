package net.journey.client.render.base;

import net.journey.api.entity.IEssenceBoss;
import net.journey.util.EnumHexColor;
import net.journey.util.JourneyBossStatus;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderBoss extends RenderSizeable {

    private final String bar;
    public EnumHexColor stringHexColor;
    public EnumHexColor stringOutlineColor;
    boolean splitText = false;

    public RenderBoss(ModelBase model, float shadow, float size, ResourceLocation tex, final String bar, EnumHexColor bossTextColor, EnumHexColor textOutlineColor) {
        super(model, shadow, size, tex);
        this.bar = bar;
        this.stringHexColor = bossTextColor;
        this.stringOutlineColor = textOutlineColor;
    }

    //TODO: fix the rendering for boss health bars
    @Override
    public void doRender(EntityLiving par1Entity, double par2, double par4, double par6, float par8, float par9) {
        JourneyBossStatus.setStatus((IEssenceBoss) par1Entity, bar, stringHexColor, stringOutlineColor);
        super.doRender(par1Entity, par2, par4, par6, par8, par9);
    }

    //TODO: Finish split text for boss bars
    public RenderBoss splitText() {
        this.splitText = true;
        return this;
    }
}
