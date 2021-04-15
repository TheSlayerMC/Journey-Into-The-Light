package net.jitl.common.capability.armorability;

import net.jitl.JITL;
import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.common.helper.JArmorMaterial;
import net.jitl.common.item.JArmorItem;
import net.jitl.common.item.gearabilities.FullArmorAbilities;
import net.jitl.common.item.gearabilities.PieceArmorAbilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class ArmorSetCapability implements IArmorSetCapability {
    private PieceArmorAbilities[] armorAbility = new PieceArmorAbilities[4];
    private FullArmorAbilities fullSet;

    public void setArmor(int slot, PieceArmorAbilities piece) {
        armorAbility[slot] = piece;
        refreshSet();
        if (armorAbility[0] != null) {
            System.out.println("Boot material = " + ((JArmorItem)armorAbility[0].getStack().getItem()).getMaterial().getName());
        }
        if (armorAbility[1] != null) {
            System.out.println("Leg material = " + ((JArmorItem)armorAbility[1].getStack().getItem()).getMaterial().getName());
        }
        if (armorAbility[2] != null) {
            System.out.println("Chest material = " + ((JArmorItem)armorAbility[2].getStack().getItem()).getMaterial().getName());
        }
        if (armorAbility[3] != null) {
            System.out.println("Helmet material = " + ((JArmorItem)armorAbility[3].getStack().getItem()).getMaterial().getName());
        }
        if (fullSet != null) {
            System.out.println("Full set = " + fullSet.getClass().toString());
        }
    }

    private void refreshSet() {
        IArmorMaterial material = null;
        for (PieceArmorAbilities current : armorAbility) {
            if (current != null) {
                IArmorMaterial currentMaterial = ((JArmorItem) current.getStack().getItem()).getMaterial();
                if (currentMaterial != material) {
                    if (material != null) {
                        return;
                    } else {
                        material = currentMaterial;
                    }
                }
            } else {
                return;
            }
        }
        fullSet = ((JArmorMaterial) material).getFullAbility(); //TODO: fix
        if (fullSet != null) fullSet.setStacks(armorAbility[0].getStack(), armorAbility[1].getStack(), armorAbility[2].getStack(), armorAbility[3].getStack());
    }

    public PieceArmorAbilities[] getArmor() {
        return armorAbility;
    }

    public FullArmorAbilities getFullAbilities() {
        return fullSet;
    }

    @SubscribeEvent()
    public static void registerCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (!event.getObject().level.isClientSide() && event.getObject() instanceof LivingEntity) {
            event.addCapability(JITL.rl("current_armor"), new JCapabilityProvider());
        }
    }
}
