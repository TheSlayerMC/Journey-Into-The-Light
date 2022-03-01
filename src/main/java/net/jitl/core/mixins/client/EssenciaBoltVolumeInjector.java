package net.jitl.core.mixins.client;

import net.jitl.common.entity.EssenciaBoltEntity;
import net.minecraft.world.entity.LightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LightningBolt.class)
public class EssenciaBoltVolumeInjector {

    @ModifyConstant(method = "tick", constant = @Constant(floatValue = 2.0F))
    private float injectStrikeVolume(float constant) {
        if ((LightningBolt) (Object) this instanceof EssenciaBoltEntity essenciaBoltEntity) {
            return essenciaBoltEntity.getStrikeVolume();
        } else {
            return constant;
        }
    }

    @ModifyConstant(method = "tick", constant = @Constant(floatValue = 10000.0F))
    private float injectThunderVolume(float constant) {
        if ((LightningBolt) (Object) this instanceof EssenciaBoltEntity essenciaBoltEntity) {
            return essenciaBoltEntity.getThunderVolume();
        } else {
            return constant;
        }
    }
}
