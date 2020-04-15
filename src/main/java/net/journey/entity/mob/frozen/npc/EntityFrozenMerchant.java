package net.journey.entity.mob.frozen.npc;

import net.journey.client.GuiHandler;
import net.journey.entity.JourneyMerchantRecipe;
import net.journey.init.items.JourneyArmory;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityFrozenMerchant extends EntityModVillager {

    public EntityFrozenMerchant(World var1) {
        super(var1);
    }

    @Override
    public void abstractInteract(EntityPlayer p) {
        switch (rand.nextInt(3)) {
            case 0:
                SlayerAPI.addFormattedChatMessage(p, "elf.welcome");
                break;
            case 1:
                SlayerAPI.addFormattedChatMessage(p, "elf.hello");
                break;
            case 2:
                SlayerAPI.addFormattedChatMessage(p, "elf.good");
                break;
        }
    }

    @Override
    public int guiID() {
        return GuiHandler.frozenMerchant;
    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.crystalFlake, 15), new ItemStack(JourneyItems.frostGem, 15), new ItemStack(JourneyWeapons.frostySword, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.crystalFlake, 15), new ItemStack(JourneyItems.frostGem, 15), new ItemStack(JourneyWeapons.frostyBow, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.crystalFlake, 10), new ItemStack(JourneyItems.frostGem, 10), new ItemStack(JourneyWeapons.frostyPiercer, 15)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.frostFlake, 15), new ItemStack(JourneyWeapons.frostySword, 1), new ItemStack(JourneyWeapons.frostbittenSword, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.frostFlake, 15), new ItemStack(JourneyWeapons.frostyBow, 1), new ItemStack(JourneyWeapons.frostbittenBow, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.frostFlake, 10), new ItemStack(JourneyWeapons.frostyPiercer, 10), new ItemStack(JourneyWeapons.frostbittenPiercer, 15)));

        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.crystalFlake, 4), new ItemStack(JourneyItems.frostGem, 15), new ItemStack(JourneyArmory.crystalFlakeHelmet, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.crystalFlake, 4), new ItemStack(JourneyItems.frostGem, 15), new ItemStack(JourneyArmory.crystalFlakeChest, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.crystalFlake, 4), new ItemStack(JourneyItems.frostGem, 15), new ItemStack(JourneyArmory.crystalFlakeLegs, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.crystalFlake, 4), new ItemStack(JourneyItems.frostGem, 15), new ItemStack(JourneyArmory.crystalFlakeBoots, 1)));

        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.frostFlake, 4), new ItemStack(JourneyArmory.crystalFlakeHelmet), new ItemStack(JourneyArmory.frostbittenHelmet, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.frostFlake, 4), new ItemStack(JourneyArmory.crystalFlakeChest), new ItemStack(JourneyArmory.frostbittenChest, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.frostFlake, 4), new ItemStack(JourneyArmory.crystalFlakeLegs), new ItemStack(JourneyArmory.frostbittenLegs, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.frostFlake, 4), new ItemStack(JourneyArmory.crystalFlakeBoots), new ItemStack(JourneyArmory.frostbittenBoots, 1)));
    }
}