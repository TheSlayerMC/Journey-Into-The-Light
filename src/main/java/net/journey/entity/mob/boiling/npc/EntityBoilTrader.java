package net.journey.entity.mob.boiling.npc;

import net.journey.client.GuiHandler;
import net.journey.entity.JourneyMerchantRecipe;
import net.journey.init.items.JourneyArmory;
import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityBoilTrader extends EntityModVillager {

    public EntityBoilTrader(World var1) {
        super(var1);
        this.isImmuneToFire = true;

    }

    @Override
    public void abstractInteract(EntityPlayer p) {
        switch (rand.nextInt(3)) {
            case 0:
                SlayerAPI.addFormattedChatMessage(p, "Boil Trader: It's not often that newcomers arrive here. Can you stand the heat?");
                break;
            case 1:
                SlayerAPI.addFormattedChatMessage(p, "Boil Trader: I'm an unusual collector, but I have the best deals of any realm!");
                break;
            case 2:
                SlayerAPI.addFormattedChatMessage(p, "Boil Trader: I can see that you're having a tough time getting around. An armor upgrade, perhaps?");
                break;
        }
    }

    @Override
    public int guiID() {
        return GuiHandler.boilTrader;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.ash, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyWeapons.flamingBow, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.ash, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyWeapons.boilingBlade, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.ash, 10), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyWeapons.moltenKnife, 15)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.boilingSkull, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyArmory.charskullHelmet, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.boilingSkull, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyArmory.charskullChest, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.boilingSkull, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyArmory.charskullLegs, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.boilingSkull, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyArmory.charskullBoots, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyConsumables.snakeFlesh, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyArmory.snakeskinHelmet, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyConsumables.snakeFlesh, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyArmory.snakeskinChest, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyConsumables.snakeFlesh, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyArmory.snakeskinLegs, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyConsumables.snakeFlesh, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyArmory.snakeskinBoots, 1)));

    }
}