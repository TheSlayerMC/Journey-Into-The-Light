package net.journey.entity.mob.boiling.npc;

import net.journey.client.handler.GuiHandler;
import net.journey.entity.JourneyMerchantRecipe;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityEscapedConvict extends EntityModVillager {

    public EntityEscapedConvict(World var1) {
        super(var1);
        this.isImmuneToFire = true;

    }

    @Override
    public void abstractInteract(EntityPlayer p) {
        switch (rand.nextInt(3)) {
            case 0:
                SlayerAPI.addFormattedChatMessage(p, "Escaped Convict: I'm an innocent man! I have no reason to be in this realm!");
                break;
            case 1:
                SlayerAPI.addFormattedChatMessage(p, "Escaped Convict: Don't be threatened by me, strange thing!");
                break;
            case 2:
                SlayerAPI.addFormattedChatMessage(p, "Escaped Convict: I managed to find some great loot on my way here. If you can help me out of here, I'll give back in return.");
                break;
        }
    }

    @Override
    public int guiID() {
        return GuiHandler.escaped;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.boilingSkull, 10), new ItemStack(JourneyItems.boilPowder, 64), new ItemStack(JourneyWeapons.charredBow, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.boilingSkull, 10), new ItemStack(JourneyItems.boilPowder, 64), new ItemStack(JourneyWeapons.charredBlade, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.boilingSkull, 32), new ItemStack(JourneyItems.boilPowder, 64), new ItemStack(JourneyWeapons.bloodwieldSword, 1)));

    }
}