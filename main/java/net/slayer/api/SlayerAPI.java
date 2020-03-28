package net.slayer.api;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.opengl.GL11;

import net.journey.JITL;
import net.journey.client.ChatHandler;
import net.journey.util.Config;
import net.journey.util.GL11Helper;
import net.journey.util.LangHelper;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.ICommand;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SlayerAPI {

	public static int mobID = Config.baseMobID, projectileID = Config.baseProjectileID, entityListID = Config.baseEntityListID;
	public static Logger logger = Logger.getLogger(SlayerAPI.MOD_ID);

	public static final String 
	MOD_NAME = "Journey Into the Light", 
	MOD_ID = "journey", 
	PREFIX = MOD_ID + ":", 
	MOD_VERSION = "1.0.5.6";
	public static final boolean 
	DEVMODE = true,
	BETA = false;

	public static ToolMaterial addMeleeMaterial(int uses, float efficiency, float dam){
		return EnumHelper.addToolMaterial("tool", 3, uses, dam, efficiency, 30);
	}

	public static ToolMaterial addAxeMaterial(int uses, int level, float efficiency, float dam, int enchant) {
		return EnumHelper.addToolMaterial("tool", level, uses, dam, efficiency, enchant);
	}

	@SideOnly(Side.CLIENT)
	public static void scaleFont(FontRenderer f, String s, int x, int y, int color, double scale){
		GL11.glPushMatrix();
		GL11.glScaled(scale, scale, scale);
		f.drawString(s, 0, 0, color);
		GL11.glTranslatef(x, y, 0);
		double fixScale = 1 / scale;
		GL11.glScaled(fixScale, fixScale, fixScale);
		GL11.glPopMatrix();
	}

	public static void addMapGen(Class c, String s){
		try {
			MapGenStructureIO.registerStructureComponent(c, s);
			MapGenStructureIO.registerStructure(c, s);
		} catch(Exception e){
			logger.log(Level.WARNING, "[" + MOD_NAME + "] Failed To Spawn The Map Piece With The ID: " + s);
		}
	}

	public static void addVillageCreationHandler(IVillageCreationHandler v){
		VillagerRegistry.instance().registerVillageCreationHandler(v);
	}

	public static void registerEvent(Object o) {
		MinecraftForge.EVENT_BUS.register(o);
	}

	public static EntityEntry buildEntityEntry(Class entityClass, String entityID, String finalName, int base, int fore) {
		LangRegistry.addMob(entityID, finalName);
        return EntityEntryBuilder.create().entity(entityClass)
                .id(new ResourceLocation(MOD_ID, entityID), mobID++).name(MOD_ID + "." + entityID).tracker(128, 3, true)
                .egg(base, fore).build();
        
    }
	public static EntityEntry buildEntityEntryNoEgg(Class entityClass, String entityID, int id) {
	
    return EntityEntryBuilder.create().entity(entityClass)
            .id(new ResourceLocation(MOD_ID, entityID), id).name(MOD_ID + "." + entityID).tracker(128, 3, true).build();
    
	}

	public static void registerPets(Class entityClass, String entityName, String finalName, String finalN) {
		buildEntityEntry(entityClass, entityName, finalName, 0x64ffe4, 0x009cff);
	}

	public static void registerEntity(Class entityClass, String entityName, int ID) {
		EntityRegistry.registerModEntity(new ResourceLocation(entityName), entityClass, entityName, ID, JITL.instance, 120, 5, true);
	}

	public static EntityEntry registerProjectile(Class entityClass, String entityID) {
        return EntityEntryBuilder.create().entity(entityClass)
                .id(new ResourceLocation(MOD_ID, entityID), projectileID++).name(MOD_ID + "." + entityID)
                .tracker(250, 5, true).build();
    }

	public static ArmorMaterial addArmorMaterial(String name, int durability, int[] oldArmor, int enchantability, float toughness) {
		int duraNew = (int) Math.round(durability / 13.75);
		return EnumHelper.addArmorMaterial(name, SlayerAPI.PREFIX + name, duraNew, oldArmor, enchantability, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, toughness);
	}

	@SideOnly(Side.CLIENT)
	public static void addChatMessageWithColour(EntityPlayer p, String colour, String str) {
		p.sendMessage(new TextComponentString(SlayerAPI.Colour.YELLOW + "[" + SlayerAPI.Colour.GOLD + MOD_NAME + SlayerAPI.Colour.YELLOW + "] " + colour + str));
	}

	@SideOnly(Side.CLIENT)
	public static void addChatMessage(EntityPlayer p, String str) {
		TextComponentString ret = new TextComponentString(str);
		p.sendMessage(ret);
	}

	@SideOnly(Side.CLIENT)
	public static void addFormattedChatMessage(EntityPlayer p, String str) {
		TextComponentString ret = new TextComponentString(I18n.format(str, new Object[0]));
		p.sendMessage(ret);
	}

	private static final String	SECTION_SIGN	= "\u00a7";

	public static final class Colour {
		public static final String	BLACK		= SECTION_SIGN + "0";
		public static final String	DARK_BLUE	= SECTION_SIGN + "1";
		public static final String	DARK_GREEN	= SECTION_SIGN + "2";
		public static final String	DARK_AQUA	= SECTION_SIGN + "3";
		public static final String	DARK_RED	= SECTION_SIGN + "4";
		public static final String	PURPLE		= SECTION_SIGN + "5";
		public static final String	GOLD		= SECTION_SIGN + "6";
		public static final String	GRAY		= SECTION_SIGN + "7";
		public static final String	DARK_GRAY	= SECTION_SIGN + "8";
		public static final String	BLUE		= SECTION_SIGN + "9";
		public static final String	GREEN		= SECTION_SIGN + "A";
		public static final String	AQUA		= SECTION_SIGN + "B";
		public static final String	RED			= SECTION_SIGN + "C";
		public static final String	LIGHT_PURPLE= SECTION_SIGN + "D";
		public static final String	YELLOW		= SECTION_SIGN + "E";
		public static final String	WHITE		= SECTION_SIGN + "F";
	}

	public static final class Format {
		public static final String	OBFUSCATED	= SECTION_SIGN + "k";
		public static final String	BOLD		= SECTION_SIGN + "l";
		public static final String	STRIKE		= SECTION_SIGN + "m";
		public static final String	UNDERLINE	= SECTION_SIGN + "n";
		public static final String	ITALIC		= SECTION_SIGN + "o";
		public static final String	RESET		= SECTION_SIGN + "r";
	}

	@SideOnly(Side.CLIENT)
	public static void sendMessageToAll(String message, boolean showMod) {
		if(showMod) FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(SlayerAPI.Colour.DARK_AQUA + "[" + SlayerAPI.Colour.DARK_GREEN + MOD_NAME + SlayerAPI.Colour.DARK_AQUA + "] " + SlayerAPI.Colour.GREEN + message));
		else FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(SlayerAPI.Colour.GREEN + message));
	}

	@SideOnly(Side.CLIENT)
	public static void sendContinuedMessageToAll(String message) {
		FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(SlayerAPI.Colour.GREEN + message));
	}

	public static void removeSmeltingRecipe(ItemStack removed) {
		FurnaceRecipes.instance().getSmeltingList().remove(removed);
	}

	public static Item toItem(Block block){
		return Item.getItemFromBlock(block);
	}

	public static ItemStack toItemStack(Block block){
		return new ItemStack(Item.getItemFromBlock(block));
	}

	public static ItemStack toItemStack(Item item){
		return new ItemStack(item);
	}

	public static boolean giveItemStackToPlayer(EntityPlayer player, Integer count, ItemStack itemstack) {
		if (player.world.isRemote) {
			boolean boolAddedToInventory = true;
			for (int i = 0; i < count; i++) {
				boolAddedToInventory = player.inventory.addItemStackToInventory(itemstack);
				if (!boolAddedToInventory && itemstack.getItemDamage() == 0) {
					player.dropItem(itemstack.getItem(), 1);
					String itemName = itemstack.getUnlocalizedName();
					ChatHandler.sendFormattedChat(player, TextFormatting.RED, "journey.fullinv " +  LangHelper.getFormattedText(itemName + ".name"));
				}
			}
			return boolAddedToInventory;
		} else {
			return giveItemStackToPlayer((EntityPlayerMP)player, count, itemstack);
		}
	}

	public static void giveItemStackToPlayer(EntityPlayer player, ItemStack itemstack) {
		giveItemStackToPlayer(player, 1, itemstack);
	}

	public static void decreaseExp(EntityPlayer player, float amount) {
		if(player.experienceTotal - amount <= 0)  {
			player.experienceLevel = 0;
			player.experience = 0;
			player.experienceTotal = 0;
			return;
		}

		player.experienceTotal -= amount;

		if(player.experience * (float)player.xpBarCap() < amount)  {
			amount -= player.experience * (float)player.xpBarCap();
			player.experience = 1.0f;
			player.experienceLevel--;
		}

		while(player.xpBarCap() < amount)  {
			amount -= player.xpBarCap();
			player.experienceLevel--;
		}
		player.experience -= amount / (float)player.xpBarCap();
	}

	public static boolean giveItemStackToPlayer(EntityPlayerMP player, Integer count, ItemStack itemstack) {
		boolean boolAddedToInventory = true;
		for (int i = 0; i < count; i++) {
			itemstack.setCount(1);
			boolAddedToInventory = player.inventory.addItemStackToInventory(itemstack);
			if (!boolAddedToInventory && itemstack.getItemDamage() == 0) {
				player.dropItem(itemstack.getItem(), 1);
				String itemName = itemstack.getUnlocalizedName();
				ChatHandler.sendFormattedChat(player, TextFormatting.RED, "journey.fullinv " + LangHelper.getFormattedText(itemName + ".name"));
			} else {
				player.sendContainerToPlayer(player.inventoryContainer);
			}
		}
		return boolAddedToInventory;
	}
}