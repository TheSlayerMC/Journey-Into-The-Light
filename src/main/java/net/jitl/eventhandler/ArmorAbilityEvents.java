package net.jitl.eventhandler;

import net.jitl.JITL;
import net.jitl.common.helper.JArmorMaterial;
import net.jitl.common.item.JArmorItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class ArmorAbilityEvents {
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent()
    public static void armorChange(LivingEquipmentChangeEvent event) {
        LOGGER.debug("Armor change");
        LivingEntity entity = event.getEntityLiving();
        Iterator armorSlots = entity.getArmorSlots().iterator();
        Item currentItem = ((ItemStack) armorSlots.next()).getItem();
        JArmorMaterial material = null;
        if (currentItem instanceof JArmorItem) {
            material = (JArmorMaterial) ((JArmorItem) currentItem).getMaterial();
            while (armorSlots.hasNext()) {
                currentItem = ((ItemStack) armorSlots.next()).getItem();
                if (!(currentItem instanceof JArmorItem && ((JArmorItem) currentItem).getMaterial() == material)) {
                    material = null;
                    break;
                }
            }
        }
        if (material != null) {
            LOGGER.debug("Full set on " + entity.getType().getRegistryName().getPath() + ": " + material.getName());
        }
    }
}
