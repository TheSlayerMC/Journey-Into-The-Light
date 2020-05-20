package net.journey.api.block.base;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;

import java.util.List;
import java.util.Random;

import net.journey.init.JourneySounds;
import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyItems;
import net.journey.util.LangHelper;
import net.journey.util.LootHelper;
import net.journey.util.RandHelper;

public class JBlockRandomLoot extends BlockMod {

	protected ResourceLocation loot;
	
	public JBlockRandomLoot(String name, String enName, ResourceLocation lootTable) {
		super(name, enName);
		this.loot = lootTable;
		this.setCreativeTab(JourneyTabs.INTERACTIVE_BLOCKS);
		this.setHardness(2.0F);
	}
	
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		if(!worldIn.isRemote) {
			Random r = new Random();
			List<ItemStack> lootTable = LootHelper.readFromLootTable(loot, null);
			int index = r.nextInt(lootTable.size());
			ItemStack itemToSpawn = lootTable.get(index);
		}
	}
}
