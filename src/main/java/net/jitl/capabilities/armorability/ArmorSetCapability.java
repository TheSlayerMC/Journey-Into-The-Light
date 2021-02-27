package net.jitl.capabilities.armorability;

import net.jitl.JITL;
import net.jitl.common.item.gearabilities.BaseAbilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class ArmorSetCapability implements IArmorSetCapability {
    private BaseAbilities armorAbility;

    public void setArmor(BaseAbilities setAbility) {
        armorAbility = setAbility;
    }

    public BaseAbilities getArmor() {
        return armorAbility;
    }

    @SubscribeEvent()
    public static void registerCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (!event.getObject().level.isClientSide() && event.getObject() instanceof LivingEntity) {
            event.addCapability(JITL.rl("current_armor"), new ArmorSetProvider());
        }
    }
}
