package net.journey.util.recipes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class IncubatorRecipes {
	
    private static final IncubatorRecipes smeltingBase = new IncubatorRecipes();
    private Map smeltingList = new HashMap();
    private Map experienceList = new HashMap();

    public static IncubatorRecipes smelting() {
        return smeltingBase;
    }

    private IncubatorRecipes() {

        /*this.smeltItem(EssenceItems.redEgg, new ItemStack(EssenceItems.incubatedRedEgg), 0.2F);
        this.smeltItem(EssenceItems.greenEgg, new ItemStack(EssenceItems.incubatedGreenEgg), 0.2F);
        this.smeltItem(EssenceItems.blueEgg, new ItemStack(EssenceItems.incubatedBlueEgg), 0.2F);
        this.smeltItem(EssenceItems.orangeEgg, new ItemStack(EssenceItems.incubatedOrangeEgg), 0.2F);
        this.smeltItem(EssenceItems.purpleEgg, new ItemStack(EssenceItems.incubatedPurpleEgg), 0.2F);
        this.smeltItem(EssenceItems.yellowEgg, new ItemStack(EssenceItems.incubatedYellowEgg), 0.2F);
        this.smeltItem(EssenceItems.pinkEgg, new ItemStack(EssenceItems.incubatedPinkEgg), 0.2F);
        this.smeltItem(EssenceItems.cyanEgg, new ItemStack(EssenceItems.incubatedCyanEgg), 0.2F);*/
    }

    public void smeltBlock(Block b, ItemStack i, float xp) {
        this.smeltItem(Item.getItemFromBlock(b), i, xp);
    }

    public void smeltItem(Item it, ItemStack i, float xp) {
        this.smelt(new ItemStack(it, 1, 32767), i, xp);
    }

    public void smelt(ItemStack i, ItemStack i1, float x) {
        this.smeltingList.put(i, i1);
        this.experienceList.put(i1, Float.valueOf(x));
    }

    public ItemStack getSmeltingResult(ItemStack i) {
        Iterator iterator = this.smeltingList.entrySet().iterator();
        Entry entry;
        do {
            if(!iterator.hasNext()) {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while(!this.func_151397_a(i, (ItemStack)entry.getKey()));
        return (ItemStack)entry.getValue();
    }

    private boolean func_151397_a(ItemStack i, ItemStack i1) {
        return i1.getItem() == i.getItem() && (i1.getItemDamage() == 32767 || i1.getItemDamage() == i.getItemDamage());
    }

    public Map getSmeltingList() {
        return this.smeltingList;
    }
}