package net.jitl.common.eventhandler;

import net.jitl.core.JITL;
import net.jitl.core.init.JItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class CurioEventHandler {

    @SubscribeEvent
    public static void onPlayerAttacked(LivingHurtEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (!entity.level.isClientSide()) {
            if (entity instanceof Player player) {
                Optional<SlotResult> equippedCurio = getEquippedCurio(player, JItems.SKULL_OF_DECAY);
                if (equippedCurio.isPresent()) {
                    if (event.getSource().getEntity() instanceof LivingEntity attacker) {
                        attacker.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
                        equippedCurio.get().stack().hurtAndBreak(1, player, (context) -> context.broadcastBreakEvent(player.getUsedItemHand()));
                    }
                }
            }
        }
    }

    private static Optional<SlotResult> getEquippedCurio(LivingEntity entity, Item curio) {
        return CuriosApi.getCuriosHelper().findFirstCurio(entity, curio);
    }

    public static void onKeyPressed(Player player) {

    }
}
