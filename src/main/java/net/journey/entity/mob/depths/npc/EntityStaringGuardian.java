package net.journey.entity.mob.depths.npc;

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

public class EntityStaringGuardian extends EntityModVillager {

    public EntityStaringGuardian(World var1) {
        super(var1);
    }

    @Override
    public void abstractInteract(EntityPlayer p) {
        switch (rand.nextInt(3)) {
            case 0:
                SlayerAPI.addFormattedChatMessage(p, "Guardian: It sure is dark down here... I've always speculated about what's on the surface.");
                break;
            case 1:
                SlayerAPI.addFormattedChatMessage(p, "Guardian: You don't look like any normal creature I've seen here before. We don't get a lot of travelers.");
                break;
            case 2:
                SlayerAPI.addFormattedChatMessage(p, "Guardian: It's always too dark here to see. Hopefully, there'll be a journey to the light as some wish.");
                break;
        }
    }

    @Override
	public GuiHandler.Identifier getGuiIdentifier() {
		return GuiHandler.STARING_GUARDIAN;
	}

    @Override
    public boolean shouldRenderInPass(int pass) {
        return pass == 0;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.depthsFlake, 15), new ItemStack(JourneyItems.darkCrystal, 16), new ItemStack(JourneyWeapons.depthsSlayer, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.depthsFlake, 15), new ItemStack(JourneyItems.beastlyStomach, 16), new ItemStack(JourneyWeapons.depthsBow, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.depthsFlake, 15), new ItemStack(JourneyWeapons.depthsSlayer, 1), new ItemStack(JourneyWeapons.depthsDarksword, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.depthsFlake, 15), new ItemStack(JourneyWeapons.depthsBow, 1), new ItemStack(JourneyWeapons.darkEnforcer, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.depthsFlake, 15), new ItemStack(JourneyItems.darkCrystal, 16), new ItemStack(JourneyItems.darkKey, 1)));

    }
}