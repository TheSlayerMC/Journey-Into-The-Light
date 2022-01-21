package net.jitl.core.mixins.client;

import net.jitl.core.init.world.Dimensions;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LevelRenderer.class)
public class EucaSkyFixer {
    
    @Shadow
    private ClientLevel level;
    
    @ModifyVariable(at = @At(value = "STORE"), method = "renderSky", ordinal = 0)
    private double eucaSkyRender(double d0) {
        if(this.level.dimension() == Dimensions.EUCA) {
            return 1.0D;
        }
        return d0;
    }
}
