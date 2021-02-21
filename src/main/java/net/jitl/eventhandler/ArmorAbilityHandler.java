package net.jitl.eventhandler;

import net.jitl.JITL;
import net.jitl.capabilities.armorcapability.ArmorSetProvider;
import net.jitl.capabilities.armorcapability.IArmorSetCapability;
import net.jitl.common.helper.JArmorMaterial;
import net.jitl.common.item.JArmorItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class ArmorAbilityHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent()
    public static void armorChange(LivingEquipmentChangeEvent event) {
        LOGGER.debug("Armor change");
        LivingEntity entity = event.getEntityLiving();
        JArmorMaterial material = null;
        for (ItemStack currentStack : entity.getArmorSlots()) {
            if (currentStack.getItem() instanceof JArmorItem) { //current piece is part of a jitl set
                JArmorMaterial currentMaterial = (JArmorMaterial) ((JArmorItem) currentStack.getItem()).getMaterial();
                if (currentMaterial != material) { //is the current part different from the previous, or has the check simply not started?
                    if (material != null) { //a part of the set is different. Loop should end
                        material = null;
                        break;
                    } else {
                        material = currentMaterial; //changed the material
                    }
                }
            } else { //a jitl armor piece is not being worn. This makes it impossible to have a full set
                material = null;
                break;
            }
        }
        if (material != null) {
            Optional<IArmorSetCapability> optional = entity.getCapability(ArmorSetProvider.ARMOR).resolve();
            if (optional.isPresent()) {
                optional.get().setArmor(material);
                LOGGER.debug("Full set on " + entity.getType().getRegistryName().getPath() + ": " + optional.get().getArmor());
            }
        }
    }
}
