package net.journey.entity.mob.overworld.npc;

import net.journey.client.handler.GuiHandler;
import net.journey.entity.JourneyMerchantRecipe;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

import javax.annotation.Nullable;

public class EntityMage extends EntityModVillager {

    public EntityMage(World var1) {
        super(var1);
    }

    @Override
    public void abstractInteract(EntityPlayer p) {
        switch (rand.nextInt(3)) {
            case 0:
                SlayerAPI.addFormattedChatMessage(p, "mage.valuables");
                break;
            case 1:
                SlayerAPI.addFormattedChatMessage(p, "mage.greetings");
                break;
            case 2:
                SlayerAPI.addFormattedChatMessage(p, "mage.deals");
                break;
        }
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(JourneyWeapons.conjuringStaff));
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        return livingdata;
    }

    @Override
    public ItemStack getHeldItem(EnumHand hand) {
        return new ItemStack(JourneyWeapons.conjuringStaff);
    }

    @Override
	public GuiHandler.Identifier getGuiIdentifier() {
		return GuiHandler.MAGE;
	}

    @Override
    public void addRecipies(MerchantRecipeList list) {
        list.add(new JourneyMerchantRecipe(new ItemStack(Items.STICK, 1), new ItemStack(Items.GOLD_INGOT, 5), new ItemStack(JourneyItems.wandBase)));
        list.add(new JourneyMerchantRecipe(new ItemStack(Items.STICK, 1), new ItemStack(Items.DIAMOND, 2), new ItemStack(JourneyItems.staffBase)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.stoneStick, 1), new ItemStack(JourneyItems.greenGem, 10), new ItemStack(JourneyWeapons.earthenHammer)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.staffBase, 1), new ItemStack(JourneyItems.greenGem, 10), new ItemStack(JourneyWeapons.staffOfGreenpace)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.staffBase, 1), new ItemStack(JourneyItems.hellstoneIngot, 10), new ItemStack(JourneyWeapons.staffOfHellstone)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.staffBase, 1), new ItemStack(JourneyItems.purpleGem, 10), new ItemStack(JourneyWeapons.doomsBringer)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.staffBase, 1), new ItemStack(JourneyItems.yellowGem, 10), new ItemStack(JourneyWeapons.wizardsStar)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.staffBase, 1), new ItemStack(JourneyItems.hellstoneIngot, 5), new ItemStack(JourneyWeapons.chaosCannon)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.staffBase, 1), new ItemStack(JourneyItems.blueGem, 10), new ItemStack(JourneyWeapons.staffOfEnlightenment)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.greenGem, 10), new ItemStack(JourneyWeapons.staffOfGreenpace, 1), new ItemStack(JourneyWeapons.conjuringStaff)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.wandBase, 1), new ItemStack(Blocks.ICE, 32), new ItemStack(JourneyWeapons.iceWand)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.wandBase, 1), new ItemStack(Items.FIRE_CHARGE, 32), new ItemStack(JourneyWeapons.fireWand)));
        list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.wandBase, 1), new ItemStack(Items.DIAMOND, 32), new ItemStack(JourneyWeapons.lightningWand)));
    }
}