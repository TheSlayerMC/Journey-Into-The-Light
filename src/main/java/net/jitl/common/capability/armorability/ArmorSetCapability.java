package net.jitl.common.capability.armorability;

import net.jitl.common.item.FullArmorAbility;
import net.jitl.common.item.gear.base.JArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;

public class ArmorSetCapability implements IArmorSetCapability {
    private ArrayList<ItemStack> armorPieces;
    private FullArmorAbility fullSet;

    public void setArmor(Iterator<ItemStack> iterator) {
        ArrayList<ItemStack> stacks = new ArrayList<>();
        ItemStack currentStack = iterator.next();
        Item item = currentStack.getItem();
        boolean isFull = false;
        IArmorMaterial material = null;
        if (item instanceof JArmorItem) {
            stacks.add(currentStack);
            if (item instanceof FullArmorAbility) {
                isFull = true;
                material = ((JArmorItem) item).getMaterial();
            }
        }
        while (iterator.hasNext()) {
            currentStack = iterator.next();
            item = currentStack.getItem();
            if (item instanceof JArmorItem) {
                if (((JArmorItem) item).getMaterial() != material) {
                    isFull = false;
                }
                stacks.add(currentStack);
            } else {
                isFull = false;
            }
        }
        stacks.trimToSize();
        armorPieces = stacks;
        if (isFull) fullSet = (FullArmorAbility) stacks.get(0).getItem();
    }

    public ArrayList<ItemStack> getArmor() {
        return armorPieces;
    }

    public FullArmorAbility getFullArmor() {
        return fullSet;
    }
}
