package net.jitl.common.capability.armorability;

import net.jitl.common.item.gear.JArmorItem;
import net.jitl.common.item.gear.abilities.FullArmorAbility;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;
import java.util.Iterator;

public class ArmorSetCapability implements IArmorSetCapability {
    private ArrayList<ItemStack> armorPieces;
    private FullArmorAbility fullSet;
    private CompoundTag nbt = new CompoundTag();

    public void setArmor(Iterator<ItemStack> iterator) {
        ArrayList<ItemStack> stacks = new ArrayList<>();
        ItemStack currentStack = iterator.next();
        Item item = currentStack.getItem();
        ArmorMaterial material = null;
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
    public CompoundTag getNBT() {
        return nbt;
    }

    @Override
    public void setNBT(CompoundTag nbt) {
        this.nbt = nbt;
    }
}
