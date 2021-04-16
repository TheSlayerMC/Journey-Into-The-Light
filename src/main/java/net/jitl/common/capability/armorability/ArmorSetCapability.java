package net.jitl.common.capability.armorability;

import net.jitl.JITL;
import net.jitl.common.capability.JCapabilityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class ArmorSetCapability implements IArmorSetCapability {
    private ArrayList<Item> armorPieces;
    private Item fullSetItem;

    public void setArmor(Iterable<ItemStack> stacks) {

    }

    public ArrayList<Item> getArmor() {
        return armorPieces;
    }

    public Item getFullSet() {
        return fullSetItem;
    }

    @SubscribeEvent()
    public static void registerCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (!event.getObject().level.isClientSide() && event.getObject() instanceof LivingEntity) {
            event.addCapability(JITL.rl("current_armor"), new JCapabilityProvider());
        }
    }
}
