package net.journey.entity.mob.cloudia.npc;

import net.journey.client.GuiHandler;
import net.journey.entity.JourneyMerchantRecipe;
import net.journey.init.items.JourneyItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModVillager;

public class EntityStarlightVillager extends EntityModVillager {

    public EntityStarlightVillager(World par1World) {
        super(par1World);
        setSize(1.7F, 2.5F);
    }

    @Override
    public void abstractInteract(EntityPlayer p) {

    }

    @Override
    public int guiID() {
        return GuiHandler.starlightVillager;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.fluffyFeather, 32), new ItemStack(JourneyItems.golemChunk, 16), new ItemStack(JourneyItems.cloudiaOrb, 1)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.fluffyFeather, 64), new ItemStack(JourneyItems.golemChunk, 32), new ItemStack(JourneyItems.luniteChunk, 8)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.cloudiaOrb, 15), new ItemStack(JourneyItems.luniteChunk, 16), new ItemStack(JourneyItems.mysteriousDisk, 1)));
    }
}