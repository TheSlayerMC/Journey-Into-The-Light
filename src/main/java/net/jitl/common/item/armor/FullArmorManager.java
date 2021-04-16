package net.jitl.common.item.armor;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public abstract class FullArmorManager {
    public ItemStack[] stacks = new ItemStack[4];

    public FullArmorManager(ItemStack boots, ItemStack legs, ItemStack chest, ItemStack helm) {
        stacks[0] = boots;
        stacks[1] = legs;
        stacks[2] = chest;
        stacks[3] = helm;
    }

    public void setTags(CompoundNBT[] tags) {
        for (int count = 0; count < stacks.length; count++) {
            stacks[count].setTag(tags[count]);
        }
    }

    public CompoundNBT[] getTags() {
        CompoundNBT[] tags = new CompoundNBT[4];
        for (int count = 0; count < tags.length; count++) {
            tags[count] = getTag(stacks[count]);
        }
        return tags;
    }

    public void setInts(String key, int value) {
        for (ItemStack stack : stacks) {
            CompoundNBT tag = getTag(stack);
            tag.putInt(key, value);
            stack.setTag(tag);
        }
    }

    public void setFloats(String key, float value) {
        for (ItemStack stack : stacks) {
            CompoundNBT tag = getTag(stack);
            tag.putFloat(key, value);
            stack.setTag(tag);
        }
    }

    public int getMaxInt(String key) {
        int value = 0;
        for (ItemStack stack : stacks) {
            value = Math.max(getTag(stack).getInt(key), value);
        }
        return value;
    }

    public float getMaxFloat(String key) {
        float value = 0;
        for (ItemStack stack : stacks) {
            value = Math.max(getTag(stack).getFloat(key), value);
        }
        return value;
    }

    private CompoundNBT getTag(ItemStack stack) {
        return stack.hasTag() ? stack.getTag() : new CompoundNBT();
    }
}
