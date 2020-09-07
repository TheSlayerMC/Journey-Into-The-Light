package net.slayer.api;

import net.journey.JITL;
import net.journey.client.handler.ChatHandler;
import net.journey.util.LangHelper;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SlayerAPI {
    private static int entityId;

    public static final String PREFIX = JITL.MOD_ID + ":";
    public static final boolean BETA = false;
    private static final String SECTION_SIGN = "\u00a7";
    public static Logger logger = Logger.getLogger(JITL.MOD_ID);

    public static ToolMaterial addMeleeMaterial(int uses, float dam, float efficiency) {
        return EnumHelper.addToolMaterial("tool", 3, uses, efficiency, dam - 4, 30);
    }

    public static ToolMaterial addAxeMaterial(int uses, int level, float efficiency, float dam, int enchant) {
        return EnumHelper.addToolMaterial("tool", level, uses, efficiency, dam, enchant);
    }

    @SideOnly(Side.CLIENT)
    public static void scaleFont(FontRenderer f, String s, int x, int y, int color, double scale) {
        GL11.glPushMatrix();
        GL11.glScaled(scale, scale, scale);
        f.drawString(s, 0, 0, color);
        GL11.glTranslatef(x, y, 0);
        double fixScale = 1 / scale;
        GL11.glScaled(fixScale, fixScale, fixScale);
        GL11.glPopMatrix();
    }

    public static void addMapGen(Class c, String s) {
        try {
            MapGenStructureIO.registerStructureComponent(c, s);
            MapGenStructureIO.registerStructure(c, s);
        } catch (Exception e) {
            logger.log(Level.WARNING, "[" + JITL.MOD_NAME + "] Failed To Spawn The Map Piece With The ID: " + s);
        }
    }

    public static void addVillageCreationHandler(IVillageCreationHandler v) {
        VillagerRegistry.instance().registerVillageCreationHandler(v);
    }

    public static void registerEventListener(Object o) {
        MinecraftForge.EVENT_BUS.register(o);
    }

    public static EntityEntry buildMobEntryNoEgg(Class<? extends Entity> entityClass, String entityName) {
        return createInitialSetup(entityClass, entityName).tracker(128, 3, true).build();
    }

    public static EntityEntry buildMobEntry(Class<? extends Entity> entityClass, String entityName, String enName, int primaryEggColor, int secondaryEggColor) {
        EntityEntry entry = createInitialSetup(entityClass, entityName)
                .tracker(128, 3, true)
                .egg(primaryEggColor, secondaryEggColor).build();

        LangGeneratorFacade.addEntityEntry(entry, enName);

        return entry;
    }

    public static EntityEntry buildProjectileEntry(Class<? extends Entity> entityClass, String entityName) {
        return createInitialSetup(entityClass, entityName)
                .tracker(250, 5, true).build();
    }

    /**
     * Setups builder id and name.
     */
    @SuppressWarnings("unchecked")
    private static <E extends Entity> EntityEntryBuilder<E> createInitialSetup(Class<? extends Entity> entityClass, String entityName) {
        return ((EntityEntryBuilder<E>) EntityEntryBuilder.create().entity(entityClass)
                .id(JITL.rl(entityName), entityId++)
                .name(JITL.MOD_ID + "." + entityName));
    }

    public static ArmorMaterial addArmorMaterial(String name, int durability, int[] oldArmor, int enchantability, float toughness) {
        int duraNew = (int) Math.round(durability / 13.75);
        return EnumHelper.addArmorMaterial(name, SlayerAPI.PREFIX + name, duraNew, oldArmor, enchantability, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, toughness);
    }

    public static void addChatMessageWithColour(EntityPlayer p, String colour, String str) {
        p.sendMessage(new TextComponentString(SlayerAPI.Colour.YELLOW + "[" + SlayerAPI.Colour.GOLD + JITL.MOD_NAME + SlayerAPI.Colour.YELLOW + "] " + colour + str));
    }

    public static void addChatMessage(EntityPlayer p, String str) {
        p.sendMessage(LangHelper.getClientSideTranslation(p, str));
    }

    public static void addFormattedChatMessage(EntityPlayer p, String str, Object...args) {
        p.sendMessage(new TextComponentTranslation(str, args));
    }

    @SideOnly(Side.CLIENT)
    public static void sendMessageToAll(String message, boolean showMod) {
        if (showMod)
            FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(SlayerAPI.Colour.DARK_AQUA + "[" + SlayerAPI.Colour.DARK_GREEN + JITL.MOD_NAME + SlayerAPI.Colour.DARK_AQUA + "] " + SlayerAPI.Colour.GREEN + message));
        else
            FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(SlayerAPI.Colour.GREEN + message));
    }

    @SideOnly(Side.CLIENT)
    public static void sendContinuedMessageToAll(String message) {
        FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(SlayerAPI.Colour.GREEN + message));
    }

    public static void removeSmeltingRecipe(ItemStack removed) {
        FurnaceRecipes.instance().getSmeltingList().remove(removed);
    }

    public static Item toItem(Block block) {
        return Item.getItemFromBlock(block);
    }

    public static ItemStack toItemStack(Block block) {
        return new ItemStack(Item.getItemFromBlock(block));
    }

    public static ItemStack toItemStack(Item item) {
        return new ItemStack(item);
    }

    public static boolean giveItemStackToPlayer(EntityPlayer player, Integer count, ItemStack itemstack) {
        if (player.world.isRemote) {
            boolean boolAddedToInventory = true;
            for (int i = 0; i < count; i++) {
                boolAddedToInventory = player.inventory.addItemStackToInventory(itemstack);
                if (!boolAddedToInventory && itemstack.getItemDamage() == 0) {
                    player.dropItem(itemstack.getItem(), 1);
                    String itemName = itemstack.getTranslationKey();
                    ChatHandler.sendFormattedChat(player, TextFormatting.RED, "journey.fullinv " + LangHelper.getFormattedText(itemName + ".name"));
                }
            }
            return boolAddedToInventory;
        } else {
            return giveItemStackToPlayer((EntityPlayerMP) player, count, itemstack);
        }
    }

    public static void giveItemStackToPlayer(EntityPlayer player, ItemStack itemstack) {
        giveItemStackToPlayer(player, 1, itemstack);
    }

    public static void decreaseExp(EntityPlayer player, float amount) {
        if (player.experienceTotal - amount <= 0) {
            player.experienceLevel = 0;
            player.experience = 0;
            player.experienceTotal = 0;
            return;
        }

        player.experienceTotal -= amount;

        if (player.experience * (float) player.xpBarCap() < amount) {
            amount -= player.experience * (float) player.xpBarCap();
            player.experience = 1.0f;
            player.experienceLevel--;
        }

        while (player.xpBarCap() < amount) {
            amount -= player.xpBarCap();
            player.experienceLevel--;
        }
        player.experience -= amount / (float) player.xpBarCap();
    }

    public static boolean giveItemStackToPlayer(EntityPlayerMP player, Integer count, ItemStack itemstack) {
        boolean boolAddedToInventory = true;
        for (int i = 0; i < count; i++) {
            itemstack.setCount(1);
            boolAddedToInventory = player.inventory.addItemStackToInventory(itemstack);
            if (!boolAddedToInventory && itemstack.getItemDamage() == 0) {
                player.dropItem(itemstack.getItem(), 1);
                String itemName = itemstack.getTranslationKey();
                ChatHandler.sendFormattedChat(player, TextFormatting.RED, "journey.fullinv " + LangHelper.getFormattedText(itemName + ".name"));
            } else {
                player.sendContainerToPlayer(player.inventoryContainer);
            }
        }
        return boolAddedToInventory;
    }

    @Deprecated
    /**
     * use 'TextFormatting'
     */
    public static final class Colour {
        public static final String BLACK = SECTION_SIGN + "0";
        public static final String DARK_BLUE = SECTION_SIGN + "1";
        public static final String DARK_GREEN = SECTION_SIGN + "2";
        public static final String DARK_AQUA = SECTION_SIGN + "3";
        public static final String DARK_RED = SECTION_SIGN + "4";
        public static final String PURPLE = SECTION_SIGN + "5";
        public static final String GOLD = SECTION_SIGN + "6";
        public static final String GRAY = SECTION_SIGN + "7";
        public static final String DARK_GRAY = SECTION_SIGN + "8";
        public static final String BLUE = SECTION_SIGN + "9";
        public static final String GREEN = SECTION_SIGN + "A";
        public static final String AQUA = SECTION_SIGN + "B";
        public static final String RED = SECTION_SIGN + "C";
        public static final String LIGHT_PURPLE = SECTION_SIGN + "D";
        public static final String YELLOW = SECTION_SIGN + "E";
        public static final String WHITE = SECTION_SIGN + "F";
    }

    public static final class Format {
        public static final String OBFUSCATED = SECTION_SIGN + "k";
        public static final String BOLD = SECTION_SIGN + "l";
        public static final String STRIKE = SECTION_SIGN + "m";
        public static final String UNDERLINE = SECTION_SIGN + "n";
        public static final String ITALIC = SECTION_SIGN + "o";
        public static final String RESET = SECTION_SIGN + "r";
    }
}