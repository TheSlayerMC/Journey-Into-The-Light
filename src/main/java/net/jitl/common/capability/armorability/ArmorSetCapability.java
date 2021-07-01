package net.jitl.common.capability.armorability;

import net.jitl.common.item.gear.JArmorItem;
import net.jitl.common.item.gear.abilities.FullArmorAbility;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import java.util.ArrayList;
import java.util.Iterator;

public class ArmorSetCapability implements IArmorSetCapability {
    private ArrayList<ItemStack> armorPieces;
    private FullArmorAbility fullSet;
    private CompoundNBT nbt = new CompoundNBT();

    public void setArmor(Iterator<ItemStack> iterator) {
        ArrayList<ItemStack> stacks = new ArrayList<>();
        ItemStack currentStack = iterator.next();
        Item item = currentStack.getItem();
        IArmorMaterial material = null;
        FullArmorAbility fullArmorAbility = null;
        if (item instanceof JArmorItem) {
            stacks.add(currentStack);
            fullArmorAbility = ((JArmorItem) item).getAbility().getFullAbility(nbt);
            if (fullArmorAbility != null) {
                material = ((JArmorItem) item).getMaterial();
            }
        }
        while (iterator.hasNext()) {
            currentStack = iterator.next();
            item = currentStack.getItem();
            if (item instanceof JArmorItem) {
                if (((JArmorItem) item).getMaterial() != material) {
                    fullArmorAbility = null;
                    material = null;
                }
                stacks.add(currentStack);
            } else {
                fullArmorAbility = null;
                material = null;
            }
        }
        stacks.trimToSize();
        armorPieces = stacks;
        fullSet = fullArmorAbility;
    }

    public ArrayList<ItemStack> getArmor() {
        return armorPieces;
    }

    public FullArmorAbility getFullArmor() {
        return fullSet;
    }

    @Override
    public CompoundNBT getNBT() {
        return nbt;
    }

    @Override
    public void setNBT(CompoundNBT nbt) {
        this.nbt = nbt;
    }
}
