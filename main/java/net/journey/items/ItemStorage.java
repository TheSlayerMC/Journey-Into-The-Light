package net.journey.items;

import java.util.Iterator;
import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.client.server.DarkEnergyBar;
import net.journey.enums.EnumSounds;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemStorage extends Item {

	protected int healAmount = 0;

	public ItemStorage(String name, String finalName){
		this(name, finalName, JourneyTabs.items);
	}

	public ItemStorage(String name, String finalName, CreativeTabs tab){
		LangRegistry.addItem(SlayerAPI.PREFIX + name, finalName);
		setUnlocalizedName(SlayerAPI.PREFIX + name);
		setCreativeTab(tab);
		JourneyItems.itemNames.add(SlayerAPI.PREFIX + name);
		JourneyItems.items.add(this);
		setRegistryName(SlayerAPI.MOD_ID, name);
	}

	public ItemStorage setHealAmount(int healAmount){
		this.healAmount = healAmount;
		return this;
	}

	public void spawnEntityIntoWorld(World w, EntityPlayer p, Entity entity, boolean magic, String sound, boolean damage, ItemStack item, int dam) {
		if(!w.isRemote){
			if(magic) w.spawnEntity(entity);
		}
		if(magic) {
			EnumSounds.playSound(sound, w, p);
			if(damage) item.damageItem(dam, p);
		}
	}

	public void spawnEntityIntoWorld(World w, EntityPlayer p, Entity entity, String sound, boolean damage, ItemStack item, int dam) {
		if(!w.isRemote){
			w.spawnEntity(entity);
			EnumSounds.playSound(sound, w, p);
			if(damage) item.damageItem(dam, p);
		}
	}

	public void spawnEntityIntoWorld(World w, EntityPlayer p, Entity entity, boolean magic, String sound) {
		spawnEntityIntoWorld(w, p, entity, magic, sound, false, new ItemStack(Items.APPLE), 0);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, List list){ }

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		addInformation(stack, tooltip);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		EnumSounds.playSound(EnumSounds.CHEST_OPEN, world, player);
		if (world.isRemote) player.displayGUIChest(null);
		return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
	}
}