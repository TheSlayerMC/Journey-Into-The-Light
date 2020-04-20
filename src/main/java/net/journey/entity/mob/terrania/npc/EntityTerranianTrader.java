package net.journey.entity.mob.terrania.npc;

import net.journey.client.handler.GuiHandler;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityTerranianTrader extends EntityModVillager {

    public EntityTerranianTrader(World var1) {
        super(var1);
        setSize(2.0F, 4.0F);
    }

    @Override
    public void abstractInteract(EntityPlayer p) {
        switch (rand.nextInt(3)) {
            case 0:
                SlayerAPI.addFormattedChatMessage(p, "smith.valuables");
                break;
            case 1:
                SlayerAPI.addFormattedChatMessage(p, "smith.greetings");
                break;
            case 2:
                SlayerAPI.addFormattedChatMessage(p, "smith.deals");
                break;
        }
    }

    @Override
    public int guiID() {
        return GuiHandler.terranian;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
        list.add(new MerchantRecipe(new ItemStack(JourneyItems.purplePowder, 16), new ItemStack(JourneyItems.darkTerrarianSoil, 32), new ItemStack(JourneyWeapons.darkTerraBow)));
        list.add(new MerchantRecipe(new ItemStack(JourneyItems.purplePowder, 16), new ItemStack(JourneyItems.lightTerrarianSoil, 32), new ItemStack(JourneyWeapons.lavenderBow)));
    }
}