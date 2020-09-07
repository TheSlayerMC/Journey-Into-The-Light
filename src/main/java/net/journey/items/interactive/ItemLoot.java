package net.journey.items.interactive;

import net.journey.init.JourneySounds;
import net.journey.items.base.JItem;
import net.journey.util.LootHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;
import java.util.Random;

public class ItemLoot extends JItem {

	private final ResourceLocation loot;
	private int tier;

	public ItemLoot(ResourceLocation lootTable) {
		this.loot = lootTable;
	}

	public Item setTier(int tier) {
		this.tier = tier;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack i) {
		return tier > 0;
	}

	@Override
	public EnumRarity getRarity(ItemStack i) {
		if (this.tier == 1) {
			return EnumRarity.RARE;
		}
		if (this.tier == 2) {
			return EnumRarity.EPIC;
		}
		return EnumRarity.UNCOMMON;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			Random r = new Random();
			List<ItemStack> lootTable = LootHelper.genFromLootTable(loot, (EntityPlayerMP) player, builder -> builder.withLuck(player.getLuck()));
			int index = r.nextInt(lootTable.size());
			ItemStack itemToSpawn = lootTable.get(index);

			SlayerAPI.addFormattedChatMessage(player, "journey.recieve.item", itemToSpawn.getCount() + "x " + new TextComponentTranslation(itemToSpawn.getItem().getTranslationKey() + ".name").getFormattedText());

			EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, itemToSpawn);
			world.spawnEntity(item);
			player.getHeldItem(hand).shrink(1);
			JourneySounds.playSound(JourneySounds.WRAPPER, world, player);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItemMainhand());
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, player.getHeldItemMainhand());
	}

	@Override
	public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
		l.add("Right click to open");
		switch (tier) {
			case 0:
				l.add("Tier: Basic");
				break;
			case 1:
				l.add("Tier: Gold");
				break;
			case 2:
				l.add("Tier: Diamond");
				break;
		}
	}
}