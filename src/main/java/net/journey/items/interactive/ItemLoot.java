package net.journey.items.interactive;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.journey.init.JourneySounds;
import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyItems;
import net.journey.util.JourneyLootTables;
import net.journey.util.LangHelper;
import net.journey.util.LootHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

public class ItemLoot extends ItemMod {

	private ResourceLocation loot;
	
    public ItemLoot(String name, String f, ResourceLocation lootTable) {
        super(name, f, JourneyTabs.UTIL);
        this.loot = lootTable;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			Random r = new Random();
			List<ItemStack> lootTable = LootHelper.readFromLootTable(loot, (EntityPlayerMP) player);
			int index = r.nextInt(lootTable.size());
			ItemStack itemToSpawn = lootTable.get(index);

			String name = LangHelper.getFormattedText(itemToSpawn.getItem().getTranslationKey() + ".name");
			SlayerAPI.addChatMessage(player, "You recieved " + name);
			
			JourneySounds.playSound(JourneySounds.WRAPPER, world, player);

			EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, itemToSpawn);
			world.spawnEntity(item);
	        player.getHeldItem(hand).shrink(1);
		}
        return EnumActionResult.PASS;
    }

    @Override
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        l.add("Right click to open");
    }
}