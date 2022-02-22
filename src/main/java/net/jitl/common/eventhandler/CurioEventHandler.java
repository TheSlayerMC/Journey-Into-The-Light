package net.jitl.common.eventhandler;

import net.jitl.core.JITL;
import net.jitl.core.init.JItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class CurioEventHandler {

    @SubscribeEvent
    public static void onPlayerAttacked(LivingHurtEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (!entity.level.isClientSide()) {
            if (entity instanceof Player player) {
                Optional<ImmutableTriple<String, Integer, ItemStack>> equippedCurio = CuriosApi.getCuriosHelper().findEquippedCurio(JItems.SKULL_OF_DECAY, entity);
                if (equippedCurio.isPresent()) {
                    if (event.getSource().getDirectEntity() instanceof LivingEntity attacker) {
                        attacker.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
                        equippedCurio.get().getRight().hurtAndBreak(1, player, (context) -> context.broadcastBreakEvent(player.getUsedItemHand()));
                    }
                }
            }
        }
    }

    public static void onKeyPressed(Player player) {

    }
}
