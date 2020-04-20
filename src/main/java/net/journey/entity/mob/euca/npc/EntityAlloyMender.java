package net.journey.entity.mob.euca.npc;

import net.journey.client.handler.GuiHandler;
import net.journey.entity.JourneyMerchantRecipe;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityAlloyMender extends EntityModVillager {

    public EntityAlloyMender(World var1) {
        super(var1);
    }

    @Override
    public void abstractInteract(EntityPlayer p) {
        switch (rand.nextInt(3)) {
            case 0:
                SlayerAPI.addFormattedChatMessage(p, "Mender: It takes over a thousand degreese to melt this gold!");
                break;
            case 1:
                SlayerAPI.addFormattedChatMessage(p, "Mender: My weapons have been melted and shaped into deadly perfection!");
                break;
            case 2:
                SlayerAPI.addFormattedChatMessage(p, "Mender: It's a real struggle living here, when everything wants to kill you...");
                break;
        }
    }

    @Override
    public int guiID() {
        return GuiHandler.alloyMender;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.shimmerdust, 64), new ItemStack(JourneyItems.metalDisk, 1), new ItemStack(JourneyWeapons.royalBow, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.golderDust, 16), new ItemStack(JourneyItems.goldClump, 16), new ItemStack(JourneyWeapons.royalBlade, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.shimmerdust, 64), new ItemStack(JourneyItems.metalDisk, 2), new ItemStack(JourneyWeapons.royalHammer, 1)));
        //list.add(new MerchantRecipe(new ItemStack(JourneyItems.golderDust, 10), new ItemStack(JourneyItems.goldClump, 10), new ItemStack(JourneyWeapons.royalKnife, 16)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyBlocks.celestiumBlock, 8), new ItemStack(JourneyBlocks.mekyumBlock, 8), new ItemStack(JourneyWeapons.celekiumBattleaxe, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyBlocks.celestiumBlock, 7), new ItemStack(JourneyBlocks.koriteBlock, 7), new ItemStack(JourneyWeapons.celestiteBattleaxe, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyBlocks.storonBlock, 6), new ItemStack(JourneyItems.mekyumIngot, 6), new ItemStack(JourneyWeapons.storumBattleaxe, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyBlocks.storonBlock, 5), new ItemStack(JourneyItems.koriteIngot, 5), new ItemStack(JourneyWeapons.bronzedBattleaxe, 1)));
    }
}