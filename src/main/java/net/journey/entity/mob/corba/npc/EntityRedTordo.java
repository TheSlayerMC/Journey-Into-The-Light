package net.journey.entity.mob.corba.npc;

import net.journey.JourneyCrops;
import net.journey.JourneyItems;
import net.journey.client.GuiHandler;
import net.journey.entity.JourneyMerchantRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityRedTordo extends EntityModVillager {

    public EntityRedTordo(World par1World) {
        super(par1World);
        setSize(0.7F, 2.5F);
    }

    @Override
    public void abstractInteract(EntityPlayer p) {
        switch (rand.nextInt(3)) {
            case 0:
                SlayerAPI.addFormattedChatMessage(p, "Tordo: It sure is easy to break a limb, especially when you're completely made out of sticks.");
                break;
            case 1:
                SlayerAPI.addFormattedChatMessage(p, "Tordo: I'm not sure where you come from, but you don't look like a normal inhabitant.");
                break;
            case 2:
                SlayerAPI.addFormattedChatMessage(p, "Tordo: My crops are grown to perfection and treated with great nutriants!");
                break;
        }

    }

    @Override
    public int guiID() {
        return GuiHandler.tordo;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.natureTablet, 15), new ItemStack(JourneyItems.collectorRock, 15), new ItemStack(JourneyCrops.tomatoSeeds, 15)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.natureTablet, 15), new ItemStack(JourneyItems.collectorRock, 15), new ItemStack(JourneyCrops.corveggieSeeds, 15)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.natureTablet, 15), new ItemStack(JourneyItems.collectorRock, 15), new ItemStack(JourneyCrops.crackenCaneSeeds, 15)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.natureTablet, 15), new ItemStack(JourneyItems.collectorRock, 15), new ItemStack(JourneyCrops.crakeBulbSeeds, 15)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.natureTablet, 15), new ItemStack(JourneyItems.collectorRock, 15), new ItemStack(JourneyCrops.spineberrySeeds, 15)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.natureTablet, 15), new ItemStack(JourneyItems.collectorRock, 15), new ItemStack(JourneyCrops.glowaSeeds, 15)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.natureTablet, 15), new ItemStack(JourneyItems.collectorRock, 15), new ItemStack(JourneyCrops.zatSeeds, 15)));

    }
}