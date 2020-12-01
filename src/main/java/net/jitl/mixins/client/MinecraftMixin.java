package net.jitl.mixins.client;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Minecraft.class)
public class MinecraftMixin {
//
//    @ModifyVariable(method = "doLoadLevel",
//            at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/Lifecycle;stable()Lcom/mojang/serialization/Lifecycle;"),
//            index = 9, require = 1)
//    private boolean disableExperimentalWarning(boolean flag1) {
//        System.out.println("Changed flag1, from: " + flag1);
//        return false;
//    }
}
