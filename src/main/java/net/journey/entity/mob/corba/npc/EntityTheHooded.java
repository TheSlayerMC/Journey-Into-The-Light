package net.journey.entity.mob.corba.npc;

import net.journey.client.handler.GuiHandler;
import net.journey.entity.JourneyMerchantRecipe;
import net.journey.init.common.JourneyCrops;
import net.journey.init.items.JourneyItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityTheHooded extends EntityModVillager {

    public EntityTheHooded(World par1World) {
        super(par1World);
        setSize(0.7F, 2.5F);
    }

    @Override
    public void abstractInteract(EntityPlayer p) {
        switch (rand.nextInt(1)) {
            case 0:
                SlayerAPI.addFormattedChatMessage(p, "The Hooded: ...");
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
    }
}