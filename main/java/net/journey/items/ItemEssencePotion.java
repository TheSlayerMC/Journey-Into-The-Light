package net.journey.items;

import java.util.List;

import net.journey.JourneyTabs;
import net.journey.client.server.EssenceProvider;
import net.journey.client.server.IEssence;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.item.ItemMod;

public class ItemEssencePotion extends ItemMod {

	private boolean isStrong;

	public ItemEssencePotion(String name, String f, boolean strong) {
		super(name, f, JourneyTabs.util);
		isStrong = strong;
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn .getHeldItem(handIn);
		playerIn.setActiveHand(handIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);	
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 20;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.DRINK;
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.drink(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));
            if(entityplayer instanceof EntityPlayerMP) {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
            }
        }

        stack.shrink(1);
        return stack;
    }

	public ItemStack drink(ItemStack stack, World world, EntityPlayer player) {
		int amount = isStrong ? 10 : 5;
		if(!world.isRemote) {
			IEssence mana = player.getCapability(EssenceProvider.ESSENCE_CAP, null);
			mana.fill(player, amount);
			if(!player.capabilities.isCreativeMode) stack.shrink(1);
		}
		return stack;
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return isStrong ? EnumRarity.EPIC : EnumRarity.RARE;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return isStrong;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
		String type = " Essence";
		int amount = isStrong ? 10 : 5;
		l.add("Replenishes " + amount + type);
	}
}