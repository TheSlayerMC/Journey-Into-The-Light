package net.jitl.common.eventhandler;

import net.jitl.JITL;
import net.jitl.init.JItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class CurioEventHandler {

    @SubscribeEvent
    public static void onPlayerAttacked(LivingHurtEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (!entity.level.isClientSide()) {
            if (entity instanceof Player) {
                if (CuriosApi.getCuriosHelper().findEquippedCurio(JItems.SKULL_OF_DECAY, entity).isPresent()) {
                    if (event.getSource().getDirectEntity() instanceof LivingEntity) {
                        LivingEntity attacker = (LivingEntity) event.getSource().getDirectEntity();
                        attacker.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
                    }
                }
            }
        }
    }

    public static void onKeyPressed(Player player) {

    }
}
