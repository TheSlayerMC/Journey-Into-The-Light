package net.jitl.common.eventhandler;

import net.jitl.JITL;
import net.jitl.init.JAttributes;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributeEventHandler {

    @SubscribeEvent
    public static void addAttributes(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, JAttributes.MAX_ESSENCE.get());
        event.add(EntityType.PLAYER, JAttributes.ESSENCE_REGEN_SPEED.get());
        event.add(EntityType.PLAYER, JAttributes.ESSENCE_BURNOUT.get());
    }
}
