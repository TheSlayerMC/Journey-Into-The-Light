package net.journey.client;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChatHandler {

    @SideOnly(Side.CLIENT)
    public static void sendChat(Entity entityLiving, String string) {
        TextComponentString chatcomponenttranslation = new TextComponentString(string);
        chatcomponenttranslation.getStyle().setColor(TextFormatting.GRAY);
        if (entityLiving != null && entityLiving instanceof EntityPlayerMP)
            entityLiving.sendMessage(chatcomponenttranslation);
    }

    @SideOnly(Side.CLIENT)
    public static void sendFormattedChat(EntityPlayer entityLiving, TextFormatting chatformat, String string) {
        TextComponentString chatcomponenttranslation = new TextComponentString(string);
        chatcomponenttranslation.getStyle().setColor(chatformat);
        if (entityLiving != null) entityLiving.sendMessage(chatcomponenttranslation);
    }

	/*@SideOnly(Side.SERVER)
	public static void sendServerMessage(String string) {
		TextComponentString translation = new TextComponentString(string);
		MinecraftServer.getServer().sendMessage(translation);
	}*/
}